package ooexample.bank.person

import java.time.LocalDate

class Person(val name : String, val gender : Gender, val birthyear : Int) {
    var job : Job = Unemployed()

    fun job(job : Job) {
        this.job = job
    }

    fun hasMoreThan18(): Boolean {
        return LocalDate.now().year - birthyear >= 18
    }
}
