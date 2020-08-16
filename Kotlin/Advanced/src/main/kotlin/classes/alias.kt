package classes

data class Topping(val id: String, val ingredients: Ingredients)

typealias Ingredients = List<String>

fun main() {
    val topping = Topping(id = "1", ingredients = listOf("1", "2", "3"))
    println(topping)
}
