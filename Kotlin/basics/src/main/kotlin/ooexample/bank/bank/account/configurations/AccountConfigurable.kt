package ooexample.bank.bank.account.configurations

interface AccountConfigurable {
    fun enableSavingAccount()
    fun disableSavingAccount()

    fun enableInternetBanking()
    fun disableInternetBanking()

    fun enableLis()
    fun disableLis()
}