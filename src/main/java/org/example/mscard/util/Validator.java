package org.example.mscard.util;

import lombok.extern.slf4j.Slf4j;
import org.example.mscard.entity.CardType;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import java.math.BigDecimal;

@Slf4j
public class Validator {

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

    public static boolean isValidCardType(String cardType) {
        try {
            CardType.valueOf(cardType.toUpperCase());
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
    public static boolean isValidPaymentSystem(String paymentSystem) {
        List<String> validPaymentSystems = Arrays.asList("VISA", "MASTERCARD");
        return paymentSystem != null && validPaymentSystems.contains(paymentSystem.toUpperCase());
    }
}
