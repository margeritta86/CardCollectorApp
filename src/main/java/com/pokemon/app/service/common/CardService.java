package com.pokemon.app.service.common;

import com.pokemon.app.model.Card;
import com.pokemon.app.model.Trainer;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class CardService {

    private static final int BOOSTER_COST = 100;

    private BoosterGenerator boosterGenerator;
    private TrainerAccessService trainerAccessService;


    public CardService(BoosterGenerator boosterGenerator, TrainerAccessService trainerAccessService) {
        this.boosterGenerator = boosterGenerator;
        this.trainerAccessService = trainerAccessService;
    }

    public List<Card> buyBooster() {
        trainerAccessService.subtractMoneyFromTrainer(BOOSTER_COST);
        List<Card> boosterCards = boosterGenerator.generateBooster();
        Trainer trainer = trainerAccessService.getLoggedTrainer().orElseThrow(() -> new CardServiceException("Brak trenera zalogowanego użytkownika"));
        trainer.addCards(boosterCards);
        trainerAccessService.save(trainer);
        return boosterCards;
    }
}
