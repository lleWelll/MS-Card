package org.example.mscard.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.mscard.dto.CardDTO;
import org.example.mscard.service.CardService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/cards")
public class CardController {
    private final CardService cardService;

    @GetMapping
    public List<CardDTO> getAllCards() {
        return cardService.getAllCards();
    }

    @GetMapping("/{id}")
    public CardDTO getCardById(@PathVariable Long id) {
        return cardService.getCardById(id);
    }

    @PostMapping
    public CardDTO createCard(@RequestBody @Valid CardDTO cardDTO) {
        log.info("Received request: {}", cardDTO);
        return cardService.saveCardById(cardDTO);
    }

    @PostMapping("/setActive")
    public CardDTO setActive(@RequestParam Long id, @RequestParam Boolean active) {
        return cardService.updateCardById(id,
                (entity) -> entity.setActive(active)
        );
    }

    @DeleteMapping("/{id}")
    public boolean deleteById(@PathVariable Long id) {
        return cardService.deleteCardById(id);
    }
}



