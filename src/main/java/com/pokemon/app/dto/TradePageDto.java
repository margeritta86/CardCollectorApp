package com.pokemon.app.dto;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.domain.PageImpl;

@Data
@Builder
public class TradePageDto {

    private PageImpl<TradeDto> trades;
    private int ownedMoney;

}
