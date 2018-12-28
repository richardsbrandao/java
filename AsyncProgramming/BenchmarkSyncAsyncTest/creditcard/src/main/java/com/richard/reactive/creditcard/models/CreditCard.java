package com.richard.reactive.creditcard.models;

public class CreditCard {
    private Integer customerId;
    private Integer totalLimit;
    private Integer totalBalance;

    public CreditCard(Integer customerId, Integer totalLimit, Integer totalBalance) {
        this.customerId = customerId;
        this.totalLimit = totalLimit;
        this.totalBalance = totalBalance;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public Integer getTotalLimit() {
        return totalLimit;
    }

    public Integer getTotalBalance() {
        return totalBalance;
    }
}
