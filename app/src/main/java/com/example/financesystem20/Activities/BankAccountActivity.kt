package com.example.financesystem20.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.financesystem20.Activities.ClientActivity.Companion.ACCOUNT_IDBA_SELECTED
import com.example.financesystem20.Activities.ClientActivity.Companion.ACCOUNT_ID_SELECTED
import com.example.financesystem20.Controllers.BankAccountController
import com.example.financesystem20.Controllers.BankAccountsController
import com.example.financesystem20.DataBases.BankAccounts.BankAccountsDBManager
import com.example.financesystem20.Entities.BankAccount
import com.example.financesystem20.Entities.Transfer
import com.example.financesystem20.Interfaces.IBankAccountView
import com.example.financesystem20.R
import org.w3c.dom.Text

class BankAccountActivity : AppCompatActivity(), IBankAccountView {
    val bankAccountsDBManager = BankAccountsDBManager(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bank_account)

    }

    override fun onResume() {
        super.onResume()
        bankAccountsDBManager.openDB()
        findViewById<TextView>(R.id.account_info_tv).text = ""
        val selectedAccountId = intent.extras?.getString(ACCOUNT_ID_SELECTED)
        val selectedAccountIdOfBankAccount = intent.extras?.getString(ACCOUNT_IDBA_SELECTED)
        val selectedBankAccount =
            bankAccountsDBManager.getBankAccountFromDB(selectedAccountIdOfBankAccount.toString())
        val bankAccountPresenter = BankAccountController(this, bankAccountsDBManager)

        findViewById<TextView>(R.id.account_info_tv).text =
            selectedAccountIdOfBankAccount + "\n" + selectedBankAccount?.countOfMoney.toString()

        findViewById<Button>(R.id.delete_btn).setOnClickListener() {
            bankAccountPresenter.OnDelete(selectedAccountId.toString())

        }
        findViewById<Button>(R.id.transfer_button).setOnClickListener() {
            val sum = findViewById<EditText>(R.id.sum_of_transfer_edit).text.toString()
            val idOfReceiver = findViewById<EditText>(R.id.id_of_receiver_edit).text.toString()
            bankAccountPresenter.OnTransfer(sum,idOfReceiver,selectedAccountIdOfBankAccount.toString())
        }
        findViewById<Button>(R.id.add_money_btn).setOnClickListener() {
            bankAccountPresenter.OnAddMoney(selectedBankAccount)
        }
        findViewById<Button>(R.id.cash_btn).setOnClickListener() {
            bankAccountPresenter.OnTakeMoney(selectedBankAccount)
        }
    }

    override fun OnAddMoneySuccess(selectedBankAccount: BankAccount?) {
        findViewById<TextView>(R.id.account_info_tv).text =
            selectedBankAccount?.idOfAccount + "\n" + selectedBankAccount?.countOfMoney.toString()+" $"
    }

    override fun OnTakeMoneySuccess(selectedBankAccount: BankAccount?) {
        findViewById<TextView>(R.id.account_info_tv).text =
            selectedBankAccount?.idOfAccount + "\n" + selectedBankAccount?.countOfMoney.toString()+" $"
    }

    override fun OnActionError(message: String?) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun OnDeleteSuccess(message: String?) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        finish()
    }

    override fun OnTransferSuccess(selectedBankAccount: BankAccount?) {
        findViewById<TextView>(R.id.account_info_tv).text =
            selectedBankAccount?.idOfAccount + "\n" + selectedBankAccount?.countOfMoney.toString()+" $"
    }


}