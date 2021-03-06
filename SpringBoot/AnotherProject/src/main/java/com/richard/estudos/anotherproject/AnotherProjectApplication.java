package com.richard.estudos.anotherproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableCaching
//Multiple Spring Data modules found, entering strict repository configuration mode
@EnableJpaRepositories(basePackages="com.richard.estudos.anotherproject.daos")
public class AnotherProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(AnotherProjectApplication.class, args);
	}
}
