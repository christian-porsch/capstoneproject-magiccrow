package de.christianporsch.backend.service;

import de.christianporsch.backend.model.CardImage;
import de.christianporsch.backend.model.MagicCard;
import de.christianporsch.backend.model.Price;
import de.christianporsch.backend.repository.MagicCardRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CardSearchServiceTest {

    private final MagicCardRepository magicCardRepository = mock(MagicCardRepository.class);
    private final CardSearchService cardSearchService = new CardSearchService(magicCardRepository);

    @Test
    @DisplayName("Method should return magic cards from Db")
    public void findMagicCardsTest() {

        // Given

        List<MagicCard> magicCard = List.of(new MagicCard("1", "Tarmogoyf", "some oracle text about tarmo", new CardImage("tarmoHighresImg"), "some set", new Price(10, 20, 15)));
        when(magicCardRepository.filterCardsByCardName("Tarmogoyf")).thenReturn(magicCard);

        // When

        List<MagicCard> magicCards = cardSearchService.findMagicCards("Tarmogoyf");

        // Then

        assertThat(magicCards, containsInAnyOrder(new MagicCard("1", "Tarmogoyf", "some oracle text about tarmo", new CardImage("tarmoHighresImg"), "some set", new Price(10, 20, 15))));

    }

    @Test
    @DisplayName("Method should return magic cards from Db by Id")
    public void findMagicCardByIdTest() {

        // Given

        MagicCard magicCard = new MagicCard("1", "Tarmogoyf", "some oracle text about tarmo", new CardImage("tarmoHighresImg"), "some set", new Price(10, 20, 15));
        when(magicCardRepository.findMagicCardById("1")).thenReturn(Optional.of(magicCard));

        // When

        Optional<MagicCard> magicCardToFind = cardSearchService.findMagicCardById("1");

        // Then

        assertThat(magicCardToFind.get(), is(new MagicCard().builder()
                .id("1")
                .name("Tarmogoyf")
                .oracle_text("some oracle text about tarmo")
                .image_uris(new CardImage("tarmoHighresImg"))
                .set_name("some set")
                .prices(new Price(10, 20, 15))
                .build()));
    }

}