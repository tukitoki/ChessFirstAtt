package ru.vsu.cs.raspopov.chess.graphics;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.vsu.cs.raspopov.chess.essence.board.Board;
import ru.vsu.cs.raspopov.chess.essence.board.Cell;
import ru.vsu.cs.raspopov.chess.gameservice.Service;

import java.util.Iterator;


public class Table {

    private final GridPane root;
    private Board board;
    private static final Logger logger = LoggerFactory.getLogger(Table.class);

    public Table() {
        board = new Board();
        root = new GridPane();
        for (int row = board.getSizeBoard(); row >= 1; row--) {
            for (char col = 'A'; col <= 'H'; col++) {
                Rectangle square = new Rectangle();
                if ((row + col) % 2 == 0) {
                    square.setFill(Color.WHITE);
                } else {
                    square.setFill(Color.SANDYBROWN);
                }
                root.add(square, col, row);
                square.widthProperty().bind(root.widthProperty().divide(board.getSizeBoard()));
                square.heightProperty().bind(root.heightProperty().divide(board.getSizeBoard()));
            }
        }
        logger.info("Table was init successfully.");
    }

    public Board getBoard() {
        return board;
    }

    public GridPane getRoot() {
        return root;
    }

}
