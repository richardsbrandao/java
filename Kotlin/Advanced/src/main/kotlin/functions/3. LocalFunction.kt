package functions

class Login {
    private val repository: Map<String, String> = mapOf("richard" to "123456")

    fun login(username: String?, password: String?): Boolean {
        fun validateInput(input: String?, fieldName: String) {
            input.takeIf { !input.isNullOrBlank() } ?: throw RuntimeException("$fieldName is null")
        }

        fun perform(): Boolean {
            return repository.entries.any { it.key == username && it.value == password }
        }

        validateInput(input = username, fieldName = "Username")
        validateInput(input = password, fieldName = "Password")
        return perform()
    }
}

fun main() {
    fun authenticatedExample() {
        val isAuthenticated = Login().login("richard", "123456")
        println("isAuthenticated: $isAuthenticated")
    }

    fun notAuthenticatedExample() {
        val isAuthenticated = Login().login("richard", "0000000")
        println("isAuthenticated: $isAuthenticated")
    }

    fun errorExample() {
        val isAuthenticated = Login().login(null, "123456")
        println("isAuthenticated: $isAuthenticated")
    }

    authenticatedExample()
    notAuthenticatedExample()
    errorExample()
}
