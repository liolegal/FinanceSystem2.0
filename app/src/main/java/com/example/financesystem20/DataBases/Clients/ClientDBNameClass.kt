package com.example.financesystem20.DataBases.Clients

import android.provider.BaseColumns


object ClientDBNameClass {
    const val TABLE_NAME = "client_table"
    const val COLUMN_NAME_BANK = "bank"
    const val COLUMN_NAME_LOGIN = "login"
    const val COLUMN_NAME_PASSWORD = "password"
    const val COLUMN_NAME_NAME = "name"
    const val COLUMN_NAME_SURNAME = "surname"
    const val COLUMN_NAME_PHONENUMBER = "phone_number"
    const val COLUMN_NAME_EMAIL = "email"
    const val COLUMN_NAME_APPROVE = "approve"

    const val DATABASE_VERSION = 1
    const val DATABASE_NAME="ClientDB.db"
    const val CREAT_TABLE="CREATE TABLE IF NOT EXISTS $TABLE_NAME("+
            "${BaseColumns._ID} INTEGER PRIMARY KEY,${COLUMN_NAME_BANK} TEXT,${COLUMN_NAME_LOGIN} TEXT,${COLUMN_NAME_PASSWORD} TEXT," +
            "$COLUMN_NAME_NAME TEXT,$COLUMN_NAME_SURNAME TEXT,"+
            "$COLUMN_NAME_PHONENUMBER TEXT,$COLUMN_NAME_EMAIL TEXT,$COLUMN_NAME_APPROVE INTEGER)"
    const val SQL_DELETE_TABLE="DROP TABLE IF EXISTS $TABLE_NAME"

}