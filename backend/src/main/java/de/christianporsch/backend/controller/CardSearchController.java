package de.christianporsch.backend.controller;

import de.christianporsch.backend.model.MagicCard;
import de.christianporsch.backend.service.CardSearchService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


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

}
