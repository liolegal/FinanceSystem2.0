package com.example.financesystem20.Activities


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import com.example.financesystem20.Adapters.ApprovesAdapter
import com.example.financesystem20.DataBases.Clients.ClientDBManager
import com.example.financesystem20.Entities.Client
import com.example.financesystem20.Entities.Staff.Manager
import com.example.financesystem20.R

class ManagerActivity : AppCompatActivity() {
    val clientDBManager=ClientDBManager(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_manager)
    }

    override fun onResume() {
        super.onResume()
        clientDBManager.openDB()
//        val login = intent.extras?.getString(LoginActivity.STAFF_LOGIN)
//        val bank = intent.extras?.getString(LoginActivity.BANK_LOGIN)
//        //val manager=получчить менеджера
        val manager= Manager("","","","","")
        //созжать объект менеджер
        val clientsList=clientDBManager.readDBData()
        var notApprovedCLients=ArrayList<Client>()
        for(item in clientsList){
            if(item.approved==0){
                notApprovedCLients.add(item)
            }
        }

        //ListView
        val listView=findViewById<ListView>(R.id.approve_lv)
        val adapter = ApprovesAdapter(this, notApprovedCLients)
        listView.adapter = adapter

        listView.setOnItemClickListener { _, view, position: Int, id: Long ->
            val client=manager.approve(notApprovedCLients[position])
            clientDBManager.updateItem(client,client.id.toString())
            notApprovedCLients.remove(client)
            adapter.notifyDataSetChanged()
        }


    }
    override fun onDestroy() {
        super.onDestroy()
        clientDBManager.closeDB()
    }
}