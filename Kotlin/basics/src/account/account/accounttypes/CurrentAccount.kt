package account.account.accounttypes

import account.account.MovementType
import account.account.extract.Movement
import account.account.extract.Period
import java.time.LocalDateTime

class CurrentAccount(balance : Double) : AccountDefinition(balance) {
    override fun deposit(amount: Double) : Movement {
        val time : LocalDateTime = LocalDateTime.now()
        balance += amount
        return Movement(Period(time), time, amount, MovementType.DEPOSIT)
    }

    override fun withdraw(amount: Double) : Movement {
        val time : LocalDateTime = LocalDateTime.now()
        return Movement(Period(time), time, amount, MovementType.WITHDRAW)
    }

    override fun toString(): String {
        return "CurrentAccount[balance=$balance]"
    }
}