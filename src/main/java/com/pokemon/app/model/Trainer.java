package com.pokemon.app.model;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "trainers")
public class Trainer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private int money;

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST},fetch = FetchType.EAGER)
    @JoinTable(
            name = "trainers_cards", joinColumns = @JoinColumn(name = "trainer_id"),
            inverseJoinColumns = @JoinColumn(name = "card_id")
    )
    private Set<Card> cards = new HashSet<>();

    public Trainer() {
        money = 1000;
    }

    public int getMoney() {
        return money;
    }

    public void addCard(Card card) {
        this.cards.add(card);
    }

    public void addCards(Collection<Card> cards){
        this.cards.addAll(cards);
    }

    public void removeCard(Card card) {
        this.cards.remove(card);
    }


}
