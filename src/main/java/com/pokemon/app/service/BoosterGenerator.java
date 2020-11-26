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
        cardRepository.save(new Card("Charmander"));
        cardRepository.save(new Card("Slowpoke"));
        cardRepository.save(new Card("Bulbazaur"));
        cardRepository.save(new Card("Pikaczu"));
        cardRepository.save(new Card("Xybo"));
        cardRepository.save(new Card("Fluttershy"));
        cardRepository.save(new Card("RainbowDash"));
        cardRepository.save(new Card("Xenon"));
        cardRepository.save(new Card("Biggi"));
        cardRepository.save(new Card("Starte"));

    }

    //TODO pula kart np 10 szt. i losowanie odpowiedniej ilości

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
