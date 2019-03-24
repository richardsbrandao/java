package ooexample.bank

import ooexample.bank.bank.Bank
import ooexample.bank.bank.account.Account
import ooexample.bank.bank.account.accounttype.AccountType
import ooexample.bank.bank.errors.InvalidWithdrawOperation
import ooexample.bank.person.Gender
import ooexample.bank.person.Job
import ooexample.bank.person.Person

fun main(args: Array<String>) {
    val richard = Person(name = "Richard", gender = Gender.MALE, birthyear = 1991)
    richard.job = Job(position = "Software Engineer", salary = 10000.0) // haaaa

    val itau = Bank(name = "ITAU")

    itau.signAccountContract(richard)

    val account : Account? = itau.accounts[richard]
    println(account.toString())

    account?.enableSavingAccount()
    println(account.toString())

    account?.deposit(800.0, AccountType.CURRENT)
    account?.deposit(600.0, AccountType.SAVING)
    println(account)

    account?.withdraw(300.0, AccountType.CURRENT)
    account?.withdraw(300.0, AccountType.SAVING)
    println(account)

    account?.withdraw(600.0, AccountType.CURRENT) // DECREASE FROM LIS
    println(account)

    try {
        account?.withdraw(800.0, AccountType.CURRENT) // WITHDRAW DENIED
        throw Exception("Must throw exception")
    } catch (e: InvalidWithdrawOperation) {
        println("Withdraw denied CURRENT")
    }
    println(account)

    try {
        account?.withdraw(400.0, AccountType.SAVING) // WITHDRAW DENIED
        throw Exception("Must throw exception")
    } catch (e: InvalidWithdrawOperation) {
        println("Withdraw denied SAVING")
    }
    println(account)


}