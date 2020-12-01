package com.pokemon.app.service;

import com.pokemon.app.model.Card;
import com.pokemon.app.model.Trainer;
import com.pokemon.app.repository.CardRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CardService {

    private CardRepository cardRepository;
    private BoosterGenerator boosterGenerator;
    private TrainerAccessService trainerAccessService;


    public CardService(CardRepository cardRepository, BoosterGenerator boosterGenerator, TrainerAccessService trainerAccessService) {
        this.cardRepository = cardRepository;
        this.boosterGenerator = boosterGenerator;
        this.trainerAccessService = trainerAccessService;
    }

    public List<Card> buyBooster() {
        List<Card> boosterCards = boosterGenerator.generateBooster();
        Trainer trainer = trainerAccessService.getLoggedTrainer().orElseThrow(() -> new CardServiceException("Brak trenera zalogowanego użytkownika"));
        trainer.addCards(boosterCards);
        trainerAccessService.save(trainer);
        return boosterCards;

    }
}