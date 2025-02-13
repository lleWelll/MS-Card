package org.example.mscard.entity;

import jakarta.persistence.Embeddable;
import lombok.*;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CardNumber {

    private String cardNumber;

    public boolean isValid() {
        return cardNumber != null && cardNumber.matches("\\d{16}");
    }
}
