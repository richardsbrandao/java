package com.richard.studies.react.controllers;


import com.richard.studies.react.exceptions.UniqueNameException;
import org.springframework.boot.autoconfigure.web.ResourceProperties;
import org.springframework.boot.autoconfigure.web.reactive.error.AbstractErrorWebExceptionHandler;
import org.springframework.boot.web.reactive.error.ErrorAttributes;
import org.springframework.context.ApplicationContext;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebExchangeBindException;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.*;
import reactor.core.publisher.Mono;

import javax.validation.ValidationException;
import java.util.Map;

@Component
@Order(-2)
class GlobalErrorWebExceptionHandler extends AbstractErrorWebExceptionHandler {
    private String UNKOWN_ERROR_CODE = "unknown-error";
    private String UNKNOWN_ERROR_MESSAGE = "Unknown error";

    public GlobalErrorWebExceptionHandler(GlobalErrorAttributes g, ApplicationContext applicationContext,
                                          ServerCodecConfigurer serverCodecConfigurer) {
        super(g, new ResourceProperties(), applicationContext);
        super.setMessageWriters(serverCodecConfigurer.getWriters());
        super.setMessageReaders(serverCodecConfigurer.getReaders());
    }

    @Override
    protected RouterFunction<ServerResponse> getRoutingFunction(final ErrorAttributes errorAttributes) {
        return RouterFunctions
                .route(RequestPredicates.GET(EmployeesController.EMPLOYEES_BY_ID_PATH), this::findEmployeeByIdErrorHandler)
                .andRoute(RequestPredicates.POST(EmployeesController.EMPLOYEES_PATH), this::handleCreateEmployeeError)
                .andRoute(RequestPredicates.all(), this::genericErrorHandler);
    }

    private Mono<ServerResponse> handleCreateEmployeeError(ServerRequest serverRequest) {
        Throwable error = getError(serverRequest);
        ServerResponseBuilder serverResponseBuilder = new ServerResponseBuilder()
                .mediaType(MediaType.APPLICATION_JSON_UTF8);

        if(error instanceof WebExchangeBindException) {
            return serverResponseBuilder
                    .message("Invalid fields")
                    .code("invalid-fields")
                    .status(HttpStatus.BAD_REQUEST)
                    .build();
        } else {
            return serverResponseBuilder
                    .message(UNKNOWN_ERROR_MESSAGE)
                    .code(UNKOWN_ERROR_CODE)
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .build();
        }
    }

    private Mono<ServerResponse> genericErrorHandler(ServerRequest serverRequest) {
        final Map<String, Object> errorPropertiesMap = getErrorAttributes(serverRequest, false);

        return ServerResponse.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .body(BodyInserters.fromObject(errorPropertiesMap));
    }

    private Mono<ServerResponse> findEmployeeByIdErrorHandler(final ServerRequest request) {
        Throwable error = getError(request);
        ServerResponseBuilder serverResponseBuilder = new ServerResponseBuilder()
                .mediaType(MediaType.APPLICATION_JSON_UTF8);

        if(error instanceof NullPointerException) {
            return serverResponseBuilder.message("Employee not found")
                    .code("employee-not-found")
                    .status(HttpStatus.NOT_FOUND)
                    .build();
        } else {
            return serverResponseBuilder.message(UNKNOWN_ERROR_MESSAGE)
                    .code(UNKOWN_ERROR_CODE)
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .build();
        }


    }
}
