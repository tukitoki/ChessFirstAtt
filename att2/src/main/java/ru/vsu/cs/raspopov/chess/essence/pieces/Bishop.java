package ru.vsu.cs.raspopov.chess.essence.pieces;

import javafx.geometry.Pos;
import javafx.scene.paint.Color;
import ru.vsu.cs.raspopov.chess.essence.board.Board;
import ru.vsu.cs.raspopov.chess.essence.board.Cell;
import ru.vsu.cs.raspopov.chess.essence.board.Position;

import java.util.ArrayList;
import java.util.LinkedList;

public class Bishop extends Piece {

    public Bishop(Color color, Position pos) {
        super(color, pos, "B");
    }

    @Override
    public void drawPiece() {

    }

    @Override
    public ArrayList<Position> moveAblePositions(Board board) {
        ArrayList<Position> correctPos = new ArrayList<>();
        char column = this.getPos().getColumn();
        column--;
        int row = this.getPos().getRow();
        row++;
        while (new Position(column, row).posOnDesk()) {
            correctPos.add(new Position(column, row));
            if (board.getCell(new Position(column, row)).getPiece() != null) {
                if (!(board.getCell(new Position(column, row)).getPiece() instanceof King)
                        || board.getCell(new Position(column, row)).getPiece().getColor() == this.getColor()) {
                    break;
                } else {
                    column--;
                    row++;
                    if (new Position(column, row).posOnDesk()) {
                        correctPos.add(new Position(column, row));
                    }
                    break;
                }
            }
            column--;
            row++;
        }
        column = this.getPos().getColumn();
        column++;
        row = this.getPos().getRow();
        row--;
        while (new Position(column, row).posOnDesk()) {
            correctPos.add(new Position(column, row));
            if (board.getCell(new Position(column, row)).getPiece() != null) {
                if (!(board.getCell(new Position(column, row)).getPiece() instanceof King)
                        || board.getCell(new Position(column, row)).getPiece().getColor() == this.getColor()) {
                    break;
                } else {
                    column++;
                    row--;
                    if (new Position(column, row).posOnDesk()) {
                        correctPos.add(new Position(column, row));
                    }
                    break;
                }
            }
            column++;
            row--;
        }
        column = this.getPos().getColumn();
        column++;
        row = this.getPos().getRow();
        row++;
        while (new Position(column, row).posOnDesk()) {
            correctPos.add(new Position(column, row));
            if (board.getCell(new Position(column, row)).getPiece() != null) {
                if (!(board.getCell(new Position(column, row)).getPiece() instanceof King)
                        || board.getCell(new Position(column, row)).getPiece().getColor() == this.getColor()) {
                    break;
                } else {
                    column++;
                    row++;
                    if (new Position(column, row).posOnDesk()) {
                        correctPos.add(new Position(column, row));
                    }
                    break;
                }
            }
            column++;
            row++;
        }
        column = this.getPos().getColumn();
        column--;
        row = this.getPos().getRow();
        row--;
        while (new Position(column, row).posOnDesk()) {
            correctPos.add(new Position(column, row));
            if (board.getCell(new Position(column, row)).getPiece() != null) {
                if (!(board.getCell(new Position(column, row)).getPiece() instanceof King)
                        || board.getCell(new Position(column, row)).getPiece().getColor() == this.getColor()) {
                    break;
                } else {
                    column--;
                    row--;
                    if (new Position(column, row).posOnDesk()) {
                        correctPos.add(new Position(column, row));
                    }
                    break;
                }
            }
            column--;
            row--;
        }
        return correctPos;
    }

    @Override
    public ArrayList<Position> betweenKingAndPiece(Board board) {
        ArrayList<Position> correctPos = new ArrayList<>();
        correctPos.add(this.getPos());
        char column = this.getPos().getColumn();
        column--;
        int row = this.getPos().getRow();
        row++;
        while (new Position(column, row).posOnDesk()) {
            if (board.getCell(new Position(column, row)).getPiece() != null) {
                if (board.getCell(new Position(column, row)).getPiece() instanceof King
                        && board.getCell(new Position(column, row)).getPiece().getColor() != this.getColor()) {
                    return correctPos;
                }
            }
            correctPos.add(new Position(column, row));
            column--;
            row++;
        }
        correctPos.clear();
        correctPos.add(this.getPos());
        column = this.getPos().getColumn();
        column++;
        row = this.getPos().getRow();
        row--;
        while (new Position(column, row).posOnDesk()) {
            if (board.getCell(new Position(column, row)).getPiece() != null) {
                if (board.getCell(new Position(column, row)).getPiece() instanceof King
                        && board.getCell(new Position(column, row)).getPiece().getColor() != this.getColor()) {
                    return correctPos;
                }
            }
            correctPos.add(new Position(column, row));
            column++;
            row--;
        }
        correctPos.clear();
        correctPos.add(this.getPos());
        column = this.getPos().getColumn();
        column++;
        row = this.getPos().getRow();
        row++;
        while (new Position(column, row).posOnDesk()) {
            if (board.getCell(new Position(column, row)).getPiece() != null) {
                if (board.getCell(new Position(column, row)).getPiece() instanceof King
                        && board.getCell(new Position(column, row)).getPiece().getColor() != this.getColor()) {
                    return correctPos;
                }
            }
            correctPos.add(new Position(column, row));
            column++;
            row++;
        }
        correctPos.clear();
        correctPos.add(this.getPos());
        column = this.getPos().getColumn();
        column--;
        row = this.getPos().getRow();
        row--;
        while (new Position(column, row).posOnDesk()) {
            if (board.getCell(new Position(column, row)).getPiece() != null) {
                if (board.getCell(new Position(column, row)).getPiece() instanceof King
                        && board.getCell(new Position(column, row)).getPiece().getColor() != this.getColor()) {
                    return correctPos;
                }
            }
            correctPos.add(new Position(column, row));
            column--;
            row--;
        }
        correctPos.clear();
        correctPos.add(this.getPos());
        return correctPos;
    }

}
