package coroutines

import kotlinx.coroutines.*

class ModelExternalService {
    suspend fun getBlockingOperation(): String {
        println("${Thread.currentThread().name} --- GET Blocking Operation ... 1s")
        delay(1100)
        return "GET RETURN"
    }

    suspend fun postBlockingOperation() {
        println("${Thread.currentThread().name} --- POST Blocking Operation ... 1s")
        delay(1000)
    }
}

class ModelService(private val modelExternalService: ModelExternalService = ModelExternalService()) {
    fun create() {
        GlobalScope.async {
            println("${Thread.currentThread().name} --- POST - GlobalScopeAsync - START")
            modelExternalService.postBlockingOperation()
            println("${Thread.currentThread().name} --- POST - GlobalScopeAsync  - END")
            println("LOG: POST - CREATED")
        }
    }

    fun get(): String {
        return runBlocking {
            println("${Thread.currentThread().name} --- GET - RunBlocking - START: ")
            val getReturn = modelExternalService.getBlockingOperation()
            println("${Thread.currentThread().name} --- GET - RunBlocking - END: ${Thread.currentThread().name}")
            getReturn
        }
    }
}

fun main() {
    val modelService = ModelService()
    println("${Thread.currentThread().name} --- MAIN / 1")
    GlobalScope.launch {
        println("${Thread.currentThread().name} --- GET - GlobalScope Launch / 1")
        val modelReturned = modelService.get()
        println(modelReturned)
    }

    GlobalScope.launch {
        println("${Thread.currentThread().name} ---  POST - GlobalScope Launch / 2")
        modelService.create()
    }

    println("${Thread.currentThread().name} --- MAIN / 2")

//    GlobalScope.launch {
////        delay(1000L)
//        println("World")
//    }
//
//    println("Hello, ")
    Thread.sleep(2000L)
}

//data class User(val userId: Long)
