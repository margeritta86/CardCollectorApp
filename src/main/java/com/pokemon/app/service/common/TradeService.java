package com.pokemon.app.service.common;

import com.pokemon.app.dto.TradeDto;
import com.pokemon.app.dto.TradePageDto;
import com.pokemon.app.model.Card;
import com.pokemon.app.model.Trade;
import com.pokemon.app.model.Trainer;
import com.pokemon.app.repository.CardRepository;
import com.pokemon.app.repository.TradeRepository;
import com.pokemon.app.request.SellRequest;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

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

    public void buyCards(int tradeId) {
        Trainer buyer = trainerAccessService.getLoggedTrainerOrThrow();
        Trade trade = tradeRepository.getOne(tradeId);
        Trainer seller = trade.getTrainer();
        Card card = trade.getCard();
        if (buyer.equals(seller)) {
            seller = buyer;
        } else {
            trainerAccessService.subtractMoneyFromTrainer(trade.getCardPrice());
            seller.addMoney(trade.getCardPrice());
        }

        addCardToBuyerAndDeleteTradeFromRepository(buyer, trade, card);
    }

    public void addCardToBuyerAndDeleteTradeFromRepository(Trainer buyer, Trade trade, Card card) {
        buyer.addCard(card, trade.getCardsAmount());
        tradeRepository.delete(trade);
        trainerAccessService.save(buyer);
    }

    public TradePageDto getPageDto(Pageable pageable) {
        return TradePageDto.builder()
                .trades(preparePaginatedTrades(pageable))
                .ownedMoney(trainerAccessService
                        .getLoggedTrainerOrThrow()
                        .getMoney())
                .build();
    }

    private PageImpl<TradeDto> preparePaginatedTrades(Pageable pageable) {
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        List<TradeDto> allTrades = getAllTradeCards();
        int startItem = currentPage * pageSize;

        List<TradeDto> trades;

        if (allTrades.size() < startItem) {
            trades = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, allTrades.size());
            trades = allTrades.subList(startItem, toIndex);
        }

        return new PageImpl<>(trades, PageRequest.of(currentPage, pageSize), allTrades.size());
    }

    public List<TradeDto> getAllTradeCards() {

        return tradeRepository.findAll().stream()
                .map(trade -> TradeDto.builder()
                        .tradeId(trade.getId())
                        .cardUrl(trade.getCard().getImageUrl())
                        .howManyCards(trade.getCardsAmount())
                        .price(trade.getCardPrice())
                        .trainer(trade.getTrainer().getName())
                        .yourTrade(trade.getTrainer().equals(trainerAccessService.getLoggedTrainerOrThrow()))
                        .build())
                .collect(Collectors.toList());
    }


}
