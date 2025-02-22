package org.example.mscard.util;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Validator {
    public static boolean isValidCardNumber(String cardNumber) {
        return cardNumber != null && cardNumber.matches("\\d{16}");
    }

    public static boolean isCardNumberNullOrBlank(String cardNumber) {
        return cardNumber == null || cardNumber.isBlank();
    }

    public static boolean isCardIdValid(Long id) {
        if (id == null) return false;
        return id >= 1;
    }

}
