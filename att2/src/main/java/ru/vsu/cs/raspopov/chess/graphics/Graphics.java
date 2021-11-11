package ru.vsu.cs.raspopov.chess.graphics;

import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import ru.vsu.cs.raspopov.chess.essence.board.Cell;

public class Graphics {

    public void imagesOnCells(Table table) {
        for (Cell cell : table.getBoard().getGraph()) {
            if (cell.getPiece() != null) {
                Image img = new Image("File:pieceImg/" + (cell.getPiece().getColor().equals(Color.BLACK)
                        ? "B"
                        : "W") + cell.getPiece().getName() + ".png");
                ImageView imageView = new ImageView();
                imageView.setImage(img);
                table.getRoot().add(imageView, cell.getPiece().getPos().getColumn(), cell.getPiece().getPos().getRow());
                imageView.fitHeightProperty().bind(table.getRoot().heightProperty().divide(table.getBoard().getSizeBoard()));
                imageView.fitWidthProperty().bind(table.getRoot().widthProperty().divide(table.getBoard().getSizeBoard()));
            }
        }
    }
}
