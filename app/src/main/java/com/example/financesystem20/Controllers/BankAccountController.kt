package com.example.financesystem20.Controllers

import android.widget.EditText
import android.widget.TextView
import com.example.financesystem20.DataBases.BankAccounts.BankAccountsDBManager
import com.example.financesystem20.DataBases.Clients.ClientDBManager
import com.example.financesystem20.Entities.BankAccount
import com.example.financesystem20.Interfaces.IBankAccountController
import com.example.financesystem20.Interfaces.IBankAccountView
import com.example.financesystem20.R


class BankAccountController(
    private val bankAccountView: IBankAccountView,
    val bankAccountsDBManager: BankAccountsDBManager
) :
    IBankAccountController {
    override fun OnTransfer(
        sumOfTransfer: String,
        idOfReceiver: String,
        selectedAccountIdOfBankAccount: String
    ) {
        bankAccountsDBManager.openDB()
        if (sumOfTransfer.isNotEmpty() && idOfReceiver.isNotEmpty()
        ) {
            val listOfBankAccounts = bankAccountsDBManager.readDBData()
            val sum = sumOfTransfer.toFloat()
            lateinit var sender: BankAccount
            var receiver: BankAccount? = null
            for (item in listOfBankAccounts) {
                if (item.idOfAccount == selectedAccountIdOfBankAccount) {
                    sender = item
                }
                if (item.idOfAccount == idOfReceiver) {
                    receiver = item
                }
            }
            if (receiver != null) {
                if (sender.takeMoney(sum)) {
                    receiver.putMoney(sum)
                } else {
                    bankAccountView.OnActionError("Not enough money")
                }
                bankAccountsDBManager.updateItem(sender, sender.id.toString())
                bankAccountsDBManager.updateItem(receiver, receiver.id.toString())
                bankAccountView.OnTransferSuccess(sender)
            }else {
                bankAccountView.OnActionError("No such receivers")
            }

        }else {
            bankAccountView.OnActionError("Fill in all the fields")
        }
    }

    override fun OnDelete(id: String) {
        bankAccountsDBManager.deleteFromDb(id)
        bankAccountView.OnDeleteSuccess("Bank Account is deleted")
    }

    override fun OnAddMoney(selectedBankAccount: BankAccount?) {

        selectedBankAccount?.putMoney(100F)
        if (selectedBankAccount != null) {
            bankAccountsDBManager.updateItem(
                selectedBankAccount,
                selectedBankAccount.id.toString()
            )
        }
        bankAccountView.OnAddMoneySuccess(selectedBankAccount)

    }

    override fun OnTakeMoney(selectedBankAccount: BankAccount?) {
        if (selectedBankAccount?.countOfMoney!! >= 100F) {
            selectedBankAccount.takeMoney(100F)
            bankAccountsDBManager.updateItem(
                selectedBankAccount,
                selectedBankAccount.id.toString()
            )
            bankAccountView.OnTakeMoneySuccess(selectedBankAccount)
        } else {
            bankAccountView.OnActionError("Not enough money")
        }
    }

}