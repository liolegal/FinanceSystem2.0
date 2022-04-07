package com.example.financesystem20.Interfaces

interface IRegistrateController {
    fun OnRegistrate(
        bank: String,
        login: String,
        password: String,
        name: String,
        surname: String,
        phoneNumber: String,
        email: String
    )
}