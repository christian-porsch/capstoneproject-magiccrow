package de.christianporsch.backend.service;

import de.christianporsch.backend.model.MagicCard;
import de.christianporsch.backend.model.MagicCardInPile;
import de.christianporsch.backend.model.User;
import de.christianporsch.backend.model.dto.MagicCardDto;
import de.christianporsch.backend.repository.MagicCardInPileRepository;
import de.christianporsch.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;


@Service
public class CardPileService {

    private final UserRepository userRepository;
    private final MagicCardInPileRepository magicCardInPileRepository;
    private final CardSearchService cardSearchService;

    @Autowired
    public CardPileService(CardSearchService cardSearchService, UserRepository userRepository, MagicCardInPileRepository magicCardInPileRepository) {
        this.cardSearchService = cardSearchService;
        this.userRepository = userRepository;
        this.magicCardInPileRepository = magicCardInPileRepository;
    }

    public List<MagicCardInPile> findPileOfCardsByUser(String id) {
        return userRepository.findUserById(id).getPileOfCards();
    }

    public Optional<MagicCardInPile> findMagicCardInPileById(String id) {
        return magicCardInPileRepository.findMagicCardInPileById(id);
    }

    public MagicCardInPile addMagicCardToPile(MagicCardDto magicCardToAdd) {
        Optional<MagicCard> magicCard = cardSearchService.findMagicCardById(magicCardToAdd.getId());

        if (magicCard.isPresent()) {

            User user = userRepository.findUserById("60d2f120c76f8707f38e9a99");

            Optional<MagicCardInPile> magicCardInPile = user.getPileOfCards().stream().filter((card) -> magicCard.get().getId().equals(card.getId())).findFirst();

            if (magicCardInPile.isPresent()) {
                magicCardInPile.get().setAmount(magicCardInPile.get().getAmount() + 1);
                magicCardInPileRepository.save(magicCardInPile.get());
                userRepository.save(user);

                return magicCardInPile.get();
            } else {
                MagicCardInPile newMagicCardInPile = MagicCardInPile.builder()
                        .id(magicCard.get().getId())
                        .amount(1)
                        .name(magicCard.get().getName())
                        .oracle_text(magicCard.get().getOracle_text())
                        .image_uris(magicCard.get().getImage_uris())
                        .set_name(magicCard.get().getSet_name())
                        .build();
                user.getPileOfCards().add(newMagicCardInPile);
                magicCardInPileRepository.save(newMagicCardInPile);
                userRepository.save(user);

                return newMagicCardInPile;
            }
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error");
        }
    }

    public MagicCardInPile decreaseMagicCardInPileAmount(String id){

        Optional<MagicCardInPile> magicCard = findMagicCardInPileById(id);

        if(magicCard.isPresent()){
            User user = userRepository.findUserById("60d2f120c76f8707f38e9a99");

            Optional<MagicCardInPile> magicCardInPile = user.getPileOfCards().stream().filter((card) -> magicCard.get().getId().equals(card.getId())).findFirst();

            if(magicCardInPile.isPresent()){
                magicCardInPile.get().setAmount(magicCardInPile.get().getAmount() - 1);
                magicCardInPileRepository.save(magicCardInPile.get());
                userRepository.save(user);

                return magicCardInPile.get();
            }
        }
        throw new IllegalArgumentException();
    }

    public void deleteMagicCardInPileById(String id){
        magicCardInPileRepository.deleteById(id);
    }
}
