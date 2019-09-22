package com.richard.studies.batchdemo.config

import com.richard.studies.batchdemo.dto.PersonDto
import com.richard.studies.batchdemo.models.Person
import org.springframework.batch.core.Job
import org.springframework.batch.core.Step
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory
import org.springframework.batch.core.launch.support.RunIdIncrementer
import org.springframework.batch.item.database.JdbcBatchItemWriter
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder
import org.springframework.batch.item.file.FlatFileItemReader
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.io.Resource
import javax.sql.DataSource

@EnableBatchProcessing
@Configuration
class BatchConfiguration(
    private val jobBuilderFactory: JobBuilderFactory,
    private val stepBuilderFactory: StepBuilderFactory
) {

    @Bean
    fun reader(
        @Value("\${input}") resource: Resource
    ): FlatFileItemReader<PersonDto> {
        return FlatFileItemReaderBuilder<PersonDto>()
            .name("personItemReader")
            .resource(resource)
            .targetType(PersonDto::class.java)
            .delimited().delimiter(",").names(arrayOf("firstName", "lastName", "bornDate"))
            .build()
    }

    @Bean
    fun jdbcWriter(
        datasource: DataSource
    ): JdbcBatchItemWriter<PersonDto> {
        return JdbcBatchItemWriterBuilder<PersonDto>()
            .dataSource(datasource)
            .sql("INSERT INTO peopledto (firstName, lastName, bornDate) VALUES (:firstName, :lastName, :bornDate)")
            .beanMapped()
            .build()
    }

    @Bean
    fun job(
        jobBuilderFactory: JobBuilderFactory,
        stepBuilderFactory: StepBuilderFactory,
        itemReader: FlatFileItemReader<PersonDto>,
        itemWriter: JdbcBatchItemWriter<PersonDto>
    ): Job {
        val step1 : Step = stepBuilderFactory.get("file-db")
            .chunk<PersonDto, PersonDto>(100)
            .reader(itemReader)
            .writer(itemWriter)
            .build()

        return jobBuilderFactory.get("etl")
            .incrementer(RunIdIncrementer())
            .start(step1)
            .build()
    }
}
