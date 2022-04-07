package com.example.financesystem20.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.example.financesystem20.Controllers.RegistrateController
import com.example.financesystem20.DataBases.BankSystemAccounts.BankSystemAccountNameSlass
import com.example.financesystem20.DataBases.BankSystemAccounts.BankSystemAccountsDBManager
import com.example.financesystem20.DataBases.Clients.ClientDBManager
import com.example.financesystem20.Interfaces.IRegistrateView
import com.example.financesystem20.R

class RegistrateActivity : AppCompatActivity(), IRegistrateView {
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
        val bank = findViewById<EditText>(R.id.bank_registration_edit).text.toString()
        val login = findViewById<EditText>(R.id.login_registration_edit).text.toString()
        val password = findViewById<EditText>(R.id.password_registration_edit).text.toString()
        val name = findViewById<EditText>(R.id.name_registration_edit).text.toString()
        val surname = findViewById<EditText>(R.id.surname_registration_edit).text.toString()
        val phoneNumber = findViewById<EditText>(R.id.phone_registration_edit).text.toString()
        val email=findViewById<EditText>(R.id.email_registration_edit).text.toString()
        val registratePresenter = RegistrateController(this, clientDBManager)
        registratePresenter.OnRegistrate(bank,login,password,name,surname,phoneNumber,email)
    }

    override fun onDestroy() {
        super.onDestroy()
        clientDBManager.closeDB()
    }

    override fun OnRegistrateSuccess(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        finish()
    }

    override fun OnRegistrateError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

}