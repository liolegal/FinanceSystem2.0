package com.example.financesystem20.Entities

class Transfer(
        val sender:BankAccount,
        val receiver:BankAccount,
        val sumOfTransfer:Float
) {
fun transfer(){
        sender.takeMoney(sumOfTransfer)
        receiver.putMoney(sumOfTransfer)
}
}