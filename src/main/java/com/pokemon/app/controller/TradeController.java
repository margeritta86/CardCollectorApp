package com.pokemon.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class TradeController {

    @GetMapping("/trade")
    public String getTradePage() {

        return "trade";
    }

    @GetMapping("/trade-selling")
    public String getTradeSellingPage() {

        return "trade-selling";
    }
    @GetMapping("/trade-buying")
    public String getTradeBuyingPage() {

        return "trade-buying";
    }

}
