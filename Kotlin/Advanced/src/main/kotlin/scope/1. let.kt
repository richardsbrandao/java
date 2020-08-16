package scope

/**
 * UseCase: null check
 * Return: last statement
 * Context: it
 */

fun main() {
    val authorizationHeaderResolver = AuthorizationHeaderResolver()

    val validAuthorizationHeader = authorizationHeaderResolver.resolve("Bearer TOKEN")
    println("VALID = $validAuthorizationHeader")

    try {
        authorizationHeaderResolver.resolve("Bearer TOKEN INVALID_ENTRY")
    } catch (e: RuntimeException) {
        println("Error: ${e.message}")
    }

    try {
        authorizationHeaderResolver.resolve(null)
    } catch (e: RuntimeException) {
        println("Error: ${e.message}")
    }

}

data class Authorization(val schema: String?, val value: String)

class AuthorizationHeaderResolver {
    fun resolve(headerValue: String?): Authorization {
        val str = null
        return headerValue?.let {
            val authorizationSplit = it.split(" ")
            return if (authorizationSplit.size == 2) {
                Authorization(schema = authorizationSplit.component1(), value = authorizationSplit.component2())
            } else {
                throw RuntimeException(headerValue)
            }
        } ?: throw RuntimeException("NULL")
    }
}
