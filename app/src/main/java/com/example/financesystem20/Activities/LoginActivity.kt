package com.example.financesystem20.Activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.financesystem20.DataBases.BankSystemAccounts.BankSystemAccountsDBManager
import com.example.financesystem20.R

class LoginActivity : AppCompatActivity() {
    val bankSystemAccountsManager = BankSystemAccountsDBManager(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
    }

    override fun onResume() {
        super.onResume()
        bankSystemAccountsManager.openDB()
        val bankSpinner = findViewById<Spinner>(R.id.bank_spinner)
        val banks = resources.getStringArray(R.array.Banks)
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, banks)
        bankSpinner.adapter = adapter
    }

    fun onClickLogin(view: View) {

        val inputLogin = findViewById<EditText>(R.id.login_edit)
        val inputPassword = findViewById<EditText>(R.id.password_edit)

        val dataList = bankSystemAccountsManager.readDBData()
        for (item in dataList) {
            if (item.bank == findViewById<EditText>(R.id.bank_edit).text.toString()) {
                if (item.login == inputLogin.text.toString()) {
                    if (item.password == inputPassword.text.toString()) {
                        val newIntent = Intent(this, ClientActivity::class.java)
                        newIntent.putExtra(CLIENT_LOGIN, item.login)
                        newIntent.putExtra(BANK_LOGIN, item.bank)
                        startActivity(newIntent)

                    } else {
                        Toast.makeText(this, "Wrong password", Toast.LENGTH_SHORT).show()
                    }

                } else {
                    Toast.makeText(this, "Wrong login", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    fun onClickRegistrate(view: View) {
        val newActivityIntent = Intent(this, RegistrateActivity::class.java)
        startActivity(newActivityIntent)
    }

    companion object {
        const val CLIENT_LOGIN = "client_login"
        const val BANK_LOGIN = "bank_login"
    }

}