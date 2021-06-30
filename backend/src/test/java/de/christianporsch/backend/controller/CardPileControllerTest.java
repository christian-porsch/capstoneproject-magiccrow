package de.christianporsch.backend.controller;

import de.christianporsch.backend.model.CardImage;
import de.christianporsch.backend.model.MagicCardInPile;
import de.christianporsch.backend.model.User;
import de.christianporsch.backend.repository.MagicCardInPileRepository;
import de.christianporsch.backend.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.arrayContainingInAnyOrder;
import static org.hamcrest.Matchers.is;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CardPileControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MagicCardInPileRepository magicCardInPileRepository;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    @DisplayName("Method should return pile of cards from a user found by user id")
    public void findPileOfCardsByUser() {

        // Given

        userRepository.save(new User("17", "christian", List.of(new MagicCardInPile("1", "Tarmogoyf", "some oracle text about tarmo",
                new CardImage("tarmoHighresImg"), "some set", 1, false))));
        magicCardInPileRepository.save(new MagicCardInPile("1", "Tarmogoyf", "some oracle text about tarmo",
                new CardImage("tarmoHighresImg"), "some set", 1, false));

        // When

        ResponseEntity<MagicCardInPile[]> response = restTemplate.getForEntity("http://localhost:" + port + "/api/cardsInPile/17", MagicCardInPile[].class);

        // Then

        assertThat(response.getStatusCode(), is(HttpStatus.OK));
        assertThat(response.getBody(),arrayContainingInAnyOrder(new MagicCardInPile("1", "Tarmogoyf", "some oracle text about tarmo",
                new CardImage("tarmoHighresImg"), "some set", 1, false)));
    }

    @Test
    void findMagicCardInPileById() {
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