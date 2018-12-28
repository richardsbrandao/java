package com.richard.reactive.creditcard.controllers;

import com.richard.reactive.creditcard.models.CreditCard;
import com.richard.reactive.creditcard.services.CreditCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CreditCardController {
    @Autowired
    private CreditCardService creditCardService;

    @GetMapping("/account/{accountId}/credit-card")
    public ResponseEntity<CreditCard> findByCustomerId(@PathVariable("accountId") Integer accountId) {
        return new ResponseEntity<>(creditCardService.findByAccountId(accountId), HttpStatus.OK);
    }
}
