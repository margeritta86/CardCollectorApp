package com.pokemon.app.model;


import javax.persistence.*;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue
    private int id;
    private String email;
    private String name;
    private String password;
    private LocalDate registerTime;

    @OneToOne
    private Trainer trainer;

    public User() {

    }

    public User(String email, String password, Trainer trainer) {
        this.email = email;
        this.password = password;
        this.trainer = trainer;
        registerTime = LocalDate.now();
    }

    public String getEmail() {
        return email;
    }

    public Trainer getTrainer() {
        return trainer;
    }

    public String getPassword() {
        return password;
    }

    public LocalDate getRegisterTime() {
        return registerTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
