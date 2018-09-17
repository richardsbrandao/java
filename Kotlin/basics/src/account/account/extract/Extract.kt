package account.account.extract

class Extract {
    var movements = mutableMapOf<Period, MutableList<Movement>>()

    fun add(period : Period, movement : Movement) {
        var dailyMovements : MutableList<Movement> = movements.getOrDefault(period, mutableListOf())
        dailyMovements.add(movement)
        movements[period] = dailyMovements
    }

    override fun toString(): String {
        return "Extract[movements=$movements]"
    }
}
