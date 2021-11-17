package ru.vsu.cs.raspopov.chess.essence.board;

import java.io.Serializable;
import java.util.Objects;

public class Position implements Serializable {

    private char column;
    private int row;

    public Position(char column, int row) {
        this.column = column;
        this.row = row;
    }

    public char getColumn() {
        return column;
    }

    public void setColumn(char column) {
        this.column = column;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public boolean posOnDesk() {
        return this.getRow() <= 8 && this.getRow() >= 1 && this.getColumn() >= 'A' && this.getColumn() <= 'H';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return column == position.column && row == position.row;
    }

    @Override
    public int hashCode() {
        return Objects.hash(column, row);
    }

    @Override
    public String toString() {
        return Character.toString(this.column) + Integer.toString(this.row);
    }
}
