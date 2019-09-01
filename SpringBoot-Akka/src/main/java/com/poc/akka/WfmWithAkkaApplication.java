package com.poc.akka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

//import springfox.documentation.swagger2.annotations.EnableSwagger2;
/**
 * @author by Pritom Gogoi
 */

@SpringBootApplication
@EnableEurekaClient
public class WfmWithAkkaApplication {

	public static void main(String[] args) {
		SpringApplication.run(WfmWithAkkaApplication.class, args);
	}
}