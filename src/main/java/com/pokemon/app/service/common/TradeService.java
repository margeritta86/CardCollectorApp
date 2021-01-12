package com.pokemon.app.service.common;

import com.pokemon.app.model.Card;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class TradeService {

    private TrainerAccessService trainerAccessService;

    public TradeService(TrainerAccessService trainerAccessService) {
        this.trainerAccessService = trainerAccessService;
    }

    public Map<Card,Integer> getAllTrainerCards(){
        return trainerAccessService.getLoggedTrainerOrThrow().getCards();
    }

}
