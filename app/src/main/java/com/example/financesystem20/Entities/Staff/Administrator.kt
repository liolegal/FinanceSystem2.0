package com.example.financesystem20.Entities.Staff

class Administrator(
    bank:String,
    login: String,
    password:String,
    name: String,
    post:String="admin",
) :Staff(bank,login,password,name, post){
}