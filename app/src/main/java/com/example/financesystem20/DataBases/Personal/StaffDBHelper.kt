package com.example.financesystem20.DataBases.Personal

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class StaffDBHelper(context: Context):SQLiteOpenHelper(context,
    StaffDBNameClass.DATABASE_NAME,null,
    StaffDBNameClass.DATABASE_VERSION
){
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(StaffDBNameClass.CREAT_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL(StaffDBNameClass.SQL_DELETE_TABLE)
        onCreate(db)
    }


}