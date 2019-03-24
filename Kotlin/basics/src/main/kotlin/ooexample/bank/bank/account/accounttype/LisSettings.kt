package ooexample.bank.bank.account.accounttype

interface LisSettings {
    fun enableLis(value: Double)
    fun disableLis()
    fun getLisLimit() : Double
    fun isLisEnabled() : Boolean
}
