package com.richard.studies.realtime.controllers

import com.richard.studies.realtime.models.RealTime
import com.richard.studies.realtime.models.RealTimeMessageType
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux
import java.time.Duration
import java.time.OffsetDateTime
import java.util.UUID
import java.util.concurrent.ThreadLocalRandom

@RestController("/realtime")
class RealTimeController {

    @GetMapping("/{id}")
    fun findById(@PathVariable("id") id: String): Flux<RealTime> {
        return Flux.just()
    }

    @GetMapping("/{id}/stream")
    fun stream(@PathVariable("id") id: String): Flux<RealTime> {
        return Flux.just(
            RealTime(
                id = UUID.randomUUID(),
                time = OffsetDateTime.now().toString(),
                text = "Start!!",
                type = RealTimeMessageType.FIRST_HALF_START
            )
        )
    }

    @GetMapping(value = ["/example"], produces = [MediaType.APPLICATION_STREAM_JSON_VALUE])
    fun example(): Flux<Int> {
        return Flux.interval(Duration.ofSeconds(1))
                .map { ThreadLocalRandom.current().nextInt(100, 125) }
                .log()
    }
}
