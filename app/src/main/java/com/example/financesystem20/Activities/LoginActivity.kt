package com.example.financesystem20.Activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.financesystem20.DataBases.BankSystemAccounts.BankSystemAccountsDBManager
import com.example.financesystem20.DataBases.Clients.ClientDBManager
import com.example.financesystem20.DataBases.Personal.StaffDBManager
import com.example.financesystem20.R

class LoginActivity : AppCompatActivity() {
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
        if (loginAsPersonal) {
            loginStaffIfExists(inputBank, inputLogin, inputPassword)
        } else {
            loginClientIfExists(inputBank, inputLogin, inputPassword)
        }
    }

    fun loginStaffIfExists(inputBank: String, inputLogin: String, inputPassword: String) {
        val dataList = staffDBManager.readDBData()

        for (item in dataList) {
            if (item.bank == inputBank) {
                if (item.login == inputLogin) {
                    if (item.password == inputPassword) {
                        if (item.post == "manager") {
                            val newIntent = Intent(this, ManagerActivity::class.java)
//                            newIntent.putExtra(STAFF_LOGIN, item.login)
//                            newIntent.putExtra(MANAGER_LOGIN, item)
//                            newIntent.putExtra(BANK_LOGIN, item.bank)
                            startActivity(newIntent)
                        }
                        else if (item.post=="admin"){
                            //TODO() "start AdminActivity"
                        }
                    } else {
                        Toast.makeText(this, "Wrong password", Toast.LENGTH_SHORT).show()
                    }

                } else {
                    Toast.makeText(this, "Wrong login", Toast.LENGTH_SHORT).show()
                }
            }
        }


//        val selectedStaff = staffDBManager.getStaffFromDB(inputBank, inputLogin, inputPassword)
//        if (selectedStaff != null) {
//            val newIntent = Intent(this, ClientActivity::class.java)
//            newIntent.putExtra(CLIENT_LOGIN, selectedStaff.login)
//            newIntent.putExtra(BANK_LOGIN, selectedStaff.bank)
//            startActivity(newIntent)
//        } else {
//            Toast.makeText(this, "No matches", Toast.LENGTH_SHORT).show()
//        }
    }

    fun loginClientIfExists(inputBank: String, inputLogin: String, inputPassword: String) {
        val dataList = clientDBManager.readDBData()

        for (item in dataList) {
            if (item.bank == inputBank) {
                if (item.login == inputLogin) {
                    if (item.password == inputPassword) {
                        if (item.approved == 1) {
                            val newIntent = Intent(this, ClientActivity::class.java)
                            newIntent.putExtra(CLIENT_LOGIN, item.login)
                            newIntent.putExtra(BANK_LOGIN, item.bank)
                            startActivity(newIntent)
                        } else {
                            Toast.makeText(this, "Need an approve", Toast.LENGTH_SHORT).show()
                        }
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

    fun onClickPersonal(view: View) {
        if (loginAsPersonal) {
            findViewById<TextView>(R.id.login_as_tv).text = "Login as Staff"
            loginAsPersonal=false
        } else {
            findViewById<TextView>(R.id.login_as_tv).text = "Login as Client"
            loginAsPersonal = true
        }
    }

    override fun onDestroy() {
        super.onDestroy()

        clientDBManager.closeDB()
        staffDBManager.closeDB()
    }

    companion object {
        const val CLIENT_LOGIN = "client_login"
        const val BANK_LOGIN = "bank_login"
        const val STAFF_LOGIN="staff_login"
        const val MANAGER_LOGIN="manager login"

    }

}