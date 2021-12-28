package ru.vsu.cs.raspopov.chess.graphics;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.paint.Color;
import ru.vsu.cs.raspopov.chess.essence.board.Cell;
import ru.vsu.cs.raspopov.chess.essence.board.Position;
import ru.vsu.cs.raspopov.chess.essence.pieces.Piece;
import ru.vsu.cs.raspopov.chess.gameservice.Table;

import java.util.LinkedList;

public class GraphicCell extends Label {

    private final Position position;
    private static final String defWhiteGraphicCell = "-fx-background-color: white;";
    private static final String defBlackGraphicCell = "-fx-background-color: #ef8401;";
    private static final String defEmptyWhiteGraphicCell = "-fx-background-color: #019b01;";
    private static final String defEmptyBlackGraphicCell = "-fx-background-color: #00460c;";
    private static final String defKillGraphicCell = "-fx-background-color: red;";

    public GraphicCell(Position position, Image image) {
        this.position = position;
        setDefaultGraphicCell();
        setImage(image);
        setMinSize(75, 75);
        setMaxSize(75, 75);
    }

    public void setImage(Image image) {
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(75);
        imageView.setFitWidth(75);
        this.setGraphic(imageView);
    }

    public Position getPosition() {
        return position;
    }

    public void cellAnimation(Table table) {
        setOnDragDetected(mouseEvent -> onDragDetected(mouseEvent, table));
        setOnDragOver(this::onDragOver);
        setOnDragDropped(dragEvent -> onDragDropped(dragEvent, table));
        setOnDragDone(dragEvent -> onDragDone(dragEvent, table));
        setOnMouseEntered(e -> onMouseEntered(table));
        setOnMouseExited(e -> onMouseExited(table));
    }

    private void onMouseEntered(Table table) {
        Piece piece = table.getPiece(position);
        if (piece != null && piece.getColor() == table.getCurrentTurn()) {
            for (GraphicCell gc : table.getCells()) {
                for (Cell cell : piece.calculateValidPositions(table.getBoard())) {
                    if (cell.getPos().equals(gc.getPosition())) {
                        if (cell.getPiece() != null) {
                            gc.setKillGraphicCell();
                        } else {
                            gc.setEmptyGraphicCell();
                        }
                    }
                }
            }
        }
    }

    private void onMouseExited(Table table) {
        Piece piece = table.getPiece(position);
        if (piece != null) {
            LinkedList<Cell> helpLink = piece.calculateValidPositions(table.getBoard());
            for (GraphicCell gc : table.getCells()) {
                for (Cell cell : helpLink) {
                    if (cell.getPos().equals(gc.getPosition())) {
                        gc.setDefaultGraphicCell();
                    }
                }
            }
        }
    }

    private void onDragDetected(MouseEvent e, Table table) {
        Piece piece = table.getPiece(position);
        if (piece != null && piece.getColor() == table.getCurrentTurn()) {
            LinkedList<Cell> helpLink = piece.calculateValidPositions(table.getBoard());
            if (helpLink.size() > 0) {
                Dragboard db = startDragAndDrop(TransferMode.MOVE);
                db.setDragView(piece.getImage());
                ClipboardContent content = new ClipboardContent();
                content.put(Piece.CHESS_PIECE, piece);
                db.setContent(content);
                for (GraphicCell gc : table.getCells()) {
                    for (Cell cell : helpLink) {
                        if (cell.getPos().equals(gc.getPosition())) {
                            gc.setEmptyGraphicCell();
                        }
                    }
                }
            }
        }
        e.consume();
    }

    private void onDragOver(DragEvent e) {
        if (e.getDragboard().hasContent(Piece.CHESS_PIECE)) {
            e.acceptTransferModes(TransferMode.MOVE);
        }
        e.consume();
    }


    private void onDragDropped(DragEvent e, Table table) {
        Dragboard db = e.getDragboard();
        if (db.hasContent(Piece.CHESS_PIECE)) {
            Piece piece = (Piece) db.getContent(Piece.CHESS_PIECE);
            piece = table.getPiece(piece.getPos());
            LinkedList<Cell> helpLink = piece.calculateValidPositions(table.getBoard());
            if (helpLink.size() > 0 && piece.calculateValidPositions(table.getBoard()).contains(table.getBoard().getCell(this.position))) {
                setDefaultGraphicCell();
                for (Cell cell : helpLink) {
                    if (cell.getPos().equals(this.position)) {
                        setImage(piece.getImage());
                        for (GraphicCell gc : table.getCells()) {
                            if (gc.position.equals(piece.getPos())) {
                                gc.setGraphic(null);
                            }
                        }
                    }
                    if (cell.getPos().equals(this.getPosition())) {
                        piece.move(table.getBoard(), cell);
                    }
                }
                table.gameTest();
            }
        }
        e.consume();
    }

    private void onDragDone(DragEvent e, Table table) {
        Dragboard db = e.getDragboard();
        if (db.hasContent(Piece.CHESS_PIECE)) {
            Piece piece = (Piece) db.getContent(Piece.CHESS_PIECE);
            piece.setPos(table.getBoard().getCell(piece.getPos()).getPos());
            LinkedList<Cell> helpLink = piece.calculateValidPositions(table.getBoard());
            for (GraphicCell gc : table.getCells()) {
                for (Cell cell : helpLink) {
                    if (cell.getPos().equals(gc.getPosition())) {
                        gc.setDefaultGraphicCell();
                    }
                    //cell.getPiece().moveAblePositions();
                }
            }
        }

        e.consume();
    }

    private void setDefaultGraphicCell() {
        if (getColor() == Color.WHITE) {
            setStyle(defWhiteGraphicCell);
        } else {
            setStyle(defBlackGraphicCell);
        }
    }

    private void setKillGraphicCell() {
        setStyle(defKillGraphicCell);
    }

    private void setEmptyGraphicCell() {
        if (getColor() == Color.WHITE) {
            setStyle(defEmptyWhiteGraphicCell);
        } else {
            setStyle(defEmptyBlackGraphicCell);
        }
    }

    private Color getColor() {
        if ((position.getRow() + position.getColumn()) % 2 == 0) {
            return Color.WHITE;
        } else {
            return Color.BLACK;
        }
    }


}
