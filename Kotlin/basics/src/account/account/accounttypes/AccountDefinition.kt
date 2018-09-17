package account.account.accounttypes

import account.account.extract.Movement

abstract class AccountDefinition(var balance: Double) {
    abstract fun deposit(amount : Double): Movement

    abstract fun withdraw(amount : Double): Movement
}