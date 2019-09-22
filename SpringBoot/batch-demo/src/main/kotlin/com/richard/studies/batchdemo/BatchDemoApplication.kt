package com.richard.studies.batchdemo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class BatchDemoApplication

fun main(args: Array<String>) {
	System.setProperty("input", "file:///home/r.brandao/Projects/studies/java/SpringBoot/batch-demo/src/main/resources/data.csv")
	System.setProperty("input", "file:///home/r.brandao/Projects/studies/java/SpringBoot/batch-demo/src/main/resources/data.csv")
	runApplication<BatchDemoApplication>(*args)
}
