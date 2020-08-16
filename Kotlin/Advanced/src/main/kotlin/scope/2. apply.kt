package scope

/**
 * UseCase: Configure hard objects
 * Return: Instance configured
 * Context: this
 */

class PrintoutDtoJavaClass {
    var templateId: String? = null
    var locale: String? = null
}

data class PrintoutModelClass(val templateId: String, val locale: String)

fun main() {
    val printoutModelClass = PrintoutModelClass(templateId = "TemplateID", locale = "de_DE")

    val printoutDto = PrintoutDtoJavaClass().apply {
        templateId = printoutModelClass.templateId
        locale = printoutModelClass.locale
    }

    println(printoutDto.templateId)
    println(printoutDto.locale)
}
