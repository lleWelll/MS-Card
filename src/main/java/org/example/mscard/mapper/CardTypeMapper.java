package org.example.mscard.mapper;

import org.example.mscard.entity.CardType;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CardTypeMapper {

    String toString(CardType cardType);

    CardType toCardType(String cardType);
}
