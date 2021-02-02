package com.pokemon.app.request;

public class BuyRequest {

    private int price;
    private String tradeId;

    public BuyRequest() {
    }


    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getTradeId() {
        return tradeId;
    }

    public void setTradeId(String tradeId) {
        this.tradeId = tradeId;
    }

    @Override
    public String toString() {
        return "BuyRequest{" +
                "price=" + price +
                ", tradeId='" + tradeId + '\'' +
                '}';
    }
}
