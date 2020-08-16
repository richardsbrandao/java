package scope

/**
 * UseCase: it creates a scope to run an operation over an object and get the result
 * Return: last statement
 * Context: this
 */

data class PlatformRestaurantInfo(val platformRestaurantId: String, val platformKey: String)
data class Menu(val id: String)
data class MenuDto(val id: String)

class Validator {
    fun validate(platformRestaurantInfo: PlatformRestaurantInfo) {

    }
}
class MenuService {
    fun findById(platformRestaurantId: String): Menu {
        return Menu(platformRestaurantId)
    }
}
class MenuMapper {
    fun map(menu: Menu): MenuDto {
        return MenuDto(menu.id)
    }
}

fun main() {
    val responseRun = PlatformRestaurantInfo("1", "FO_SG").run {
        Validator().validate(this)
        val menu = MenuService().findById(this.platformRestaurantId)
        MenuMapper().map(menu)
    }
    println(responseRun)

    val responseWith = with(PlatformRestaurantInfo("1", "FO_SG")) {
        Validator().validate(this)
        val menu = MenuService().findById(this.platformRestaurantId)
        MenuMapper().map(menu)
    }
    println(responseWith)
}
