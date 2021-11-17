package ru.vsu.cs.raspopov.chess.gameservice;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import javafx.application.Application;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.vsu.cs.raspopov.utils.BoardJsonDeserializer;
import ru.vsu.cs.raspopov.utils.BoardJsonSerializer;
import ru.vsu.cs.raspopov.chess.essence.board.Board;

import java.io.File;
import java.io.IOException;

public class Controller extends Application {

    private static final Logger logger = LoggerFactory.getLogger(Controller.class);
    private Table table;
    private HBox gameStatus;
    private static Controller controller;
    private BorderPane pane;

    @Override
    public void start(Stage primaryStage) {
        controller = this;
        pane = new BorderPane();
        table = new Table();
        createMenu();
        table.getRoot().setAlignment(Pos.CENTER);
        pane.setCenter(table.getRoot());
        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(pane, 800, 800));
        primaryStage.setTitle("Chess");
        primaryStage.show();
    }

    public void createMenu() {
        MenuBar menuBar = new MenuBar();
        Menu fileMenu = new Menu("Main");
        Menu baseScenario = new Menu("Scenario");
        baseScenario.setOnAction(actionEvent -> {

        });
        MenuItem newItem = new MenuItem("New game");
        newItem.setOnAction(actionEvent -> {
            table.resetBoard();
        });
        MenuItem saveFileItem = new MenuItem("Save File");
        saveFileItem.setOnAction(actionEvent -> {
            FileChooser fileOpener = new FileChooser();
            fileOpener.setTitle("Choose file");
            fileOpener.setInitialDirectory(new File("src/main/resources/jsons"));
            fileOpener.getExtensionFilters().add(new FileChooser.ExtensionFilter("JSON files", "*.json"));
            serialization(fileOpener.showSaveDialog(new Stage()));
        });
        MenuItem loadFileItem = new MenuItem("Load File");
        loadFileItem.setOnAction(actionEvent -> {
            FileChooser fileOpener = new FileChooser();
            fileOpener.setTitle("Choose file");
            fileOpener.setInitialDirectory(new File("src/main/resources/jsons"));
            fileOpener.getExtensionFilters().add(new FileChooser.ExtensionFilter("JSON files", "*.json"));
            deserialization(fileOpener.showOpenDialog(new Stage()));
        });
        MenuItem exitItem = new MenuItem("Exit");
        fileMenu.getItems().addAll(newItem, saveFileItem, loadFileItem, exitItem);
        menuBar.getMenus().addAll(fileMenu, baseScenario);
        pane.setTop(menuBar);
    }

    public void endGame(Color color) {
        Stage stage = new Stage();
        Label text = new Label();
        text.setFont(new Font("Arial", 30));
        text.setTextFill(Color.RED);
        text.setText((color == Color.WHITE ? "WHITE " : "BLACK ") + "ARE LOST");
        text.setAlignment(Pos.CENTER);
        controller.gameStatus = new HBox(text);
        controller.gameStatus.setAlignment(Pos.CENTER);
        stage.setScene(new Scene(controller.gameStatus, 400, 200));
        stage.show();
        //controller.pane.set(controller.gameStatus);
    }

    public void serialization(File file) {
        SimpleModule simpleModule = new SimpleModule();
        simpleModule.addSerializer(Board.class, new BoardJsonSerializer());
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(simpleModule);
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        try {
            mapper.writeValue(file, table.getBoard());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void deserialization(File file) {
        ObjectMapper mapper = new ObjectMapper();
        SimpleModule simpleModule = new SimpleModule();
        mapper.configure(
                DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
        simpleModule.addDeserializer(Board.class, new BoardJsonDeserializer());
        mapper.registerModule(simpleModule);
        simpleModule.addSerializer(Board.class, new BoardJsonSerializer());
        mapper.registerModule(simpleModule);
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        try {
            table.setBoard(mapper.readValue(file, Board.class));
            logger.info("Game was loaded successfully");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
