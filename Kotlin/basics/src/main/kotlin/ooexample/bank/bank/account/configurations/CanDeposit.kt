package ooexample.bank.bank.account.configurations

import ooexample.bank.bank.account.accounttype.AccountType

interface CanDeposit {
    fun deposit(amount: Double, accountType: AccountType)
}