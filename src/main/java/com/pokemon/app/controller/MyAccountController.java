package com.pokemon.app.controller;

import com.pokemon.app.service.common.TrainerAccessService;
import com.pokemon.app.service.use_case.MyAccountService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MyAccountController {

    private TrainerAccessService trainerAccessService;
    private MyAccountService myAccountService;

    public MyAccountController( TrainerAccessService trainerAccessService, MyAccountService myAccountService) {

        this.trainerAccessService = trainerAccessService;
        this.myAccountService = myAccountService;
    }

    @GetMapping("/my-account")
    public String getMyAccountPage(Model model) {
        trainerAccessService.addMoneyToTrainer();
        model.addAttribute("model",myAccountService.createMyAccountViewModel());
        return "my-account";
    }
}
