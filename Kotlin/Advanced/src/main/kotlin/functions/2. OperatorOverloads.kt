package functions

import java.util.*

data class Money(val currency: Currency, val amount: Int) {
    operator fun plus(money: Money) = Money(currency = currency, amount = amount + money.amount)
    operator fun minus(money: Money) = Money(currency = currency, amount = amount - money.amount)
    operator fun not(): Boolean = amount <= 0
    operator fun inc() = Money(currency = currency, amount = amount + 1)
    operator fun dec() = Money(currency = currency, amount = amount - 1)
    operator fun contains(money: Money): Boolean = money.currency == currency && money.amount <= amount
    operator fun compareTo(other: Money): Int = amount.compareTo(other.amount)

    override fun toString(): String {
        val cents = amount % 100
        val value = amount / 100
        return "$currency $value,${cents.toString().padEnd(2, '0')}"
    }
}


fun main() {
    val currency = Currency.getInstance("EUR")
    var balance = Money(currency = currency, amount = 1_000)
    print("1. ")
    println(balance)

    balance += Money(currency = currency, amount = 500)
    print("2. ")
    println(balance)

    balance -= Money(currency = currency, amount = 200)
    print("3. ")
    println(balance)

    print("4. ")
    println(!balance)

    print("5. ")
    println(!Money(currency = currency, amount = 0))

    balance++
    print("6. ")
    println(balance)

    balance--
    print("7. ")
    println(balance)

    print("8. ")
    println(Money(currency = currency, amount = 1_000) in balance)
    print("9. ")
    println(Money(currency = currency, amount = 200_000) in balance)

    print("10. ")
    println(Money(currency = currency, amount = 1_000) > Money(currency = currency, amount = 2_000))
    print("11. ")
    println(Money(currency = currency, amount = 1_000) < Money(currency = currency, amount = 2_000))
}
