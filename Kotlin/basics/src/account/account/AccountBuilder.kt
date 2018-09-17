package account.account

import account.Bank
import account.account.bankaccounts.*
import account.person.Person

class AccountBuilder(bank : Bank, customer : Person) {
    private var account: Account

    init {
        when(bank.name) {
            "ITAU" -> this.account = ItauAccount(customer = customer)
            else -> this.account = NoBankAccount(customer = customer)
        }
    }

    fun withCreditCard() : AccountBuilder {
        return this
    }

    fun withInternetBankEnabled() : AccountBuilder {
        account.enableInternetBanking()
        return this
    }

    fun withSavingAccountEnabled() : AccountBuilder {
        account.enableSavingAccount()
        return this
    }

    fun withLisEnabled() : AccountBuilder {
        account.enableLis()
        return this
    }

    fun build() : Account {
        return account
    }
}