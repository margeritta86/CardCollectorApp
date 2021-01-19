package com.pokemon.app.request;

import javax.validation.constraints.Min;

public class SellRequest {
    @Min(value = 1, message = "nie można liczby ujemnej")
    private Integer howMany;
    @Min(value = 1, message = "nie można liczby ujemnej")
    private Integer howMuch;
    private String id;

    public SellRequest() {
    }

    public Integer getHowMany() {
        return howMany;
    }

    public Integer getHowMuch() {
        return howMuch;
    }

    public String getCardId() {
        return id;
    }

    public void setHowMany(int howMany) {
        this.howMany = howMany;
    }

    public void setHowMuch(int howMuch) {
        this.howMuch = howMuch;
    }

    public void setCardId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "SellRequest{" +
                "howMany=" + howMany +
                ", howMuch=" + howMuch +
                ", cardId='" + id + '\'' +
                '}';
    }
}
