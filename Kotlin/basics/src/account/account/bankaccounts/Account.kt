package account.account.bankaccounts

import account.account.AccountConfigurable
import account.account.AccountSetting
import account.account.Movimentable
import account.account.accounttypes.AccountDefinition
import account.account.accounttypes.AccountType
import account.account.accounttypes.CurrentAccount
import account.account.extract.Extract
import account.person.Person

abstract class Account(customer : Person) : AccountConfigurable, Movimentable {
    val extract = Extract()
    var balances = mutableMapOf<AccountType, AccountDefinition>(AccountType.CURRENT to CurrentAccount(0.0))
    var settings = AccountSetting()
}