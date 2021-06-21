package de.christianporsch.backend.controller;

import de.christianporsch.backend.model.CardImage;
import de.christianporsch.backend.model.MagicCard;
import de.christianporsch.backend.model.Price;
import de.christianporsch.backend.repository.MagicCardRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CardSearchControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private MagicCardRepository magicCardRepository;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    @DisplayName("Method should return magic cards from Db")
    public void findMagicCardsTest() {

        // Given

        magicCardRepository.save(new MagicCard("1","Tarmogoyf", "some oracle text about tarmo", new CardImage("tarmoHighresImg"), new Price(10, 20, 15)));
        magicCardRepository.save(new MagicCard("2","Jace, the Mindsculptor", "some oracle text about jace", new CardImage("jaceHighresImg"), new Price(1, 2, 5)));

        // When

        ResponseEntity<MagicCard[]> response = restTemplate.getForEntity("http://localhost:" + port + "/api/cards?cardName=Tarmogoyf", MagicCard[].class);

        // Then

        assertThat(response.getStatusCode(), is(HttpStatus.OK));
        assertThat(response.getBody(),arrayContainingInAnyOrder(new MagicCard("1","Tarmogoyf", "some oracle text about tarmo", new CardImage("tarmoHighresImg"), new Price(10, 20, 15))));
    }

}