package ru.vsu.cs.raspopov.chess.essence.pieces;

import javafx.scene.paint.Color;

public abstract class Piece {

    private String pos, name;
    private Color color;

    protected Piece(Color color, String pos, String name) {
        this.color = color;
        this.pos = pos;
        this.name = name;
    }

    public Color getColor() {
        return color;
    }

    public String getPos() {
        return pos;
    }

    public void setPos(String pos) {
        this.pos = pos;
    }

    public String getName() {
        return name;
    }

    public abstract boolean isValid();

    public abstract void move();

    public abstract void drawPiece();

}
