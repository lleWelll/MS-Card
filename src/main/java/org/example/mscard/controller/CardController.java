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

    @GetMapping("/{id}")
    public CardDTO getCardById(@PathVariable Long id) {
        return cardServiceImpl.getCardById(id);
    }

    @PostMapping
    public CardDTO createCard(@RequestBody @Valid CardDTO cardDTO) {
        log.info("Received request: {}", cardDTO);
        return cardServiceImpl.saveCard(cardDTO);
    }

    @PostMapping("/setActive")
    public CardDTO setActive(@RequestParam Long id, @RequestParam Boolean active) {
        return cardServiceImpl.updateCard(id,
                (entity) -> entity.setActive(active)
                );
    }
}
