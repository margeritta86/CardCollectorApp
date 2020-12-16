package com.pokemon.app.client;

import com.pokemon.app.repository.CardRepository;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.util.Objects;

@Component
public class PokemonTcgClient {

    private static final String URL = "https://api.pokemontcg.io/v1/cards";

    private RestTemplate restTemplate;
    private CardRepository cardRepository;

    public PokemonTcgClient(RestTemplate restTemplate, CardRepository cardRepository) {
        this.restTemplate = restTemplate;
        this.cardRepository = cardRepository;
    }

    @PostConstruct
    public void downloadAllCards() {
        if (cardRepository.count() != 0) {
            return;
        }
        HttpHeaders httpHeaders = restTemplate.headForHeaders(URL);
        int howManyPokemons = Integer.parseInt(Objects.requireNonNull(httpHeaders.getFirst("Total-Count")));
        int howManyPAges = howManyPokemons / 1000 + 1;

        Thread thread = new Thread(() -> {
            for (int i = 1; i <= howManyPAges; i++) {
                CardsClientResponse response = restTemplate.getForObject(URL + "?page=" + i + "&pageSize=1000", CardsClientResponse.class);
                cardRepository.saveAll(response.getCards());
            }
        });
        thread.start();

    }
}

