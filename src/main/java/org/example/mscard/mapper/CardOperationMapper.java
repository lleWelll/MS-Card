package org.example.mscard.mapper;

import org.example.mscard.dto.CardOperationInKafkaDTO;
import org.example.mscard.entity.CardOperationInKafkaEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CardOperationMapper {

    CardOperationInKafkaEntity toEntity(CardOperationInKafkaDTO dto);


    CardOperationInKafkaDTO toDTO(CardOperationInKafkaEntity entity);
}
