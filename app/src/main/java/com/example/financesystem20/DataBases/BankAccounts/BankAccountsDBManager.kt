package com.example.financesystem20.DataBases.BankAccounts

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.provider.BaseColumns
import com.example.financesystem20.DataBases.BankSystemAccounts.BankSystemAccountNameSlass
import com.example.financesystem20.DataBases.BankSystemAccounts.BankSystemAccountsDBHelper
import com.example.financesystem20.Entities.BankAccount
import com.example.financesystem20.Entities.BankSystemAccount
import java.util.ArrayList

class BankAccountsDBManager(context: Context){
    val bankAccountsDBHelper = BankAccountsDBHelper(context)
    var db: SQLiteDatabase? = null

    fun openDB() {
        db = bankAccountsDBHelper.writableDatabase
    }

    fun insertToDB(
            bank:String,
            login: String,
            id:String,
            money:Float
    ) {
        val values = ContentValues().apply {
            put(BankAccountNameClass.COLUMN_NAME_BANK, bank)
            put(BankAccountNameClass.COLUMN_NAME_LOGIN, login)
            put(BankAccountNameClass.COLUMN_NAME_IDBA, id)
            put(BankAccountNameClass.COLUMN_NAME_MONEY, money)
        }
        db?.insert(BankAccountNameClass.TABLE_NAME, null, values)

    }
    fun deleteFromDb(id:String){
        val selection=BaseColumns._ID +"=$id"
        db?.delete(BankAccountNameClass.TABLE_NAME,selection,null)
    }

    fun readDBData(): ArrayList<BankAccount> {
        val dataList = ArrayList<BankAccount>()
        val cursor = db?.query(BankAccountNameClass.TABLE_NAME, null, null, null, null, null, null)


        while(cursor?.moveToNext()!!){
            val dataObj= BankAccount(
                    cursor.getString(cursor.getColumnIndex(BankAccountNameClass.COLUMN_NAME_BANK)),
                    cursor.getString(cursor.getColumnIndex(BankAccountNameClass.COLUMN_NAME_LOGIN)),
                    cursor.getString(cursor.getColumnIndex(BankAccountNameClass.COLUMN_NAME_IDBA)).toString(),
                    cursor.getString(cursor.getColumnIndex(BankAccountNameClass.COLUMN_NAME_MONEY)).toFloat(),
                    cursor.getInt(cursor.getColumnIndex(BaseColumns._ID))

            )
            dataList.add(dataObj)

        }

        cursor.close()
        return dataList
    }
    fun closeDB(){
        bankAccountsDBHelper.close()
    }

}