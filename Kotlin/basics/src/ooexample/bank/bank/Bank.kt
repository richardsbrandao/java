package ooexample.bank.bank

import ooexample.bank.bank.account.Account
import ooexample.bank.bank.account.AccountBuilder
import ooexample.bank.person.Person

class Bank(val name: String) {
    var accounts = HashMap<Person, Account>()

    fun signAccountContract(customer: Person) {
        if(customer.hasMoreThan18()) {
            accounts[customer] = AccountBuilder(bank = this, customer = customer)
                    .withCreditCard()
                    .withInternetBankEnabled()
                    .withLisEnabled()
                    .build()

            println("agreement between ${customer.name} and $name")
        } else {
            println("Denied")
        }
    }
}