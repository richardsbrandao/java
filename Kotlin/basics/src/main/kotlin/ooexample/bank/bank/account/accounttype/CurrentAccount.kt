package ooexample.bank.bank.account.accounttype

import ooexample.bank.bank.errors.InvalidWithdrawOperation
import ooexample.bank.bank.movement.Movement
import ooexample.bank.bank.movement.MovementType
import ooexample.bank.bank.movement.Period
import java.time.LocalDateTime

class CurrentAccount(balance : Double) : AccountDefinition(balance) {
    var limit : Double = 0.0

    override fun enableLis(value: Double) {
        this.limit = value
    }

    override fun disableLis() {
        this.limit = 0.0
    }

    override fun getLisLimit(): Double {
        return this.limit
    }

    override fun isLisEnabled(): Boolean {
        return true
    }

    override fun withdraw(amount: Double) : Movement {
        if( ! isAllowedWithdraw(amount) ) {
            throw InvalidWithdrawOperation("Invalid operation for this customer")
        }
        val time : LocalDateTime = LocalDateTime.now()
        val amountToDecrementFromLis = decrementBalance(amount)
        this.limit -= amountToDecrementFromLis
        return Movement(Period(time), time, amount, MovementType.WITHDRAW)
    }

    private fun isAllowedWithdraw(amount: Double): Boolean {
        return this.balance + this.limit >= amount
    }


}