package com.estudos.places;

import java.io.IOException;

import org.springframework.context.annotation.Bean;

import com.mongodb.Mongo;

import cz.jirutka.spring.embedmongo.EmbeddedMongoBuilder;

public class PlacesApplicationTest extends PlacesApplication {

	@Bean(destroyMethod="close")
    public Mongo mongo() throws IOException {
        return new EmbeddedMongoBuilder()
                .build();
    }
	
}
