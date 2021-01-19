package com.pokemon.app.service.common;

import com.pokemon.app.model.Card;
import com.pokemon.app.model.Trade;
import com.pokemon.app.model.Trainer;
import com.pokemon.app.repository.CardRepository;
import com.pokemon.app.repository.TradeRepository;
import com.pokemon.app.request.SellRequest;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class TradeService {

    private TrainerAccessService trainerAccessService;
    private CardRepository cardRepository;
    private TradeRepository tradeRepository;

    public TradeService(TrainerAccessService trainerAccessService, CardRepository cardRepository, TradeRepository tradeRepository) {
        this.trainerAccessService = trainerAccessService;
        this.cardRepository = cardRepository;
        this.tradeRepository = tradeRepository;
    }

    public Map<Card, Integer> getAllTrainerCards() {
        return trainerAccessService.getLoggedTrainerOrThrow().getCards();
    }

    public void sellCards(SellRequest sellRequest) {

        Trainer trainer = trainerAccessService.getLoggedTrainerOrThrow();

        Card card = cardRepository.findById(sellRequest.getCardId()).orElseThrow(() -> new TradeServiceException("Nie odnaleziono karty"));

        if (sellRequest.getHowMany() > trainer.howManyOf(card)) {
            throw new TradeServiceException("Nie masz tylu kart do sprzedaży");
        }
        trainer.removeCard(card, sellRequest.getHowMany());
        trainerAccessService.save(trainer);
        Trade trade = new Trade(trainer, card, sellRequest.getHowMuch(), sellRequest.getHowMany());
        tradeRepository.save(trade);


    }

}
