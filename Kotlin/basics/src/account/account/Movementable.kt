package account.account

import account.account.accounttypes.AccountType

interface Movimentable : Depositable, Withdrawable {

}

interface Depositable {
    fun deposit(amount: Double, accountType: AccountType)
}

interface Withdrawable {
    fun withdraw(amount: Double, accountType: AccountType)
}