package coroutines

import kotlinx.coroutines.*

class Repository {
    suspend fun fetch(delayTime: Long, requestBody: Any): Any {
        log(this.javaClass.name, "REQUEST START / fetch $requestBody")
        delay(delayTime)
        return requestBody
    }

    suspend fun post(delayTime: Long, requestBody: Any?) {
        log(this.javaClass.name, "REQUEST START / post $requestBody")
        delay(delayTime)
    }
}

class LoginController(private val repository: Repository = Repository()) {
    fun login(requestBody: Any?): Any {
        log(this.javaClass.name, "REQUEST START / Login $requestBody")
        return runBlocking {
            repository.fetch(100L, requestBody!!)
        }
    }
}

class LogoutController(private val repository: Repository = Repository()) {
    fun logout(requestBody: Any?) {
        log(this.javaClass.name, "REQUEST START / Logout $requestBody")
        GlobalScope.async { repository.post(100L, requestBody) }
    }
}

class DeviceController(private val repository: Repository = Repository()) {
    fun login(requestBody: Any?): Any {
        log(this.javaClass.name, "REQUEST START / Device $requestBody")
        return runBlocking {
            repository.fetch(100L, requestBody!!)
        }
    }
}

class WebServer {
    fun request(uri: String, requestBody: Any?): Any? {
        log(this.javaClass.name, "REQUEST $uri - $requestBody")
        return when(uri) {
            "/login" -> LoginController().login(requestBody)
            "/device" -> DeviceController().login(requestBody)
            "/logout" -> LogoutController().logout(requestBody)
            else -> println("ELSE")
        }
    }
}

data class UsernameAndPassword(val username: String, val password: String)
data class Device(val deviceUuid: String)

fun main() {
    log("MAIN", "Start Coroutines")
    val webServer = WebServer()

    fun login(username: String) {
        val loginResult = webServer.request(uri = "/login", requestBody = UsernameAndPassword(username = username, password = "123456"))
        log("-- LoginResult", loginResult)
    }

    fun device(id: String) {
        val deviceResult = webServer.request(uri = "/device", requestBody = Device(deviceUuid = id))
        log("-- DeviceResult", deviceResult)
    }

    fun logout(id: String) {
        val logoutResult = webServer.request(uri = "/logout", requestBody = Device(deviceUuid = id))
        log("-- LogoutResult", "$logoutResult id: $id")
    }

    listOf(
        "/login" to "r.brandao",
        "/login" to "a.ash",
        "/device" to "1",
        "/login" to "f.siqueira",
        "/logout" to "11",
        "/login" to "e.heinen",
        "/device" to "2",
        "/login" to "f.valente",
        "/logout" to "22",
        "/login" to "t.bessa",
        "/device" to "3",
        "/login" to "s.maroufi",
        "/login" to "n.reymund",
        "/logout" to "33",
        "/device" to "4",
        "/login" to "a.paschoal",
        "/device" to "5",
        "/logout" to "44",
        "/device" to "6",
        "/device" to "7"
    ).forEach {
        GlobalScope.launch {
            when(it.first) {
                "/login" -> login(it.second)
                "/device" -> device(it.second)
                "/logout" -> logout(it.second)
            }
        }
    }

    Thread.sleep(10000)
}

fun log(javaClass: String, content: Any?) = println("$javaClass - ${Thread.currentThread().name} - $content")
