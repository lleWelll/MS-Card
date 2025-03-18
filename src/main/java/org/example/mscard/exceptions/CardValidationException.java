package org.example.mscard.exceptions;

public class CardValidationException extends CardException {
	public CardValidationException() {
	}

	public CardValidationException(String message) {
		super(message);
	}

	public CardValidationException(String message, Throwable cause) {
		super(message, cause);
	}

	public CardValidationException(Throwable cause) {
		super(cause);
	}

	public CardValidationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
