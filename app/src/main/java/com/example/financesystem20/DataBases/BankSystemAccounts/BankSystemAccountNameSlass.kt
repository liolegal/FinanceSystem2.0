package com.example.financesystem20.DataBases.BankSystemAccounts

import android.provider.BaseColumns

object BankSystemAccountNameSlass {
    const val TABLE_NAME = "bank_system_accounts_table"
    const val COLUMN_NAME_BANK = "bank"
    const val COLUMN_NAME_LOGIN = "login"
    const val COLUMN_NAME_PASSWORD = "password"

    const val DATABASE_VERSION = 1
    const val DATABASE_NAME="BankSystemAccounts.db"
    const val CREAT_TABLE="CREATE TABLE IF NOT EXISTS $TABLE_NAME("+
            "${BaseColumns._ID} INTEGER PRIMARY KEY,$COLUMN_NAME_BANK TEXT,$COLUMN_NAME_LOGIN TEXT,$COLUMN_NAME_PASSWORD TEXT)"
    const val SQL_DELETE_TABLE="DROP TABLE IF EXISTS $TABLE_NAME"
}