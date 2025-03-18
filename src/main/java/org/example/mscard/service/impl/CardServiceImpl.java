package org.example.mscard.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.mscard.dto.CardDTO;
import org.example.mscard.entity.CardEntity;
import org.example.mscard.exceptions.CardValidationException;
import org.example.mscard.exceptions.CardNotFoundException;
import org.example.mscard.exceptions.InvalidCardTypeException;
import org.example.mscard.exceptions.InvalidPaymentSystemException;
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
        log.info("Getting all the cards");
        List<CardDTO> cards = cardRepository.findAll()
                .stream()
                .map(cardMapper::toDTO)
                .toList();
        log.info("Received {} cards", cards.size());
        return cards;
    }

    @Transactional(readOnly = true)
    @Override
    public CardDTO getCardById(Long id) {
        log.info("Getting a card with an id {}", id);
        CardDTO cardDTO = cardMapper.toDTO(getEntityById(id));
        log.info("Card with id {} successfully received", id);
        return cardDTO;
    }

    @Transactional(readOnly = true)
    @Override
    public CardDTO getCardByUserId(Long userId) {
        log.info("Getting a card by userId {}", userId);
        CardDTO cardDTO = cardMapper.toDTO(getEntityByUserId(userId));
        log.info("The card with the userId {} was successfully received", userId);
        return cardDTO;
    }

    @Transactional(readOnly = true)
    @Override
    public CardDTO getCardByAccountId(Long accountId) {
        log.info("Getting a card by AccountId {}", accountId);
        CardDTO cardDTO = cardMapper.toDTO(getEntityByAccountId(accountId));
        log.info("The card with the AccountId {} was successfully received", accountId);
        return cardDTO;
    }

    @Transactional
    @Override
    public CardDTO saveCardById(CardDTO cardDTO) {

        String convertToUpperCasePaymentSystem = cardDTO.getPaymentSystem().toUpperCase();
        String convertToUpperCaseCardType = cardDTO.getCardType().toUpperCase();

        cardDTO.setPaymentSystem(convertToUpperCasePaymentSystem);
        cardDTO.setCardType(convertToUpperCaseCardType);

        if (!Validator.isValidId(cardDTO.getAccountId()) || !Validator.isValidId(cardDTO.getUserId())) {
            throw new CardValidationException("Incorrect userId or accountId, it should be > 0");
        }

        if (!Validator.isValidCardType(cardDTO.getCardType())) {
            throw new InvalidCardTypeException("The card type is incorrect.");
        }
        if (!Validator.isValidPaymentSystem(cardDTO.getPaymentSystem())) {
            throw new InvalidPaymentSystemException("Incorrect payment system: " + cardDTO.getPaymentSystem());
        }
        CardEntity cardEntity = cardMapper.toEntity(cardDTO);
        CardEntity savedCardEntity = cardRepository.save(cardEntity);
        log.info("The card was saved successfully: {}", savedCardEntity.getId());
        return cardMapper.toDTO(savedCardEntity);
    }

    @Transactional
    @Override
    public boolean deleteCardById(Long id) {
        log.info("Attempt to delete a card with an id {}", id);
        CardEntity entity = getEntityById(id);
        cardRepository.delete(entity);
        log.info("Card with id {} successfully deleted", id);
        return true;
    }

    @Transactional
    @Override
    public CardDTO updateCardById(Long id, Consumer<CardEntity> updateFunction) {
        log.info("Attempt to update a card with an id {}", id);
        CardEntity entity = getEntityById(id);
        updateFunction.accept(entity);
        cardRepository.save(entity);
        log.info("The card with the id {} has been successfully updated", id);
        return cardMapper.toDTO(entity);
    }

    private CardEntity getEntityById(Long id) {
        log.info("Attempt to get a card with an id {}", id);
        if (!Validator.isValidId(id)) {
            throw new CardValidationException("the card id cannot be null or < 0");
        }
        return cardRepository.findById(id).orElseThrow(() -> new CardNotFoundException("A card with an id " + id + " not found"));
    }

    private CardEntity getEntityByUserId(Long id) {
        log.info("Attempt to get a card with user Id {}", id);
        if (!Validator.isValidId(id)) {
            throw new CardValidationException("the user Id of the card cannot be null or < 0");
        }
        return cardRepository.findCardEntityByUserId(id).orElseThrow(() -> new CardNotFoundException("A card with a userId " + id + " not found"));
    }

    private CardEntity getEntityByAccountId(Long id) {
        log.info("Attempt to get a card with an AccountId {}", id);
        if (!Validator.isValidId(id)) {
            throw new CardValidationException("accountId card cannot be equal to null or < 0");
        }
        return cardRepository.findCardEntityByAccountId(id).orElseThrow(() -> new CardNotFoundException("card with a accountId " + id + " not found"));
    }
}