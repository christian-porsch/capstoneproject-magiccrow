package de.christianporsch.backend.service;

import de.christianporsch.backend.model.MagicCard;
import de.christianporsch.backend.repository.MagicCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CardSearchService {

    private final MagicCardRepository magicCardRepository;

    @Autowired
    public CardSearchService(MagicCardRepository magicCardRepository) {
        this.magicCardRepository = magicCardRepository;
    }

    public List<MagicCard> findMagicCards(String cardName){
        return magicCardRepository.filterCardsByCardName(cardName);

    }

}
