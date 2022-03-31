package com.example.financesystem20.Entities

data class BankAccount(

        val bank:String,
        val login:String,
        val idOfAccount:String,
        val countOfMoney:Float,
        val id: Int = 0
) {

}