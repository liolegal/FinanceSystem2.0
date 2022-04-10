package com.example.financesystem20.Interfaces

import com.example.financesystem20.Entities.Credit

interface ICreditsController {
    fun OnGetCredits(bank:String?,login:String?):ArrayList<Credit>
    fun OnAddCredit(bank:String,login:String, sum:String,term:String)
}