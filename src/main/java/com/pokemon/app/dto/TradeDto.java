package com.pokemon.app.dto;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TradeDto {

    private String trainer;
    private int howManyCards;
    private int price;
    private String cardUrl;
    private int tradeId;
    private boolean yourTrade;

}
