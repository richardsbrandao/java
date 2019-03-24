package ooexample.bank.bank.account

class AccountSetting {

    var internetBankingEnabled = false
    var savingAccountEnabled = false
    var lisEnabled = false

    override fun toString(): String {
        return "AccountSettings[internetBankingEnabled=$internetBankingEnabled, " +
                "savingAccountEnabled=$savingAccountEnabled, lisEnabled=$lisEnabled]"
    }

}