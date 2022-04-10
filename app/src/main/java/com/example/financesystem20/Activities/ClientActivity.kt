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
import com.example.financesystem20.DataBases.Clients.ClientDBManager
import com.example.financesystem20.Entities.BankAccount
import com.example.financesystem20.Entities.Client
import com.example.financesystem20.R

class ClientActivity : AppCompatActivity() {
    val clientDBManager = ClientDBManager(this)
    val bankAccountsDBManager = BankAccountsDBManager(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_client)

    }

    override fun onResume() {
        super.onResume()
        val login = intent.extras?.getString(CLIENT_LOGIN)
        val bank = intent.extras?.getString(BANK_LOGIN)
        clientDBManager.openDB()
        val selectedClient = clientDBManager.getClientFromDB(login.toString())

        findViewById<TextView>(R.id.client_info_tv).text = "${selectedClient?.login}\n" +
                "${selectedClient?.name}" + " ${selectedClient?.surname}\n" +
                "${selectedClient?.phoneNumber}\n" + "${selectedClient?.email}"



        findViewById<Button>(R.id.show_bank_accounts_btn).setOnClickListener() {
            val newActivityIntent = Intent(this, BankAccountsView::class.java)
            newActivityIntent.putExtra(CLIENT_LOGIN, login)
            newActivityIntent.putExtra(BANK_LOGIN, bank)
            startActivity(newActivityIntent)
        }
        findViewById<Button>(R.id.show_credits_btn).setOnClickListener() {
            val newActivityIntent = Intent(this, CreditsView::class.java)
            newActivityIntent.putExtra(CLIENT_LOGIN, login)
            newActivityIntent.putExtra(BANK_LOGIN, bank)
            startActivity(newActivityIntent)
        }


    }


    override fun onDestroy() {
        super.onDestroy()
        bankAccountsDBManager.closeDB()
        clientDBManager.closeDB()
    }

    companion object {
        const val ACCOUNT_ID_SELECTED = "id"
        const val ACCOUNT_IDBA_SELECTED = "id of bankAccount"
    }

}