package com.example.financesystem20.DataBases.Credits

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.provider.BaseColumns
import com.example.financesystem20.Entities.Credit

class CreditsDBManager(context: Context) {
    val creditsDBHelper = CreditsDBHelper(context)
    var db: SQLiteDatabase? = null

    fun openDB() {
        db = creditsDBHelper.writableDatabase
    }

    fun insertToDB(
        bank: String,
        login: String,
        idOfCredit: String,
        countOfMoney: Int,
        creditTerm: Int,
        percent: Float,
        approved: Int,
        //amountOfDebt: Float
    ) {
        val values = ContentValues().apply {
            put(CreditsNameClass.COLUMN_NAME_BANK, bank)
            put(CreditsNameClass.COLUMN_NAME_LOGIN, login)
            put(CreditsNameClass.COLUMN_NAME_ID, idOfCredit)
            put(CreditsNameClass.COLUMN_NAME_MONEY, countOfMoney)
            put(CreditsNameClass.COLUMN_NAME_TERM, creditTerm)
            put(CreditsNameClass.COLUMN_NAME_PERCENT, percent)
            put(CreditsNameClass.COLUMN_NAME_APPROVED, approved)
           // put(CreditsNameClass.COLUMN_NAME_AMOUNT_DEBT, amountOfDebt)
        }
        db?.insert(CreditsNameClass.TABLE_NAME, null, values)

    }

    fun deleteFromDb(id: String) {
        val selection = BaseColumns._ID + "=$id"
        db?.delete(CreditsNameClass.TABLE_NAME, selection, null)
    }

    fun updateItem(credit: Credit, id: String) {
        val selection = BaseColumns._ID + "=$id"
        val values = ContentValues().apply {
            put(CreditsNameClass.COLUMN_NAME_BANK, credit.bank)
            put(CreditsNameClass.COLUMN_NAME_LOGIN, credit.login)
            put(CreditsNameClass.COLUMN_NAME_ID, credit.idOfCredit)
            put(CreditsNameClass.COLUMN_NAME_MONEY, credit.countOfMoney)
            put(CreditsNameClass.COLUMN_NAME_TERM, credit.creditTerm)
            put(CreditsNameClass.COLUMN_NAME_PERCENT, credit.percent)
            put(CreditsNameClass.COLUMN_NAME_APPROVED, credit.approved)
            //put(CreditsNameClass.COLUMN_NAME_AMOUNT_DEBT, credit.amountOfDebt)
        }
        db?.update(CreditsNameClass.TABLE_NAME, values, selection, null)
    }

    fun readDBData(): ArrayList<Credit> {
        val dataList = ArrayList<Credit>()
        val cursor = db?.query(CreditsNameClass.TABLE_NAME, null, null, null, null, null, null)


        while (cursor?.moveToNext()!!) {
            val dataObj = Credit(
                cursor.getString(cursor.getColumnIndex(CreditsNameClass.COLUMN_NAME_BANK)),
                cursor.getString(cursor.getColumnIndex(CreditsNameClass.COLUMN_NAME_LOGIN)),
                cursor.getString(cursor.getColumnIndex(CreditsNameClass.COLUMN_NAME_ID)),
                cursor.getInt(cursor.getColumnIndex(CreditsNameClass.COLUMN_NAME_MONEY)),
                cursor.getInt(cursor.getColumnIndex(CreditsNameClass.COLUMN_NAME_TERM)),
                cursor.getFloat(cursor.getColumnIndex(CreditsNameClass.COLUMN_NAME_PERCENT)),
                cursor.getInt(cursor.getColumnIndex(BaseColumns._ID)),
                cursor.getInt(cursor.getColumnIndex(CreditsNameClass.COLUMN_NAME_APPROVED))
               // cursor.getFloat(cursor.getColumnIndex(CreditsNameClass.COLUMN_NAME_AMOUNT_DEBT))

            )
            dataList.add(dataObj)

        }

        cursor.close()
        return dataList
    }

    fun closeDB() {
        creditsDBHelper.close()
    }

}