package com.example.financesystem20.DataBases.Clients

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class ClientDBHelper(context: Context):SQLiteOpenHelper(context,
    ClientDBNameClass.DATABASE_NAME,null,
    ClientDBNameClass.DATABASE_VERSION
){
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(ClientDBNameClass.CREAT_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL(ClientDBNameClass.SQL_DELETE_TABLE)
        onCreate(db)
    }


}