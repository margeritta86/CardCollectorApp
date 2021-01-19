package com.pokemon.app.controller;

import com.pokemon.app.request.SellRequest;
import com.pokemon.app.service.common.TradeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;


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
        SellRequest sellRequest = new SellRequest();
        model.addAttribute("cards", tradeService.getAllTrainerCards());
        model.addAttribute("request", sellRequest);
        return "trade-selling";
    }

    @GetMapping("/trade-buying")
    public String getTradeBuyingPage() {

        return "trade-buying";
    }

    @PostMapping("/trade-selling/{id}")//path parameter
    public String sellCard(@Valid @ModelAttribute("request") SellRequest sellRequest, @PathVariable String id, BindingResult bindingResult, Model model) {

        sellRequest.setCardId(id);//bez dodania tej linijki kodu, wcześniej wyświetlał sie cardId jako null
        System.out.println("Sell Request:" + sellRequest);
        tradeService.sellCards(sellRequest);
        System.out.println("Id: " + id);
        return "redirect:/trade";
    }

}
