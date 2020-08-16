package functions

data class Category(val category: Category? = null, val name: String, val availability: Boolean)
data class Menu(val categories: List<Category>) {
    fun searchForEnabledCategories(): Set<String> {
        val enabledCategories = mutableSetOf<String>()
        categories.forEach {
            // it was it.category
            enabledCategories.addAll(searchForEnabledCategories(category = it))
        }
        return enabledCategories
    }

    // Show byte code with and without tailrec
    private tailrec fun searchForEnabledCategories(
        category: Category?,
        enabledCategories: MutableSet<String> = mutableSetOf()
    ): MutableSet<String> {
//        println("${category?.name}")
        if(category == null) {
            return enabledCategories
        }

        if(category.availability) {
//            println("$category")
            enabledCategories.add(category.name)
        }

        return searchForEnabledCategories(category.category, enabledCategories)
    }
}

fun main() {
    val enabledCategories = Menu(
        categories = listOf(
            Category(
                name = "A",
                category = Category(
                    name = "D",
                    category = Category(
                        name = "E",
                        availability = true
                    ),
                    availability = false
                ),
                availability = true
            ),
            Category(
                name = "B",
                category = Category(
                    name = "F",
                    availability = false
                ),
                availability = false
            ),
            Category(name = "C", availability = true)
        )
    ).searchForEnabledCategories()
    // show bytecode
    println(enabledCategories)
}


