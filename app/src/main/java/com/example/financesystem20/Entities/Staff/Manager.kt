package com.example.financesystem20.Entities.Staff

import com.example.financesystem20.Entities.Client
import com.example.financesystem20.Entities.Credit

class Manager(
    bank:String,
    login: String,
    password:String,
    name: String,
    post:String="manager",
) :Staff(bank,login,password,name, post){
    fun approveClient(_client: Client): Client {
        _client.approved=1
        return _client
    }
    fun approveCredit(_credit: Credit):Credit{
        _credit.approved=1
        return _credit
    }

}