package com.richard.studies.reactive.fetchFromFakeApi;

import io.reactivex.Flowable;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ReactiveGetOrders {
    public static void main(String[] args) {
        GetOrdersApi getOrdersApi = new GetOrdersApi();

        Flowable<List<Order>> flowable = Flowable
                            .fromFuture(CompletableFuture.supplyAsync(() -> getOrdersApi.fetchNew()));

        System.out.println("Non Blocking stuffs");

        flowable.subscribe(ReactiveGetOrders::printOrders);
    }

    private static void printOrders(List<Order> orders) {
        System.out.println("Orders: " + orders);
    }
}
