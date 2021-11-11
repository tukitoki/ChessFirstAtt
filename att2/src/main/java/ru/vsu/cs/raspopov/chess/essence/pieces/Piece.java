package ru.vsu.cs.raspopov.chess.essence.pieces;

import javafx.scene.paint.Color;
import ru.vsu.cs.raspopov.chess.essence.board.Board;
import ru.vsu.cs.raspopov.chess.essence.board.Cell;
import ru.vsu.cs.raspopov.chess.essence.board.Position;
import ru.vsu.cs.raspopov.utils.Graph;

public abstract class Piece {

    private Position pos;
    private String name;
    private Color color;

    protected Piece(Color color, Position pos, String name) {
        this.color = color;
        this.pos = pos;
        this.name = name;
    }

    public Color getColor() {
        return color;
    }

    public Position getPos() {
        return pos;
    }

    public String getName() {
        return name;
    }

    public abstract boolean isValid(Board board);

    public abstract void move();

    public abstract void drawPiece();

}
