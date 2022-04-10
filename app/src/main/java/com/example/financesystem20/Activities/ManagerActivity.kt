package com.example.financesystem20.Activities


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import com.example.financesystem20.Adapters.ApprovesAdapter
import com.example.financesystem20.Adapters.ApprovesCreditAdapter
import com.example.financesystem20.DataBases.Clients.ClientDBManager
import com.example.financesystem20.DataBases.Credits.CreditsDBManager
import com.example.financesystem20.Entities.Client
import com.example.financesystem20.Entities.Credit
import com.example.financesystem20.Entities.Staff.Manager
import com.example.financesystem20.R

class ManagerActivity : AppCompatActivity() {
    val clientDBManager = ClientDBManager(this)
    val creditsDBManager = CreditsDBManager(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_manager)
    }

    override fun onResume() {
        super.onResume()
        clientDBManager.openDB()
        creditsDBManager.openDB()
        val manager = Manager("", "", "", "", "")
        val clientsList = clientDBManager.readDBData()
        var notApprovedCLients = ArrayList<Client>()
        for (item in clientsList) {
            if (item.approved == 0) {
                notApprovedCLients.add(item)
            }
        }
        val creditsList = creditsDBManager.readDBData()
        var notApprovedCredits = ArrayList<Credit>()
        for (item in creditsList) {
            if (item.approved == 0) {
                notApprovedCredits.add(item)
            }
        }

        //ListView
        val clientListView = findViewById<ListView>(R.id.approve_lv)
        val adapter = ApprovesAdapter(this, notApprovedCLients)
        clientListView.adapter = adapter

        clientListView.setOnItemClickListener { _, view, position: Int, id: Long ->
            val client = manager.approveClient(notApprovedCLients[position])
            clientDBManager.updateItem(client, client.id.toString())
            notApprovedCLients.remove(client)
            adapter.notifyDataSetChanged()
        }
        val creditsListView = findViewById<ListView>(R.id.credits_to_approve_lv);
        val creditApproveAdapter = ApprovesCreditAdapter(this, notApprovedCredits)
        creditsListView.adapter = creditApproveAdapter
        creditsListView.setOnItemClickListener { _, view, position: Int, id: Long ->
            val credit = manager.approveCredit(notApprovedCredits[position])
            creditsDBManager.updateItem(credit, credit.id.toString())
            notApprovedCredits.remove(credit)
            adapter.notifyDataSetChanged()
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        clientDBManager.closeDB()
    }
}