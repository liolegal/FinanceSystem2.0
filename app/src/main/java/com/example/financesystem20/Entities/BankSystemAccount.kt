package com.example.financesystem20.Entities

import java.io.Serializable

data class BankSystemAccount(
    val bank:String,
    val login:String,
    val password:String
):Serializable {

}