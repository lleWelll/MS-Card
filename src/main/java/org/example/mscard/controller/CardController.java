package org.example.mscard.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.mscard.dto.CardDTO;
import org.example.mscard.service.impl.CardServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/cards")
public class CardController {
    private final CardServiceImpl cardServiceImpl;

    @GetMapping
    public List<CardDTO> getAllCards() {
        return cardServiceImpl.getAllCards();
    }

    @GetMapping("/{cardNumber}")
    public CardDTO getCardByNumber(@PathVariable String cardNumber) {
        return cardServiceImpl.getCardByNumber(cardNumber);
    }

    @PostMapping
    public CardDTO createCard(@RequestBody @Valid CardDTO cardDTO) {
        log.info("Received request: {}", cardDTO);
        return cardServiceImpl.saveCard(cardDTO);
    }

}
