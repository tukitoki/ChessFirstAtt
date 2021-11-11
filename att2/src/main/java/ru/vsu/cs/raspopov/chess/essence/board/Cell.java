package ru.vsu.cs.raspopov.chess.essence.board;

import javafx.geometry.Pos;
import ru.vsu.cs.raspopov.chess.essence.pieces.Piece;

public class Cell {

    private final Position pos;
    private Piece piece;

    public Cell(Position pos, Piece piece) {
        this.pos = pos;
        this.piece = piece;
    }

    public Position getPos() {
        return pos;
    }

    public Piece getPiece() {
        return piece;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }
}
