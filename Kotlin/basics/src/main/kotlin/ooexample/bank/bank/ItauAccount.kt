package ooexample.bank.bank

import ooexample.bank.bank.account.Account
import ooexample.bank.bank.account.accounttype.AccountDefinition
import ooexample.bank.bank.account.accounttype.AccountType
import ooexample.bank.bank.account.accounttype.SavingAccount
import ooexample.bank.bank.movement.Movement
import ooexample.bank.person.Person

class ItauAccount(private val customer : Person) : Account(customer) {
    val DEFAULT_LIS_VALUE : Double = 700.0

    override fun deposit(amount : Double, accountType : AccountType) {
        val accountDefinition : AccountDefinition? = balances[accountType]
        val movement : Movement? = accountDefinition?.deposit(amount)
        if(movement?.isSuccess() == true) {
            extract.add(period = movement?.period, movement = movement)
        }
    }

    override fun withdraw(amount : Double, accountType: AccountType) {
        val accountDefinition : AccountDefinition? = balances[accountType]
        if(accountDefinition != null) {
           val movement: Movement = accountDefinition.withdraw(amount)
           extract.add(period = movement.period, movement = movement)
        }
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
        this.balances[AccountType.CURRENT]?.enableLis(DEFAULT_LIS_VALUE)
        settings.lisEnabled = true
    }

    override fun disableLis() {
        this.balances[AccountType.CURRENT]?.disableLis()
        settings.lisEnabled = false

    }

    override fun toString() : String {
        return "ItauAccount[balance=$balances, customerName=${customer?.name}, settings=$settings, extract=$extract]"
    }
}