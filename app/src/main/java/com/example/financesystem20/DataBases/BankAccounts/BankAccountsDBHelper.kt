package com.example.financesystem20.DataBases.BankAccounts

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class BankAccountsDBHelper (context: Context): SQLiteOpenHelper(context,
        BankAccountNameClass.DATABASE_NAME,null,
        BankAccountNameClass.DATABASE_VERSION
){
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(BankAccountNameClass.CREAT_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL(BankAccountNameClass.SQL_DELETE_TABLE)
        onCreate(db)
    }


}