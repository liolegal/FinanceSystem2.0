package com.example.financesystem20.Interfaces

interface ILoginView {
    fun OnLoginAsClientSuccess(message: String?,bank:String,login:String)
    fun OnLoginAsStaffSuccess(post:String?, bank:String, login:String)
    fun OnLoginError(message: String?)
}