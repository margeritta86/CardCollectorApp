package com.pokemon.app.controller;


import com.pokemon.app.model.Card;
import com.pokemon.app.model.NotEnoughMoneyException;
import com.pokemon.app.service.common.CardService;
import com.pokemon.app.service.common.TrainerAccessService;
import com.pokemon.app.service.use_case.MyAccountService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class BoosterController {

    private CardService cardService;
    private MyAccountService myAccountService;
    private TrainerAccessService trainerAccessService;

    public BoosterController(CardService cardService, MyAccountService myAccountService, TrainerAccessService trainerAccessService) {
        this.cardService = cardService;

        this.myAccountService = myAccountService;
        this.trainerAccessService = trainerAccessService;
    }

    @GetMapping("/booster")
    public String getBoosterPage(Model model) {
        model.addAttribute("model", myAccountService.createMyAccountViewModel());
        return "booster";
    }

    @PostMapping("/booster")
    public String buyBooster(Model model) {

        model.addAttribute("message", "Udało się pomyślnie kupić booster!");
        try {
            List<Card> boughtBooster = cardService.buyBooster();
            model.addAttribute("boughtBooster", boughtBooster);
            trainerAccessService.subtractMoneyFromTrainer(100);
        } catch (Exception e) {
            model.addAttribute("message", e.getMessage());
        } finally {
            model.addAttribute("model", myAccountService.createMyAccountViewModel());
        }
        return "booster";
    }
}
