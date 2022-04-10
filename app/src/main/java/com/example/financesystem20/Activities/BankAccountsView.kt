package com.example.financesystem20.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ListView
import com.example.financesystem20.Adapters.BankAccountsAdapter
import com.example.financesystem20.Controllers.BankAccountsController
import com.example.financesystem20.DataBases.BankAccounts.BankAccountsDBManager
import com.example.financesystem20.Entities.BankAccount
import com.example.financesystem20.Interfaces.IBankAccountsView
import com.example.financesystem20.R

class BankAccountsView: AppCompatActivity(),IBankAccountsView {
    val bankAccountsDBManager = BankAccountsDBManager(this)
    lateinit var adapter:BankAccountsAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bank_accounts_view)
    }

    override fun onResume() {
        super.onResume()
        val login = intent.extras?.getString(LoginActivity.CLIENT_LOGIN)
        val bank = intent.extras?.getString(LoginActivity.BANK_LOGIN)
        var listView = findViewById<ListView>(R.id.list_of_bank_accounts)
        val bankAccountsPresenter = BankAccountsController(this, bankAccountsDBManager)
        val bankAccountsListOfClient=bankAccountsPresenter.OnGetBankAccounts(bank,login)
        adapter = BankAccountsAdapter(this, bankAccountsListOfClient)
        listView.adapter = adapter

        listView.setOnItemClickListener { _, view, position: Int, id: Long ->
            OnSelectBankAccount(bankAccountsListOfClient,position)
        }

        findViewById<Button>(R.id.add_bank_account_btn).setOnClickListener() {
            bankAccountsPresenter.OnAddBankAccount(bank.toString(),login.toString(),bankAccountsListOfClient)

        }

    }
    override fun OnSelectBankAccount(bankAccountsListOfClient: ArrayList<BankAccount>,position:Int) {
            val newActivityIntent = Intent(this, BankAccountActivity::class.java)
            newActivityIntent.putExtra(ClientActivity.ACCOUNT_ID_SELECTED,bankAccountsListOfClient[position].id.toString())
            newActivityIntent.putExtra(ClientActivity.ACCOUNT_IDBA_SELECTED,bankAccountsListOfClient[position].idOfAccount)
            startActivity(newActivityIntent)

    }

    override fun OnAddBankAccountSuccess(
        bankAccount: BankAccount,
        bankAccountsListOfClient: ArrayList<BankAccount>
    ) {
        bankAccountsListOfClient.add(bankAccount)
        adapter.notifyDataSetChanged()
    }

}