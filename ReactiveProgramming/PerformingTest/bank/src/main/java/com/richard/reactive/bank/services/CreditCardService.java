package com.richard.reactive.bank.services;

import com.richard.reactive.bank.models.CreditCard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CreditCardService {
    @Autowired
    private RestTemplate restTemplate;
    @Value("${endpoints.credit-card}")
    private String baseUrl;

    public CreditCard findByAccountId(Integer accountId) {
        String endpoint = String.format(baseUrl, accountId);
        return restTemplate.getForObject(endpoint, CreditCard.class);
    }
}
