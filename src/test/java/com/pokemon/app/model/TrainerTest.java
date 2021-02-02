package com.pokemon.app.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class TrainerTest {

    private Trainer trainer;

    @BeforeEach
    void createTrainer(){
        trainer = new Trainer();

    }

    @Test
    void whenAddCard_thenCardsShouldContainCard() {
        //given
        Card card = new Card();

        //when
       // trainer.addCard(card);

        //then
        assertTrue(trainer.getCards().containsKey(card));
    }

    @Test
    void whenAddCards_thenCardsShouldContainCollectionOfCards() {
        //given
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
        assertTrue(trainer.getCards().keySet().containsAll(cardsToAdd));
    }

    @Test
    void whenRemoveCard_thenCardsShouldNotContainACard() {

        //given
        Set<Card> cardsToAdd = new HashSet<>();
        Card card1 = new Card();
        Card card2 = new Card();
        Card card3 = new Card();
        cardsToAdd.add(card1);
        cardsToAdd.add(card2);
        cardsToAdd.add(card3);

        //when
      //  trainer.removeCard(card1);

        //then
        assertFalse(trainer.getCards().containsKey(card1));
    }

    @Test
    void whenAddMoney_shouldIncrementedMoneyBy100() {

        //given
        trainer.setCreateTime(LocalDate.now().minusDays(10));
        trainer.setHowMAnyTimesYouAddedMoney(0);

        //when
        trainer.addDailyMoney();

        //then
        assertEquals(1100,trainer.getMoney());
    }

    @Test
    void whenAddMoneyAndChangedNumberOfHowManyTimesYOuAddedMoney_shouldIncrementedMoneyBy80() {

        //given
        trainer.setCreateTime(LocalDate.now().minusDays(10));
        trainer.setHowMAnyTimesYouAddedMoney(2);

        //when
        trainer.addDailyMoney();

        //then
        assertEquals(1080,trainer.getMoney());

    }
}