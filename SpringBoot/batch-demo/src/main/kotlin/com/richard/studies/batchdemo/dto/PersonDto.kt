package com.richard.studies.batchdemo.dto

class PersonDto(
    var firstName: String = "",
    var lastName: String = "",
    var bornDate: String = ""
) {
    override fun toString(): String {
        return "PersonDto(firstName=$firstName, lastName=$lastName, bornDate=$bornDate)"
    }
}
