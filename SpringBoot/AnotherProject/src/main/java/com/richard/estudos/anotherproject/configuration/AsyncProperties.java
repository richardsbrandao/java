package com.richard.estudos.anotherproject.configuration;

import lombok.Getter;
import lombok.Setter;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix="async", ignoreUnknownFields=true)
@Getter @Setter
public class AsyncProperties {
	private Integer corePoolSize;
	private Integer maxPoolSize;
	private Integer queueCapacity;
	private String threadNamePrefix;
}
