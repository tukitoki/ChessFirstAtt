package ru.vsu.cs.raspopov.chess.essence.board;

import java.util.Objects;

public class Position {

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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        try {
            String position = (String) o;
            return column == position.charAt(0) && row == Character.getNumericValue(position.charAt(1));
        } catch (ClassCastException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(column, row);
    }
}
