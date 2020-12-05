package com.pokemon.app.service.common;

import com.pokemon.app.model.Card;
import com.pokemon.app.repository.CardRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
public class BoosterGenerator {

    private CardRepository cardRepository;

    public BoosterGenerator(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    public List<Card> generateBooster() {
        List<Card> booster = new ArrayList<>();
        Random random = new Random();
        List<Card> cards = cardRepository.findAll();
        for (int i = 0; i < 5; i++) {
            Card card = cards.get(random.nextInt(cards.size()-1));
            if (!booster.contains(card)) {
                booster.add(card);
            }
        }
        return booster;
    }

}
