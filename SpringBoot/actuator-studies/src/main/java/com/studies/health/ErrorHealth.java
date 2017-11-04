package com.studies.health;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class ErrorHealth implements HealthIndicator {

	@Override
	public Health health() {
		return Health.down()
						.withDetail("invalid", "Invalid Something")
						.build();
	}

}
