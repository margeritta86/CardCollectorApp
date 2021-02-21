package com.pokemon.app.controller;

import com.pokemon.app.dto.CardDto;
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

    public MyCardsController(MyAccountService myAccountService) {
        this.myAccountService = myAccountService;
    }

    @GetMapping("/my-cards")
    public String getMyCardsPage(Model model,
                                 @RequestParam("page") Optional<Integer> page,
                                 @RequestParam("size") Optional<Integer> size) {
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(6);

        model.addAttribute("model", myAccountService.createMyAccountViewModel());
        Page<CardDto> cardPage = myAccountService.findPaginatedForTrainersCards(PageRequest.of(currentPage-1,pageSize));
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

