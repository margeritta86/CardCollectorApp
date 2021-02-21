package com.pokemon.app.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CardDto {

    private String id;
    private String imageUrl;
    private int amount;

}
