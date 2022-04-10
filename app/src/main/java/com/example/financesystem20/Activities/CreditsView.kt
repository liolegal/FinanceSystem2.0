package com.example.financesystem20.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.example.financesystem20.Adapters.BankAccountsAdapter
import com.example.financesystem20.Adapters.CreditsAdapter
import com.example.financesystem20.DataBases.BankAccounts.BankAccountsDBManager
import com.example.financesystem20.DataBases.Banks.BanksDBManager
import com.example.financesystem20.DataBases.Credits.CreditsDBManager
import com.example.financesystem20.Entities.Bank
import com.example.financesystem20.Entities.BankAccount
import com.example.financesystem20.Entities.Credit
import com.example.financesystem20.R

class CreditsView : AppCompatActivity() {
    val creditsDBManager=CreditsDBManager(this)
    val banksDBManager= BanksDBManager(this)
    val bankAccountsDBManager=BankAccountsDBManager(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_credits_view)
    }

    override fun onResume() {
        super.onResume()
        banksDBManager.openDB()
        creditsDBManager.openDB()
        bankAccountsDBManager.openDB()

        val datalist=creditsDBManager.readDBData()
        val login = intent.extras?.getString(LoginActivity.CLIENT_LOGIN)
        val bank = intent.extras?.getString(LoginActivity.BANK_LOGIN)
        val selectedBank:Bank= banksDBManager.getBankFromDB(bank.toString())!!

        var listView = findViewById<ListView>(R.id.list_of_credits_lv)
        var creditsListOfClient = ArrayList<Credit>()
        for (item in datalist) {
            if (item.bank == bank && item.login==login && item.approved==1) {
                creditsListOfClient.add(item)

            }
        }
        val adapterCreditsView = CreditsAdapter(this, creditsListOfClient)
        listView.adapter = adapterCreditsView

        val termSpinner = findViewById<Spinner>(R.id.spinner_terms)
        val terms = resources.getStringArray(R.array.Terms)
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, terms)
        termSpinner.adapter = adapter

        var selectedTerm="3"

        termSpinner.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>,
                                        view: View, position: Int, id: Long) {
                selectedTerm=terms[position].toString()
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // write code to perform some action
            }
        }





        findViewById<Button>(R.id.add_credit_btn).setOnClickListener(){
            val sum=findViewById<EditText>(R.id.sum_of_credit).text.toString()
            var procent=0.12F
            when(selectedTerm){
                "3 months"->{
                    procent=selectedBank.percent3
                    selectedTerm="3"
                }

                "6 months"->{
                    procent=selectedBank.percent6
                    selectedTerm="6"
                }
                "12 months"->{
                    procent=selectedBank.percent12
                    selectedTerm="12"
                }
                "24 months"->{
                    procent=selectedBank.percent24
                    selectedTerm="24"
                }
                "36 months"->{
                    procent=selectedBank.percent36
                    selectedTerm="36"
                }
            }
            val id = (1..1000).random().toString() + "creditAccount"
            if (bank != null) {
                if (login != null) {
                    creditsDBManager.insertToDB(bank, login, id, sum.toInt(),selectedTerm.toInt(),procent,0)
                    bankAccountsDBManager.insertToDB(bank,login,id,sum.toInt().toFloat())
                }
            }
        }
    }
}