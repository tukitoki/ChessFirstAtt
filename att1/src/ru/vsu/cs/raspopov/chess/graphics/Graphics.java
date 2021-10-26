package ru.vsu.cs.raspopov.chess.graphics;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import ru.vsu.cs.raspopov.chess.essence.board.Cell;

import java.util.Iterator;

public class Graphics {

    public static void imagesOnCells(Table table) {
        Iterator<Cell> cellIterator = table.getBoard().getGraph().iterator();
        while (cellIterator.hasNext()) {
            Cell cell = cellIterator.next();
            if (cell.getPiece() != null) {
                Image img = new Image("File:pieceImg/" + (cell.getPiece().getColor().equals(Color.BLACK)
                        ? "B"
                        : "W") + cell.getPiece().getName() + ".png");
                ImageView imageView = new ImageView();
                imageView.setImage(img);
                table.getRoot().add(imageView, cell.getPiece().getPos().charAt(0), Character.getNumericValue(cell.getPiece().getPos().charAt(1)));
                imageView.fitHeightProperty().bind(table.getRoot().heightProperty().divide(table.getBoard().getSizeBoard()));
                imageView.fitWidthProperty().bind(table.getRoot().widthProperty().divide(table.getBoard().getSizeBoard()));
            }
        }
    }
}
