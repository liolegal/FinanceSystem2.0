package com.example.financesystem20.DataBases.BankSystemAccounts

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class BankSystemAccountsDBHelper(context: Context):SQLiteOpenHelper(context,
    BankSystemAccountNameSlass.DATABASE_NAME,null,
    BankSystemAccountNameSlass.DATABASE_VERSION
){
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(BankSystemAccountNameSlass.CREAT_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL(BankSystemAccountNameSlass.SQL_DELETE_TABLE)
        onCreate(db)
    }


}