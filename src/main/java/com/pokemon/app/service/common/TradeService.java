package com.pokemon.app.service.common;

import org.springframework.stereotype.Service;

@Service
public class TradeService {

    private TrainerAccessService trainerAccessService;

    public TradeService(TrainerAccessService trainerAccessService) {
        this.trainerAccessService = trainerAccessService;
    }


}
