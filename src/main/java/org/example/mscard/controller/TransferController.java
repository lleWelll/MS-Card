package org.example.mscard.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.mscard.service.CardTransactionService;
import org.example.mscard.util.Validator;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("/transfer")
@Slf4j
@RequiredArgsConstructor
public class TransferController {

	private final CardTransactionService transactionService;

	@PostMapping("/pay")
	public void processPayment(@RequestParam Long id, @RequestParam BigDecimal amount) {
		checkAmount(amount);
		transactionService.processPayment(id, amount);
	}

	@PostMapping("/receive")
	public void processReceiving(@RequestParam Long id, @RequestParam BigDecimal amount) {
		checkAmount(amount);
		transactionService.processReceiving(id, amount);
	}

	@PostMapping()
	public void processTransfer(@RequestParam Long fromId, @RequestParam Long toId, @RequestParam BigDecimal amount) {
		checkAmount(amount);
		transactionService.processTransfer(fromId, toId, amount);
	}
	private void checkAmount(BigDecimal amount) {
		if (! Validator.isValidAmount(amount)) {
			log.error("amount of money is not valid, it should be != null and > 0");
			throw new IllegalArgumentException("Invalid amount of money");
		}
	}

}
