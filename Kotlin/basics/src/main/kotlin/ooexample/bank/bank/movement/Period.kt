package ooexample.bank.bank.movement

import java.time.LocalDateTime

class Period(val time : LocalDateTime) : Comparable<Period> {
    fun get() : String {
        return "${time.year}-${time.monthValue}"
    }

    override fun compareTo(other: Period): Int {
        val currentPeriod : List<String> = get().split("-")
        var otherPeriod : List<String> = other.get().split("-")

        return if(currentPeriod[0].toInt() == otherPeriod[0].toInt()) {
            when {
                currentPeriod[1].toInt() > otherPeriod[1].toInt() -> 1
                currentPeriod[1].toInt() < otherPeriod[1].toInt() -> -1
                else -> 0
            }
        } else {
            if(currentPeriod[0].toInt() > otherPeriod[0].toInt()) {
                1
            } else {
                -1
            }
        }
    }

    override fun toString(): String {
        return get()
    }
}