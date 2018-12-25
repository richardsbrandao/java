package com.richard.reactive.bank.services;

import com.richard.reactive.bank.models.Account;
import com.richard.reactive.bank.models.BankReport;
import com.richard.reactive.bank.models.CreditCard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

@Service
public class BankReportService {
    @Autowired
    private AccountService accountService;
    @Autowired
    private CreditCardService creditCardService;

    public BankReport findByAccountId(Integer accountId) {
        Account account = accountService.findById(accountId);
        CreditCard creditCard = findCreditCardByAccountIdOrReturnNull(accountId);
        return new BankReport(account, creditCard);
    }

    private CreditCard findCreditCardByAccountIdOrReturnNull(Integer accountId) {
        try {
            return creditCardService.findByAccountId(accountId);
        } catch (HttpClientErrorException e) {
            return null;
        }
    }
}
