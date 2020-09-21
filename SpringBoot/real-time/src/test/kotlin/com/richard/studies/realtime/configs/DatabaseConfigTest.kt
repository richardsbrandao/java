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
    fun startPostgres() {
        try {
            Socket("localhost", 5433)
            log.info(">>> PostgresSQL  container already created")
        } catch (e: Exception) {
            ProcessBuilder("docker-compose", "up", "-d", "test-postgres").start().waitFor()
            log.info(">>> PostgresSQL container created")
        }
    }

    @PreDestroy
    fun stopPostgres() {
        ProcessBuilder("docker-compose", "stop", "test-postgres").start().waitFor()
        log.info(">>> PostgresSQL container stopped")
    }
}
