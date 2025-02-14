package org.example.mscard.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import lombok.*;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CardDTO {

    @Pattern(regexp = "\\d{16}", message = "Card number must be 16 digits")
    private String cardNumber;

    @NotBlank(message = "First name is required")
    private String cardHolderFirstName;

    @NotBlank(message = "Last name is required")
    private String cardHolderLastName;

    @NotNull
    @DecimalMin(value = "0.00", message = "Balance cannot be negative")
    private BigDecimal balance;

    @NotNull(message = "Expiry date is required")
    private LocalDate expiryDate;

    private boolean active;


    @NotBlank
    private String cardType;

    @NotBlank
    private String paymentSystem;

    @Pattern(regexp = "\\d{3}", message = "CVV must be 3 digits")
    private String cvv;

    public String getCardHolderFullName() {
        return cardHolderFirstName + " " + cardHolderLastName;
    }
}
