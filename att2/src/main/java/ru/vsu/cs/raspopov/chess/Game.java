package ru.vsu.cs.raspopov.chess;

import javafx.application.Application;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.vsu.cs.raspopov.chess.gameservice.Controller;

public class Game {

    private static final Logger logger = LoggerFactory.getLogger(Controller.class);

    public static void main(String[] args) {
         Application.launch(Controller.class);
         logger.info("Application launched successfully.");
    }

}
