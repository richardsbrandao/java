package functions

class Client {
    fun perform(method: String) {
        println("Performing HTTP REQUEST: $method")
    }
}

class Micrometer {
    fun send() {
        println("metric sent")
    }
}

class OrderService(private val client: Client = Client(), private val micrometer: Micrometer = Micrometer()) {
    fun post() {
        println("Prepare Request Body ...")
        performRequest {
            client.perform("POST")
        }

    }

    fun get() {
        println("Preparing nothing :D")

        performRequest {
            client.perform("GET")
        }

    }

    private inline fun performRequest(function: () -> Unit) {
        micrometer.send()
        function.invoke()
    }
}

fun main() {
    OrderService().get()
    println("----------")
    OrderService().post()
    // Action -> show bytecode kotlin -> Decompile
}
