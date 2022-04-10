package com.example.financesystem20.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.example.financesystem20.Adapters.BankAccountsAdapter
import com.example.financesystem20.Adapters.CreditsAdapter
import com.example.financesystem20.Controllers.CreditsController
import com.example.financesystem20.DataBases.BankAccounts.BankAccountsDBManager
import com.example.financesystem20.DataBases.Banks.BanksDBManager
import com.example.financesystem20.DataBases.Credits.CreditsDBManager
import com.example.financesystem20.Entities.Bank
import com.example.financesystem20.Entities.BankAccount
import com.example.financesystem20.Entities.Credit
import com.example.financesystem20.Interfaces.ICreditsView
import com.example.financesystem20.R

class CreditsView : AppCompatActivity(),ICreditsView {
    val creditsDBManager=CreditsDBManager(this)
    val banksDBManager= BanksDBManager(this)
    val bankAccountsDBManager=BankAccountsDBManager(this)
    lateinit var adapterCreditsView:CreditsAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_credits_view)
    }

    override fun onResume() {
        super.onResume()
        var listView = findViewById<ListView>(R.id.list_of_credits_lv)
        val login = intent.extras?.getString(LoginActivity.CLIENT_LOGIN)
        val bank = intent.extras?.getString(LoginActivity.BANK_LOGIN)
        val creditsPresenter=CreditsController(this,creditsDBManager,bankAccountsDBManager,banksDBManager)
        val creditsListOfClient=creditsPresenter.OnGetCredits(bank,login)
        adapterCreditsView = CreditsAdapter(this, creditsListOfClient)
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
            creditsPresenter.OnAddCredit(bank.toString(),login.toString(),sum,selectedTerm)
        }
    }

    override fun OnAddCreditSuccess() {
        Toast.makeText(this, "Now u need an approve", Toast.LENGTH_SHORT).show()
    }
}