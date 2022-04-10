package com.example.financesystem20.Entities

class Credit(

    val bank: String,
    val login: String,
    val idOfCredit: String,
    var countOfMoney: Int,
    val creditTerm: Int,
    val percent: Float,
    val id: Int = 0,
    var approved: Int = 0,
    var amountOfDebt:Float = countOfMoney + countOfMoney * percent
) {

    fun makePayment(sum: Float) {
        amountOfDebt -= sum
    }

}