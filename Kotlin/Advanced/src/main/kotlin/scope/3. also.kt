package scope

/**
 * UseCase: Addition operation after execute something task successfuly
 * Return: -
 * Context: it
 */

class HttpClient {
    fun post(model: String): String {
        if (model == "ERROR") {
            throw RuntimeException(model)
        }
        println("printing.... $model")
        return model
    }
}

class MetricsService {
    fun increment(result: String, operation: String) {
        println("METRIC: $result - $operation")
    }
}

class PrintoutService(
    private val httpClient: HttpClient = HttpClient(),
    private val metricsService: MetricsService = MetricsService()
) {
    fun printout(model: String): String {
        return try {
            this.httpClient.post(model).also {
                metricsService.increment("SUCCESS", "POST")
            }
        } catch (e: Exception) {
            println("${e.message}").also {
                metricsService.increment("ERROR", "POST")
            }
            throw e
        }
    }
}

fun main() {
    val result = PrintoutService().printout("MODEL")
    println(result)
    PrintoutService().printout("ERROR")
}
