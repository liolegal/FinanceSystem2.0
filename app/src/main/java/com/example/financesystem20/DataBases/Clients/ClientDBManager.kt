package com.example.financesystem20.DataBases.Clients

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.example.financesystem20.Entities.Client
import java.util.ArrayList

class ClientDBManager(context: Context) {
    val clientDBHelper = ClientDBHelper(context)
    var db: SQLiteDatabase? = null

    fun openDB() {
        db = clientDBHelper.writableDatabase
    }

    fun insertToDB(
        login: String,
        name: String,
        surname: String,
        phoneNumber: String,
        email: String
    ) {
        val values = ContentValues().apply {
            put(ClientDBNameClass.COLUMN_NAME_LOGIN, login)
            put(ClientDBNameClass.COLUMN_NAME_NAME, name)
            put(ClientDBNameClass.COLUMN_NAME_SURNAME, surname)
            put(ClientDBNameClass.COLUMN_NAME_PHONENUMBER, phoneNumber)
            put(ClientDBNameClass.COLUMN_NAME_EMAIL, email)
        }
        db?.insert(ClientDBNameClass.TABLE_NAME, null, values)

    }

    fun readDBData(): ArrayList<Client> {
        val dataList = ArrayList<Client>()
        val cursor = db?.query(ClientDBNameClass.TABLE_NAME, null, null, null, null, null, null)


        while(cursor?.moveToNext()!!){
            val dataObj= Client(
                cursor.getString(cursor.getColumnIndex(ClientDBNameClass.COLUMN_NAME_LOGIN)).toString(),
                cursor.getString(cursor.getColumnIndex(ClientDBNameClass.COLUMN_NAME_NAME)).toString(),
                cursor.getString(cursor.getColumnIndex(ClientDBNameClass.COLUMN_NAME_SURNAME)).toString(),
                cursor.getString(cursor.getColumnIndex(ClientDBNameClass.COLUMN_NAME_PHONENUMBER)).toString(),
                cursor.getString(cursor.getColumnIndex(ClientDBNameClass.COLUMN_NAME_EMAIL)).toString()
            )
            dataList.add(dataObj)

        }

        cursor.close()
        return dataList
    }
    fun closeDB(){
        clientDBHelper.close()
    }

}