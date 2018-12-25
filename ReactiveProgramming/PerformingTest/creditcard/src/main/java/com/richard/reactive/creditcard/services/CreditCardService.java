package com.richard.reactive.creditcard.services;

import com.richard.reactive.creditcard.errors.NotFoundException;
import com.richard.reactive.creditcard.models.CreditCard;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Service
public class CreditCardService {
    private Map<Integer, CreditCard> creditCards;

    @PostConstruct
    public void init() {
        Random random = new Random();
        this.creditCards = new HashMap<>();
        for(int accountId = 0; accountId < 10000; accountId++) {
            if(accountId % 7 == 0) {
                this.creditCards.put(accountId, null);
                continue;
            }
            int limit = Math.abs(random.nextInt(1000000));
            int percentageBalanceUsed = random.nextInt(100);
            int totalBalance = limit * percentageBalanceUsed / 100;
            CreditCard creditCard = new CreditCard(accountId, limit, totalBalance);
            this.creditCards.put(accountId, creditCard);
        }
    }

    public CreditCard findByAccountId(Integer accountId) {
        CreditCard creditCard = this.creditCards.get(accountId);
        if(creditCard == null) {
            throw new NotFoundException();
        }
        return creditCard;
    }
}
