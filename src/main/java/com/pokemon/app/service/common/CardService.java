package com.pokemon.app.service.common;

import com.pokemon.app.model.Card;
import com.pokemon.app.model.Trainer;
import com.pokemon.app.repository.CardRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CardService {

    private static final int BOOSTER_COST = 100;


    private CardRepository cardRepository;
    private BoosterGenerator boosterGenerator;
    private TrainerAccessService trainerAccessService;


    public CardService(CardRepository cardRepository, BoosterGenerator boosterGenerator, TrainerAccessService trainerAccessService) {
        this.cardRepository = cardRepository;
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
