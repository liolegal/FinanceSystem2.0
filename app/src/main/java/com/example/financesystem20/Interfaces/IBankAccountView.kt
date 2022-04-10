package com.example.financesystem20.Interfaces

import com.example.financesystem20.Entities.BankAccount

interface IBankAccountView {
    fun OnAddMoneySuccess(selectedBankAccount: BankAccount?)
    fun OnTakeMoneySuccess(selectedBankAccount:BankAccount?)
    fun OnActionError(message:String?)
    fun OnDeleteSuccess(message:String?)
    fun OnTransferSuccess(selectedBankAccount: BankAccount?)
}