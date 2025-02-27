package org.example.mscard.exceptions;

public class InvalidPaymentSystemException extends RuntimeException {
    public InvalidPaymentSystemException(String message) {
        super(message);
    }
}