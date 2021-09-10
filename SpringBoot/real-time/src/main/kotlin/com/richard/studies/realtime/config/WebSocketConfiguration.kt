package com.richard.studies.realtime.config

import com.fasterxml.jackson.databind.ObjectMapper
import com.richard.studies.realtime.websocket.MatchEventPublisher
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.handler.SimpleUrlHandlerMapping
import org.springframework.web.reactive.socket.WebSocketHandler
import org.springframework.web.reactive.socket.server.support.WebSocketHandlerAdapter
import reactor.core.publisher.Flux
import java.util.concurrent.Executor
import java.util.concurrent.Executors

@Configuration
class WebSocketConfiguration {
    private val log: Logger = LoggerFactory.getLogger(this.javaClass)

    @Bean
    fun executor(): Executor = Executors.newSingleThreadExecutor()

    @Bean
    fun webSocketHandlerAdapter() = WebSocketHandlerAdapter()

    @Bean
    fun webSocketHandler(
        objectMapper: ObjectMapper,
        matchEventPublisher: MatchEventPublisher
    ): WebSocketHandler {
        val publish = Flux.create(matchEventPublisher).share()
        return WebSocketHandler { session ->
            log.info("Connection established")
            val messageFlux = publish
                .map { objectMapper.writeValueAsString(it.realTimeEventSource) }
                .map(session::textMessage)
            session.send(messageFlux)
        }
    }

    @Bean
    fun handlerMapping(webSocketHandler: WebSocketHandler): SimpleUrlHandlerMapping {
        return object : SimpleUrlHandlerMapping() {
            init {
                urlMap = mapOf("/ws/match-event" to webSocketHandler)
                order = 10
            }
        }
    }
}
