package com.example.financesystem20.Entities

import java.io.Serializable

abstract class User(
    val bank:String,
    val login:String,
    val password:String,
    val name:String,

) :Serializable{

}