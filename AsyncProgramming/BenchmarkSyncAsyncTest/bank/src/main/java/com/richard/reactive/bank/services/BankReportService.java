package com.richard.reactive.bank.services;

import com.richard.reactive.bank.models.Account;
import com.richard.reactive.bank.models.BankReport;
import com.richard.reactive.bank.models.CreditCard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.concurrent.Future;

@Service
public class BankReportService {
    @Autowired
    private AccountService accountService;
    @Autowired
    private CreditCardService creditCardService;

    public BankReport syncFindByAccountId(Integer accountId) {
        Account account = accountService.syncFindById(accountId);
        CreditCard creditCard = syncFindCreditCardByAccountIdOrReturnNull(accountId);
        return new BankReport(account, creditCard);
    }

    public BankReport asyncFindByAccountId(Integer accountId) throws Exception {
        Future<Account> accountResponse = accountService.asyncFindById(accountId);
        Future<CreditCard> creditCardResponse = creditCardService.asyncFindById(accountId);
        return new BankReport(accountResponse.get(), creditCardResponse.get());
    }

    private CreditCard syncFindCreditCardByAccountIdOrReturnNull(Integer accountId) {
        try {
            return creditCardService.syncFindByAccountId(accountId);
        } catch (HttpClientErrorException e) {
            return null;
        }
    }
}
