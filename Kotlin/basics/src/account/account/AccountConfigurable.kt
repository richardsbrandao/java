package account.account

interface AccountConfigurable {
    fun enableSavingAccount()
    fun disableSavingAccount()

    fun enableInternetBanking()
    fun disableInternetBanking()

    fun enableLis()
    fun disableLis()
}