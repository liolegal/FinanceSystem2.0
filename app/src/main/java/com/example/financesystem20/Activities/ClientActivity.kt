package com.example.financesystem20.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import com.example.financesystem20.Activities.LoginActivity.Companion.BANK_LOGIN
import com.example.financesystem20.Activities.LoginActivity.Companion.CLIENT_LOGIN
import com.example.financesystem20.Adapters.BankAccountsAdapter
import com.example.financesystem20.DataBases.BankAccounts.BankAccountsDBManager
import com.example.financesystem20.DataBases.BankSystemAccounts.BankSystemAccountsDBManager
import com.example.financesystem20.Entities.BankAccount
import com.example.financesystem20.Entities.Client
import com.example.financesystem20.R

class ClientActivity : AppCompatActivity() {
    val bankSystemAccountsDBManager = BankSystemAccountsDBManager(this)
    val bankAccountsDBManager = BankAccountsDBManager(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_client)

    }

    override fun onResume() {
        super.onResume()
        bankSystemAccountsDBManager.openDB()
        bankAccountsDBManager.openDB()
        val bankAccountsList = bankAccountsDBManager.readDBData()

        //Text View
        val login = intent.extras?.getString(CLIENT_LOGIN)
        val bank = intent.extras?.getString(BANK_LOGIN)
        findViewById<TextView>(R.id.client_info_tv).text=""
        findViewById<TextView>(R.id.client_info_tv).append(bank)
        findViewById<TextView>(R.id.client_info_tv).append(":  ")
        findViewById<TextView>(R.id.client_info_tv).append(login)
        findViewById<TextView>(R.id.client_info_tv).append(" ")

        //List View
        var listView = findViewById<ListView>(R.id.list_of_bank_accounts)
        var bankAccountsListOfClient = ArrayList<BankAccount>()
        for (item in bankAccountsList) {
            if (item.bank == bank && item.login==login) {
                    bankAccountsListOfClient.add(item)

            }
        }
        val adapter = BankAccountsAdapter(this, bankAccountsListOfClient)
        listView.adapter = adapter

        listView.setOnItemClickListener { _, view, position: Int, id: Long ->

            val newActivityIntent = Intent(this, BankAccountActivity::class.java)
            newActivityIntent.putExtra(ACCOUNT_SELECTED,bankAccountsListOfClient[position].id.toString())
            startActivity(newActivityIntent)
        }

        //Add button
        findViewById<Button>(R.id.add_bank_account_btn).setOnClickListener() {
            val id = (1..1000).random().toString() + "account"
            if (bank != null) {
                if (login != null) {
                    bankAccountsDBManager.insertToDB(bank, login, id, 0F)
                    bankAccountsListOfClient.add(BankAccount(bank,login,id,0F))
                    adapter.notifyDataSetChanged()
                }
            }
        }


    }



    override fun onDestroy() {
        super.onDestroy()
        bankAccountsDBManager.closeDB()
    }
    companion object{
        const val ACCOUNT_SELECTED="selected_account"
    }

}