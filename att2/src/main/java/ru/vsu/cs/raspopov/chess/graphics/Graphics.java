package ru.vsu.cs.raspopov.chess.graphics;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.vsu.cs.raspopov.chess.essence.board.Board;
import ru.vsu.cs.raspopov.chess.essence.board.Cell;
import ru.vsu.cs.raspopov.chess.essence.board.Position;
import ru.vsu.cs.raspopov.chess.essence.pieces.*;
import ru.vsu.cs.raspopov.chess.gameservice.Table;


public class Graphics {

    private static final Logger logger = LoggerFactory.getLogger(Graphics.class);

    public GraphicCell setGraphicCellAnimation(Table table, char col, int row) {
        GraphicCell cell = new GraphicCell(new Position(col, row), table.getBoard().getCell(new Position(col, row)).getPiece() == null
                ? null
                :  table.getBoard().getCell(new Position(col, row)).getPiece().getImage());
        cell.cellAnimation(table);
        return cell;
    }

    public void showPieces(Color color, Position position, Table table) {
        Stage stage = new Stage();
        stage.setTitle("Choose figure instead of pawn");
        Queen queen = new Queen(color, position);
        Bishop bishop = new Bishop(color, position);
        Knight knight = new Knight(color, position);
        Rook rook = new Rook(color, position);
        Button queenButton = new Button();
        queenButton.setGraphic(new ImageView(queen.getImage()));
        Button bishopButton = new Button();
        bishopButton.setGraphic(new ImageView(bishop.getImage()));
        Button knightButton = new Button();
        knightButton.setGraphic(new ImageView(knight.getImage()));
        Button rookButton = new Button();
        rookButton.setGraphic(new ImageView(rook.getImage()));
        queenButton.setOnAction(actionEvent -> {
            table.getBoard().getCell(position).setPiece(queen);
            for (GraphicCell gc : table.getCells()) {
                if (gc.getPosition().equals(position)) {
                    gc.setImage(table.getBoard().getCell(position).getPiece().getImage());
                }
            }
            stage.close();
            actionEvent.consume();
        });
        bishopButton.setOnAction(actionEvent -> {
            table.getBoard().getCell(position).setPiece(bishop);
            for (GraphicCell gc : table.getCells()) {
                if (gc.getPosition().equals(position)) {
                    gc.setImage(table.getBoard().getCell(position).getPiece().getImage());
                }
            }
            stage.close();
            actionEvent.consume();
        });
        knightButton.setOnAction(actionEvent -> {
            table.getBoard().getCell(position).setPiece(knight);
            for (GraphicCell gc : table.getCells()) {
                if (gc.getPosition().equals(position)) {
                    gc.setImage(table.getBoard().getCell(position).getPiece().getImage());
                }
            }
            stage.close();
            actionEvent.consume();
        });
        rookButton.setOnAction(actionEvent -> {
            table.getBoard().getCell(position).setPiece(rook);
            for (GraphicCell gc : table.getCells()) {
                if (gc.getPosition().equals(position)) {
                    gc.setImage(table.getBoard().getCell(position).getPiece().getImage());
                }
            }
            stage.close();
            actionEvent.consume();
        });
        HBox hBox = new HBox();
        hBox.getChildren().addAll(queenButton, bishopButton, knightButton, rookButton);
        stage.setScene((new Scene(hBox, 500, 200)));
        stage.setResizable(false);
        stage.show();
    }

//    public void imagesOnCells(Table table) {
//        int count = 0;
//        for (Cell cell : table.getBoard().getGraph()) {
//            if (cell.getPiece() != null) {
//                ImageView imageView = new ImageView();
//                table.getCells().get(count).setImage(cell.getPiece().getImage());
//                count++;
//                imageView.setImage(cell.getPiece().getImage());
//                table.getRoot().add(imageView, cell.getPiece().getPos().getColumn(), cell.getPiece().getPos().getRow());
//                imageView.fitHeightProperty().bind(table.getRoot().heightProperty().divide(table.getBoard().getSizeBoard()));
//                imageView.fitWidthProperty().bind(table.getRoot().widthProperty().divide(table.getBoard().getSizeBoard()));
//            }
//        }
//    }

    /*public void showCorrectCells(Table table) {
        final double[] startDragX = new double[1];
        final double[] startDragY = new double[1];
        table.getRoot().setOnMousePressed(e -> {
            startDragX[0] = e.getSceneX();
            startDragY[0] = e.getSceneY();
        });
        table.getRoot().setOnMouseDragged(e -> {
            Node node = e.getPickResult().getIntersectedNode();
            if (node instanceof ImageView) {
                node.setTranslateX(e.getSceneX() - startDragX[0]);
                node.setTranslateY(e.getSceneY() - startDragY[0]);
            }
        });
    }*/

    /*public void showCorrectCells(Table table) {
        table.getRoot().setOnMouseClicked(e -> {
            ObservableList<Node> work = FXCollections.observableArrayList(table.getRoot().get);
        });
    }*/

    /*public void showCorrectCells(Table table) {
        try {
            table.getRoot().setOnMouseMoved(mouseEvent -> {
                Node node = mouseEvent.getPickResult().getIntersectedNode();
                node.setOnDragDetected(event -> {
                    node.setStyle("-fx-background-color: palegreen");
                    Dragboard dragboard = node.startDragAndDrop(TransferMode.MOVE);
                    Image cellImage = ((ImageView) node).getImage();
                    ClipboardContent clipboardContent = new ClipboardContent();
                    clipboardContent.putImage(cellImage);
                    dragboard.setContent(clipboardContent);
                    logger.info("OK");
                    event.consume();
                });
                node.setOnDragOver(event -> {
                    event.acceptTransferModes(TransferMode.MOVE);
                    event.consume();
                });
                node.setOnDragDropped(event -> {
                    Dragboard dragboard = event.getDragboard();

                    if (node instanceof ImageView) {
                        ((ImageView) node).setImage(dragboard.getImage());
                    } else {
                        ImageView imageView = new ImageView(dragboard.getImage());
                        table.getRoot().add(imageView, GridPane.getColumnIndex(node), GridPane.getRowIndex(node));
                        imageView.fitHeightProperty().bind(table.getRoot().heightProperty().divide(table.getBoard().getSizeBoard()));
                        imageView.fitWidthProperty().bind(table.getRoot().widthProperty().divide(table.getBoard().getSizeBoard()));
                    }

                    event.setDropCompleted(true);
                    event.consume();
                });
                node.setOnDragDone(event -> {
                    if (event.getTransferMode() == TransferMode.MOVE) {
                        table.getRoot().getChildren().remove(node);
                    }
                    event.consume();
                });
            });

        } catch (ClassCastException e) {
            logger.error("bad");
        }
    }*/

}
