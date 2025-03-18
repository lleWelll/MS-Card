package org.example.mscard.exceptions;

public class InvalidPaymentSystemException extends CardException {
    public InvalidPaymentSystemException(String message) {
        super(message);
    }
}