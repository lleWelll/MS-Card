package org.example.mscard.mapper;

public interface MapperHelper<S, D> {

    S toDto(D d);

    D toDao(S s);

}