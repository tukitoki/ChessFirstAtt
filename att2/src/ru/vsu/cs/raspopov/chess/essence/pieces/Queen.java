package ru.vsu.cs.raspopov.chess.essence.pieces;

import javafx.scene.paint.Color;

public class Queen extends Piece {

    public Queen(Color color, String pos) {
        super(color, pos, "Q");
    }

    @Override
    public boolean isValid() {
        return false;
    }

    @Override
    public void move() {

    }

    @Override
    public void drawPiece() {

    }

}
