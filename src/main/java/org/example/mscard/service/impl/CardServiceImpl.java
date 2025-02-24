package org.example.mscard.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.mscard.dto.CardDTO;
import org.example.mscard.entity.CardEntity;
import org.example.mscard.exceptions.CardNotFoundException;
import org.example.mscard.mapper.CardMapper;
import org.example.mscard.repository.CardRepository;
import org.example.mscard.service.CardService;
import org.example.mscard.util.Validator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.function.Consumer;

@Service
@RequiredArgsConstructor
@Slf4j
public class CardServiceImpl implements CardService {
    private final CardRepository cardRepository;
    private final CardMapper cardMapper;

    @Transactional(readOnly = true)
    public List<CardDTO> getAllCards() {
        return cardRepository.findAll()
                .stream()
                .map(cardMapper::toDTO)
                .toList();
    }

    @Transactional(readOnly = true)
    @Override
    public CardDTO getCardById(Long id) {
        return cardMapper.toDTO(getEntityById(id));
    }

    @Transactional
    @Override
    public CardDTO saveCardById(CardDTO cardDTO) {
        log.info("Received request: {}", cardDTO);

        if (!Validator.isValidCardNumber(cardDTO.getCardNumber())) {
            log.error("Invalid card number: {}", cardDTO.getCardNumber());
            throw new IllegalArgumentException("Invalid card number");
        }
        CardEntity cardEntity = cardMapper.toEntity(cardDTO);
        CardEntity savedCardEntity = cardRepository.save(cardEntity);
        return cardMapper.toDTO(savedCardEntity);
    }

    @Transactional
    @Override
    public boolean deleteCardById(Long id) {
       CardEntity entity = getEntityById(id);
       cardRepository.delete(entity);
       log.info("Card {} deleted", id);
       return true;
    }

    @Transactional
    @Override
    public CardDTO updateCardById(Long id, Consumer<CardEntity> updateFunction) {
        if (! Validator.isValidCardId(id)) {
            log.error("Attempted to find card with invalid id: {}", id);
            throw new IllegalArgumentException("Card id is null or < 0");
        }
        CardEntity entity = getEntityById(id);
        updateFunction.accept(entity);
        cardRepository.save(entity);
        return cardMapper.toDTO(entity);
    }

    private CardEntity getEntityById(Long id) {
        if (! Validator.isValidCardId(id)) {
            log.error("Attempted to find card with invalid id: {}", id);
            throw new IllegalArgumentException("Card id is null or < 0");
        }
        return cardRepository.findById(id).orElseThrow(() -> {
            log.error("Card with id {} not found", id);
            return new CardNotFoundException("Card with id " + id + " not found");
        });
    }
}