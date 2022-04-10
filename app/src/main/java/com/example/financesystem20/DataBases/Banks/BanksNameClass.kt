package com.example.financesystem20.DataBases.Banks

import android.provider.BaseColumns

object BanksNameClass {
    const val TABLE_NAME = "banks_table"
    const val COLUMN_NAME_BANK = "bank"
    const val COLUMN_NAME_PERCENT3 = "percent3"
    const val COLUMN_NAME_PERCENT6 = "percent6"
    const val COLUMN_NAME_PERCENT12 = "percent12"
    const val COLUMN_NAME_PERCENT24 = "percent24"
    const val COLUMN_NAME_PERCENT36 = "percent36"

    const val DATABASE_VERSION = 1
    const val DATABASE_NAME="Banks.db"
    const val CREAT_TABLE="CREATE TABLE IF NOT EXISTS $TABLE_NAME("+
            "${BaseColumns._ID} INTEGER PRIMARY KEY,$COLUMN_NAME_BANK TEXT,$COLUMN_NAME_PERCENT3 REAL,$COLUMN_NAME_PERCENT6 REAL,$COLUMN_NAME_PERCENT12 REAL,$COLUMN_NAME_PERCENT24 REAL,$COLUMN_NAME_PERCENT36 REAL)"
    const val SQL_DELETE_TABLE="DROP TABLE IF EXISTS $TABLE_NAME"
}