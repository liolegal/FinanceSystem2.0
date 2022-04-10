package com.example.financesystem20.Interfaces

import com.example.financesystem20.Entities.Client
import com.example.financesystem20.Entities.Credit

interface IManagerController {
    fun OnGetCredits(bank:String):ArrayList<Credit>
    fun OnApproveCredit(position:Int,notApprovedCredits:ArrayList<Credit>)
    fun OnGetClients(bank:String):ArrayList<Client>
    fun OnApproveClient(position:Int,notApprovedClients:ArrayList<Client>)

}