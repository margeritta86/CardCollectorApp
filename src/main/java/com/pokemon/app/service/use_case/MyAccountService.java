package com.pokemon.app.service.use_case;

import com.pokemon.app.dto.MyAccountDto;
import com.pokemon.app.model.Trainer;
import com.pokemon.app.model.User;
import com.pokemon.app.service.common.LoginService;
import org.springframework.stereotype.Service;

@Service
public class MyAccountService {

    private LoginService loginService;

    public MyAccountService(LoginService loginService) {
        this.loginService = loginService;
    }

    public MyAccountDto createMyAccountViewModel(){
        User user = loginService.getLoggedUserOrThrow();
        Trainer trainer  = user.getTrainer();
        return MyAccountDto.builder()
                .email(user.getEmail())
                .name(trainer.getName())
                .money(trainer.getMoney())
                .registerTime(user.getRegisterTime())
                .daysAfterRegistration(trainer.getDaysAfterCreation())
                .cardsCount(trainer.getCards().size())
                .build();

    }


}
