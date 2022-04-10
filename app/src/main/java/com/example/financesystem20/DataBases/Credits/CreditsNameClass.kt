package com.example.financesystem20.DataBases.Credits

import android.provider.BaseColumns

object CreditsNameClass {
    const val TABLE_NAME = "credits_table"
    const val COLUMN_NAME_BANK = "bank"
    const val COLUMN_NAME_LOGIN = "login"
    const val COLUMN_NAME_ID = "id_of_credit"
    const val COLUMN_NAME_MONEY="count_of_money"
    const val COLUMN_NAME_TERM = "count_of_month"
    const val COLUMN_NAME_PERCENT = "percent"
    const val COLUMN_NAME_APPROVED = "approved"
   // const val COLUMN_NAME_AMOUNT_DEBT = "amount_of_debt"


    const val DATABASE_VERSION = 1
    const val DATABASE_NAME = "Credits.db"
    const val CREAT_TABLE = "CREATE TABLE IF NOT EXISTS $TABLE_NAME(" +
            "${BaseColumns._ID} INTEGER PRIMARY KEY,$COLUMN_NAME_BANK TEXT,$COLUMN_NAME_LOGIN TEXT,$COLUMN_NAME_ID TEXT,$COLUMN_NAME_MONEY INTEGER,$COLUMN_NAME_TERM INTEGER,$COLUMN_NAME_PERCENT REAL,$COLUMN_NAME_APPROVED INTEGER)"
    const val SQL_DELETE_TABLE = "DROP TABLE IF EXISTS $TABLE_NAME"
}