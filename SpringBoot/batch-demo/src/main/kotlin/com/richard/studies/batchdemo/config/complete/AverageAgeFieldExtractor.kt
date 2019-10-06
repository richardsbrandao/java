package com.richard.studies.batchdemo.config.complete

import org.springframework.batch.item.file.transform.FieldExtractor
import org.springframework.stereotype.Component

@Component
class AverageAgeFieldExtractor : FieldExtractor<Map<String, Int>> {
    override fun extract(item: Map<String, Int>): Array<Any> {
        val next = item.entries.iterator().next()
        return arrayOf(next.key, next.value)
    }
}
