package org.example.mscard.mapper;

import org.example.mscard.dto.CardDTO;
import org.example.mscard.entity.CardEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface CardMapper {

    @Mapping(source = "cardNumber", target = "cardNumber", qualifiedByName = "mapCardNumber")
    CardEntity toEntity(CardDTO cardDTO);

    CardDTO toDTO(CardEntity cardEntity);

    @Named("mapCardNumber")
    static String mapCardNumber(String cardNumber) {
        cardNumber = cardNumber.replaceAll("\\s", "");
        cardNumber = cardNumber.replaceAll("-", "");
        cardNumber = maskCardNumber(cardNumber);
        return cardNumber;
    }

    static String maskCardNumber(String cardNumber) {
        return "************" + cardNumber.substring(12);
    }

}