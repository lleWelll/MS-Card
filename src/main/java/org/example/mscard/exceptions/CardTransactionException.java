package org.example.mscard.exceptions;

public class CardTransactionException extends RuntimeException {
	public CardTransactionException() {
	}

	public CardTransactionException(String message) {
		super(message);
	}

	public CardTransactionException(String message, Throwable cause) {
		super(message, cause);
	}

	public CardTransactionException(Throwable cause) {
		super(cause);
	}

	public CardTransactionException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
