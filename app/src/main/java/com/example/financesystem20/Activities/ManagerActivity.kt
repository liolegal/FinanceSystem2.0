package com.example.financesystem20.Activities


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import android.widget.Toast
import com.example.financesystem20.Adapters.ApprovesAdapter
import com.example.financesystem20.Adapters.ApprovesCreditAdapter
import com.example.financesystem20.Controllers.ManagerController
import com.example.financesystem20.DataBases.Clients.ClientDBManager
import com.example.financesystem20.DataBases.Credits.CreditsDBManager
import com.example.financesystem20.Entities.Client
import com.example.financesystem20.Entities.Credit
import com.example.financesystem20.Entities.Staff.Manager
import com.example.financesystem20.Interfaces.IManagerView
import com.example.financesystem20.R

class ManagerActivity : AppCompatActivity(), IManagerView {
    val clientDBManager = ClientDBManager(this)
    val creditsDBManager = CreditsDBManager(this)
    lateinit var creditApproveAdapter: ApprovesCreditAdapter
    lateinit var adapter: ApprovesAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_manager)
    }

    override fun onResume() {
        super.onResume()
        val bank = intent.extras?.getString(LoginActivity.BANK_LOGIN)
        val managerPresenter = ManagerController(this, clientDBManager, creditsDBManager)
        var notApprovedCredits = managerPresenter.OnGetCredits(bank.toString())
        var notApprovedClients = managerPresenter.OnGetClients(bank.toString())

        val clientListView = findViewById<ListView>(R.id.approve_lv)
        adapter = ApprovesAdapter(this, notApprovedClients)
        clientListView.adapter = adapter

        clientListView.setOnItemClickListener { _, view, position: Int, id: Long ->
            managerPresenter.OnApproveClient(position, notApprovedClients)
        }

        val creditsListView = findViewById<ListView>(R.id.credits_to_approve_lv);
        creditApproveAdapter = ApprovesCreditAdapter(this, notApprovedCredits)
        creditsListView.adapter = creditApproveAdapter
        creditsListView.setOnItemClickListener { _, view, position: Int, id: Long ->
            managerPresenter.OnApproveCredit(position, notApprovedCredits)
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        clientDBManager.closeDB()
    }

    override fun OnApproveCreditSuccess(credit: Credit, notApprovedCredits: ArrayList<Credit>) {
        notApprovedCredits.remove(credit)
        creditApproveAdapter.notifyDataSetChanged()
        Toast.makeText(this, "Approved", Toast.LENGTH_SHORT).show()
    }

    override fun OnApproveClientSuccess(client: Client, notApprovedClients: ArrayList<Client>) {
        notApprovedClients.remove(client)
        adapter.notifyDataSetChanged()
        Toast.makeText(this, "Approved", Toast.LENGTH_SHORT).show()
    }
}