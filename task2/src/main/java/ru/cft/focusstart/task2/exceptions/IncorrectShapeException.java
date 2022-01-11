package ru.cft.focusstart.task2.exceptions;

public class IncorrectShapeException extends RuntimeException{
    public IncorrectShapeException(String message) {
        super(message);
    }

    public IncorrectShapeException(String message, Throwable cause) {
        super(message, cause);
    }

    public IncorrectShapeException(Throwable cause) {
        super(cause);
    }
}
