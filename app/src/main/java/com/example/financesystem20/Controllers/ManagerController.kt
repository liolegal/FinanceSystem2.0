package com.example.financesystem20.Controllers

import com.example.financesystem20.DataBases.Clients.ClientDBManager
import com.example.financesystem20.DataBases.Credits.CreditsDBManager
import com.example.financesystem20.Entities.Client
import com.example.financesystem20.Entities.Credit
import com.example.financesystem20.Entities.Staff.Manager
import com.example.financesystem20.Interfaces.IManagerController
import com.example.financesystem20.Interfaces.IManagerView


class ManagerController(private val managerView: IManagerView, val clientDBManager: ClientDBManager,val creditsDBManager: CreditsDBManager)
    : IManagerController {
    val manager = Manager("", "", "", "", "")

    override fun OnGetCredits(bank:String) :ArrayList<Credit>{

        creditsDBManager.openDB()
        val creditsList = creditsDBManager.readDBData()
        var notApprovedCredits = ArrayList<Credit>()
        for (item in creditsList) {
            if (item.bank==bank && item.approved == 0) {
                notApprovedCredits.add(item)
            }
        }
        return notApprovedCredits

    }

    override fun OnApproveCredit(position:Int,notApprovedCredits:ArrayList<Credit>) {
        val credit = manager.approveCredit(notApprovedCredits[position])
        creditsDBManager.updateItem(credit, credit.id.toString())
        managerView.OnApproveCreditSuccess(credit,notApprovedCredits)
    }

    override fun OnGetClients(bank: String):ArrayList<Client> {
        clientDBManager.openDB()
        val clientsList = clientDBManager.readDBData()
        var notApprovedCLients = ArrayList<Client>()
        for (item in clientsList) {
            if (item.bank==bank && item.approved == 0) {
                notApprovedCLients.add(item)
            }
        }
        return notApprovedCLients
    }

    override fun OnApproveClient(position:Int,notApprovedClients:ArrayList<Client>) {
        val client = manager.approveClient(notApprovedClients[position])
        clientDBManager.updateItem(client, client.id.toString())
        managerView.OnApproveClientSuccess(client,notApprovedClients)
    }

}