package com.example.financesystem20.Entities

import java.io.Serializable

data class Client(
    var login: String,
    val name: String,
    val surname: String,
    var phoneNumber: String,
    var email: String
) : Serializable