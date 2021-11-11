package ru.vsu.cs.raspopov.chess.essence.board;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.vsu.cs.raspopov.utils.Graph;

public class Board {

    private static final int SIZE_BOARD = 8;
    private Graph<Cell> graph;
    private static final Logger logger = LoggerFactory.getLogger(Board.class);

    public Board() {
        graph = new Graph<Cell>();
        for (int row = SIZE_BOARD; row >= 1; row--) {
            for (char col = 'A'; col <= 'H'; col++) {
                graph.addVertex(new Cell(new Position(col, row), null));
            }
        }
        logger.info("Board init was successful.");
    }

    public void recalculateGraph() {

    }

    public int getSizeBoard() {
        return SIZE_BOARD;
    }

    public Graph<Cell> getGraph() {
        return graph;
    }

}
