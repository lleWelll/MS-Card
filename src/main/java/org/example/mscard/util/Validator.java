package org.example.mscard.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class Validator {
	public static boolean isValidCardNumber(String cardNumber) {
		return cardNumber != null && cardNumber.matches("\\d{16}");
	}

	public static boolean isValidCVV(String cvv) {
		return cvv != null && cvv.matches("\\d{3}");
	}
}
