package ru.vsu.cs.raspopov.chess;

import javafx.application.Application;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.vsu.cs.raspopov.chess.gameservice.Service;

public class Game {

    private static final Logger logger = LoggerFactory.getLogger(Service.class);

    public static void main(String[] args) {
         Application.launch(Service.class);
         logger.info("Application launched successfully.");
    }

}
