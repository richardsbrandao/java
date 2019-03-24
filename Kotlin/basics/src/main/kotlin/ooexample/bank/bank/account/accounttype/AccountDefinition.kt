package ooexample.bank.bank.account.accounttype

import ooexample.bank.bank.movement.Movement
import ooexample.bank.bank.movement.MovementType
import ooexample.bank.bank.movement.Period
import java.time.LocalDateTime

abstract class AccountDefinition(var balance: Double) : LisSettings {
    abstract fun withdraw(amount : Double): Movement

    fun deposit(amount: Double) : Movement {
        val time : LocalDateTime = LocalDateTime.now()
        incrementBalance(amount)
        return Movement(Period(time), time, amount, MovementType.DEPOSIT)
    }

    private fun incrementBalance(amount : Double) {
        this.balance += amount
    }

    protected fun decrementBalance(amount : Double) : Double {
        val amountToDecrement = Math.min(amount, this.balance)
        val amountNotDecremented : Double = if (this.balance < amount) amountToDecrement else 0.0
        this.balance -= amountToDecrement
        return amountNotDecremented
    }

    override fun toString(): String {
        val lis = if(isLisEnabled()) "[lis=${getLisLimit()}]" else ""
        return "${this.javaClass.simpleName}[balance=$balance]$lis"
    }
}
