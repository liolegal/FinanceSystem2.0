package com.example.financesystem20.DataBases.Credits

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper


class CreditsDBHelper (context: Context): SQLiteOpenHelper(context,
    CreditsNameClass.DATABASE_NAME,null,
    CreditsNameClass.DATABASE_VERSION
){
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(CreditsNameClass.CREAT_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL(CreditsNameClass.SQL_DELETE_TABLE)
        onCreate(db)
    }


}