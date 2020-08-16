package functions

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper

data class Address(val id: String, val zipCode: String)

fun main() {
    val objectMapper = jacksonObjectMapper()
    val request = """
        {"id": "1", "zipCode": "123"}
    """.trimIndent()

    val address = objectMapper.readValue(request, Address::class.java)
    val addressExtensionFunction = objectMapper.toAddress(request)

    println(address)
    println(addressExtensionFunction)
}

fun ObjectMapper.toAddress(addressString: String) = this.readValue(addressString, Address::class.java)
