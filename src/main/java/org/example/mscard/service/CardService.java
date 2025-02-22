package org.example.mscard.service;

import org.example.mscard.dto.CardDTO;
import org.example.mscard.entity.CardEntity;

import java.util.List;
import java.util.function.Consumer;

public interface CardService {

    List<CardDTO> getAllCards();
    CardDTO getCardById(Long id);
    CardDTO saveCard(CardDTO cardDTO);
    boolean deleteCard(Long id);
    CardDTO updateCard(Long id, Consumer<CardEntity> updateFunction);
}

