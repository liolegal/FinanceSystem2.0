package com.example.financesystem20.Interfaces

import com.example.financesystem20.Entities.Client
import com.example.financesystem20.Entities.Credit

interface IManagerView {

    fun OnApproveCreditSuccess(credit:Credit,notApprovedCredits:ArrayList<Credit>)
    fun OnApproveClientSuccess(client: Client, notApprovedClient:ArrayList<Client>)
}