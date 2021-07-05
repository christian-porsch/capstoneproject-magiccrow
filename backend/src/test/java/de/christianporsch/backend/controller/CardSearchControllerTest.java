package de.christianporsch.backend.controller;

import de.christianporsch.backend.controller.dto.LoginDataDto;
import de.christianporsch.backend.model.CardImage;
import de.christianporsch.backend.model.MagicCard;
import de.christianporsch.backend.model.Price;
import de.christianporsch.backend.repository.MagicCardRepository;
import de.christianporsch.backend.security.model.AppUser;
import de.christianporsch.backend.security.repository.AppUserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.TestPropertySource;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(properties = "jwt.secret=testSecret")
class CardSearchControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private MagicCardRepository magicCardRepository;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private AppUserRepository appUserRepository;

    @Autowired
    private PasswordEncoder encoder;

    @Test
    @DisplayName("Method should return magic cards from Db")
    public void findMagicCardsTest() {

        // Given

        magicCardRepository.save(new MagicCard("1","Tarmogoyf", "some oracle text about tarmo", new CardImage("tarmoHighresImg"), "some set", new Price(10, 20, 15)));
        magicCardRepository.save(new MagicCard("2","Jace, the Mindsculptor", "some oracle text about jace", new CardImage("jaceHighresImg"), "some set", new Price(1, 2, 5)));

        // When

        HttpHeaders headers = getHttpHeaderWithAuthToken();
        ResponseEntity<MagicCard[]> response = restTemplate.exchange("http://localhost:" + port + "/api/cards?cardName=Tarmogoyf",
                HttpMethod.GET,
                        new HttpEntity<>(headers),
                        MagicCard[].class);

        // Then

        assertThat(response.getStatusCode(), is(HttpStatus.OK));
        assertThat(response.getBody(),arrayContainingInAnyOrder(new MagicCard("1","Tarmogoyf", "some oracle text about tarmo", new CardImage("tarmoHighresImg"), "some set", new Price(10, 20, 15))));
    }

    @Test
    @DisplayName("Method should return magic cards from Db by Id")
    public void findMagicCardByIdTest(){

        // Given

        magicCardRepository.save(new MagicCard("1","Tarmogoyf", "some oracle text about tarmo", new CardImage("tarmoHighresImg"), "some set", new Price(10, 20, 15)));

        // When

        HttpHeaders headers = getHttpHeaderWithAuthToken();
        ResponseEntity<MagicCard> response = restTemplate.exchange("http://localhost:" + port + "/api/cards/1",
                HttpMethod.GET,
                new HttpEntity<>(headers),
                MagicCard.class);

        // Then

        assertThat(response.getStatusCode(), is(HttpStatus.OK));
        assertThat(response.getBody(), is(new MagicCard().builder()
                .id("1")
                .name("Tarmogoyf")
                .oracle_text("some oracle text about tarmo")
                .image_uris(new CardImage("tarmoHighresImg"))
                .set_name("some set")
                .prices(new Price(10, 20, 15))
                .build()));
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