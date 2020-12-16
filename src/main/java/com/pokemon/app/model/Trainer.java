package com.pokemon.app.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;

@Data
@Entity
@Table(name = "trainers")
public class Trainer {

    private static final int STARTING_MONEY = 1000;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private LocalDate createTime;
    private int money = STARTING_MONEY;
    private int howMAnyTimesYouAddedMoney;
    private String name;

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.EAGER)
    @JoinTable(
            name = "trainers_cards", joinColumns = @JoinColumn(name = "trainer_id"),
            inverseJoinColumns = @JoinColumn(name = "card_id")
    )
    private List<Card> cards = new ArrayList<>();

    public Trainer() {

    }

    public Trainer(String name) {
        this.name = name;
        createTime = LocalDate.now();
    }

    public void addCard(Card card) {
        this.cards.add(card);
    }

    public void addCards(Collection<Card> cards) {
        this.cards.addAll(cards);
    }

    public void removeCard(Card card) {
        this.cards.remove(card);
    }

    public String getName() {
        return name;
    }

    public int getMoney() {
        return money;
    }

    public int getHowMAnyTimesYouAddedMoney() {
        return howMAnyTimesYouAddedMoney;
    }

    public List<Card> getCards() {
        return cards;
    }

    public int getDaysAfterCreation() {
        return (int) ChronoUnit.DAYS.between(createTime, LocalDate.now());
    }

    public void addMoney() {
        int daysAfterCreation = getDaysAfterCreation();
        money += daysAfterCreation * 10 - (howMAnyTimesYouAddedMoney * 10);
        howMAnyTimesYouAddedMoney = daysAfterCreation;
    }

    public void subtractMoney (int howMuch) {
        money -= howMuch;
    }
}
