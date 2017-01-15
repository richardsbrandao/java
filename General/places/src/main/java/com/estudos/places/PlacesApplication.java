package com.estudos.places;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.mongodb.MongoClient;
import com.richard.filter.RestExceptionHandlerFilter;

@Configuration
@EnableMongoRepositories
@SpringBootApplication
@EnableAutoConfiguration
public class PlacesApplication {

    public static void main(String[] args) {
        SpringApplication.run(PlacesApplication.class, args);
    }
    
    @Bean
    public MongoDbFactory mongoDbFactory() throws Exception {
        return new SimpleMongoDbFactory(new MongoClient(), "geoSearch");
    }

    @Bean
    public MongoTemplate mongoTemplate() throws Exception {
        return new MongoTemplate(mongoDbFactory());
    }
    
    @Bean
    public RestExceptionHandlerFilter restExceptionHandlerFilter() {
    	return new RestExceptionHandlerFilter();
    }
    
}
