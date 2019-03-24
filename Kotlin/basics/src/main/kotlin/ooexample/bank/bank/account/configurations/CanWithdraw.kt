package ooexample.bank.bank.account.configurations

import ooexample.bank.bank.account.accounttype.AccountType

interface CanWithdraw {
    fun withdraw(amount: Double, accountType: AccountType)
}