package de.christianporsch.backend.controller;

import de.christianporsch.backend.model.MagicCard;
import de.christianporsch.backend.service.CardPileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;


@RestController
@RequestMapping("/api/cardsInPile")
public class CardPileController {

    private final CardPileService cardPileService;

    @Autowired
    public CardPileController(CardPileService cardPileService) {
        this.cardPileService = cardPileService;
    }

    @GetMapping("{id}")
    public List<MagicCard> findPileOfCardsByUser(@PathVariable String id){
        List<MagicCard> response = cardPileService.findPileOfCardsByUser(id);
        return response;
    }


}
