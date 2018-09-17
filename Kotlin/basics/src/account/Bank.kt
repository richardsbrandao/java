package account

import account.account.bankaccounts.Account
import account.account.AccountBuilder
import account.person.Person

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
