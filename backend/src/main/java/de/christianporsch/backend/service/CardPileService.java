package de.christianporsch.backend.service;

import de.christianporsch.backend.model.MagicCard;
import de.christianporsch.backend.model.User;
import de.christianporsch.backend.model.dto.MagicCardDto;
import de.christianporsch.backend.repository.UserPileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CardPileService {

    private final UserPileRepository userPileRepository;
    private final CardSearchService cardSearchService;

    @Autowired
    public CardPileService(UserPileRepository userPileRepository, CardSearchService cardSearchService) {
        this.userPileRepository = userPileRepository;
        this.cardSearchService = cardSearchService;
    }

    public List<MagicCard> findPileOfCardsByUser(String id) {
        return userPileRepository.findUserById(id).getPileOfCards();
    }

    public MagicCard addMagicCardToPile(MagicCardDto magicCardToAdd) {
        Optional<MagicCard> magicCard = cardSearchService.findMagicCardById(magicCardToAdd.getId());
        if (magicCard.isPresent()) {
            User user = userPileRepository.findUserById("60d2f120c76f8707f38e9a99");
            user.getPileOfCards().add(magicCard.get());
            userPileRepository.save(user);
        }
        return magicCard.get();
    }


}
