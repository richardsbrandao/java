package com.richard.studies.realtime.configs

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.net.Socket
import javax.annotation.PreDestroy

@Configuration
class DatabaseConfigTest {
    private val log: Logger = LoggerFactory.getLogger(this.javaClass)

    @Bean
    fun startMongo() {
        try {
            Socket("localhost", 27018)
            log.info(">>> MongoDB container already created")
        } catch (e: Exception) {
            ProcessBuilder("docker-compose", "up", "-d", "test-mongo").start().waitFor()
            log.info(">>> MongoDB container created")
        }
    }

    @PreDestroy
    fun stopMongo() {
        ProcessBuilder("docker-compose", "stop", "test-mongo").start().waitFor()
        log.info(">>> MongoDB container stopped")
    }
}
