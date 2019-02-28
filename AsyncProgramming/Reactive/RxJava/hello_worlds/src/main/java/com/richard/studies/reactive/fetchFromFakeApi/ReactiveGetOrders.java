package com.richard.studies.reactive.fetchFromFakeApi;

import io.reactivex.Flowable;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ReactiveGetOrders {
    static GetOrdersApi getOrdersApi = new GetOrdersApi();

    public static void main(String[] args) {

        fakeSchedule(0, 10);

        System.out.println("Finished");
    }

    private static void fakeSchedule(final int times, int max) {
        if(times == max) {
            return;
        }

        Flowable<List<Order>> flowable = Flowable
                .fromFuture(CompletableFuture.supplyAsync(() -> getOrdersApi.fetchNew(times)));

        fakeSchedule(times+1, max);

        flowable.subscribe(orders -> ReactiveGetOrders.printOrders(times, orders));
    }

    private static void printOrders(int times, List<Order> orders) {
        System.out.println(String.format("%s[%s] - Orders: %s", Thread.currentThread().getName(), times, orders));
    }
}
