package com.richard.studies.batchdemo.config

import com.richard.studies.batchdemo.dto.PersonDto
import com.richard.studies.batchdemo.models.Person
import org.springframework.batch.item.ItemProcessor
import org.springframework.stereotype.Component
import java.time.LocalDate

//@Component
//class PersonItemProcessor : ItemProcessor<PersonDto, Person> {
//    override fun process(item: PersonDto): Person? {
//        val fullName = "${item.firstName.toUpperCase()} ${item.lastName.toUpperCase()}"
////        val age = LocalDate.now().year - item.bornDate.year
//        val age = 90
//        return Person(fullName, age)
//    }
//}
