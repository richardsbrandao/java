package com.richard.reactive.account.models;

public class Account {
    private int id;
    private String customerName;
    private Integer balance;

    public Account(int id, String customerName, Integer balance) {
        this.id = id;
        this.customerName = customerName;
        this.balance = balance;
    }

    public int getId() {
        return id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public Integer getBalance() {
        return balance;
    }
}
