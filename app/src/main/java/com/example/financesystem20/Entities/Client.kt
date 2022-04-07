package com.example.financesystem20.Entities

import java.io.Serializable
class Client(
    val id:Int,
    bank:String,
    login: String,
    password:String,
    name: String,
    val surname: String,
    var phoneNumber: String,
    var email: String,
    var approved:Int=0
) : User(bank,login,password,name){

}