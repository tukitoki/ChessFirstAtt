package ru.vsu.cs.raspopov.chess.gameservice;

import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.vsu.cs.raspopov.chess.essence.board.Board;
import ru.vsu.cs.raspopov.chess.essence.board.Cell;
import ru.vsu.cs.raspopov.chess.essence.board.Position;
import ru.vsu.cs.raspopov.chess.essence.pieces.*;
import ru.vsu.cs.raspopov.chess.graphics.GraphicCell;
import ru.vsu.cs.raspopov.chess.graphics.Graphics;

import java.util.ArrayList;


public class Table {

    private final GridPane root;
    private Board board;
    private static final Logger logger = LoggerFactory.getLogger(Table.class);
    private ArrayList<GraphicCell> cells;
    private Color currentTurn = Color.WHITE;
    Graphics graphics = new Graphics();
    Controller controller = new Controller();

    public Table() {
        board = new Board();
        root = new GridPane();
        cells = new ArrayList<>();
        firstPieceInit();
        for (int row = 1; row <= board.getSizeBoard(); row++) {
            for (char col = 'A'; col <= 'H'; col++) {
                GraphicCell cell = graphics.setGraphicCellAnimation(this, col, row);
                cells.add(cell);
                root.add(cell, col, row);
            }
        }
        logger.info("Table was init successfully.");
    }

    public void gameTest() {
        pawnReplace();
        currentTurn = currentTurn == Color.BLACK ? Color.WHITE : Color.BLACK;
        if (board.getKingCell(currentTurn).getPiece().calculateValidPositions(this.getBoard()).size() == 0) {
            for (Cell cell : board.getGraph()) {
                if (cell.getPiece() != null && cell.getPiece().calculateValidPositions(board).size() > 0 && cell.getPiece().getColor() == currentTurn) {
                    return;
                }
            }
            controller.endGame(currentTurn);
            //Controller.endGame(currentTurn);
        }
    }

    public void pawnReplace() {
        Cell cell = board.isPawnCanChoose();
        if (cell != null) {
            graphics.showPieces(cell.getPiece().getColor(), cell.getPos(), this);
        }
    }

    public void setBoard(Board board) {
        this.board = board;
        board.recalculateGraph();
        cells.clear();
        for (int row = 1; row <= board.getSizeBoard(); row++) {
            for (char col = 'A'; col <= 'H'; col++) {
                GraphicCell cell = graphics.setGraphicCellAnimation(this, col, row);
                cells.add(cell);
                root.add(cell, col, row);
            }
        }
    }

    public void resetBoard() {
        cells.clear();
        for (Cell cell : this.board.getGraph()) {
            cell.setPiece(null);
        }
        firstPieceInit();
        for (int row = 1; row <= board.getSizeBoard(); row++) {
            for (char col = 'A'; col <= 'H'; col++) {
                GraphicCell cell = graphics.setGraphicCellAnimation(this, col, row);
                cells.add(cell);
                root.add(cell, col, row);
            }
        }
        logger.info("Game was restarted successfully");
    }

    public Color getCurrentTurn() {
        return currentTurn;
    }

    private void firstPieceInit() {
        for (Cell cell : board.getGraph()) {
            if (cell.getPos().equals(new Position('A', 8)) || cell.getPos().equals(new Position('H', 8)) ||
                    cell.getPos().equals(new Position('A', 1)) || cell.getPos().equals(new Position('H', 1))) {
                cell.setPiece(new Rook((cell.getPos().equals(new Position('A', 8)) || cell.getPos().equals(new Position('H', 8)))
                        ? Color.BLACK
                        : Color.WHITE, cell.getPos()));
            }
            if (cell.getPos().equals(new Position('B', 8)) || cell.getPos().equals(new Position('G', 8)) ||
                    cell.getPos().equals(new Position('B', 1)) || cell.getPos().equals(new Position('G', 1))) {
                cell.setPiece(new Knight((cell.getPos().equals(new Position('B', 8)) || cell.getPos().equals(new Position('G', 8)))
                        ? Color.BLACK
                        : Color.WHITE, cell.getPos()));
                if (cell.getPos().getRow() == 1) {
                    char ch = cell.getPos().getColumn();
                    ch++;
                    board.getGraph().createEdgeFromV1ToV2(cell, board.getCell(new Position(ch, 3)));
                    ch -= 2;
                    board.getGraph().createEdgeFromV1ToV2(cell, board.getCell(new Position(ch, 3)));
                } else {
                    char ch = cell.getPos().getColumn();
                    ch++;
                    board.getGraph().createEdgeFromV1ToV2(cell, board.getCell(new Position(ch, 6)));
                    ch -= 2;
                    board.getGraph().createEdgeFromV1ToV2(cell, board.getCell(new Position(ch, 3)));
                }
            }
            if (cell.getPos().equals(new Position('C', 8)) || cell.getPos().equals(new Position('F', 8)) ||
                    cell.getPos().equals(new Position('C', 1)) || cell.getPos().equals(new Position('F', 1))) {
                cell.setPiece(new Bishop((cell.getPos().equals(new Position('C', 8)) || cell.getPos().equals(new Position('F', 8)))
                        ? Color.BLACK
                        : Color.WHITE, cell.getPos()));
            }
            if (cell.getPos().equals(new Position('D', 8)) || cell.getPos().equals(new Position('D', 1))) {
                cell.setPiece(new Queen(cell.getPos().equals(new Position('D', 8))
                        ? Color.BLACK
                        : Color.WHITE, cell.getPos()));
            }
            if (cell.getPos().equals(new Position('E', 8)) || cell.getPos().equals(new Position('E', 1))) {
                cell.setPiece(new King(cell.getPos().equals(new Position('E', 8))
                        ? Color.BLACK
                        : Color.WHITE, cell.getPos()));
            }
            if (cell.getPos().getRow() == 7 || cell.getPos().getRow() == 2) {
                cell.setPiece(new Pawn(cell.getPos().getRow() == 7
                        ? Color.BLACK
                        : Color.WHITE, cell.getPos()));

                if (cell.getPos().getRow() == 7) {
                    char ch = cell.getPos().getColumn();
                    board.getGraph().createEdgeFromV1ToV2(cell, board.getCell(new Position(ch, 7 - 1)));
                    board.getGraph().createEdgeFromV1ToV2(cell, board.getCell(new Position(ch, 7 - 2)));
                } else {
                    char ch = cell.getPos().getColumn();
                    board.getGraph().createEdgeFromV1ToV2(cell, board.getCell(new Position(ch, 2 + 1)));
                    board.getGraph().createEdgeFromV1ToV2(cell, board.getCell(new Position(ch, 2 + 2)));
                }
            }
            try {
                logger.info("Piece " + cell.getPiece().getName() + " was put on: " +  cell.getPos().getColumn() + cell.getPos().getRow() + " successfully.");
            } catch (Exception e) {
                logger.error("Cell is not specified for firstInit piece.");
            }
        }
    }

    public ArrayList<GraphicCell> getCells() {
        return cells;
    }

    public Board getBoard() {
        return board;
    }

    public Piece getPiece(Position position) {
        return this.board.getCell(position).getPiece();
    }

    public GridPane getRoot() {
        return root;
    }

    @Override
    public String toString() {
        return "Table{" +
                ", board=" + board +
                '}';
    }
}
