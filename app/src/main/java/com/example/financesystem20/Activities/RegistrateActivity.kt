package com.example.financesystem20.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.example.financesystem20.DataBases.BankSystemAccounts.BankSystemAccountNameSlass
import com.example.financesystem20.DataBases.BankSystemAccounts.BankSystemAccountsDBManager
import com.example.financesystem20.DataBases.Clients.ClientDBManager
import com.example.financesystem20.R

class RegistrateActivity : AppCompatActivity() {
    val clientDBManager = ClientDBManager(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registrate)
    }

    override fun onResume() {
        super.onResume()
        clientDBManager.openDB()
    }

    fun onClickRegistrate(view: View) {
        if (clientDBManager.getClientFromDB(findViewById<EditText>(R.id.login_registration_edit).text.toString()) == null) {
            clientDBManager.insertToDB(
                findViewById<EditText>(R.id.bank_registration_edit).text.toString(),
                findViewById<EditText>(R.id.login_registration_edit).text.toString(),
                findViewById<EditText>(R.id.password_registration_edit).text.toString(),
                findViewById<EditText>(R.id.name_registration_edit).text.toString(),
                findViewById<EditText>(R.id.surname_registration_edit).text.toString(),
                findViewById<EditText>(R.id.phone_registration_edit).text.toString(),
                findViewById<EditText>(R.id.email_registration_edit).text.toString(),
                0
            )
            Toast.makeText(this, "Welcome", Toast.LENGTH_SHORT).show()
            finish()
        }else{Toast.makeText(this, "This login is already exist", Toast.LENGTH_SHORT).show()}

    }

    override fun onDestroy() {
        super.onDestroy()
        clientDBManager.closeDB()
    }

}