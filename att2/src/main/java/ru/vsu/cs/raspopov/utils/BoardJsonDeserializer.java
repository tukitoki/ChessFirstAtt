package ru.vsu.cs.raspopov.utils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.node.TextNode;
import javafx.scene.paint.Color;
import org.w3c.dom.Text;
import ru.vsu.cs.raspopov.chess.essence.board.Board;
import ru.vsu.cs.raspopov.chess.essence.board.Cell;
import ru.vsu.cs.raspopov.chess.essence.pieces.*;

import java.io.IOException;

public class BoardJsonDeserializer extends JsonDeserializer<Board> {

    @Override
    public Board deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        Board board = new Board();
        TreeNode treeNode = jsonParser.readValueAsTree();
        for (Cell cell : board.getGraph()) {
            TextNode str = (TextNode) treeNode.get(cell.getPos().toString());
            if (!str.textValue().equals("null")) {
                Color color = null;
                TextNode stringColor = (TextNode) treeNode.get(cell.getPos().toString() + str.textValue());
                if (stringColor.textValue().equals("Black")) {
                    color = Color.BLACK;
                } else if (stringColor.textValue().equals("White")){
                    color = Color.WHITE;
                }
                if (str.textValue().equals("P")) {
                    cell.setPiece(new Pawn(color, cell.getPos()));
                }
                if (str.textValue().equals("K")) {
                    cell.setPiece(new King(color, cell.getPos()));
                }
                if (str.textValue().equals("Knt")) {
                    cell.setPiece(new Knight(color, cell.getPos()));
                }
                if (str.textValue().equals("B")) {
                    cell.setPiece(new Bishop(color, cell.getPos()));
                }
                if (str.textValue().equals("Q")) {
                    cell.setPiece(new Queen(color, cell.getPos()));
                }
                if (str.textValue().equals("R")) {
                    cell.setPiece(new Rook(color, cell.getPos()));
                }
            }

            //Color color = treeNode.get(cell.getPos().toString() + cell.getPiece().getName()).toString();
            //cell.setPiece(treeNode.get(cell.getPos().toString()).toString());
        }
        return board;
    }
}
