package ooexample.bank.bank.account

import ooexample.bank.bank.account.accounttype.AccountDefinition
import ooexample.bank.bank.account.accounttype.AccountType
import ooexample.bank.bank.account.accounttype.CurrentAccount
import ooexample.bank.bank.account.configurations.AccountConfigurable
import ooexample.bank.bank.account.configurations.Movementable
import ooexample.bank.bank.movement.Extract
import ooexample.bank.person.Person

abstract class Account(customer : Person) : AccountConfigurable, Movementable {
    val extract = Extract()
    var balances = mutableMapOf<AccountType, AccountDefinition>(AccountType.CURRENT to CurrentAccount(0.0))
    var settings = AccountSetting()
}