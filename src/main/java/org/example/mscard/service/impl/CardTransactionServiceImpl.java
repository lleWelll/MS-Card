//package org.example.mscard.service.impl;
//
//import jakarta.transaction.Transactional;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//
//import org.example.mscard.exceptions.CardNotFoundException;
//import org.example.mscard.exceptions.InsufficientBalanceException;
//import org.example.mscard.entity.CardEntity;
//import org.example.mscard.repository.CardRepository;
//import org.example.mscard.service.CardTransactionService;
//import org.springframework.stereotype.Service;
//
//import java.math.BigDecimal;
//
//@Service
//@RequiredArgsConstructor
//@Slf4j
//public class CardTransactionServiceImpl implements CardTransactionService {
//    private final CardRepository cardRepository;
//
//    @Override
//    @Transactional
//    public boolean processPayment(Long id, BigDecimal amount) {
//        CardEntity cardEntity = cardRepository.findById(id)
//                .orElseThrow(() -> new CardNotFoundException("Card with id " + id + " not found"));
//
//        if (cardEntity.getBalance().compareTo(amount) < 0) {
//            throw new InsufficientBalanceException("Insufficient balance on card with id " + id);
//        }
//
//
//        cardEntity.setBalance(cardEntity.getBalance().subtract(amount));
//        cardRepository.save(cardEntity);
//
//        log.info("Payment of {} processed successfully for card with id {}", amount, id);
//        return true;
//    }
//
//    @Override
//    @Transactional
//    public boolean processReceiving(Long id, BigDecimal amount) {
//        CardEntity cardEntity = cardRepository.findById(id)
//                .orElseThrow(() -> new CardNotFoundException("Card with id " + id + " not found"));
//
//
//        cardEntity.setBalance(cardEntity.getBalance().add(amount));
//        cardRepository.save(cardEntity);
//
//        log.info("Receiving of {} processed successfully for card with id {}", amount, id);
//        return true;
//    }
//}
