package higherorderfunction

fun main(args: Array<String>) {
    val filter = (0..10).filter { number -> number % 2 == 0 }
    val map = (0..10).map { number -> number * 2 }
    val reduce = (0..10).reduce { acc, number -> acc + number }

    println(filter)
    println(map)
    println(reduce)
}