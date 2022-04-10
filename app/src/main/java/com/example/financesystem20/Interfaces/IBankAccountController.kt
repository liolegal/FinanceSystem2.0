package com.example.financesystem20.Interfaces

import com.example.financesystem20.Entities.BankAccount

interface IBankAccountController {
    fun OnTransfer(sum:String,idOfReceiver:String,selectedAccountIdOfBankAccount:String)
    fun OnDelete(id:String)
    fun OnAddMoney(selectedBankAccount: BankAccount?)
    fun OnTakeMoney(selectedBankAccount:BankAccount?)
}