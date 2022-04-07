package com.example.financesystem20.DataBases.Personal

import android.provider.BaseColumns


object StaffDBNameClass {
    const val TABLE_NAME = "staff_table"
    const val COLUMN_NAME_POST="post"
    const val COLUMN_NAME_BANK = "bank"
    const val COLUMN_NAME_LOGIN = "login"
    const val COLUMN_NAME_PASSWORD = "password"
    const val COLUMN_NAME_NAME = "name"


    const val DATABASE_VERSION = 1
    const val DATABASE_NAME="StaffDB.db"
    const val CREAT_TABLE="CREATE TABLE IF NOT EXISTS $TABLE_NAME("+
            "${BaseColumns._ID} INTEGER PRIMARY KEY,$COLUMN_NAME_POST TEXT,${COLUMN_NAME_BANK} TEXT,${COLUMN_NAME_LOGIN} TEXT,${COLUMN_NAME_PASSWORD} TEXT," +
            "$COLUMN_NAME_NAME TEXT)"
    const val SQL_DELETE_TABLE="DROP TABLE IF EXISTS $TABLE_NAME"

}