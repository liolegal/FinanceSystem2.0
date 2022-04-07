package com.example.financesystem20.Controllers

import com.example.financesystem20.DataBases.Personal.StaffDBManager
import com.example.financesystem20.Interfaces.ILoginController
import com.example.financesystem20.Interfaces.ILoginView

class LoginAsStaffController (private val loginView: ILoginView, val staffDBManager: StaffDBManager) :
    ILoginController {
    override fun OnLogin(bank: String?, login: String?, password: String?) {
        val dataList = staffDBManager.readDBData()

        for (item in dataList) {
            if (item.bank == bank) {
                if (item.login == login) {
                    if (item.password == password) {
                        if (item.post == "manager") {
                            loginView.OnLoginAsStaffSuccess("manager",item.bank,item.login)
                        } else {
                            //TODO "Admin and etc."
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
//for (item in dataList) {
//    if (item.bank == inputBank) {
//        if (item.login == inputLogin) {
//            if (item.password == inputPassword) {
//                if (item.post == "manager") {
//                    val newIntent = Intent(this, ManagerActivity::class.java)
////                            newIntent.putExtra(STAFF_LOGIN, item.login)
////                            newIntent.putExtra(MANAGER_LOGIN, item)
////                            newIntent.putExtra(BANK_LOGIN, item.bank)
//                    startActivity(newIntent)
//                }
//                else if (item.post=="admin"){
//                    //TODO() "start AdminActivity"
//                }
//            } else {
//                Toast.makeText(this, "Wrong password", Toast.LENGTH_SHORT).show()
//            }
//
//        } else {
//            Toast.makeText(this, "Wrong login", Toast.LENGTH_SHORT).show()
//        }
//    }
//}