package com.example.financesystem20.DataBases.Banks

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper


class BanksDBHelper (context: Context): SQLiteOpenHelper(context,
    BanksNameClass.DATABASE_NAME,null,
    BanksNameClass.DATABASE_VERSION
){
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(BanksNameClass.CREAT_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL(BanksNameClass.SQL_DELETE_TABLE)
        onCreate(db)
    }


}