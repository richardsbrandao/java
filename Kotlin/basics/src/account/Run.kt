package account
import account.account.accounttypes.AccountType
import account.account.bankaccounts.Account
import account.person.Gender
import account.person.Job
import account.person.Person

fun main(args: Array<String>) {
    val richard = Person(name = "Richard", gender = Gender.MALE, birthyear = 1991)
    richard.job = Job(position = "Software Engineer", salary = 10000.0) // haaaa

    val itau = Bank(name = "ITAU")

    itau.signAccountContract(richard)

    val account : Account? = itau.accounts[richard]
    println(account.toString())

    account?.enableSavingAccount()
    println(account.toString())

    account?.deposit(100.0, AccountType.CURRENT)
//    account?.deposit(600.0, AccountType.SAVING)
    println(account)
}