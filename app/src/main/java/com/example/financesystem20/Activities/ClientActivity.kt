package com.example.financesystem20.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.financesystem20.Activities.LoginActivity.Companion.CLIENT_LOGIN
import com.example.financesystem20.DataBases.BankSystemAccounts.BankSystemAccountsDBManager
import com.example.financesystem20.R

class ClientActivity : AppCompatActivity() {
    val bankSystemAccountsDBManager=BankSystemAccountsDBManager(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_client)

    }
    override fun onResume() {
        super.onResume()

        val login= intent.extras?.getString(CLIENT_LOGIN)
        findViewById<TextView>(R.id.client_info_tv).text=""
        bankSystemAccountsDBManager.openDB()
        val dataList=bankSystemAccountsDBManager.readDBData()
        for(item in dataList){
            if(item.login==login) {
                findViewById<TextView>(R.id.client_info_tv).append(item.bank)
                findViewById<TextView>(R.id.client_info_tv).append(" ")
                findViewById<TextView>(R.id.client_info_tv).append(item.login)
                findViewById<TextView>(R.id.client_info_tv).append(" ")
                findViewById<TextView>(R.id.client_info_tv).append(item.password)
                findViewById<TextView>(R.id.client_info_tv).append(" ")
            }
        }
    }
    override fun onDestroy() {
        super.onDestroy()
        bankSystemAccountsDBManager.closeDB()
    }
}