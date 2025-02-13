package org.example.mscard.mapper;

import org.example.mscard.dto.CardDTO;
import org.example.mscard.entity.CardEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {CardNumberMapper.class, CvvMapper.class})
public interface CardMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(source = "cardNumber", target = "cardNumber.cardNumber")
    @Mapping(source = "cvv", target = "cvv.cvvNumber")
    CardEntity toEntity(CardDTO cardDTO);

    @Mapping(source = "cardNumber.cardNumber", target = "cardNumber")
    @Mapping(source = "cvv.cvvNumber", target = "cvv")
    CardDTO toDTO(CardEntity cardEntity);
}
