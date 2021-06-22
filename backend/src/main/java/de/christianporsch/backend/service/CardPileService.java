package de.christianporsch.backend.service;

import de.christianporsch.backend.model.MagicCard;
import de.christianporsch.backend.repository.UserPileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CardPileService {

    private final UserPileRepository userPileRepository;

    @Autowired
    public CardPileService(UserPileRepository userPileRepository) {
        this.userPileRepository = userPileRepository;
    }

    public List<MagicCard> findPileOfCardsByUser(String id){
        return userPileRepository.findUserById(id).getPileOfCards();
    }


}
