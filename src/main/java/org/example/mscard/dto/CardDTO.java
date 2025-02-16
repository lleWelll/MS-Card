package org.example.mscard.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class CardDTO {

    @NotNull(message = "account id is required")
    private Long accountId;

    @NotNull(message = "user id is required")
    private Long userId;

    @Pattern(regexp = "\\d{16}", message = "Card number must be 16 digits")
    private String cardNumber;

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
    private String cvvNumber;
}
