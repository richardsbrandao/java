package account.account.bankaccounts

import account.account.accounttypes.AccountType
import account.person.Person

class NoBankAccount(private val customer : Person) : Account(customer) {
    override fun deposit(amount: Double, accountType: AccountType) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun withdraw(amount: Double, accountType: AccountType) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun enableSavingAccount() {
    }

    override fun disableSavingAccount() {
    }

    override fun enableInternetBanking() {
    }

    override fun disableInternetBanking() {
    }

    override fun enableLis() {
    }

    override fun disableLis() {
    }

}
