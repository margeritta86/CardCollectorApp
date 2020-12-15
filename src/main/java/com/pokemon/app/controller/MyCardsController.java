package com.pokemon.app.controller;

import com.pokemon.app.model.Card;
import com.pokemon.app.service.common.TrainerAccessService;
import com.pokemon.app.service.use_case.MyAccountService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class MyCardsController {


    private MyAccountService myAccountService;
    private TrainerAccessService trainerAccessService;

    public MyCardsController(MyAccountService myAccountService, TrainerAccessService trainerAccessService) {

        this.myAccountService = myAccountService;
        this.trainerAccessService = trainerAccessService;
    }

    @GetMapping("/my-cards")
    public String getMyCardsPage(Model model) {
        model.addAttribute("model",myAccountService.createMyAccountViewModel());

        try {
            List<Card> cards = trainerAccessService.getLoggedTrainer().get().getCards();
            model.addAttribute("cards", cards);
            model.addAttribute("howMany",cards.size());

        } catch (Exception e) {
            model.addAttribute("message", e.getMessage());
        }
        return "my-cards";
    }
}
