package ru.vsu.cs.raspopov.chess.essence.board;

import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import javafx.scene.paint.Color;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.vsu.cs.raspopov.chess.essence.pieces.King;
import ru.vsu.cs.raspopov.chess.essence.pieces.Pawn;
import ru.vsu.cs.raspopov.chess.essence.pieces.Piece;
import ru.vsu.cs.raspopov.chess.gameservice.Table;
import ru.vsu.cs.raspopov.utils.Graph;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Board {

    private static final int SIZE_BOARD = 8;
    private Graph<Cell> graph;
    private static final Logger logger = LoggerFactory.getLogger(Board.class);
    private Table table;

    public Board() {
        graph = new Graph<Cell>();
        for (int row = 1; row <= SIZE_BOARD; row++) {
            for (char col = 'A'; col <= 'H'; col++) {
                graph.addVertex(new Cell(new Position(col, row), null));
            }
        }
        logger.info("Board init was successful.");
    }

    public void recalculateGraph() {
        try {
            for (Cell cell : graph) {
                if (cell.getPiece() == null) {
                    if (graph.allEdges(cell).size() > 0) {
                        graph.removeEdges(cell);
                    }
                } else {
                    if (graph.allEdges(cell).size() > 0) {
                        graph.removeEdges(cell);
                    }
                    for (Position position : cell.getPiece().moveAblePositions(this)) {
                        if (getCell(position).getPiece() != null) {
                            if (getCell(position).getPiece().getColor() != cell.getPiece().getColor()) {
                                graph.createEdgeFromV1ToV2(cell, getCell(position));
                            }
                        } else {
                            if (cell.getPiece() instanceof Pawn) {
                                if (position.getColumn() == cell.getPiece().getPos().getColumn()) {
                                    graph.createEdgeFromV1ToV2(cell, getCell(position));
                                }
                            } else {
                                graph.createEdgeFromV1ToV2(cell, getCell(position));
                            }
                        }
                    }
                }
            }
            recalculateGraphForKing(getKingCell(Color.WHITE));
            recalculateGraphForKing(getKingCell(Color.BLACK));
            ArrayList<Piece> attackingPieces = attackingKingPieces(getKingCell(Color.WHITE));
            if (attackingPieces.size() == 1) {
                recalculateGraphIfKingAttacked(Color.WHITE, attackingPieces.get(0));
            }
            if (attackingPieces.size() > 1) {
                for (Cell cell : graph) {
                    if (cell.getPiece().getColor() != Color.WHITE && !(cell.getPiece() instanceof King)) {
                        graph.removeEdges(cell);
                    }
                }
            }
            attackingPieces = attackingKingPieces(getKingCell(Color.BLACK));
            if (attackingPieces.size() == 1) {
                recalculateGraphIfKingAttacked(Color.BLACK, attackingPieces.get(0));
            }
            if (attackingPieces.size() > 1) {
                for (Cell cell : graph) {
                    if (cell.getPiece().getColor() != Color.BLACK && !(cell.getPiece() instanceof King)) {
                        graph.removeEdges(cell);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Cell isPawnCanChoose() {
        for (Cell cell : graph) {
            if (cell.getPiece() != null && cell.getPiece() instanceof Pawn) {
                if (cell.getPos().getRow() == 1 && cell.getPiece().getColor() == Color.BLACK) {
                    return cell;
                }
                if (cell.getPos().getRow() == 8 && cell.getPiece().getColor() == Color.WHITE) {
                    return cell;
                }
            }
        }
        return null;
    }

    public void recalculateGraphIfKingAttacked(Color color, Piece piece) {
        for (Cell cell : graph) {
            if (cell.getPiece() != null && cell.getPiece().getColor() == color) {
                if (!(cell.getPiece() instanceof King)) {
                    graph.removeEdges(cell);
                } else {
                    continue;
                }
                for (Position position : cell.getPiece().moveAblePositions(this)) {
                    if (piece.betweenKingAndPiece(this).contains(position)) {
                        System.out.println(Arrays.toString(piece.betweenKingAndPiece(this).toArray()));
                        if (cell.getPiece() instanceof Pawn) {
                            if (cell.getPiece().getPos().getColumn() != position.getColumn()) {
                                if (position.getColumn() == piece.getPos().getColumn()) {
                                    graph.createEdgeFromV1ToV2(cell, this.getCell(position));
                                }
                            } else {
                                graph.createEdgeFromV1ToV2(cell, this.getCell(position));
                            }
                        } else if (this.getCell(position).getPiece() == null || this.getCell(position).getPiece().getColor() != color) {
                            graph.createEdgeFromV1ToV2(cell, this.getCell(position));
                        }
                    }
                }
            }
        }
    }

    public ArrayList<Piece> attackingKingPieces(Cell kingCell) {
        ArrayList<Piece> attackingPieces = new ArrayList<>();
        for (Cell cell : graph) {
            if (graph.allEdges(cell).contains(kingCell)) {
                attackingPieces.add(cell.getPiece());
            }
        }
        return attackingPieces;
    }

    public void recalculateGraphForKing(Cell targetCell) {
        try {
            for (Cell cell : graph) {
                if (cell.getPiece() != null && cell.getPiece().getColor() != targetCell.getPiece().getColor()) {
                    if (cell.getPiece() instanceof King) {
                        for (Position position : cell.getPiece().moveAblePositions(this)) {
                            if (targetCell.getPiece().calculateValidPositions(this).contains(this.getCell(position))) {
                                graph.removeEdge(targetCell, this.getCell(position));
                            }
                        }
                    }
                    for (Position position : cell.getPiece().moveAblePositions(this)) {
//                        if (targetCell.getPiece().calculateValidPositions(this).contains(this.getCell(position))) {
//                            graph.removeEdge(targetCell, this.getCell(position));
//                        }
//                        if (pawnConditionToKing(cell, secondCell, targetCell)) {
//                            if (targetCell.getPiece().calculateValidPositions(this).contains(secondCell)) {
//                                graph.removeEdge(targetCell, secondCell);
//                                logger.info(secondCell.getPos().toString() + " KING was removed");
//                            }
//                        }
                        if (pawnConditionToKing(cell, this.getCell(position), targetCell)) {
                            //graph.removeEdge(targetCell, this.getCell(position));
                            if (targetCell.getPiece().calculateValidPositions(this).contains(this.getCell(position))) {
                                logger.info(position.toString());
                                graph.removeEdge(targetCell, this.getCell(position));
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }

//    private boolean cellCanBeAttacked(Cell cell, Color color) {
//
//    }

    private boolean pawnConditionToKing(Cell cell, Cell secondCell, Cell targetCell) {
        if (cell.getPiece() instanceof Pawn) {
            for (Cell help : targetCell.getPiece().calculateValidPositions(this)) {
                if (!help.getPos().equals(secondCell.getPos())) {
                    continue;
                }
                char column = cell.getPos().getColumn();
                column++;
                char column2 = cell.getPos().getColumn();
                column2--;
                int row = cell.getPiece().getColor() == Color.WHITE ? cell.getPos().getRow() + 1 : cell.getPos().getRow() - 1;
                if (secondCell.getPos().getRow() == row && (secondCell.getPos().getColumn() == column2
                        || secondCell.getPos().getColumn() == column)) {
                    return true;
                }
                return cell.getPos().getColumn() != secondCell.getPos().getColumn();
            }
        }
        return true;
    }

    public Cell getKingCell(Color color) {
        for (Cell cell : graph) {
            if (cell.getPiece() != null) {
                if (cell.getPiece() instanceof King && cell.getPiece().getColor() == color) {
                    return cell;
                }
            }
        }
        return null;
    }

    public Cell getCell(Position position) {
        for (Cell cell : graph) {
            if (cell.getPos().equals(position)) {
                return cell;
            }
        }
        return null;
    }

    public int getSizeBoard() {
        return SIZE_BOARD;
    }

    public Graph<Cell> getGraph() {
        return graph;
    }

    @Override
    public String toString() {
        return "Board{" +
                "graph=" + graph.toString() +
                '}';
    }

}
