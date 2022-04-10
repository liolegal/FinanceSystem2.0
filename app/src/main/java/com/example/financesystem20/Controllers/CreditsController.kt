package com.example.financesystem20.Controllers


import com.example.financesystem20.DataBases.BankAccounts.BankAccountsDBManager
import com.example.financesystem20.DataBases.Banks.BanksDBManager
import com.example.financesystem20.DataBases.Credits.CreditsDBManager
import com.example.financesystem20.Entities.Bank
import com.example.financesystem20.Entities.Credit

import com.example.financesystem20.Interfaces.ICreditsController
import com.example.financesystem20.Interfaces.ICreditsView

class CreditsController(
    private val creditsView: ICreditsView,
    val creditsDBManager: CreditsDBManager,
    val bankAccountsDBManager: BankAccountsDBManager,
    val banksDBManager: BanksDBManager
) : ICreditsController {
    override fun OnGetCredits(bank: String?, login: String?):ArrayList<Credit> {
        creditsDBManager.openDB()
        val datalist=creditsDBManager.readDBData()
        var creditsListOfClient = ArrayList<Credit>()
        for (item in datalist) {
            if (item.bank == bank && item.login==login && item.approved==1) {
                creditsListOfClient.add(item)

            }
        }
        return creditsListOfClient
    }

    override fun OnAddCredit(bank: String, login: String, sum:String,term:String) {
        creditsDBManager.openDB()
        bankAccountsDBManager.openDB()
        banksDBManager.openDB()
        val selectedBank: Bank = banksDBManager.getBankFromDB(bank.toString())!!
        var procent=0.12F
        var selectedTerm="3"
        when(term){
            "3 months"->{
                procent=selectedBank.percent3
                selectedTerm="3"
            }

            "6 months"->{
                procent=selectedBank.percent6
                selectedTerm="6"
            }
            "12 months"->{
                procent=selectedBank.percent12
                selectedTerm="12"
            }
            "24 months"->{
                procent=selectedBank.percent24
                selectedTerm="24"
            }
            "36 months"->{
                procent=selectedBank.percent36
                selectedTerm="36"
            }
        }
        val id = (1..1000).random().toString() + "creditAccount"
        if (bank != null) {
            if (login != null) {
                creditsDBManager.insertToDB(bank, login, id, sum.toInt(),selectedTerm.toInt(),procent,0)
                bankAccountsDBManager.insertToDB(bank,login,id,sum.toInt().toFloat())
            }
        }
        creditsView.OnAddCreditSuccess()
    }
}