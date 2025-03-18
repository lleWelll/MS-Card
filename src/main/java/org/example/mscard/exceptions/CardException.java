package org.example.mscard.exceptions;

public class CardException extends RuntimeException {
	public CardException() {
	}

	public CardException(String message) {
		super(message);
	}

	public CardException(String message, Throwable cause) {
		super(message, cause);
	}

	public CardException(Throwable cause) {
		super(cause);
	}

	public CardException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
