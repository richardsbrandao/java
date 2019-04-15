package com.richard.studies.react.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

public class ServerResponseBuilder {
    private HttpStatus status;
    private MediaType mediaType;
    private Map<String, String> errorPropertiesMap;

    public ServerResponseBuilder() {
        this.errorPropertiesMap = new HashMap<>();
    }

    public ServerResponseBuilder code(String code) {
        this.errorPropertiesMap.put("code", code);
        return this;
    }

    public ServerResponseBuilder message(String message) {
        this.errorPropertiesMap.put("message", message);
        return this;
    }

    public ServerResponseBuilder status(HttpStatus status) {
        this.status = status;
        return this;
    }

    public ServerResponseBuilder mediaType(MediaType mediaType) {
        this.mediaType = mediaType;
        return this;
    }

    public Mono<ServerResponse> build() {
        return ServerResponse.status(HttpStatus.NOT_FOUND)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .body(BodyInserters.fromObject(errorPropertiesMap));
    }
}
