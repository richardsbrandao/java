package functions

enum class OrderState {
    CREATED, ACCEPTED, REJECTED, DISPATCHED
}

enum class OrderStateDto {
    CREATED, ACCEPTED, REJECTED, DISPATCHED
}

data class Vendor(
    val id: String,
    val name: String
)

data class VendorDto(
    val id: String,
    val name: String
)

data class Order(
    val id: String,
    val state: OrderState,
    val platformKey: String,
    val shortCode: String?,
    val vendor: Vendor
)

data class OrderDto(
    val id: String,
    val state: OrderStateDto,
    val platformKey: String,
    val shortCode: String?,
    val vendor: VendorDto
)

fun order(block: OrderBuilder.() -> Unit): Order = OrderBuilder().apply(block).build()

@OrderMarker
class OrderBuilder {
    lateinit var id: String
    lateinit var state: OrderState
    lateinit var platformKey: String
    var shortCode: String? = null
    lateinit var vendor: Vendor

    fun vendor(block: VendorBuilder.() -> Unit) {
        vendor = VendorBuilder().apply(block).build()
    }

    fun build(): Order {
        return Order(id = id, state = state, platformKey = platformKey, shortCode = shortCode, vendor = vendor)
    }
}

@OrderMarker
class VendorBuilder {
    lateinit var id: String
    lateinit var name: String

    fun build(): Vendor {
        return Vendor(id = id, name = name)
    }
}

class OrderMapper {
    fun map(orderDto: OrderDto): Order {
        return order {
            id = orderDto.id
            state = OrderState.valueOf(orderDto.state.toString())
            platformKey = orderDto.platformKey
            shortCode = orderDto.shortCode

            vendor {
                id = orderDto.vendor.id
                name = orderDto.vendor.name
            }
        }
    }
}

fun main() {
    val orderDto = OrderDto(
        id = "1",
        state = OrderStateDto.ACCEPTED,
        platformKey = "FO_CA",
        shortCode = "321321",
        vendor = VendorDto(id = "12345", name = "Restaurant")
    )

    println(OrderMapper().map(orderDto))
}

@DslMarker
annotation class OrderMarker
