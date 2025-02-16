package org.example.mscard.service;

import org.example.mscard.dto.CardDTO;
import org.example.mscard.entity.CardEntity;

import java.util.List;

public interface CardService {

    List<CardDTO> getAllCards();
    CardDTO getCardByNumber(String cardNumber);
    CardEntity saveCard(CardDTO cardDTO);
    boolean deleteCard(Long id);
    CardDTO updateCard(Long id, CardDTO cardDTO);
}

