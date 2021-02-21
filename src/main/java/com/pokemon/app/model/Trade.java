package com.pokemon.app.model;

import org.springframework.data.annotation.PersistenceConstructor;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "trades")
public class Trade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    private Trainer trainer;
    @ManyToOne
    private Card card;
    private int cardPrice;
    private int cardsAmount;

    @PersistenceConstructor
    public Trade() {
    }

    public Trade(Trainer trainer, Card card, int cardPrice, int cardsAmount) {
        this.trainer = trainer;
        this.card = card;
        this.cardPrice = cardPrice;
        this.cardsAmount = cardsAmount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Trade trade = (Trade) o;
        return id == trade.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Trade{" +
                "id=" + id +
                ", trainer=" + trainer +
                ", card=" + card +
                ", cardPrice=" + cardPrice +
                ", cardsAmount=" + cardsAmount +
                '}';
    }

    public Card getCard() {
        return card;
    }

    public int getCardPrice() {
        return cardPrice;
    }

    public int getId() {
        return id;
    }

    public Trainer getTrainer() {
        return trainer;
    }

    public int getCardsAmount() {
        return cardsAmount;
    }
}
