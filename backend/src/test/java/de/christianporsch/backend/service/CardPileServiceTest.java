package de.christianporsch.backend.service;

import de.christianporsch.backend.model.CardImage;
import de.christianporsch.backend.model.MagicCardInPile;
import de.christianporsch.backend.repository.MagicCardInPileRepository;
import de.christianporsch.backend.repository.MagicCardRepository;
import de.christianporsch.backend.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


class CardPileServiceTest {

    private final MagicCardRepository magicCardRepository = mock(MagicCardRepository.class);
    private final MagicCardInPileRepository magicCardInPileRepository = mock(MagicCardInPileRepository.class);
    private final UserRepository userRepository = mock(UserRepository.class);
    private final CardSearchService cardSearchService = new CardSearchService(magicCardRepository);
    private final CardPileService cardPileService = new CardPileService(cardSearchService, userRepository, magicCardInPileRepository);

    @Test
    void findPileOfCardsByUser() {
    }

    @Test
    @DisplayName("Method should find magic cards in pile by Id from Db")
    void findMagicCardInPileById() {

        // Given

        MagicCardInPile magicCardInPile = new MagicCardInPile().builder()
                .id("1")
                .name("Tarmogoyf")
                .oracle_text("some oracle text about tarmo")
                .image_uris(new CardImage("tarmoHighresImg"))
                .set_name("some set")
                .amount(1)
                .priceAlert(false)
                .build();
        when(magicCardInPileRepository.findMagicCardInPileById("1")).thenReturn(Optional.of(magicCardInPile));

        // When

        Optional<MagicCardInPile> magicCardInPileToFind = cardPileService.findMagicCardInPileById("1");

        // Then

        assertThat(magicCardInPileToFind.get(), is(new MagicCardInPile().builder()
                .id("1")
                .name("Tarmogoyf")
                .oracle_text("some oracle text about tarmo")
                .image_uris(new CardImage("tarmoHighresImg"))
                .set_name("some set")
                .amount(1)
                .priceAlert(false)
                .build()));

    }

    @Test
    void addMagicCardToPile() {
    }

    @Test
    void decreaseMagicCardInPileAmount() {
    }

    @Test
    void deleteMagicCardInPileById() {
    }

}