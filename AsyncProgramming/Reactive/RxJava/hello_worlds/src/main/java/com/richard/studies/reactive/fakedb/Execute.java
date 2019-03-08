package com.richard.studies.reactive.fakedb;

public class Execute {
    static OrderDao orderDao = new OrderDao();

    public static void main(String[] args) {


        orderDao.findAll()
            .subscribe(System.out::println);

        orderDao.findById(1)
                .subscribe(System.out::println);

        orderDao.findById(9)
                .subscribe(orderOrNull -> System.out.println(orderOrNull));

        orderDao.delete(2)
                .subscribe(() -> checkOrders("Delete"));

        orderDao.findById(4)
                .subscribe((order) -> orderDao
                                        .updateStatus(order.getId(), new Order(order.getId(),
                                                                                order.getPrice(),
                                                                                OrderStatus.DISPATCHED))
                                        .subscribe(() -> checkOrders("UpdateStatus"))
                );

        orderDao.save(Order.create(100, OrderStatus.CREATED))
                .subscribe((order) -> checkOrders("Save"));
    }

    private static void checkOrders(String operationName) {
        orderDao.findAll()
                .toList()
                .subscribe(orders -> System.out.println(String.format("%s: %s", operationName, orders)));
    }
}
