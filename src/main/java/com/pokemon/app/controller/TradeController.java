package com.pokemon.app.controller;

import com.pokemon.app.dto.TradeDto;
import com.pokemon.app.dto.TradePageDto;
import com.pokemon.app.model.Card;
import com.pokemon.app.request.SellRequest;
import com.pokemon.app.service.common.TradeService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


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
    public String getTradeBuyingPage(
                                     @RequestParam("page") Optional<Integer> page,
                                     @RequestParam("size") Optional<Integer> size,Model model) {

        int currentPage = page.orElse(1);
        int pageSize = size.orElse(6);


        TradePageDto pageData = tradeService.getPageDto(PageRequest.of(currentPage - 1, pageSize));
        PageImpl<TradeDto> trades = pageData.getTrades();
        model.addAttribute("pageData", pageData);

        int totalPages = trades.getTotalPages();

        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }

        return "trade-buying";
    }

    @PostMapping("/trade-selling/{id}")//path parameter
    public String sellCard(@Valid @ModelAttribute("request") SellRequest sellRequest, @PathVariable String id, BindingResult bindingResult, Model model) {
        sellRequest.setCardId(id);
        tradeService.sellCards(sellRequest);
        return "redirect:/trade";
    }
//NEW
    @PostMapping("/trade-buying/{tradeId}")//path parameter
    public String buyCard(@PathVariable int tradeId,Model model) {
        tradeService.buyCards(tradeId);
        return "redirect:/trade";
    }
//
}
