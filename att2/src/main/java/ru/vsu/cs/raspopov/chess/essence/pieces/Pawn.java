package ru.vsu.cs.raspopov.chess.essence.pieces;

import javafx.scene.paint.Color;
import ru.vsu.cs.raspopov.chess.essence.board.Board;
import ru.vsu.cs.raspopov.chess.essence.board.Cell;
import ru.vsu.cs.raspopov.chess.essence.board.Position;
import ru.vsu.cs.raspopov.utils.Graph;

public class Pawn extends Piece {

    public Pawn(Color color, Position pos) {
        super(color, pos, "P");
    }

    @Override
    public boolean isValid(Board board) {
        for (Cell cell : board.getGraph()) {
            if (getPos().getColumn() + 1 == cell.getPos().getColumn()) {
                if (cell.getPiece() == null) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public void move() {

    }

    @Override
    public void drawPiece() {

    }

}
