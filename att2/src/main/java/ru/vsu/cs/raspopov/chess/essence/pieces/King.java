package ru.vsu.cs.raspopov.chess.essence.pieces;

import javafx.scene.paint.Color;
import ru.vsu.cs.raspopov.chess.essence.board.Board;
import ru.vsu.cs.raspopov.chess.essence.board.Cell;
import ru.vsu.cs.raspopov.chess.essence.board.Position;

import java.util.ArrayList;
import java.util.LinkedList;

public class King extends Piece {

    public King(Color color, Position pos) {
        super(color, pos, "K");
    }

    @Override
    public void drawPiece() {

    }

    @Override
    public ArrayList<Position> moveAblePositions(Board board) {
        ArrayList<Position> correctPos = new ArrayList<>();
        char column = this.getPos().getColumn();
        int row = this.getPos().getRow();
//        if (new Position(column, row + 1).posOnDesk() && board.getCell(new Position(column, row + 1)).getPiece().getColor() != this.getColor()) {
//            correctPos.add(new Position(column, row - 1));
//        }
        for (int i = row - 1; i <= row + 1; i++) {
            column--;
            if (new Position(column, i).posOnDesk()) {
                if (board.getCell(new Position(column, i)).getPiece() == null) {
                    correctPos.add(new Position(column, i));
                } else if (board.getCell(new Position(column, i)).getPiece().getColor() != this.getColor()) {
                    correctPos.add(new Position(column, i));
                }
            }
            column++;
            if (new Position(column, i).posOnDesk()) {
                if (board.getCell(new Position(column, i)).getPiece() == null) {
                    correctPos.add(new Position(column, i));
                } else if (board.getCell(new Position(column, i)).getPiece().getColor() != this.getColor()) {
                    correctPos.add(new Position(column, i));
                }
            }
            column++;
            if (new Position(column, i).posOnDesk()) {
                if (board.getCell(new Position(column, i)).getPiece() == null) {
                    correctPos.add(new Position(column, i));
                } else if (board.getCell(new Position(column, i)).getPiece().getColor() != this.getColor()) {
                    correctPos.add(new Position(column, i));
                }
            }
            column = this.getPos().getColumn();
        }
        return correctPos;
    }

    @Override
    public ArrayList<Position> betweenKingAndPiece(Board board) {
        return new ArrayList<>();
    }

}
