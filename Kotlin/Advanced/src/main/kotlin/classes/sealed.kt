package classes

enum class DeviceBulkValidationType {
    EMPTY_DEVICE_ID, EMPTY_RPS_ID, DEVICE_ID_ALREADY_EXISTS, DUPLICATED_DEVICE_ID, DEVICE_CREATED,
    DEVICE_NOT_CREATED, INVALID_ENABLED
}

data class DeviceBulkResponseItem(val validationType: DeviceBulkValidationType)

sealed class DeviceBulkResponse
data class Success(val devicesCreated: Int, val devicesFailed: Int): DeviceBulkResponse()
data class Fail(
    val duplicatedDeviceIds: List<DeviceBulkResponseItem>,
    val emptyDeviceIds: List<DeviceBulkResponseItem>,
    val emptyRpsIds: List<DeviceBulkResponseItem>,
    val devicesAlreadyRegistered: List<DeviceBulkResponseItem>,
    val invalidEnabled: List<DeviceBulkResponseItem>
): DeviceBulkResponse()


class DeviceBulkResponseFactory {
    fun create(deviceBulkResponseItems: List<DeviceBulkResponseItem>): DeviceBulkResponse {
        fun filterResponseByType(validationType: DeviceBulkValidationType): List<DeviceBulkResponseItem> {
            return deviceBulkResponseItems.filter { deviceBulkResponseItem ->
                deviceBulkResponseItem.validationType == validationType
            }
        }

        val devicesCreated = filterResponseByType(DeviceBulkValidationType.DEVICE_CREATED)
        val devicesNotCreated = filterResponseByType(DeviceBulkValidationType.DEVICE_NOT_CREATED)

        if (devicesCreated.isNotEmpty() || devicesNotCreated.isNotEmpty()) {
            return Success(devicesCreated = devicesCreated.size, devicesFailed = devicesNotCreated.size)
        }

        val duplicatedDeviceIds = filterResponseByType(DeviceBulkValidationType.DUPLICATED_DEVICE_ID)
        val emptyDeviceIds = filterResponseByType(DeviceBulkValidationType.EMPTY_DEVICE_ID)
        val emptyRpsIds = filterResponseByType(DeviceBulkValidationType.EMPTY_RPS_ID)
        val devicesAlreadyRegistered = filterResponseByType(DeviceBulkValidationType.DEVICE_ID_ALREADY_EXISTS)
        val invalidEnabled = filterResponseByType(DeviceBulkValidationType.INVALID_ENABLED)

        return Fail(
            duplicatedDeviceIds = duplicatedDeviceIds,
            emptyDeviceIds = emptyDeviceIds,
            emptyRpsIds = emptyRpsIds,
            devicesAlreadyRegistered = devicesAlreadyRegistered,
            invalidEnabled = invalidEnabled
        )
    }
}

fun main() {
    val deviceBulkResponseItemsSucceed = listOf(
        DeviceBulkResponseItem(DeviceBulkValidationType.DEVICE_CREATED),
        DeviceBulkResponseItem(DeviceBulkValidationType.DEVICE_NOT_CREATED),
        DeviceBulkResponseItem(DeviceBulkValidationType.DEVICE_CREATED),
        DeviceBulkResponseItem(DeviceBulkValidationType.DEVICE_NOT_CREATED),
        DeviceBulkResponseItem(DeviceBulkValidationType.DEVICE_CREATED),
        DeviceBulkResponseItem(DeviceBulkValidationType.EMPTY_RPS_ID),
        DeviceBulkResponseItem(DeviceBulkValidationType.DEVICE_CREATED),
        DeviceBulkResponseItem(DeviceBulkValidationType.EMPTY_DEVICE_ID),
        DeviceBulkResponseItem(DeviceBulkValidationType.DEVICE_CREATED),
        DeviceBulkResponseItem(DeviceBulkValidationType.DEVICE_CREATED),
        DeviceBulkResponseItem(DeviceBulkValidationType.DEVICE_ID_ALREADY_EXISTS),
        DeviceBulkResponseItem(DeviceBulkValidationType.DEVICE_CREATED),
        DeviceBulkResponseItem(DeviceBulkValidationType.DEVICE_ID_ALREADY_EXISTS),
        DeviceBulkResponseItem(DeviceBulkValidationType.DEVICE_CREATED)
    )

    // Success
    println(DeviceBulkResponseFactory().create(deviceBulkResponseItemsSucceed))

    val deviceBulkResponseItemsFailed = listOf(
        DeviceBulkResponseItem(DeviceBulkValidationType.DEVICE_ID_ALREADY_EXISTS),
        DeviceBulkResponseItem(DeviceBulkValidationType.EMPTY_RPS_ID),
        DeviceBulkResponseItem(DeviceBulkValidationType.INVALID_ENABLED),
        DeviceBulkResponseItem(DeviceBulkValidationType.EMPTY_RPS_ID),
        DeviceBulkResponseItem(DeviceBulkValidationType.DEVICE_ID_ALREADY_EXISTS)
    )
    println(DeviceBulkResponseFactory().create(deviceBulkResponseItemsFailed))
    // Fail
}
