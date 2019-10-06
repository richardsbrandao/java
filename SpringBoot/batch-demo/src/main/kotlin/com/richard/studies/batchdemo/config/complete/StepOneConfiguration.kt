package com.richard.studies.batchdemo.config.complete

import com.richard.studies.batchdemo.dto.PersonDto
import com.richard.studies.batchdemo.models.Person
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory
import org.springframework.batch.core.step.tasklet.TaskletStep
import org.springframework.batch.item.database.JdbcBatchItemWriter
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder
import org.springframework.batch.item.file.FlatFileItemReader
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.io.Resource
import javax.sql.DataSource

@Configuration
class StepOneConfiguration(
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
    ): JdbcBatchItemWriter<Person> {
        return JdbcBatchItemWriterBuilder<Person>()
            .dataSource(datasource)
            .sql("INSERT INTO people (fullName, age) VALUES (:fullName, :age)")
            .beanMapped()
            .build()
    }

    @Bean
    fun stepOne(
        itemReader: FlatFileItemReader<PersonDto>,
        itemWriter: JdbcBatchItemWriter<Person>,
        personItemProcessor: PersonItemProcessor
    ): TaskletStep {
        return stepBuilderFactory.get("file-db")
            .chunk<PersonDto, Person>(2)
            .reader(itemReader)
            .processor(personItemProcessor)
            .writer(itemWriter)
            .build()
    }
}
