package de.christianporsch.backend.controller;

import de.christianporsch.backend.model.*;
import de.christianporsch.backend.model.dto.MagicCardDto;
import de.christianporsch.backend.repository.MagicCardInPileRepository;
import de.christianporsch.backend.repository.MagicCardRepository;
import de.christianporsch.backend.security.repository.AppUserRepository;
import de.christianporsch.backend.security.model.AppUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertTrue;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CardPileControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private MagicCardRepository magicCardRepository;

    @Autowired
    private AppUserRepository appUserRepository;

    @Autowired
    private MagicCardInPileRepository magicCardInPileRepository;

    @Autowired
    private TestRestTemplate restTemplate;

    @BeforeEach
    public void clearRepositories(){
        magicCardRepository.deleteAll();
        magicCardInPileRepository.deleteAll();
        appUserRepository.deleteAll();
    }



    @Test
    @DisplayName("Method should return pile of cards from a user found by user id")
    public void findPileOfCardsByUser() {

        // Given

        appUserRepository.save(new AppUser("17", "christian", List.of(new MagicCardInPile("1", "Tarmogoyf", "some oracle text about tarmo",
                new CardImage("tarmoHighresImg"), "some set", 1, false))));
        magicCardInPileRepository.save(new MagicCardInPile("1", "Tarmogoyf", "some oracle text about tarmo",
                new CardImage("tarmoHighresImg"), "some set", 1, false));

        // When

        ResponseEntity<MagicCardInPile[]> response = restTemplate.getForEntity("http://localhost:" + port + "/api/cardsInPile/17", MagicCardInPile[].class);

        // Then

        assertThat(response.getStatusCode(), is(HttpStatus.OK));
        assertThat(response.getBody(), arrayContainingInAnyOrder(new MagicCardInPile("1", "Tarmogoyf", "some oracle text about tarmo",
                new CardImage("tarmoHighresImg"), "some set", 1, false)));
    }

    @Test
    @DisplayName("Method should find magic cards in pile by Id from Db")
    public void findMagicCardInPileById() {

        // Given

        magicCardInPileRepository.save(new MagicCardInPile("1", "Tarmogoyf", "some oracle text about tarmo",
                new CardImage("tarmoHighresImg"), "some set", 1, false));

        // When

        ResponseEntity<MagicCardInPile> response = restTemplate.getForEntity("http://localhost:" + port + "/api/cardsInPile/specificCardInPile/1", MagicCardInPile.class);

        // Then

        assertThat(response.getStatusCode(), is(HttpStatus.OK));
        assertThat(response.getBody(), is(new MagicCardInPile("1", "Tarmogoyf", "some oracle text about tarmo",
                new CardImage("tarmoHighresImg"), "some set", 1, false)));


    }

    @Test
    @DisplayName("Method should add whole magic card to pile of user")
    public void addMagicCardToPile() {

        // Given

        appUserRepository.save(new AppUser("60d2f120c76f8707f38e9a99", "christian", new ArrayList<>()));
        magicCardRepository.save(new MagicCard("1", "Tarmogoyf", "some oracle text about tarmo",
                new CardImage("tarmoHighresImg"), "some set", new Price(100, 25, 17)));
        MagicCardDto magicCardToAdd = new MagicCardDto("1");


        // When

        ResponseEntity<MagicCardInPile> response = restTemplate.postForEntity("http://localhost:" + port + "/api/cardsInPile", magicCardToAdd, MagicCardInPile.class);

        // Then

        assertThat(response.getStatusCode(), is(HttpStatus.OK));
        assertThat(response.getBody(), is(new MagicCardInPile("1", "Tarmogoyf", "some oracle text about tarmo",
                new CardImage("tarmoHighresImg"), "some set", 1, false)));

        Optional<MagicCardInPile> pileOfCards = magicCardInPileRepository.findMagicCardInPileById("1");
        assertThat(pileOfCards.get(), is(new MagicCardInPile("1", "Tarmogoyf", "some oracle text about tarmo",
                new CardImage("tarmoHighresImg"), "some set", 1, false)));

    }

    @Test
    @DisplayName("If magic card is already in collection method should increase amount by 1")
    public void addMagicCardToPileIncreaseAmount() {

        // Given

        appUserRepository.save(new AppUser("60d2f120c76f8707f38e9a99", "christian", List.of(
                new MagicCardInPile("1", "Tarmogoyf", "some oracle text about tarmo",
                        new CardImage("tarmoHighresImg"), "some set", 10, false))));
        magicCardRepository.save(new MagicCard("1", "Tarmogoyf", "some oracle text about tarmo",
                new CardImage("tarmoHighresImg"), "some set", new Price(100, 25, 17)));
        magicCardInPileRepository.save(new MagicCardInPile("1", "Tarmogoyf", "some oracle text about tarmo",
                new CardImage("tarmoHighresImg"), "some set", 10, false));

        MagicCardDto magicCardToAdd = new MagicCardDto("1");


        // When

        ResponseEntity<MagicCardInPile> response = restTemplate.postForEntity("http://localhost:" + port + "/api/cardsInPile", magicCardToAdd, MagicCardInPile.class);

        // Then

        assertThat(response.getStatusCode(), is(HttpStatus.OK));
        assertThat(response.getBody(), is(new MagicCardInPile("1", "Tarmogoyf", "some oracle text about tarmo",
                new CardImage("tarmoHighresImg"), "some set", 11, false)));

    }

    @Test
    @DisplayName("Method should decrease the amount of a magic card if it is present in users pile")
    public void decreaseMagicCardInPileAmount() {

        // Given

        appUserRepository.save(new AppUser("60d2f120c76f8707f38e9a99", "christian", List.of(
                new MagicCardInPile("1", "Tarmogoyf", "some oracle text about tarmo",
                        new CardImage("tarmoHighresImg"), "some set", 10, false))));
        magicCardRepository.save(new MagicCard("1", "Tarmogoyf", "some oracle text about tarmo",
                new CardImage("tarmoHighresImg"), "some set", new Price(100, 25, 17)));
        magicCardInPileRepository.save(new MagicCardInPile("1", "Tarmogoyf", "some oracle text about tarmo",
                new CardImage("tarmoHighresImg"), "some set", 10, false));

        // When

        restTemplate.put("http://localhost:" + port + "/api/cardsInPile/updateCardInPile/1", MagicCardInPile.class);

        // Then

        Optional<MagicCardInPile> pileOfCards = magicCardInPileRepository.findMagicCardInPileById("1");
        assertThat(pileOfCards.get(), is(new MagicCardInPile("1", "Tarmogoyf", "some oracle text about tarmo",
                new CardImage("tarmoHighresImg"), "some set", 9, false)));

    }

    @Test
    @DisplayName("Method should delete magic cards from pile from db")
    public void deleteMagicCardInPileById() {

        // Given

        appUserRepository.save(new AppUser("60d2f120c76f8707f38e9a99", "christian", List.of(
                new MagicCardInPile("1", "Tarmogoyf", "some oracle text about tarmo",
                        new CardImage("tarmoHighresImg"), "some set", 10, false))));
        magicCardInPileRepository.save(new MagicCardInPile("1", "Tarmogoyf", "some oracle text about tarmo",
                new CardImage("tarmoHighresImg"), "some set", 10, false));

        // When

        restTemplate.delete("http://localhost:" + port + "/api/cardsInPile/1");

        // Then

        Optional<MagicCardInPile> pileOfCards = magicCardInPileRepository.findMagicCardInPileById("1");
        assertTrue(pileOfCards.isEmpty());

    }
}