package com.pokemon.app.controller;


import com.pokemon.app.model.Card;
import com.pokemon.app.service.common.CardService;
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

    public BoosterController(CardService cardService, MyAccountService myAccountService) {
        this.cardService = cardService;
        this.myAccountService = myAccountService;

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

        } catch (Exception e) {
            model.addAttribute("message", e.getMessage());
        } finally {
            model.addAttribute("model", myAccountService.createMyAccountViewModel());
        }
        return "booster";
    }
}
