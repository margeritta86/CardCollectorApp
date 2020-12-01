package com.pokemon.app.service;

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
        int count = 0;
        Random random = new Random();
        int allCardsCount = (int) cardRepository.count();
        //TODO zmienić na fora
        while (count < 5) {
            Card card = cardRepository.findById(random.nextInt(allCardsCount - 1) + 1).get();
            if (!booster.contains(card)) {
                booster.add(card);
                count++;
            }
        }

        return booster;
    }


}
