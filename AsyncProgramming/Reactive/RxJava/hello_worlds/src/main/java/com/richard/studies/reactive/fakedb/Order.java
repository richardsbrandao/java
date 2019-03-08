package com.richard.studies.reactive.fakedb;

public class Order {
    private Integer id;
    private Integer price;
    private OrderStatus status;

    public Order(Integer id, Integer price, OrderStatus status) {
        this.id = id;
        this.price = price;
        this.status = status;
    }

    public static Order create(Integer price, OrderStatus status) {
        return new Order(null, price, status);
    }

    public Integer getId() {
        return id;
    }

    public Integer getPrice() {
        return price;
    }

    public OrderStatus getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", price=" + price +
                ", status=" + status +
                '}';
    }
}
