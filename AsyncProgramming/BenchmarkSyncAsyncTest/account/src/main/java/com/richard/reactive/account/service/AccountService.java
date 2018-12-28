package com.richard.reactive.account.service;

import com.richard.reactive.account.errors.NotFoundException;
import com.richard.reactive.account.models.Account;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Service
public class AccountService {
    private Map<Integer, Account> accounts;

    @PostConstruct
    public void init() {
        Random random = new Random();
        Integer MAX_BALANCE = 1000000;
        this.accounts = new HashMap<>();
        for(int i = 0; i < 10000; i++) {
            int balance = Math.abs(random.nextInt(MAX_BALANCE));
            this.accounts.put(i, new Account(i, "Customer_"+i, balance));
        }
    }

    public Account findById(int id) {
        Account account = accounts.get(id);
        if(account == null) {
            throw new NotFoundException();
        }
        return account;
    }
}
