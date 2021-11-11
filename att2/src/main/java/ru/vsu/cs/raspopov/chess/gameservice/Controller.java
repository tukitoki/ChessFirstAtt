package ru.vsu.cs.raspopov.chess.gameservice;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.vsu.cs.raspopov.chess.essence.board.Board;
import ru.vsu.cs.raspopov.chess.essence.board.Cell;
import ru.vsu.cs.raspopov.chess.essence.pieces.*;
import ru.vsu.cs.raspopov.chess.graphics.Graphics;
import ru.vsu.cs.raspopov.chess.graphics.Table;

import java.util.Iterator;

public class Controller extends Application {

    private static final Logger logger = LoggerFactory.getLogger(Controller.class);
    private final Graphics graphics = new Graphics();

    public void start(Stage primaryStage) {
        Table table = new Table();
        firstPieceInit(table.getBoard());
        graphics.imagesOnCells(table);
        primaryStage.setScene(new Scene(table.getRoot(), 600, 600));
        primaryStage.setTitle("Chess");
        primaryStage.show();
    }

    private void firstPieceInit(Board board) {
        for (Cell cell : board.getGraph()) {
            if (cell.getPos().equals("A8") || cell.getPos().equals("H8") || cell.getPos().equals("A1") || cell.getPos().equals("H1")) {
                cell.setPiece(new Rook((cell.getPos().equals("A8") || cell.getPos().equals("H8"))
                        ? Color.BLACK
                        : Color.WHITE, cell.getPos()));
            }
            if (cell.getPos().equals("B8") || cell.getPos().equals("G8") || cell.getPos().equals("B1") || cell.getPos().equals("G1")) {
                cell.setPiece(new Knight((cell.getPos().equals("B8") || cell.getPos().equals("G8"))
                        ? Color.BLACK
                        : Color.WHITE, cell.getPos()));
            }
            if (cell.getPos().equals("C8") || cell.getPos().equals("F8") || cell.getPos().equals("C1") || cell.getPos().equals("F1")) {
                cell.setPiece(new Bishop((cell.getPos().equals("C8") || cell.getPos().equals("F8"))
                        ? Color.BLACK
                        : Color.WHITE, cell.getPos()));
            }
            if (cell.getPos().equals("D8") || cell.getPos().equals("D1")) {
                cell.setPiece(new Queen(cell.getPos().equals("D8")
                        ? Color.BLACK
                        : Color.WHITE, cell.getPos()));
            }
            if (cell.getPos().equals("E8") || cell.getPos().equals("E1")) {
                cell.setPiece(new King(cell.getPos().equals("E8")
                        ? Color.BLACK
                        : Color.WHITE, cell.getPos()));
            }
            if (cell.getPos().getRow() == 7 || cell.getPos().getRow() == 2) {
                cell.setPiece(new Pawn(cell.getPos().getRow() == 7
                        ? Color.BLACK
                        : Color.WHITE, cell.getPos()));
            }
            try {
                logger.info("Piece " + cell.getPiece().getName() + " was put on: " +  cell.getPos().getColumn() + cell.getPos().getRow() + " successfully.");
            } catch (Exception e) {
                logger.error("Cell is not specified for firstInit piece.");
            }
        }
    }
}
