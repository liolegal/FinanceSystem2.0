package com.example.financesystem20.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.financesystem20.Activities.ClientActivity.Companion.ACCOUNT_SELECTED
import com.example.financesystem20.DataBases.BankAccounts.BankAccountsDBManager
import com.example.financesystem20.R
import org.w3c.dom.Text

class BankAccountActivity : AppCompatActivity() {
    val bankAccountsDBManager = BankAccountsDBManager(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bank_account)

    }

    override fun onResume() {
        super.onResume()
        bankAccountsDBManager.openDB()
        findViewById<TextView>(R.id.account_info_tv).text = ""
        val selectedAccountId = intent.extras?.getString(ACCOUNT_SELECTED)
        findViewById<TextView>(R.id.account_info_tv).append(selectedAccountId)

        //Delete button
        findViewById<Button>(R.id.delete_btn).setOnClickListener(){
                bankAccountsDBManager.deleteFromDb(selectedAccountId.toString())
            finish()
        }

        //Transfer button

    }


}