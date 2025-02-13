package org.example.mscard.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "cards")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CardEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private CardNumber cardNumber;

    @Column(nullable = false)
    private String cardHolderFirstName;

    @Column(nullable = false)
    private String cardHolderLastName;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PaymentSystem paymentSystem;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CardType cardType;

    @Embedded
    private Cvv cvv;

    @Column(columnDefinition = "TINYINT(1)", nullable = false)
    private boolean isActive;

    @Column(nullable = false)
    private LocalDate expiryDate;

    @Column(precision = 19, scale = 2, nullable = false)
    private BigDecimal balance = BigDecimal.ZERO;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime created;

    @UpdateTimestamp
    @Column(nullable = false)
    private LocalDateTime lastUpdated;

    public String getCardHolderFullName() {
        return cardHolderFirstName + " " + cardHolderLastName;
    }
}
