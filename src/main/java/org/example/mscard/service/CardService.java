package org.example.mscard.service;

import org.example.mscard.dto.CardDTO;
import org.example.mscard.entity.CardEntity;

import java.util.List;
import java.util.function.Consumer;

public interface CardService {

    List<CardDTO> getAllCards();

    CardDTO getCardById(Long id);

    CardDTO getCardByUserId(Long userId);

    CardDTO getCardByAccountId(Long accountId);

    CardDTO saveCardById(CardDTO cardDTO);
    boolean deleteCardById(Long id);
    CardDTO updateCardById(Long id, Consumer<CardEntity> updateFunction);
}

