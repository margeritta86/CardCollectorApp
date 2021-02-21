package com.pokemon.app.service.common;

import com.pokemon.app.dto.CardDto;
import com.pokemon.app.model.Card;
import com.pokemon.app.model.NotEnoughMoneyException;
import com.pokemon.app.model.Trainer;
import com.pokemon.app.repository.TrainerRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TrainerAccessService {

    private LoginService loginService;
    private TrainerRepository trainerRepository;


    public TrainerAccessService(LoginService loginService, TrainerRepository trainerRepository) {
        this.loginService = loginService;
        this.trainerRepository = trainerRepository;
    }

    public Trainer getLoggedTrainerOrThrow() {
        return getLoggedTrainer().orElseThrow(() -> new TrainerAccessServiceException("Brak zalogowanego użytkownika lub jego trenera"));
    }

    public Optional<Trainer> getLoggedTrainer() {
        return Optional.ofNullable(loginService.getLoggedUser().getTrainer());
    }

    public void save(Trainer trainer) {
        trainerRepository.save(trainer);
    }

    public void addMoneyToTrainer() {
        Trainer trainer = getLoggedTrainerOrThrow();
        trainer.addDailyMoney();
        trainerRepository.save(trainer);
    }

    public void subtractMoneyFromTrainer(int moneyToSubtract) {
        Trainer trainer = getLoggedTrainerOrThrow();

        if (trainer.getMoney() >= moneyToSubtract) {
            trainer.subtractMoney(moneyToSubtract);
            trainerRepository.save(trainer);
        } else {
            throw new NotEnoughMoneyException("Masz za mało pieniędzy!");
        }
    }

    public Page<CardDto> findPaginatedForTrainersCards(Pageable pageable) {
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;

        Map<Card, Integer> cardsMap = getLoggedTrainerOrThrow()
                .getCards();

        List<CardDto> cards= cardsMap.entrySet().stream()
                .map(entry -> CardDto.builder()
                        .id(entry.getKey().getId())
                        .imageUrl(entry.getKey().getImageUrl())
                        .amount(entry.getValue())
                        .build())
                .collect(Collectors.toList());

        List<CardDto> result;
        if (cards.size() < startItem) {
            result = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, cards.size());
            result = cards.subList(startItem, toIndex);
        }

        Page<CardDto> cardPage = new PageImpl<>(result, PageRequest.of(currentPage, pageSize), cards.size());

        return cardPage;
    }

}
