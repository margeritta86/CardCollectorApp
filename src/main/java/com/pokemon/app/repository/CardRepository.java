package com.pokemon.app.repository;

import com.pokemon.app.model.Card;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CardRepository extends JpaRepository<Card,Integer> {

}
