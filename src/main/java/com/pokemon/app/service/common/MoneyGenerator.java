package com.pokemon.app.service.common;

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
