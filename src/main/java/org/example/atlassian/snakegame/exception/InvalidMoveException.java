package org.example.atlassian.snakegame.exception;

public class InvalidMoveException extends RuntimeException{
    public InvalidMoveException(String message) {
        super("Invalid move exception: " + message);
    }

    public InvalidMoveException(String message, Throwable cause) {
        super(message, cause);
    }
}
