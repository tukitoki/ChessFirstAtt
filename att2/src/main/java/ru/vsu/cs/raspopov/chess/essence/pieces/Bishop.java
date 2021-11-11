package ru.vsu.cs.raspopov.chess.essence.pieces;

import javafx.scene.paint.Color;
import ru.vsu.cs.raspopov.chess.essence.board.Board;
import ru.vsu.cs.raspopov.chess.essence.board.Position;
import ru.vsu.cs.raspopov.utils.Graph;

public class Bishop extends Piece {

    public Bishop(Color color, Position pos) {
        super(color, pos, "B");
    }


    @Override
    public boolean isValid(Board board) {
        return false;
    }

    @Override
    public void move() {

    }

    @Override
    public void drawPiece() {

    }

}