package com.pokemon.app.client;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;

@Component
public class PokemonTcgClient {


    private RestTemplate restTemplate;

    public PokemonTcgClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @PostConstruct
    public void downloadAllCards() {
        CardsClientResponse response = restTemplate.getForObject("https://api.pokemontcg.io/v1/cards", CardsClientResponse.class);
        System.out.println(response.getCards());

    }
}
