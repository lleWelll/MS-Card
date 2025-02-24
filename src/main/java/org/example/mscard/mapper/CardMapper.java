package org.example.mscard.mapper;

import org.example.mscard.dto.CardDTO;
import org.example.mscard.entity.CardEntity;
import org.example.mscard.entity.PaymentSystem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring", uses = {CardTypeMapper.class, PaymentSystemMapper.class})
public interface CardMapper extends EntityDtoMapper<CardDTO, CardEntity> {

    @Mapping(source = "cardNumber", target = "cardNumber", qualifiedByName = "mapCardNumber")
    CardEntity toEntity(CardDTO cardDTO);

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