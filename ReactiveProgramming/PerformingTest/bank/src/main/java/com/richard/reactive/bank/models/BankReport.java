package com.richard.reactive.bank.models;

public class BankReport {
    private Account account;
    private CreditCard creditCard;

    public BankReport(Account account, CreditCard creditCard) {
        this.account = account;
        this.creditCard = creditCard;
    }

    public Account getAccount() {
        return account;
    }

    public CreditCard getCreditCard() {
        return creditCard;
    }
}
