package de.christianporsch.backend.controller;

import de.christianporsch.backend.model.MagicCardInPile;
import de.christianporsch.backend.model.dto.MagicCardDto;
import de.christianporsch.backend.service.CardPileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/cardsInPile")
public class CardPileController {

    private final CardPileService cardPileService;

    @Autowired
    public CardPileController(CardPileService cardPileService) {
        this.cardPileService = cardPileService;
    }

    @GetMapping("{id}")
    public List<MagicCardInPile> findPileOfCardsByUser(@PathVariable String id) {
        return cardPileService.findPileOfCardsByUser(id);
    }

    @GetMapping("/specificCardInPile/{id}")
    public MagicCardInPile findMagicCardInPileById(@PathVariable String id) {
        Optional<MagicCardInPile> response = cardPileService.findMagicCardInPileById(id);
        if (response.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Magic card with id " + id + " not found");
        }
        return response.get();
    }

    @PostMapping
    public MagicCardInPile addMagicCardToPile(Principal principal, @RequestBody MagicCardDto magicCardToAdd) {

        String username = principal.getName();

        try {
            return cardPileService.addMagicCardToPile(username, magicCardToAdd);
        } catch (IllegalArgumentException error) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, error.getMessage());
        }
    }

    @PutMapping("/updateCardInPile/{id}")
    public MagicCardInPile decreaseMagicCardInPileAmount(Principal principal, @PathVariable String id) {

        String username = principal.getName();

        return cardPileService.decreaseMagicCardInPileAmount(username, id);
    }

    @DeleteMapping("{id}")
    public void deleteMagicCardInPileById(@PathVariable String id) {
        cardPileService.deleteMagicCardInPileById(id);
    }
}
