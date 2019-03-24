package ooexample.bank.bank.account.accounttype

import ooexample.bank.bank.errors.InvalidWithdrawOperation
import ooexample.bank.bank.movement.Movement
import ooexample.bank.bank.movement.MovementType
import ooexample.bank.bank.movement.Period
import java.time.LocalDateTime

class SavingAccount(balance : Double) : AccountDefinition(balance) {
    override fun enableLis(value: Double) {
        println("WARNING: LIS not enabled for Saving Accounts")
    }

    override fun disableLis() {
        println("WARNING: LIS not enabled for Saving Accounts")
    }

    override fun getLisLimit(): Double {
        return 0.0
    }

    override fun isLisEnabled(): Boolean {
        return false
    }

    override fun withdraw(amount: Double): Movement {
        if( ! isAllowedWithdraw(amount) ) {
            throw InvalidWithdrawOperation("Invalid operation for this customer")
        }
        val time : LocalDateTime = LocalDateTime.now()
        decrementBalance(amount)
        return Movement(Period(time), time, amount, MovementType.WITHDRAW)
    }

    private fun isAllowedWithdraw(amount : Double): Boolean {
        return this.balance >= amount
    }
}