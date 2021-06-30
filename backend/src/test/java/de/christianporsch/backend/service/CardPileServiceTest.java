package de.christianporsch.backend.service;

import de.christianporsch.backend.model.*;
import de.christianporsch.backend.model.dto.MagicCardDto;
import de.christianporsch.backend.repository.MagicCardInPileRepository;
import de.christianporsch.backend.repository.MagicCardRepository;
import de.christianporsch.backend.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;


class CardPileServiceTest {

    private final MagicCardRepository magicCardRepository = mock(MagicCardRepository.class);
    private final MagicCardInPileRepository magicCardInPileRepository = mock(MagicCardInPileRepository.class);
    private final UserRepository userRepository = mock(UserRepository.class);
    private final CardSearchService cardSearchService = new CardSearchService(magicCardRepository);
    private final CardPileService cardPileService = new CardPileService(cardSearchService, userRepository, magicCardInPileRepository);

    @Test
    @DisplayName("Method should return pile of cards from a user found by user id")
    public void findPileOfCardsByUser() {

        // Given

        User user = new User("17", "christian", List.of(
                new MagicCardInPile("1", "Tarmogoyf", "some oracle text about tarmo",
                        new CardImage("tarmoHighresImg"), "some set", 1, false)));
        when(userRepository.findUserById("17")).thenReturn(user);

        // When

        List<MagicCardInPile> pileOfUserToFind = cardPileService.findPileOfCardsByUser("17");

        // Then

        assertThat(pileOfUserToFind, is(List.of(
                new MagicCardInPile("1", "Tarmogoyf", "some oracle text about tarmo",
                        new CardImage("tarmoHighresImg"), "some set", 1, false))));

    }

    @Test
    @DisplayName("Method should find magic cards in pile by Id from Db")
    public void findMagicCardInPileById() {

        // Given

        MagicCardInPile magicCardInPile = MagicCardInPile.builder()
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

        assertThat(magicCardInPileToFind.get(), is(MagicCardInPile.builder()
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
    @DisplayName("Method should add whole magic card to pile of user")
    public void addMagicCardToPile() {

        // Given

        User user = new User("17", "christian", new ArrayList<>(List.of(
                new MagicCardInPile("1", "Tarmogoyf", "some oracle text about tarmo",
                        new CardImage("tarmoHighresImg"), "some set", 1, false))));

        MagicCardDto newMagicCardToAdd = new MagicCardDto("2");

        when(userRepository.findUserById("60d2f120c76f8707f38e9a99")).thenReturn(user);
        when(magicCardRepository.findMagicCardById("2")).thenReturn(Optional.of(new MagicCard("2", "Mox", "some oracle text about mox",
                new CardImage("moxHighresImg"), "some set", new Price(10, 20, 15))));

        // When

        MagicCardInPile magicCardInPileToAdd = cardPileService.addMagicCardToPile(newMagicCardToAdd);

        // Then

        assertThat(magicCardInPileToAdd, is(new MagicCardInPile("2", "Mox", "some oracle text about mox",
                new CardImage("moxHighresImg"), "some set", 1, false)));
    }

    @Test
    @DisplayName("If magic card is already in collection method should increase amount by 1")
    public void addMagicCardToPileIncreaseAmount() {

        // Given

        // When

        // Then
    }

    @Test
    @DisplayName("Method should decrease the amount of a magic card if it is present in users pile")
    public void decreaseMagicCardInPileAmount() {

        // Given

        User user = new User("17", "christian", List.of(
                new MagicCardInPile("1", "Tarmogoyf", "some oracle text about tarmo",
                        new CardImage("tarmoHighresImg"), "some set", 10, false)));
        when(userRepository.findUserById("60d2f120c76f8707f38e9a99")).thenReturn(user);
        when(magicCardInPileRepository.findMagicCardInPileById("1")).thenReturn(Optional.of(new MagicCardInPile("1", "Tarmogoyf", "some oracle text about tarmo",
                new CardImage("tarmoHighresImg"), "some set", 10, false)));

        // When

        MagicCardInPile magicCardInPileToDecrease = cardPileService.decreaseMagicCardInPileAmount("1");

        // Then

        assertThat(magicCardInPileToDecrease, is(new MagicCardInPile("1", "Tarmogoyf", "some oracle text about tarmo",
                new CardImage("tarmoHighresImg"), "some set", 9, false)));

    }

    @Test
    @DisplayName("Method should delete magic cards from pile from db")
    public void deleteMagicCardInPileById() {

        // When

        cardPileService.deleteMagicCardInPileById("12");

        // Then

        verify(magicCardInPileRepository).deleteById("12");

    }

}