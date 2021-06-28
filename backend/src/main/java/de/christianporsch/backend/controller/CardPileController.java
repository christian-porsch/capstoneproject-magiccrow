package de.christianporsch.backend.controller;

import de.christianporsch.backend.model.MagicCardInPile;
import de.christianporsch.backend.model.dto.MagicCardDto;
import de.christianporsch.backend.service.CardPileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;


import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


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
        List<MagicCardInPile> response = cardPileService.findPileOfCardsByUser(id);
        return response
                .stream()
                .sorted(Comparator.comparing(MagicCardInPile::getName))
                .collect(Collectors.toList());
    }

    @GetMapping("/userPile/{id}")
    public MagicCardInPile findMagicCardInPileById (@PathVariable String id){
        Optional<MagicCardInPile> response = cardPileService.findMagicCardInPileById(id);
        if (response.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Magic card with id " + id + " not found");
        }
        return response.get();
    }

    @PostMapping
    public void addMagicCardToPile(@RequestBody MagicCardDto magicCardToAdd) {
        cardPileService.addMagicCardToPile(magicCardToAdd);
    }



}
