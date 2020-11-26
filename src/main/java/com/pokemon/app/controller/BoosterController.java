package com.pokemon.app.controller;


import com.pokemon.app.model.Card;
import com.pokemon.app.service.BoosterGenerator;
import com.pokemon.app.service.CardService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class BoosterController {

    private CardService cardService;


    public BoosterController(CardService cardService) {
        this.cardService = cardService;

    }

    @GetMapping("/booster")
    public String getBoosterPage() {

        return "booster";
    }

    @PostMapping("/booster")
    public String buyBooster(Model model) {
        List<Card> boughtBooster = cardService.buyBooster();
        model.addAttribute("boughtBooster",boughtBooster);
        return "booster";
    }
}
