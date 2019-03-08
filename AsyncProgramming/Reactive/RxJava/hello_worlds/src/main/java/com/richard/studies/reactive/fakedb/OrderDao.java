package com.richard.studies.reactive.fakedb;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.Single;

import java.util.HashMap;
import java.util.Map;

public class OrderDao {

    private int sequence;
    private Map<Integer, Order> orders;

    public OrderDao() {
        this.orders = new HashMap<Integer, Order>() { {
            put(1, new Order(1, 300, OrderStatus.DELIVERED));
            put(2, new Order(2, 100, OrderStatus.DISPATCHED));
            put(3, new Order(3, 50,  OrderStatus.PAYED));
            put(4, new Order(4, 870, OrderStatus.PAYED));
            put(5, new Order(5, 250, OrderStatus.DELIVERED));
        } };
        this.sequence = 5;
    }

    public Flowable<Order> findAll() {
        return Flowable.fromIterable(this.orders.values());
    }

    public Single<Order> save(Order order) {
        return Single.fromCallable(() -> {
            Order orderToSaved = new Order(++this.sequence, order.getPrice(), order.getStatus());
            this.orders.put(order.getId(), orderToSaved);
            return orderToSaved;
        });
    }

    public Maybe<Order> findById(Integer id) {
        return Maybe.fromCallable(() -> this.orders.get(id));
    }

    public Completable updateStatus(Integer id, Order order) {
        return Completable.fromAction(() -> this.orders.put(id, order));
    }

    public Completable delete(Integer id) {
        return Completable.fromAction(() -> this.orders.remove(id));
    }

}
