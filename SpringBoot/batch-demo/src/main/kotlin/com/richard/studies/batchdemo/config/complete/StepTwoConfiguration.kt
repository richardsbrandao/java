package com.richard.studies.batchdemo.config.complete

import org.springframework.batch.core.configuration.annotation.StepBuilderFactory
import org.springframework.batch.core.step.tasklet.TaskletStep
import org.springframework.batch.item.database.JdbcCursorItemReader
import org.springframework.batch.item.database.builder.JdbcCursorItemReaderBuilder
import org.springframework.batch.item.file.FlatFileItemWriter
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder
import org.springframework.batch.item.file.transform.DelimitedLineAggregator
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.io.Resource
import javax.sql.DataSource

@Configuration
class StepTwoConfiguration(
    private val stepBuilderFactory: StepBuilderFactory
) {
    @Bean
    fun readerStepTwo(dataSource: DataSource): JdbcCursorItemReader<Map<String, Int>> {
        return JdbcCursorItemReaderBuilder<Map<String, Int>>()
            .dataSource(dataSource)
            .name("step-two-reader")
            .sql("select avg(age) average from people")
            .rowMapper { rs, _ ->
                mapOf("avg" to rs.getInt("average"))
            }
            .build()
    }

    @Bean
    fun writerStepTwo(
        @Value("\${output}") resource: Resource,
        averageAgeLineAggregator: DelimitedLineAggregator<Map<String, Int>>
    ): FlatFileItemWriter<Map<String, Int>> {
        return FlatFileItemWriterBuilder<Map<String, Int>>()
            .name("step-two-writer")
            .resource(resource)
            .lineAggregator(averageAgeLineAggregator)
            .build()
    }

    @Bean
    fun averageAgeLineAggregator(): DelimitedLineAggregator<Map<String, Int>> {
        val averageAgeLineAggregator = DelimitedLineAggregator<Map<String, Int>>()
        averageAgeLineAggregator.setDelimiter(",")
        averageAgeLineAggregator.setFieldExtractor(AverageAgeFieldExtractor())
        return averageAgeLineAggregator
    }

    @Bean
    fun stepTwo(
        itemReader: JdbcCursorItemReader<Map<String, Int>>,
        itemWriter: FlatFileItemWriter<Map<String, Int>>
    ): TaskletStep {
        return stepBuilderFactory.get("db-file")
            .chunk<Map<String, Int>, Map<String, Int>>(2)
            .reader(itemReader)
            .writer(itemWriter)
            .build()
    }
}
