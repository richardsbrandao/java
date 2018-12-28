package com.richard.reactive.bank.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.richard.reactive.bank.models.CreditCard;
import org.asynchttpclient.AsyncCompletionHandler;
import org.asynchttpclient.AsyncHttpClient;
import org.asynchttpclient.ListenableFuture;
import org.asynchttpclient.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CreditCardService {
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private AsyncHttpClient asyncHttpClient;
    @Autowired
    private ObjectMapper objectMapper;
    @Value("${endpoints.credit-card}")
    private String baseUrl;

    public CreditCard syncFindByAccountId(Integer accountId) {
        String endpoint = String.format(baseUrl, accountId);
        return restTemplate.getForObject(endpoint, CreditCard.class);
    }

    public ListenableFuture<CreditCard> asyncFindById(Integer accountId) {
        String endpoint = String.format(baseUrl, accountId);
        return asyncHttpClient.prepareGet(endpoint).execute(new AsyncCompletionHandler<CreditCard>() {
            @Override
            public CreditCard onCompleted(Response response) throws Exception {
                if(response.getStatusCode() == HttpStatus.OK.value()) {
                    return objectMapper.readValue(response.getResponseBody(), CreditCard.class);
                }
                return null;
            }
        });
    }
}
