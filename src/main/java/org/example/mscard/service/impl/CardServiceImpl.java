package org.example.mscard.service.impl;

import org.example.mscard.dto.CardDTO;
import org.example.mscard.entity.*;
import org.example.mscard.exceptions.CardNotFoundException;
import org.example.mscard.mapper.CardMapper;
import org.example.mscard.repository.CardRepository;
import org.example.mscard.service.CardService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

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

    @Override
    public CardDTO getCardByNumber(String cardNumber) {
        if (cardNumber == null || cardNumber.isBlank()) {
            log.error("Attempted to find card with null or empty number");
            throw new IllegalArgumentException("Card number cannot be null or empty");
        }
        return cardRepository.findByCardNumber_CardNumber(cardNumber)
                .map(cardMapper::toDTO)
                .orElseThrow(() -> {
                    log.error("Card with number {} not found", cardNumber);
                    return new CardNotFoundException("Card with number " + cardNumber + " not found");
                });
    }

    @Transactional
    public CardDTO saveCard(CardDTO cardDTO) {

        CardNumber cardNumber = new CardNumber(cardDTO.getCardNumber());
        Cvv cvv = new Cvv(cardDTO.getCvv());

        if (!cardNumber.isValid() || !cvv.isValid()) {
            log.error("Invalid card details");
            throw new IllegalArgumentException("Invalid card number or CVV");
        }

        CardEntity cardEntity = cardMapper.toEntity(cardDTO);
        cardEntity.setCardNumber(cardNumber);
        cardEntity.setCvv(cvv);

        CardEntity savedCard = cardRepository.save(cardEntity);
        return cardMapper.toDTO(savedCard);
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

        if (cardDTO.getCardHolderFirstName() != null) {
            cardEntity.setCardHolderFirstName(cardDTO.getCardHolderFirstName());
        }
        if (cardDTO.getCardHolderLastName() != null) {
            cardEntity.setCardHolderLastName(cardDTO.getCardHolderLastName());
        }
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
