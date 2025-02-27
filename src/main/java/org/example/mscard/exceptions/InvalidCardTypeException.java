package org.example.mscard.exceptions;

public class InvalidCardTypeException extends RuntimeException {
    public InvalidCardTypeException(String message) {
        super(message);
    }
}
