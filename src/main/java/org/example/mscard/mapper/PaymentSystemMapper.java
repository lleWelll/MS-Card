package org.example.mscard.mapper;

import org.example.mscard.entity.PaymentSystem;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PaymentSystemMapper {

    String toString(PaymentSystem paymentSystem);

    PaymentSystem toPaymentSystem(String paymentSystem);
}
