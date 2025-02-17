package org.example.mscard.mapper;

import java.util.List;

public interface EntityDtoMapper<S, D> {

    S toDto(D d);

    D toDao(S s);

    List<S> toDtoList(List<D> d);
}