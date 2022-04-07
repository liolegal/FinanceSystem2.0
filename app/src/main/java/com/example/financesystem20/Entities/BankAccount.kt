package com.example.financesystem20.Entities

data class BankAccount(

    val bank: String,
    val login: String,
    val idOfAccount: String,
    var countOfMoney: Float,
    val id: Int = 0

    ) {
    var freezed: Boolean = false
    fun putMoney(sum: Float) {
        countOfMoney += sum
    }

    fun takeMoney(sum: Float):Boolean {
        if (countOfMoney >= sum) {
            countOfMoney -= sum
            return true
        }
        return false
    }

}