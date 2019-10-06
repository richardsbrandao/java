package com.richard.studies.batchdemo.config.complete

import org.springframework.batch.core.Job
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory
import org.springframework.batch.core.launch.support.RunIdIncrementer
import org.springframework.batch.core.step.tasklet.TaskletStep
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile

@EnableBatchProcessing
@Configuration
@Profile("complete")
class BatchConfigurationComplete {

    @Bean
    fun job(
        jobBuilderFactory: JobBuilderFactory,
        stepOne: TaskletStep,
        stepTwo: TaskletStep
    ): Job {
        return jobBuilderFactory.get("etl")
            .incrementer(RunIdIncrementer())
            .start(stepOne)
            .next(stepTwo)
            .build()
    }
}
