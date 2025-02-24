package org.example.mscard.util;

import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;

@Slf4j
public class Validator {
    public static boolean isValidCardNumber(String cardNumber) {
        return cardNumber != null && cardNumber.matches("\\d{16}");
    }

    public static boolean isCardNumberNullOrBlank(String cardNumber) {
        return cardNumber == null || cardNumber.isBlank();
    }

    public static boolean isValidCardId(Long id) {
        if (id == null) return false;
        return id >= 1;
    }

    public static boolean isValidAmount(BigDecimal amount) {
        if (amount == null) return false;
        return amount.compareTo(BigDecimal.valueOf(0)) > 0;
    }
}
