package org.example.mscard.service;

import org.example.mscard.dto.CardDTO;

import java.util.List;

public interface CardService {

    List<CardDTO> getAllCards();
    CardDTO getCardByNumber(String cardNumber);
    CardDTO saveCard(CardDTO cardDTO);
    boolean deleteCard(Long id);
    CardDTO updateCard(Long id, CardDTO cardDTO);
}

