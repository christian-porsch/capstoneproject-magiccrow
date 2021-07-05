package de.christianporsch.backend.service;

import de.christianporsch.backend.model.MagicCard;
import de.christianporsch.backend.model.MagicCardInPile;
import de.christianporsch.backend.security.model.AppUser;
import de.christianporsch.backend.model.dto.MagicCardDto;
import de.christianporsch.backend.repository.MagicCardInPileRepository;
import de.christianporsch.backend.security.repository.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class CardPileService {

    private final AppUserRepository appUserRepository;
    private final MagicCardInPileRepository magicCardInPileRepository;
    private final CardSearchService cardSearchService;

    @Autowired
    public CardPileService(CardSearchService cardSearchService, AppUserRepository appUserRepository, MagicCardInPileRepository magicCardInPileRepository) {
        this.cardSearchService = cardSearchService;
        this.appUserRepository = appUserRepository;
        this.magicCardInPileRepository = magicCardInPileRepository;
    }

    public List<MagicCardInPile> findPileOfCardsByUser(String id) {
        List<MagicCardInPile> response = appUserRepository.findById(id).get().getPileOfCards();
        return response
                .stream()
                .sorted(Comparator.comparing(MagicCardInPile::getName))
                .collect(Collectors.toList());
    }

    public Optional<MagicCardInPile> findMagicCardInPileById(String id) {
        return magicCardInPileRepository.findMagicCardInPileById(id);
    }

    public MagicCardInPile addMagicCardToPile(String username, MagicCardDto magicCardToAdd) {

        Optional<MagicCard> magicCard = cardSearchService.findMagicCardById(magicCardToAdd.getId());

        if (magicCard.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The magic card you are looking for does not exist");
        }

        Optional<AppUser> appUser = appUserRepository.findById(username);

        if (appUser.get().getPileOfCards() == null) {
            appUser.get().setPileOfCards(new ArrayList<>());
        }

        Optional<MagicCardInPile> magicCardInPile = appUser.get().getPileOfCards().stream().filter((card) -> magicCard.get().getId().equals(card.getId())).findFirst();

        if (magicCardInPile.isPresent()) {
            magicCardInPile.get().setAmount(magicCardInPile.get().getAmount() + 1);
            magicCardInPileRepository.save(magicCardInPile.get());
            appUserRepository.save(appUser.get());

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
            appUser.get().getPileOfCards().add(newMagicCardInPile);
            magicCardInPileRepository.save(newMagicCardInPile);
            appUserRepository.save(appUser.get());

            return newMagicCardInPile;
        }

    }

    public MagicCardInPile decreaseMagicCardInPileAmount(String username, String id) {

        Optional<MagicCardInPile> magicCard = findMagicCardInPileById(id);

        if (magicCard.isPresent()) {
            Optional<AppUser> appUser = appUserRepository.findById(username);

            Optional<MagicCardInPile> magicCardInPile = appUser.get().getPileOfCards().stream().filter((card) -> magicCard.get().getId().equals(card.getId())).findFirst();

            if (magicCardInPile.isPresent()) {
                magicCardInPile.get().setAmount(magicCardInPile.get().getAmount() - 1);
                magicCardInPileRepository.save(magicCardInPile.get());
                appUserRepository.save(appUser.get());

                return magicCardInPile.get();
            }
        }
        throw new IllegalArgumentException();
    }

    public void deleteMagicCardInPileById(String id) {
        magicCardInPileRepository.deleteById(id);
    }
}
