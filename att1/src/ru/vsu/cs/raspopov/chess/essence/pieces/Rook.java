package ru.vsu.cs.raspopov.chess.essence.pieces;

import javafx.scene.paint.Color;

public class Rook extends Piece {

    public Rook(Color color, String pos) {
        super(color, pos, "R");
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
