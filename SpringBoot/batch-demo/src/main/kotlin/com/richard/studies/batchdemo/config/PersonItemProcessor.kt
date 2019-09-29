package com.richard.studies.batchdemo.config

import com.richard.studies.batchdemo.dto.PersonDto
import com.richard.studies.batchdemo.models.Person
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.batch.item.ItemProcessor
import org.springframework.stereotype.Component
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Component
class PersonItemProcessor : ItemProcessor<PersonDto, Person> {
    private val log: Logger = LoggerFactory.getLogger(this.javaClass)

    companion object {
        val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-d")
    }

    override fun process(item: PersonDto): Person? {
        return try {
            val fullName = "${item.firstName.toUpperCase()} ${item.lastName.toUpperCase()}"
            val age = LocalDate.now().year - LocalDate.parse(item.bornDate, formatter).year
            Person(fullName = fullName, age = age)
        } catch (e: Exception) {
            log.error("Error on saving $item")
            null
        }
    }
}
