package ooexample.bank.bank.movement

import java.time.LocalDateTime

class Movement(val period: Period, val time: LocalDateTime, val amount: Double, val type: MovementType) {
    fun isSuccess() : Boolean {
        return true
    }

    override fun toString(): String {
        return "Movement[time=$time, amount=$amount, type=$type]"
    }
}