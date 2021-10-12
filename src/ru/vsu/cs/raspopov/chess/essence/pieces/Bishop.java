package ru.vsu.cs.raspopov.chess.essence.pieces;

import javafx.scene.paint.Color;

public class Bishop extends Piece {

    public Bishop(Color color, String pos) {
        super(color, pos, "B");
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
