package com.richard.reactive.bank.services;

import com.richard.reactive.bank.models.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AccountService {
    @Autowired
    private RestTemplate restTemplate;
    @Value("${endpoints.account}")
    private String baseUrl;

    public Account findById(Integer accountId) {
        String endpoint = String.format(baseUrl, accountId);
        return restTemplate.getForObject(endpoint, Account.class);
    }
}
