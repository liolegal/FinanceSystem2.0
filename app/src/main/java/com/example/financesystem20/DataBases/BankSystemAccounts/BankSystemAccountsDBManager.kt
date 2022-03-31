package com.example.financesystem20.DataBases.BankSystemAccounts

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.example.financesystem20.Entities.BankSystemAccount
import java.util.ArrayList

class BankSystemAccountsDBManager(context: Context) {
    val bankSystemAccountsDBHelper = BankSystemAccountsDBHelper(context)
    var db: SQLiteDatabase? = null

    fun openDB() {
        db = bankSystemAccountsDBHelper.writableDatabase
    }

    fun insertToDB(
        bank: String,
        login: String,
        password:String
    ) {
        val values = ContentValues().apply {
            put(BankSystemAccountNameSlass.COLUMN_NAME_BANK, bank)
            put(BankSystemAccountNameSlass.COLUMN_NAME_LOGIN, login)
            put(BankSystemAccountNameSlass.COLUMN_NAME_PASSWORD, password)
        }
        db?.insert(BankSystemAccountNameSlass.TABLE_NAME, null, values)

    }

    fun readDBData(): ArrayList<BankSystemAccount> {
        val dataList = ArrayList<BankSystemAccount>()
        val cursor = db?.query(BankSystemAccountNameSlass.TABLE_NAME, null, null, null, null, null, null)


        while(cursor?.moveToNext()!!){
            val dataObj= BankSystemAccount(
                cursor.getString(cursor.getColumnIndex(BankSystemAccountNameSlass.COLUMN_NAME_BANK)).toString(),
                cursor.getString(cursor.getColumnIndex(BankSystemAccountNameSlass.COLUMN_NAME_LOGIN)).toString(),
                cursor.getString(cursor.getColumnIndex(BankSystemAccountNameSlass.COLUMN_NAME_PASSWORD)).toString()

            )
            dataList.add(dataObj)

        }

        cursor.close()
        return dataList
    }
    fun closeDB(){
        bankSystemAccountsDBHelper.close()
    }

}