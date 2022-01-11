package ru.cft.focusstart.task5.exceptions;

public class IncorrectNumberException extends RuntimeException{
    public IncorrectNumberException(Throwable cause) {
        super(cause);
    }

    public IncorrectNumberException(String message, Throwable cause) {
        super(message, cause);
    }

    public IncorrectNumberException(String message) {
        super(message);
    }
}
