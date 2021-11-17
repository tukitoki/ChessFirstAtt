package ru.vsu.cs.raspopov.chess.essence.pieces;

import javafx.scene.paint.Color;
import ru.vsu.cs.raspopov.chess.essence.board.Board;
import ru.vsu.cs.raspopov.chess.essence.board.Cell;
import ru.vsu.cs.raspopov.chess.essence.board.Position;

import java.util.ArrayList;
import java.util.LinkedList;

public class Pawn extends Piece {

    public Pawn(Color color, Position pos) {
        super(color, pos, "P");
    }

    @Override
    public void drawPiece() {

    }

    @Override
    public ArrayList<Position> moveAblePositions(Board board) {
        ArrayList<Position> correctPos = new ArrayList<>();
        char column = this.getPos().getColumn();
        int row = this.getPos().getRow();
        if (row == 7 && getColor() == Color.BLACK) {
            if (board.getCell(new Position(column, row - 1)).getPiece() == null) {
                correctPos.add(new Position(column, row - 1));
                if (board.getCell(new Position(column, row - 2)).getPiece() == null) {
                    correctPos.add(new Position(column, row - 2));
                }
            }
        } else if (row == 2 && getColor() == Color.WHITE){
            if (board.getCell(new Position(column, row + 1)).getPiece() == null) {
                correctPos.add(new Position(column, row + 1));
                if (board.getCell(new Position(column, row + 2)).getPiece() == null) {
                    correctPos.add(new Position(column, row + 2));
                }
            }
        } else {
            row = this.getColor() == Color.WHITE ? row + 1 : row - 1;
            if (new Position(column, row).posOnDesk() && board.getCell(new Position(column, row)).getPiece() == null) {
                correctPos.add(new Position(column, row));
            }
        }
        column++;
        if (new Position(column, row).posOnDesk()) {
            correctPos.add(new Position(column, row));
        }
        column -= 2;
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
