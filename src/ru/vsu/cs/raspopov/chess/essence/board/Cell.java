package ru.vsu.cs.raspopov.chess.essence.board;

import ru.vsu.cs.raspopov.chess.essence.pieces.Piece;

public class Cell {

    private final String pos;
    private Piece piece;

    public Cell(String pos, Piece piece) {
        this.pos = pos;
        this.piece = piece;
    }

    public String getPos() {
        return pos;
    }

    public Piece getPiece() {
        return piece;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }
}
