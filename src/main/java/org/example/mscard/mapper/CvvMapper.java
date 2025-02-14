package org.example.mscard.mapper;

import org.example.mscard.entity.Cvv;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CvvMapper {

    default String toString(Cvv cvv) {
        return cvv != null ? cvv.getCvvNumber() : null;
    }

    default Cvv toCvv(String cvvNumber) {
        return cvvNumber != null ? new Cvv(cvvNumber) : null;
    }
}
