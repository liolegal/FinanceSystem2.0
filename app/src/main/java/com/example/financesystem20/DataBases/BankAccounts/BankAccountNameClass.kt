package com.example.financesystem20.DataBases.BankAccounts

import android.provider.BaseColumns

object BankAccountNameClass {
    const val TABLE_NAME = "bank_accounts_table"
    const val COLUMN_NAME_BANK = "bank"
    const val COLUMN_NAME_LOGIN = "login"
    const val COLUMN_NAME_IDBA = "id_of_bank_account"
    const val COLUMN_NAME_MONEY="count_of_money"

    const val DATABASE_VERSION = 1
    const val DATABASE_NAME="BankAccounts.db"
    const val CREAT_TABLE="CREATE TABLE IF NOT EXISTS $TABLE_NAME("+
            "${BaseColumns._ID} INTEGER PRIMARY KEY,$COLUMN_NAME_BANK TEXT,$COLUMN_NAME_LOGIN TEXT,$COLUMN_NAME_IDBA TEXT,$COLUMN_NAME_MONEY REAL)"
    const val SQL_DELETE_TABLE="DROP TABLE IF EXISTS $TABLE_NAME"
}