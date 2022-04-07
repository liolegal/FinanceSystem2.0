package com.example.financesystem20.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.example.financesystem20.Activities.ClientActivity.Companion.ACCOUNT_IDBA_SELECTED
import com.example.financesystem20.Activities.ClientActivity.Companion.ACCOUNT_ID_SELECTED
import com.example.financesystem20.DataBases.BankAccounts.BankAccountsDBManager
import com.example.financesystem20.Entities.BankAccount
import com.example.financesystem20.Entities.Transfer
import com.example.financesystem20.R
import org.w3c.dom.Text

class BankAccountActivity : AppCompatActivity() {
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
        val selectedBankAccount=bankAccountsDBManager.getBankAccountFromDB(selectedAccountIdOfBankAccount.toString())

        findViewById<TextView>(R.id.account_info_tv).append(selectedAccountIdOfBankAccount)

        //Delete button
        findViewById<Button>(R.id.delete_btn).setOnClickListener() {
            bankAccountsDBManager.deleteFromDb(selectedAccountId.toString())
            finish()
        }

        //Transfer button
        findViewById<Button>(R.id.transfer_button).setOnClickListener() {
            if (findViewById<EditText>(R.id.sum_of_transfer_edit).text.isNotEmpty() && findViewById<EditText>(
                    R.id.id_of_receiver_edit
                ).text.isNotEmpty()
            ) {
                val listOfBankAccounts = bankAccountsDBManager.readDBData()
                val sum =
                    findViewById<EditText>(R.id.sum_of_transfer_edit).text.toString().toFloat()
                lateinit var sender: BankAccount
                lateinit var receiver: BankAccount
                for (item in listOfBankAccounts) {
                    if (item.idOfAccount == selectedAccountIdOfBankAccount.toString()) {
                        sender = item
                    }
                    if (item.idOfAccount == findViewById<EditText>(R.id.id_of_receiver_edit).text.toString()) {
                        receiver = item
                    }
                }
                if (sender.takeMoney(sum)) {
                    receiver.putMoney(sum)
                }
                bankAccountsDBManager.updateItem(sender, sender.id.toString())
                bankAccountsDBManager.updateItem(receiver, receiver.id.toString())
                findViewById<TextView>(R.id.account_info_tv).text = sender.countOfMoney.toString()
            }

        }
        findViewById<Button>(R.id.add_money_btn).setOnClickListener(){
            selectedBankAccount?.putMoney(100F)
            findViewById<TextView>(R.id.account_info_tv).text = selectedBankAccount?.countOfMoney.toString()
            if (selectedBankAccount != null) {
                bankAccountsDBManager.updateItem(selectedBankAccount, selectedBankAccount.id.toString())
            }
        }
    }


}