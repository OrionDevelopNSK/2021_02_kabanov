package ru.cft.focusstart.task2.exceptions;

public class IncorrectFileException extends RuntimeException{
    public IncorrectFileException(String message, Throwable cause) {
        super(message, cause);
    }

    public IncorrectFileException(String message) {
        super(message);
    }
}
