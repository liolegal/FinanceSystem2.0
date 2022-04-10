package com.example.financesystem20.Controllers

import android.widget.EditText
import android.widget.Toast
import com.example.financesystem20.DataBases.Clients.ClientDBManager
import com.example.financesystem20.Interfaces.ILoginController
import com.example.financesystem20.Interfaces.ILoginView
import com.example.financesystem20.Interfaces.IRegistrateController
import com.example.financesystem20.Interfaces.IRegistrateView
import com.example.financesystem20.R

class RegistrateController(
    private val registrateView: IRegistrateView,
    val clientDBManager: ClientDBManager
) :
    IRegistrateController {
    override fun OnRegistrate(
        bank: String,
        login: String,
        password: String,
        name: String,
        surname: String,
        phoneNumber: String,
        email: String
    ) {
        if (bank != "" || login != "" || password != "" || name != "" || surname != "" || phoneNumber != "" || email != "") {
            if (clientDBManager.getClientFromDB(login) == null) {
                clientDBManager.insertToDB(
                    bank,
                    login,
                    password,
                    name,
                    surname,
                    phoneNumber,
                    email,
                    0
                )
                registrateView.OnRegistrateSuccess("Welcome")
            } else {
                registrateView.OnRegistrateError("This login is already exist")

            }
        }else{registrateView.OnRegistrateError("Fill in all the fields")}
    }
}