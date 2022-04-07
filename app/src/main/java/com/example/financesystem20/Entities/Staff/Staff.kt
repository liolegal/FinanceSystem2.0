package com.example.financesystem20.Entities.Staff

import com.example.financesystem20.Entities.User

open class Staff(
    bank:String,
    login: String,
    password:String,
    name: String,
    val post:String
) : User(bank,login,password,name){

}