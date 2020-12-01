package com.pokemon.app.controller;

import com.pokemon.app.service.LoginService;
import com.pokemon.app.service.TrainerAccessService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MyAccountController {

    private LoginService loginService;
    private TrainerAccessService trainerAccessService;

    public MyAccountController(LoginService loginService,TrainerAccessService trainerAccessService) {
        this.loginService = loginService;
        this.trainerAccessService = trainerAccessService;
    }

    @GetMapping("/my-account")
    public String getMyAccountPage(Model model) {
        trainerAccessService.addMoneyToTrainer();
        model.addAttribute("trainer",loginService.getLoggedUser().getTrainer());
        model.addAttribute("user",loginService.getLoggedUser());
        return "my-account";
    }
}
