package functions

enum class Rank(val value: Int) {
    TWO(2), THREE(3), FOUR(4), FIVE(5), SIX(6), SEVEN(7), EIGHT(8),
    NINE(9), TEN(10), JACK(11), QUEEN(12), KING(13), ACE(14);

    infix fun of(suit: Suit) = Card(this, suit)
}

enum class Suit {
    HEARTS, SPADES, DIAMONDS, CLUBS
}

data class Card(private val rank: Rank, private val suit: Suit) {
    infix fun against(card: Card): Card {
        if ( suit != card.suit ) {
            return this
        }

        return if (rank >= card.rank) {
            this
        } else {
            card
        }
    }
}

class Table {
    fun startMatch(playerOne: Card, playerTwo: Card): Card {
        return playerOne against playerTwo
    }
}

fun main() {
    val table = Table()

    val matchOne = table.startMatch(playerOne = Card(Rank.JACK, Suit.CLUBS), playerTwo = Card(Rank.EIGHT, Suit.CLUBS))
    println("Winner: $matchOne")

    val matchTwo = table.startMatch(playerOne = Rank.TWO of Suit.CLUBS, playerTwo = Rank.EIGHT of Suit.CLUBS)
    println("Winner: $matchTwo")

    val matchThree = table.startMatch(playerOne = Rank.TWO of Suit.DIAMONDS, playerTwo = Rank.QUEEN of Suit.CLUBS)
    println("Winner: $matchThree")
}
