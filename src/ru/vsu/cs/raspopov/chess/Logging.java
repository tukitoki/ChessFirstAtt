package ru.vsu.cs.raspopov.chess;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Logging {

    private static Logger logger;

    public Logging(Class<?> clazz) {
        logger = LoggerFactory.getLogger(Game.class);
    }
}
