package de.christianporsch.backend.controller;

import de.christianporsch.backend.controller.dto.LoginDataDto;
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
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.TestPropertySource;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertTrue;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(properties = "jwt.secret=testSecret")
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

    @Autowired
    private PasswordEncoder encoder;

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

        HttpHeaders headers = getHttpHeaderWithAuthToken();
        magicCardInPileRepository.save(new MagicCardInPile("1", "Tarmogoyf", "some oracle text about tarmo",
                new CardImage("tarmoHighresImg"), "some set", 1, false));
        appUserRepository.save(new AppUser("test_username", "test_password", List.of(new MagicCardInPile("1", "Tarmogoyf", "some oracle text about tarmo",
                new CardImage("tarmoHighresImg"), "some set", 1, false))));

        // When

        ResponseEntity<MagicCardInPile[]> response = restTemplate.exchange("http://localhost:" + port + "/api/cardsInPile/test_username",
                HttpMethod.GET,
                new HttpEntity<>(headers),
                MagicCardInPile[].class);

        // Then

        assertThat(response.getStatusCode(), is(HttpStatus.OK));
        assertThat(response.getBody(), arrayContainingInAnyOrder(new MagicCardInPile("1", "Tarmogoyf", "some oracle text about tarmo",
                new CardImage("tarmoHighresImg"), "some set", 1, false)));
    }

    @Test
    @DisplayName("Method should find magic cards in pile by Id from Db")
    public void findMagicCardInPileById() {

        // Given

        HttpHeaders headers = getHttpHeaderWithAuthToken();
        magicCardInPileRepository.save(new MagicCardInPile("1", "Tarmogoyf", "some oracle text about tarmo",
                new CardImage("tarmoHighresImg"), "some set", 1, false));

        // When

        ResponseEntity<MagicCardInPile> response = restTemplate.exchange("http://localhost:" + port + "/api/cardsInPile/specificCardInPile/1",
                HttpMethod.GET,
                new HttpEntity<>(headers),
                MagicCardInPile.class);

        // Then

        assertThat(response.getStatusCode(), is(HttpStatus.OK));
        assertThat(response.getBody(), is(new MagicCardInPile("1", "Tarmogoyf", "some oracle text about tarmo",
                new CardImage("tarmoHighresImg"), "some set", 1, false)));


    }

    @Test
    @DisplayName("Method should add whole magic card to pile of user")
    public void addMagicCardToPile() {

        // Given

        HttpHeaders headers = getHttpHeaderWithAuthToken();
        appUserRepository.save(new AppUser("test_user", "test_password", new ArrayList<>()));
        magicCardRepository.save(new MagicCard("1", "Tarmogoyf", "some oracle text about tarmo",
                new CardImage("tarmoHighresImg"), "some set", new Price(100, 25, 17)));
        MagicCardDto magicCardToAdd = new MagicCardDto("1");


        // When

        ResponseEntity<MagicCardInPile> response = restTemplate.exchange("http://localhost:" + port + "/api/cardsInPile",
                HttpMethod.POST,
                new HttpEntity<>(magicCardToAdd, headers),
                 MagicCardInPile.class);

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

        HttpHeaders headers = getHttpHeaderWithAuthToken();
        appUserRepository.save(new AppUser("test_username", "test_password", List.of(
                new MagicCardInPile("1", "Tarmogoyf", "some oracle text about tarmo",
                        new CardImage("tarmoHighresImg"), "some set", 10, false))));
        magicCardInPileRepository.save(new MagicCardInPile("1", "Tarmogoyf", "some oracle text about tarmo",
                new CardImage("tarmoHighresImg"), "some set", 10, false));
        magicCardRepository.save(new MagicCard("1", "Tarmogoyf", "some oracle text about tarmo",
                new CardImage("tarmoHighresImg"), "some set", new Price(100, 25, 17)));


        MagicCardDto magicCardToAdd = new MagicCardDto("1");


        // When

        ResponseEntity<MagicCardInPile> response = restTemplate.exchange("http://localhost:" + port + "/api/cardsInPile",
                HttpMethod.POST,
                new HttpEntity<>(magicCardToAdd, headers),
                 MagicCardInPile.class);

        // Then

        assertThat(response.getStatusCode(), is(HttpStatus.OK));
        assertThat(response.getBody(), is(new MagicCardInPile("1", "Tarmogoyf", "some oracle text about tarmo",
                new CardImage("tarmoHighresImg"), "some set", 11, false)));

    }

    @Test
    @DisplayName("Method should decrease the amount of a magic card if it is present in users pile")
    public void decreaseMagicCardInPileAmount() {

        // Given
        HttpHeaders headers = getHttpHeaderWithAuthToken();
        appUserRepository.save(new AppUser("test_username", "test_password", List.of(
                new MagicCardInPile("1", "Tarmogoyf", "some oracle text about tarmo",
                        new CardImage("tarmoHighresImg"), "some set", 10, false))));
        magicCardInPileRepository.save(new MagicCardInPile("1", "Tarmogoyf", "some oracle text about tarmo",
                new CardImage("tarmoHighresImg"), "some set", 10, false));
        magicCardRepository.save(new MagicCard("1", "Tarmogoyf", "some oracle text about tarmo",
                new CardImage("tarmoHighresImg"), "some set", new Price(100, 25, 17)));


        // When

        restTemplate.exchange("http://localhost:" + port + "/api/cardsInPile/updateCardInPile/1",
                HttpMethod.PUT,
                new HttpEntity<>(headers),
                MagicCardInPile.class);

        // Then

        Optional<MagicCardInPile> pileOfCards = magicCardInPileRepository.findMagicCardInPileById("1");
        assertThat(pileOfCards.get(), is(new MagicCardInPile("1", "Tarmogoyf", "some oracle text about tarmo",
                new CardImage("tarmoHighresImg"), "some set", 9, false)));

    }

    @Test
    @DisplayName("Method should delete magic cards from pile from db")
    public void deleteMagicCardInPileById() {

        // Given

        HttpHeaders headers = getHttpHeaderWithAuthToken();
        appUserRepository.save(new AppUser("test_user", "test_password", List.of(
                new MagicCardInPile("1", "Tarmogoyf", "some oracle text about tarmo",
                        new CardImage("tarmoHighresImg"), "some set", 10, false))));
        magicCardInPileRepository.save(new MagicCardInPile("1", "Tarmogoyf", "some oracle text about tarmo",
                new CardImage("tarmoHighresImg"), "some set", 10, false));

        // When

        restTemplate.exchange("http://localhost:" + port + "/api/cardsInPile/1",
                HttpMethod.DELETE,
                new HttpEntity<>(headers),
                MagicCardInPile.class);

        // Then

        Optional<MagicCardInPile> pileOfCards = magicCardInPileRepository.findMagicCardInPileById("1");
        assertTrue(pileOfCards.isEmpty());

    }

    private HttpHeaders getHttpHeaderWithAuthToken() {
        appUserRepository.save(AppUser.builder().username("test_username").password(encoder.encode("test_password")).build());
        LoginDataDto loginData = new LoginDataDto("test_username", "test_password");
        ResponseEntity<String> tokenResponse = restTemplate.postForEntity("http://localhost:" + port + "/auth/login", loginData, String.class);
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(tokenResponse.getBody());
        return headers;
    }
}