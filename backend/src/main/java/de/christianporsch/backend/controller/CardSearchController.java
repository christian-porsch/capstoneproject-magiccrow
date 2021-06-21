package de.christianporsch.backend.controller;

import de.christianporsch.backend.model.MagicCard;
import de.christianporsch.backend.service.CardSearchService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/cards")
public class CardSearchController {

    private final CardSearchService cardSearchService;

    public CardSearchController(CardSearchService cardSearchService) {
        this.cardSearchService = cardSearchService;
    }


    @GetMapping
    List<MagicCard> findMagicCards(@RequestParam String cardName){
        return cardSearchService.findMagicCards(cardName);
    }

    @GetMapping("{id}")
    public MagicCard findMagicCardById (@PathVariable String id){
        Optional<MagicCard> response = cardSearchService.findMagicCardById(id);
        if (response.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Magic card with id " + id + " not found");
        }
        return response.get();
    }

}
