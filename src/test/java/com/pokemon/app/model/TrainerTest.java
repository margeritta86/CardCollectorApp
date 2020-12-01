package com.pokemon.app.model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class TrainerTest {

    @Test
    void whenAddCard_thenCardsShouldContainCard() {
        //given
        Trainer trainer = new Trainer();
        Card card = new Card();

        //when
        trainer.addCard(card);

        //then
        assertTrue(trainer.getCards().contains(card));
    }

    @Test
    void whenAddCards_thenCardsShouldContainCollectionOfCards() {
        //given
        Trainer trainer = new Trainer();
        Set<Card> cardsToAdd = new HashSet<>();
        Card card1 = new Card();
        Card card2 = new Card();
        Card card3 = new Card();
        cardsToAdd.add(card1);
        cardsToAdd.add(card2);
        cardsToAdd.add(card3);

        //when
        trainer.addCards(cardsToAdd);

        //then
        assertTrue(trainer.getCards().containsAll(cardsToAdd));
    }

    @Test
    void whenRemoveCard_thenCardsShouldNotContainACard() {
        //given
        Trainer trainer = new Trainer();
        Set<Card> cardsToAdd = new HashSet<>();
        Card card1 = new Card();
        Card card2 = new Card();
        Card card3 = new Card();
        cardsToAdd.add(card1);
        cardsToAdd.add(card2);
        cardsToAdd.add(card3);

        //when
        trainer.removeCard(card1);

        //then
        assertFalse(trainer.getCards().contains(card1));

    }

    @Test
    void whenAddMoney_shouldIncrementedMoneyBy100() {

        //given
        Trainer trainer = new Trainer();
        trainer.setCreateTime(LocalDate.now().minusDays(10));
        trainer.setHowMAnyTimesYouAddedMoney(0);

        //when
        trainer.addMoney();

        //then
        assertEquals(1100,trainer.getMoney());

    }

    @Test
    void whenAddMoneyAndChangedNumberOfHowManyTimesYOuAddedMoney_shouldIncrementedMoneyBy80() {

        //given
        Trainer trainer = new Trainer();
        trainer.setCreateTime(LocalDate.now().minusDays(10));
        trainer.setHowMAnyTimesYouAddedMoney(2);

        //when
        trainer.addMoney();

        //then
        assertEquals(1080,trainer.getMoney());

    }
}