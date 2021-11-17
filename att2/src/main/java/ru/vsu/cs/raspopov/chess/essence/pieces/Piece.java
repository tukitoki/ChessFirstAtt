package ru.vsu.cs.raspopov.chess.essence.pieces;

import javafx.scene.image.Image;
import javafx.scene.input.DataFormat;
import javafx.scene.paint.Color;
import ru.vsu.cs.raspopov.chess.essence.board.Board;
import ru.vsu.cs.raspopov.chess.essence.board.Cell;
import ru.vsu.cs.raspopov.chess.essence.board.Position;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public abstract class Piece implements Serializable {

    public transient static final DataFormat CHESS_PIECE = new DataFormat("chess.piece");
    private transient static Map<String, Image> imageCache = new HashMap<>();
    private Position pos;
    private String name;
    private String imageFileName;
    private transient Color color;

    protected Piece(Color color, Position pos, String name) {
        this.color = color;
        this.pos = pos;
        this.name = name;
        imageFileName = "File:pieceImg/" + (color.equals(Color.BLACK)
                ? "B"
                : "W") + name + ".png";
        if (!imageCache.containsKey(imageFileName)) {
            imageCache.put(imageFileName, new Image(imageFileName));
        }
    }

    public Image getImage() {
        return imageCache.get(imageFileName);
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

    public void setPos(Position pos) {
        this.pos = pos;
    }

    public void move(Board board, Cell cell) {
        board.getCell(getPos()).setPiece(null);
        this.setPos(cell.getPos());
        cell.setPiece(this);
        board.recalculateGraph();
    }

    public abstract void drawPiece();

    public LinkedList<Cell> calculateValidPositions(Board board) {
        for (Cell cell : board.getGraph()) {
            if (cell.getPos().equals(this.getPos())) {
                return board.getGraph().allEdges(cell);
            }
        }
        return new LinkedList<>();
    }

    public abstract ArrayList<Position> moveAblePositions(Board board);

    public abstract ArrayList<Position> betweenKingAndPiece(Board board);

}
