package com.estudos.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.richard.filter.RestExceptionHandlerFilter;

@SpringBootApplication
public class HelloWorldApplication {

    public static void main(String[] args) {
        SpringApplication.run(HelloWorldApplication.class, args);
    }
    
    @Bean
    public RestExceptionHandlerFilter restExceptionHandlerFilter() {
    	return new RestExceptionHandlerFilter();
    }
    
}
