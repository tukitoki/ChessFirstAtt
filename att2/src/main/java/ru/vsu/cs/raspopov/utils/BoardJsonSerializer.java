package ru.vsu.cs.raspopov.utils;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import javafx.scene.paint.Color;
import ru.vsu.cs.raspopov.chess.essence.board.Board;
import ru.vsu.cs.raspopov.chess.essence.board.Cell;

import java.io.IOException;

public class BoardJsonSerializer extends JsonSerializer<Board> {

    @Override
    public void serialize(Board board, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        for (Cell cell : board.getGraph()) {
            jsonGenerator.writeStringField(cell.getPos().toString(), (cell.getPiece() != null ? cell.getPiece().getName() : "null"));
            String color;
            if (cell.getPiece() != null) {
                color = cell.getPiece().getColor() == Color.BLACK ? "Black" : "White";
            } else {
                color = "null";
            }
            jsonGenerator.writeStringField(cell.getPos().toString() + (cell.getPiece() != null ? cell.getPiece().getName() : "null"), color);
        }
        jsonGenerator.writeEndObject();
    }
}
