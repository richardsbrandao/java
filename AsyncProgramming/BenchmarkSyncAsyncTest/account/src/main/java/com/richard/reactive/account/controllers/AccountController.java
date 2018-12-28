package com.richard.reactive.account.controllers;

import com.richard.reactive.account.models.Account;
import com.richard.reactive.account.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountController {
    @Autowired
    private AccountService accountService;

    @GetMapping("/account/{accountId}")
    public ResponseEntity<Account> get(@PathVariable("accountId") int accountId) {
        return new ResponseEntity<>(accountService.findById(accountId), HttpStatus.OK);
    }
}
