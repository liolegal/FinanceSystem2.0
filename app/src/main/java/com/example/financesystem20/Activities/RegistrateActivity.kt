package com.example.financesystem20.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.example.financesystem20.DataBases.BankSystemAccounts.BankSystemAccountNameSlass
import com.example.financesystem20.DataBases.BankSystemAccounts.BankSystemAccountsDBManager
import com.example.financesystem20.R

class RegistrateActivity : AppCompatActivity() {
    val bankSystemAccountsManager=BankSystemAccountsDBManager(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registrate)
    }
    override fun onResume() {
        super.onResume()
        bankSystemAccountsManager.openDB()
    }
    fun onClickRegistrate(view: View){
        bankSystemAccountsManager.insertToDB(
            findViewById<EditText>(R.id.bank_registration_edit).text.toString(),
            findViewById<EditText>(R.id.login_registration_edit).text.toString(),
            findViewById<EditText>(R.id.password_registration_edit).text.toString()
        )
        Toast.makeText(this, "Good!", Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        bankSystemAccountsManager.closeDB()
    }

}