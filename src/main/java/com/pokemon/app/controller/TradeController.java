package com.pokemon.app.controller;

import com.pokemon.app.service.common.TradeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class TradeController {

    private TradeService tradeService;

    public TradeController(TradeService tradeService) {
        this.tradeService = tradeService;
    }

    @GetMapping("/trade")
    public String getTradePage() {

        return "trade";
    }

    @GetMapping("/trade-selling")
    public String getTradeSellingPage(Model model) {
        model.addAttribute("cards",tradeService.getAllTrainerCards());

        return "trade-selling";
    }
    @GetMapping("/trade-buying")
    public String getTradeBuyingPage() {

        return "trade-buying";
    }

   /* @PostMapping("/trade-selling/{id}")//path parameter
    public String sellCard(@PathVariable String id){

    }*/

}
