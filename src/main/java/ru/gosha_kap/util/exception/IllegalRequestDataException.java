package ru.gosha_kap.util.exception;

public class IllegalRequestDataException extends RuntimeException {
    public IllegalRequestDataException(String message) {
        super(message);
    }
}
