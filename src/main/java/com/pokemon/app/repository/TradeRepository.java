package com.pokemon.app.repository;

import com.pokemon.app.model.Trade;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TradeRepository extends JpaRepository<Trade,Integer> {
}
