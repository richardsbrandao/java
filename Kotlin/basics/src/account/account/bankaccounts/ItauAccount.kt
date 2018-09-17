package account.account.bankaccounts

import account.account.accounttypes.AccountDefinition
import account.account.accounttypes.AccountType
import account.account.accounttypes.SavingAccount
import account.account.extract.Movement
import account.person.Person

class ItauAccount(private val customer : Person) : Account(customer) {
    override fun deposit(amount : Double, accountType : AccountType) {
        val accountDefinition : AccountDefinition? = balances[accountType]
        val movement : Movement? = accountDefinition?.deposit(amount)
        if(movement?.isSuccess() == true) {
            extract.add(period = movement?.period, movement = movement)
        }
    }

    override fun withdraw(amount : Double, accountType: AccountType) {
    }

    override fun enableSavingAccount() {
        balances.putIfAbsent(AccountType.SAVING, SavingAccount(0.0))
        settings.savingAccountEnabled = true
    }

    override fun disableSavingAccount() {
        settings.savingAccountEnabled = false
    }

    override fun enableInternetBanking() {
        settings.internetBankingEnabled = true
    }

    override fun disableInternetBanking() {
        settings.internetBankingEnabled = false
    }

    override fun enableLis() {
        settings.lisEnabled = true
    }

    override fun disableLis() {
        settings.lisEnabled = false

    }

    override fun toString() : String {
        return "ItauAccount[balance=$balances, customerName=${customer?.name}, settings=$settings, extract=$extract]"
    }
}