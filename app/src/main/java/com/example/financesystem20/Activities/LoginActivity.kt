package com.example.financesystem20.Activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.financesystem20.Controllers.LoginAsClientController
import com.example.financesystem20.Controllers.LoginAsStaffController
import com.example.financesystem20.DataBases.BankSystemAccounts.BankSystemAccountsDBManager
import com.example.financesystem20.DataBases.Clients.ClientDBManager
import com.example.financesystem20.DataBases.Personal.StaffDBManager
import com.example.financesystem20.Interfaces.ILoginView
import com.example.financesystem20.R

class LoginActivity : AppCompatActivity(), ILoginView {
    val clientDBManager = ClientDBManager(this)
    val staffDBManager = StaffDBManager(this)
    var loginAsPersonal = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
    }

    override fun onResume() {
        super.onResume()
        staffDBManager.openDB()
        clientDBManager.openDB()
        val bankSpinner = findViewById<Spinner>(R.id.bank_spinner)
        val banks = resources.getStringArray(R.array.Banks)
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, banks)
        bankSpinner.adapter = adapter
    }

    fun onClickLogin(view: View) {
        val bankSpinner = findViewById<Spinner>(R.id.bank_spinner)
        val inputLogin = findViewById<EditText>(R.id.login_edit).text.toString()
        val inputPassword = findViewById<EditText>(R.id.password_edit).text.toString()
        val inputBank = findViewById<EditText>(R.id.bank_edit).text.toString()
        val loginClientPresenter = LoginAsClientController(this, clientDBManager)
        val loginStaffPresenter = LoginAsStaffController(this, staffDBManager)

        if (loginAsPersonal) {
            loginStaffPresenter.OnLogin(inputBank, inputLogin, inputPassword)

        } else {
            loginClientPresenter.OnLogin(inputBank, inputLogin, inputPassword)

        }
    }


    fun onClickRegistrate(view: View) {
        val newActivityIntent = Intent(this, RegistrateActivity::class.java)
        startActivity(newActivityIntent)
    }

    fun onClickPersonal(view: View) {
        if (loginAsPersonal) {
            findViewById<TextView>(R.id.login_as_tv).text = "Login as Staff"
            loginAsPersonal = false
        } else {
            findViewById<TextView>(R.id.login_as_tv).text = "Login as Client"
            loginAsPersonal = true
        }
    }


    override fun OnLoginAsClientSuccess(message: String?, bank: String, login: String) {
        val newIntent = Intent(this, ClientActivity::class.java)
        newIntent.putExtra(CLIENT_LOGIN, login)
        newIntent.putExtra(BANK_LOGIN, bank)
        startActivity(newIntent)
    }

    override fun OnLoginAsStaffSuccess(post: String?, bank: String, login: String) {
        val newIntent = Intent(this, ManagerActivity::class.java)
                            newIntent.putExtra(STAFF_LOGIN, login)
                            newIntent.putExtra(BANK_LOGIN, bank)
                            startActivity(newIntent)
    }

    override fun OnLoginError(message: String?) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        super.onDestroy()

        clientDBManager.closeDB()
        staffDBManager.closeDB()
    }

    companion object {
        const val CLIENT_LOGIN = "client_login"
        const val BANK_LOGIN = "bank_login"
        const val STAFF_LOGIN = "staff_login"


    }
}