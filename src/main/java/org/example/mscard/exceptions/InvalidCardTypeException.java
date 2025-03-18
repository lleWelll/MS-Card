package org.example.mscard.exceptions;

public class InvalidCardTypeException extends CardException {
    public InvalidCardTypeException(String message) {
        super(message);
    }
}
