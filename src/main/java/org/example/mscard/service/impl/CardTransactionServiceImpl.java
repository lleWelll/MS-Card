package org.example.mscard.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.mscard.exceptions.BaseTransactionException;
import org.example.mscard.exceptions.ErrorCode;
import org.example.mscard.service.CardService;
import org.example.mscard.service.CardTransactionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
@Slf4j
public class CardTransactionServiceImpl implements CardTransactionService {

	private final CardService cardService;
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	@Override
	public void processReceiving(Long id, BigDecimal amount) {
		cardService.updateCardById(id,
				(entity) -> entity.setBalance(entity.getBalance().add(amount))
		);
	}
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	@Override
	public void processPayment(Long id, BigDecimal amount) {
		cardService.updateCardById(id,
				(entity) -> {
					if (entity.getBalance().compareTo(amount) < 0) {
						throw new BaseTransactionException(ErrorCode.INSUFFICIENT_BALANCE, entity.getId(), entity.getBalance(), amount);
					}
					entity.setBalance(entity.getBalance().subtract(amount));
				}
		);
	}
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	@Override
	public void processTransfer(Long fromId, Long toId, BigDecimal amount) {
		processPayment(fromId, amount);
		processReceiving(toId, amount);
	}
}