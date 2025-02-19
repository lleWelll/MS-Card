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
    public CardDTO getCardByNumber(String cardNumber) {
        if (Validator.isCardNumberNullOrBlank(cardNumber)) {
            log.error("Attempted to find card with null or empty number");
            throw new IllegalArgumentException("Card number cannot be null or empty");
        }

        return cardMapper.toDTO(cardRepository.findByCardNumber(cardNumber).orElseThrow(() -> {
            log.error("Card with number {} not found", cardNumber);
            return new CardNotFoundException("Card with number " + cardNumber + " not found");
        }));
    }

    @Override
    public CardDTO saveCard(CardDTO cardDTO) {
        log.info("Received request: {}", cardDTO);

        if (!Validator.isValidCardNumber(cardDTO.getCardNumber())) {
            log.error("Invalid card number: {}", cardDTO.getCardNumber());
            throw new IllegalArgumentException("Invalid card number");
        }
        CardEntity cardEntity = cardMapper.toEntity(cardDTO);
        CardEntity savedCardEntity = cardRepository.save(cardEntity);
        return cardMapper.toDTO(savedCardEntity);
    }

    @Override
    public boolean deleteCard(Long id) {
        return cardRepository.findById(id)
                .map(card -> {
                    cardRepository.deleteById(id);
                    return true;
                })
                .orElseThrow(() -> {
                    log.error("Attempted to delete non-existent card with id {}", id);
                    return new CardNotFoundException("Card not found with id " + id);
                });
    }

    @Transactional
    @Override
    public CardDTO updateCard(Long id, CardDTO cardDTO) {
        CardEntity cardEntity = cardRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Card with id {} not found", id);
                    return new CardNotFoundException("Card with id " + id + " not found");
                });

        if (cardDTO.getBalance() != null) {
            cardEntity.setBalance(cardDTO.getBalance());
        }

        if (cardDTO.getExpiryDate() != null) {
            cardEntity.setExpiryDate(cardDTO.getExpiryDate());
        }


        CardEntity updatedCard = cardRepository.save(cardEntity);
        return cardMapper.toDTO(updatedCard);
    }
}