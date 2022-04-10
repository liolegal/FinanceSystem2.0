package com.example.financesystem20.DataBases.Banks

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.provider.BaseColumns
import com.example.financesystem20.DataBases.BankAccounts.BankAccountNameClass
import com.example.financesystem20.Entities.Bank
import com.example.financesystem20.Entities.BankAccount


class BanksDBManager (context: Context){
    val banksDBHelper = BanksDBHelper(context)
    var db: SQLiteDatabase? = null

    fun openDB() {
        db = banksDBHelper.writableDatabase
    }

    fun insertToDB(
        name:String,
        percent3:Float,
        percent6:Float,
        percent12:Float,
        percent24:Float,
        percent36:Float
    ) {
        val values = ContentValues().apply {
            put(BanksNameClass.COLUMN_NAME_BANK, name)
            put(BanksNameClass.COLUMN_NAME_PERCENT3, percent3)
            put(BanksNameClass.COLUMN_NAME_PERCENT6, percent6)
            put(BanksNameClass.COLUMN_NAME_PERCENT12, percent12)
            put(BanksNameClass.COLUMN_NAME_PERCENT24, percent24)
            put(BanksNameClass.COLUMN_NAME_PERCENT36, percent36)
        }
        db?.insert(BanksNameClass.TABLE_NAME, null, values)

    }
    fun deleteFromDb(id:String){
        val selection= BaseColumns._ID +"=$id"
        db?.delete(BanksNameClass.TABLE_NAME,selection,null)
    }


    fun getBankFromDB(name: String): Bank?{
        var itemToReturn: Bank?=null
        val cursor = db?.query(BanksNameClass.TABLE_NAME, null, null, null, null, null, null)
        while(cursor?.moveToNext()!!){
            if(cursor.getString(cursor.getColumnIndex(BanksNameClass.COLUMN_NAME_BANK)).toString()==name){
                itemToReturn= Bank(
                    cursor.getString(cursor.getColumnIndex(BanksNameClass.COLUMN_NAME_BANK)),
                    cursor.getFloat(cursor.getColumnIndex(BanksNameClass.COLUMN_NAME_PERCENT3)),
                    cursor.getFloat(cursor.getColumnIndex(BanksNameClass.COLUMN_NAME_PERCENT6)),
                    cursor.getFloat(cursor.getColumnIndex(BanksNameClass.COLUMN_NAME_PERCENT12)),
                    cursor.getFloat(cursor.getColumnIndex(BanksNameClass.COLUMN_NAME_PERCENT24)),
                    cursor.getFloat(cursor.getColumnIndex(BanksNameClass.COLUMN_NAME_PERCENT36))
                )

            }

        }
        cursor.close()
        return itemToReturn
    }
    fun readDBData(): ArrayList<Bank> {
        val dataList = ArrayList<Bank>()
        val cursor = db?.query(BanksNameClass.TABLE_NAME, null, null, null, null, null, null)


        while(cursor?.moveToNext()!!){
            val dataObj= Bank(
                cursor.getString(cursor.getColumnIndex(BanksNameClass.COLUMN_NAME_BANK)),
                cursor.getFloat(cursor.getColumnIndex(BanksNameClass.COLUMN_NAME_PERCENT3)),
                cursor.getFloat(cursor.getColumnIndex(BanksNameClass.COLUMN_NAME_PERCENT6)),
                cursor.getFloat(cursor.getColumnIndex(BanksNameClass.COLUMN_NAME_PERCENT12)),
                cursor.getFloat(cursor.getColumnIndex(BanksNameClass.COLUMN_NAME_PERCENT24)),
                cursor.getFloat(cursor.getColumnIndex(BanksNameClass.COLUMN_NAME_PERCENT36)),

            )
            dataList.add(dataObj)

        }

        cursor.close()
        return dataList
    }

    fun closeDB(){
        banksDBHelper.close()
    }

}