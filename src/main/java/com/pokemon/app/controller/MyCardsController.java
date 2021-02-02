package com.pokemon.app.controller;

import com.pokemon.app.model.Card;
import com.pokemon.app.service.common.TrainerAccessService;
import com.pokemon.app.service.use_case.MyAccountService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
public class MyCardsController {


    private MyAccountService myAccountService;
    private TrainerAccessService trainerAccessService;


    public MyCardsController(MyAccountService myAccountService, TrainerAccessService trainerAccessService) {
        this.myAccountService = myAccountService;
        this.trainerAccessService = trainerAccessService;
    }

   /* @GetMapping("/my-cards")
    public String getMyCardsPage(Model model) {
        model.addAttribute("model", myAccountService.createMyAccountViewModel());

        try {
            Set<Card> cards = trainerAccessService.getLoggedTrainer().get().getCards().keySet();
            model.addAttribute("cards", cards);
            model.addAttribute("howMany", cards.size());

        } catch (Exception e) {
            model.addAttribute("message", e.getMessage());
        }
        return "my-cards";
    }*/


    @GetMapping("/my-cards")
    public String getMyCardsPage(Model model, @RequestParam("page") Optional<Integer> page,@RequestParam("size") Optional<Integer> size) {
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(6);

        model.addAttribute("model", myAccountService.createMyAccountViewModel());
        Page<Card> cardPage = myAccountService.findPaginatedForTrainersCards(PageRequest.of(currentPage-1,pageSize));
        model.addAttribute("cardPage",cardPage);

        int totalPages = cardPage.getTotalPages();

        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }
        return "my-cards";
    }


}

