package account.account.accounttypes

import account.account.extract.Movement

class SavingAccount(balance : Double) : AccountDefinition(balance) {
    override fun deposit(amount: Double): Movement {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun withdraw(amount: Double): Movement {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun toString(): String {
        return "SavingAccount[balance=$balance]"
    }
}