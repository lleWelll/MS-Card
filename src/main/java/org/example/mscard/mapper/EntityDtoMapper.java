package org.example.mscard.mapper;

import java.util.List;

public interface EntityDtoMapper<S, D> {

    S toDTO(D d);

    D toEntity(S s);

}