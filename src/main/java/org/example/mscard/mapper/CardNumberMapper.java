package org.example.mscard.mapper;

import org.example.mscard.entity.CardNumber;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CardNumberMapper {

    default String toString(CardNumber cardNumber) {
        return cardNumber != null ? cardNumber.getCardNumber() : null;
    }

    default CardNumber toCardNumber(String cardNumber) {
        return cardNumber != null ? new CardNumber(cardNumber) : null;
    }
}
