package com.example.financesystem20.Controllers

import com.example.financesystem20.Adapters.BankAccountsAdapter
import com.example.financesystem20.DataBases.BankAccounts.BankAccountsDBManager
import com.example.financesystem20.DataBases.Clients.ClientDBManager
import com.example.financesystem20.Entities.BankAccount
import com.example.financesystem20.Interfaces.IBankAccountsController
import com.example.financesystem20.Interfaces.IBankAccountsView

class BankAccountsController (private val bankAccountsView: IBankAccountsView,val bankAccountsDBManager: BankAccountsDBManager)
    :IBankAccountsController {
    override fun OnGetBankAccounts(bank:String?,login:String?):ArrayList<BankAccount>{
        bankAccountsDBManager.openDB()
        val bankAccountsList = bankAccountsDBManager.readDBData()
        var bankAccountsListOfClient = ArrayList<BankAccount>()
        for (item in bankAccountsList) {
            if (item.bank == bank && item.login==login) {
                bankAccountsListOfClient.add(item)

            }
        }
        return bankAccountsListOfClient
    }


    override fun OnAddBankAccount(bank:String?,login:String?) :BankAccount?{
        var newBankAccount:BankAccount?=null
        val id = (1..1000).random().toString() + "account"
        if (bank != null) {
            if (login != null) {
                bankAccountsDBManager.insertToDB(bank, login, id, 0F)
                newBankAccount=BankAccount(bank,login,id,0F)
            }
        }
        return newBankAccount
    }


}