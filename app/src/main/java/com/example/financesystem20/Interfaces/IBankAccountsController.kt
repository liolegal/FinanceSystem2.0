package com.example.financesystem20.Interfaces

import com.example.financesystem20.Adapters.BankAccountsAdapter
import com.example.financesystem20.Entities.BankAccount


interface IBankAccountsController {
    fun OnGetBankAccounts(bank:String?,login:String?):ArrayList<BankAccount>
    fun OnAddBankAccount(bank:String?,login:String?):BankAccount?
}