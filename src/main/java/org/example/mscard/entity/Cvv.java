package org.example.mscard.entity;

import jakarta.persistence.Embeddable;
import lombok.*;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Cvv {

    private String cvvNumber;

    public boolean isValid() {
        return cvvNumber != null && cvvNumber.matches("\\d{3}");
    }
}
