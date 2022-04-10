package com.example.financesystem20.Interfaces

import com.example.financesystem20.Adapters.BankAccountsAdapter
import com.example.financesystem20.Entities.BankAccount

interface IBankAccountsView {
    fun OnSelectBankAccount(bankAccountsListOfClient: ArrayList<BankAccount>)

}