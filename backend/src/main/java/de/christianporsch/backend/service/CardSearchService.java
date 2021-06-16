package de.christianporsch.backend.service;

import de.christianporsch.backend.model.MagicCard;
import de.christianporsch.backend.model.Price;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CardSearchService {

    List<MagicCard> magicCardList = List.of(
            new MagicCard("Tarmogoyf", "Tarmogoyf's power is equal to the number of card types among cards in all graveyards and its toughness is equal to that number plus 1.", "https://c1.scryfall.com/file/scryfall-cards/normal/front/6/9/69daba76-96e8-4bcc-ab79-2f00189ad8fb.jpg?1619398799", new Price(26.00, 27.00, 12.50)),
            new MagicCard("Black Lotus", "{T}, Sacrifice Black Lotus: Add three mana of any one color.", "https://c1.scryfall.com/file/scryfall-cards/normal/front/b/d/bd8fa327-dd41-4737-8f19-2cf5eb1f7cdd.jpg?1614638838", new Price(100.00, 50.00, 45.00)));

    public List<MagicCard> findMagicCards(String cardName){
        return magicCardList.stream().filter(card -> card.getName().toLowerCase().contains(cardName.toLowerCase())).collect(Collectors.toList());

    }

}
