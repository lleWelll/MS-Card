package org.example.mscard.mapper;

import org.example.mscard.dto.CardDTO;
import org.example.mscard.entity.CardEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CardMapper extends EntityDtoMapper<CardDTO, CardEntity> {

}
