package com.richard.statemachine.bezahlen

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class BezahlenApplication

fun main(args: Array<String>) {
	runApplication<BezahlenApplication>(*args)
}
