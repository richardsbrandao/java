package com.richard.studies.reactive.fetchFromFakeApi;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GetOrdersApi {
    private Random random = new Random();
    private final int WAIT_IN_MILLS = 2000;
    private int MAX_TO_RETURN = 10;
    private int MAX_PRICE = 100;

    public List<Order> fetchNew(int times) {
        try {
            System.out.println(
                    String.format("CurrentThread[%s]: %s", times, Thread.currentThread().getName())
            );
            Thread.sleep(random.nextInt(random.nextInt(WAIT_IN_MILLS)));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        List<Order> orders = new ArrayList<>();
        for(int i = 0; i < random.nextInt(MAX_TO_RETURN); i++) {
            orders.add(newOrder());
        }
        return orders;
    }

    private Order newOrder() {
        return new Order(random.nextLong(), random.nextInt(MAX_PRICE));
    }
}
