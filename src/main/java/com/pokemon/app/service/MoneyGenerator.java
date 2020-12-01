package com.pokemon.app.service;

import com.pokemon.app.model.Trainer;
import com.pokemon.app.repository.UserRepository;
import org.springframework.stereotype.Component;

@Component
public class MoneyGenerator {

    private LoginService loginService;


    public MoneyGenerator(LoginService loginService) {
        this.loginService = loginService;
    }

    /*public int generateUserMoney(){

       Trainer trainer = loginService.getLoggedUser().getTrainer();

    }*/


}
