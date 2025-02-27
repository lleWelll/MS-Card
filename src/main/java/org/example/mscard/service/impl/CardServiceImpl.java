package org.example.mscard.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.mscard.dto.CardDTO;
import org.example.mscard.entity.CardEntity;
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
        log.info("Получение всех карт");
        List<CardDTO> cards =  cardRepository.findAll()
                .stream()
                .map(cardMapper::toDTO)
                .toList();
        log.info("Получено {} карт", cards.size());
        return cards;

    }

    @Transactional(readOnly = true)
    @Override
    public CardDTO getCardById(Long id) {
        log.info("Получение карты с id {}", id);
        CardDTO cardDTO = cardMapper.toDTO(getEntityById(id));
        log.info("Карта с id {} успешно получена", id);
        return cardDTO;
    }

    @Transactional
    @Override
    public CardDTO saveCardById(CardDTO cardDTO) {

        String convertToUpperCasePaymentSystem = cardDTO.getPaymentSystem().toUpperCase();
        String convertToUpperCaseCardType = cardDTO.getCardType().toUpperCase();

        cardDTO.setPaymentSystem(convertToUpperCasePaymentSystem);
        cardDTO.setCardType(convertToUpperCaseCardType);


        if (!Validator.isValidCardType(cardDTO.getCardType())) {
            log.error("Неверный тип карты: {}", cardDTO.getCardType());
            throw new InvalidCardTypeException("Неверно указан тип карты");
        }

        if (!Validator.isValidPaymentSystem(cardDTO.getPaymentSystem())) {
            log.error("Неверная платежная система: {}", cardDTO.getPaymentSystem());
            throw new InvalidPaymentSystemException("Неверно указана платежная система");
        }

        CardEntity cardEntity = cardMapper.toEntity(cardDTO);
        CardEntity savedCardEntity = cardRepository.save(cardEntity);
        log.info("Карта успешно сохранена: {}", savedCardEntity.getId());

        return cardMapper.toDTO(savedCardEntity);
    }

    @Transactional
    @Override
    public boolean deleteCardById(Long id) {
        log.info("Попытка удалить карту с id {}", id);
        CardEntity entity = getEntityById(id);
        cardRepository.delete(entity);
        log.info("Карта с id {} успешно удалена", id);
        return true;
    }

    @Transactional
    @Override
    public CardDTO updateCardById(Long id, Consumer<CardEntity> updateFunction) {
        log.info("Попытка обновить карту с id {}", id);
        if (! Validator.isValidCardId(id)) {
            log.error("Попытка найти карту с неверным id: {}", id);
            throw new IllegalArgumentException("id карты не может быть равен null или < 0");
        }
        CardEntity entity = getEntityById(id);
        updateFunction.accept(entity);
        cardRepository.save(entity);
        log.info("Карта с id {} успешно обновлена", id);
        return cardMapper.toDTO(entity);
    }

    private CardEntity getEntityById(Long id) {
        log.info("Попытка получить карту с id {}", id);
        if (! Validator.isValidCardId(id)) {
            log.error("Попытка найти карту с неверным id: {}", id);
            throw new IllegalArgumentException("id карты не может быть равен null или < 0");
        }
        return cardRepository.findById(id).orElseThrow(() -> {
            log.error("Карта с id {} не найдена", id);
            return new CardNotFoundException("Карта с id " + id + " не найдена");
        });
    }
}
