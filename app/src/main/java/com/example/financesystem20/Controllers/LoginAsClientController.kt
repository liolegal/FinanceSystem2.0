package com.example.financesystem20.Controllers

import com.example.financesystem20.DataBases.Clients.ClientDBManager
import com.example.financesystem20.Interfaces.ILoginController
import com.example.financesystem20.Interfaces.ILoginView

class LoginAsClientController(private val loginView: ILoginView,val clientDBManager: ClientDBManager) :
    ILoginController {
    override fun OnLogin(bank: String?, login: String?, password: String?) {
        val dataList = clientDBManager.readDBData()

        for (item in dataList) {
            if (item.bank == bank) {
                if (item.login == login) {
                    if (item.password == password) {
                        if (item.approved == 1) {
                            loginView.OnLoginAsClientSuccess("manager",item.bank,item.login)
                        } else {
                            loginView.OnLoginError("Need an approve")
                        }
                    } else {
                        loginView.OnLoginError("Wrong password")
                    }

                } else {
                    loginView.OnLoginError("Wrong login")
                }
            }
        }

    }
}
