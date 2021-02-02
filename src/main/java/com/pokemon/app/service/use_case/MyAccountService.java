package com.pokemon.app.service.use_case;

import com.pokemon.app.dto.MyAccountDto;
import com.pokemon.app.model.Card;
import com.pokemon.app.model.Trainer;
import com.pokemon.app.model.User;
import com.pokemon.app.service.common.LoginService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class MyAccountService {

    private LoginService loginService;

    public MyAccountService(LoginService loginService) {
        this.loginService = loginService;
    }

    public MyAccountDto createMyAccountViewModel(){
        User user = loginService.getLoggedUser();
        Trainer trainer  = user.getTrainer();
        return MyAccountDto.builder()
                .email(user.getEmail())
                .name(trainer.getName())
                .money(trainer.getMoney())
                .registerTime(user.getRegisterTime())
                .daysAfterRegistration(trainer.getDaysAfterCreation())
                .cardsCount(trainer.getCards().size())
                .build();

    }

    public Page<Card> findPaginatedForTrainersCards(Pageable pageable) {
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<Card> cards = new ArrayList<>(loginService.getLoggedUser().getTrainer().getCards().keySet());
        List<Card> list;

        if (cards.size() < startItem) {
            list = Collections.emptyList();
        }else{
            int toIndex = Math.min(startItem+pageSize,cards.size());
            list = cards.subList(startItem,toIndex);
        }

        Page<Card> cardPage = new PageImpl<>(list, PageRequest.of(currentPage, pageSize), cards.size());

        return cardPage;


    }

}
