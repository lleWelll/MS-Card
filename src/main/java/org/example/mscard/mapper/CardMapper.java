package org.example.mscard.mapper;

import org.example.mscard.dto.CardDTO;
import org.example.mscard.entity.CardEntity;
import org.mapstruct.Mapper;

import java.util.List;


@Mapper(componentModel = "spring")
public interface CardMapper extends MapperHelper<CardDTO, CardEntity> {

    List<CardDTO> toDtoList(List<CardEntity> humanEntities);
}
