package com.pokemon.app.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class MyAccountDto {

    private String name;
    private int money;
    private String email;
    private LocalDate registerTime;
    private int daysAfterRegistration;
    private int cardsCount;

}
