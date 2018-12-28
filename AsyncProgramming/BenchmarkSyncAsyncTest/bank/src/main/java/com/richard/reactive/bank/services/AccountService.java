package com.richard.reactive.bank.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.richard.reactive.bank.models.Account;
import org.asynchttpclient.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AccountService {
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private AsyncHttpClient asyncHttpClient;
    @Autowired
    private ObjectMapper objectMapper;
    @Value("${endpoints.account}")
    private String baseUrl;

    public Account syncFindById(Integer accountId) {
        String endpoint = String.format(baseUrl, accountId);
        return restTemplate.getForObject(endpoint, Account.class);
    }

    public ListenableFuture<Account> asyncFindById(Integer accountId) {
        String endpoint = String.format(baseUrl, accountId);
        return asyncHttpClient.prepareGet(endpoint).execute(new AsyncCompletionHandler<Account>() {
            @Override
            public Account onCompleted(Response response) throws Exception {
                return objectMapper.readValue(response.getResponseBody(), Account.class);
            }
        });
    }
}
