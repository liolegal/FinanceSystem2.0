package com.example.financesystem20.Entities.Staff

import com.example.financesystem20.Entities.Client

class Manager(
    bank:String,
    login: String,
    password:String,
    name: String,
    post:String="manager",
) :Staff(bank,login,password,name, post){
    fun approve(_client: Client): Client {
        _client.approved=1
        return _client
    }

}