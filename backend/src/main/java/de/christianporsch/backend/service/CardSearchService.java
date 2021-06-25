package de.christianporsch.backend.service;

import de.christianporsch.backend.model.MagicCard;
import de.christianporsch.backend.repository.MagicCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public Optional<MagicCard> findMagicCardById (String id){
        return magicCardRepository.findMagicCardById(id);
    }

}
