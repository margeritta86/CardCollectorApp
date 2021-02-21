package com.pokemon.app.service.use_case;

import com.pokemon.app.dto.CardDto;
import com.pokemon.app.dto.MyAccountDto;
import com.pokemon.app.model.Trainer;
import com.pokemon.app.model.User;
import com.pokemon.app.service.common.LoginService;
import com.pokemon.app.service.common.TrainerAccessService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class MyAccountService {

    private LoginService loginService;
    private TrainerAccessService trainerAccess;

    public MyAccountService(LoginService loginService, TrainerAccessService trainerAccess) {
        this.loginService = loginService;
        this.trainerAccess = trainerAccess;
    }

    public MyAccountDto createMyAccountViewModel() {
        User user = loginService.getLoggedUser();
        Trainer trainer = user.getTrainer();
        return MyAccountDto.builder()
                .email(user.getEmail())
                .name(trainer.getName())
                .money(trainer.getMoney())
                .registerTime(user.getRegisterTime())
                .daysAfterRegistration(trainer.getDaysAfterCreation())
                .cardsCount(trainer.getCards().size())
                .build();
    }

    public Page<CardDto> findPaginatedForTrainersCards(Pageable pageable) {
        return trainerAccess.findPaginatedForTrainersCards(pageable);
    }
}
