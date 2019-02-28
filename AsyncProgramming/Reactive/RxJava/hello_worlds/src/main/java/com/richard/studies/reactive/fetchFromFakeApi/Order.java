package com.richard.studies.reactive.fetchFromFakeApi;

public class Order {
    private Long id;
    private Integer price;

    public Order(Long id, Integer price) {
        this.id = id;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public Integer getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", price=" + price +
                '}';
    }
}
