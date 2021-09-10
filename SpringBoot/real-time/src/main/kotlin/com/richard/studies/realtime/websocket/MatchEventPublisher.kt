package com.richard.studies.realtime.websocket

import org.springframework.context.ApplicationListener
import org.springframework.stereotype.Component
import reactor.core.publisher.FluxSink
import java.util.concurrent.BlockingQueue
import java.util.concurrent.Executor
import java.util.concurrent.LinkedBlockingQueue
import java.util.function.Consumer

@Component
class MatchEventPublisher(
    private val executor: Executor
): ApplicationListener<RealTimeScoreEvent>, Consumer<FluxSink<RealTimeScoreEvent>> {
    private val queue: BlockingQueue<RealTimeScoreEvent> = LinkedBlockingQueue()

    override fun onApplicationEvent(scoreEvent: RealTimeScoreEvent) {
        this.queue.offer(scoreEvent)
    }

    override fun accept(sink: FluxSink<RealTimeScoreEvent>) {
        this.executor.execute {
            while (true) {
                try {
                    val event = queue.take()
                    sink.next(event)
                } catch (e: InterruptedException) {
                    throw e
                }
            }
        }
    }

}
