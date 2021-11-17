package ru.vsu.cs.raspopov.chess.essence.pieces;

import javafx.scene.paint.Color;
import ru.vsu.cs.raspopov.chess.essence.board.Board;
import ru.vsu.cs.raspopov.chess.essence.board.Cell;
import ru.vsu.cs.raspopov.chess.essence.board.Position;

import java.util.ArrayList;
import java.util.LinkedList;

public class Knight extends Piece {

    public Knight(Color color, Position pos) {
        super(color, pos, "Knt");
    }

    @Override
    public void drawPiece() {

    }

    @Override
    public ArrayList<Position> moveAblePositions(Board board) {
        ArrayList<Position> correctPos = new ArrayList<>();
        char column = this.getPos().getColumn();
        int row = this.getPos().getRow();
        row += 1;
        column += 2;
        if (new Position(column, row).posOnDesk()) {
            correctPos.add(new Position(column, row));
        }
        row -= 2;
        if (new Position(column, row).posOnDesk()) {
            correctPos.add(new Position(column, row));
        }
        row += 2;
        column -= 4;
        if (new Position(column, row).posOnDesk()) {
            correctPos.add(new Position(column, row));
        }
        row -= 2;
        if (new Position(column, row).posOnDesk()) {
            correctPos.add(new Position(column, row));
        }
        column++;
        row += 3;
        if (new Position(column, row).posOnDesk()) {
            correctPos.add(new Position(column, row));
        }
        row -= 4;
        if (new Position(column, row).posOnDesk()) {
            correctPos.add(new Position(column, row));
        }
        column += 2;
        if (new Position(column, row).posOnDesk()) {
            correctPos.add(new Position(column, row));
        }
        row += 4;
        if (new Position(column, row).posOnDesk()) {
            correctPos.add(new Position(column, row));
        }
        return correctPos;
    }

    @Override
    public ArrayList<Position> betweenKingAndPiece(Board board) {
        return new ArrayList<>();
    }

}
