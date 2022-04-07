package com.example.financesystem20.DataBases.Clients

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.provider.BaseColumns
import com.example.financesystem20.DataBases.BankAccounts.BankAccountNameClass
import com.example.financesystem20.Entities.BankAccount
import com.example.financesystem20.Entities.Client
import java.util.ArrayList
import java.util.concurrent.ConcurrentLinkedDeque

class ClientDBManager(context: Context) {
    val clientDBHelper = ClientDBHelper(context)
    var db: SQLiteDatabase? = null

    fun openDB() {
        db = clientDBHelper.writableDatabase
    }

    fun insertToDB(
        bank: String,
        login: String,
        password: String,
        name: String,
        surname: String,
        phoneNumber: String,
        email: String,
        approved: Int
    ) {
        val values = ContentValues().apply {
            put(ClientDBNameClass.COLUMN_NAME_BANK, bank)
            put(ClientDBNameClass.COLUMN_NAME_LOGIN, login)
            put(ClientDBNameClass.COLUMN_NAME_PASSWORD, password)
            put(ClientDBNameClass.COLUMN_NAME_NAME, name)
            put(ClientDBNameClass.COLUMN_NAME_SURNAME, surname)
            put(ClientDBNameClass.COLUMN_NAME_PHONENUMBER, phoneNumber)
            put(ClientDBNameClass.COLUMN_NAME_EMAIL, email)
            put(ClientDBNameClass.COLUMN_NAME_APPROVE, approved)
        }
        db?.insert(ClientDBNameClass.TABLE_NAME, null, values)

    }

    fun readDBData(): ArrayList<Client> {
        val dataList = ArrayList<Client>()
        val cursor = db?.query(ClientDBNameClass.TABLE_NAME, null, null, null, null, null, null)


        while (cursor?.moveToNext()!!) {
            val dataObj = Client(
                cursor.getInt(cursor.getColumnIndex(BaseColumns._ID)),
                cursor.getString(cursor.getColumnIndex(ClientDBNameClass.COLUMN_NAME_BANK))
                    .toString(),
                cursor.getString(cursor.getColumnIndex(ClientDBNameClass.COLUMN_NAME_LOGIN))
                    .toString(),
                cursor.getString(cursor.getColumnIndex(ClientDBNameClass.COLUMN_NAME_PASSWORD))
                    .toString(),
                cursor.getString(cursor.getColumnIndex(ClientDBNameClass.COLUMN_NAME_NAME))
                    .toString(),
                cursor.getString(cursor.getColumnIndex(ClientDBNameClass.COLUMN_NAME_SURNAME))
                    .toString(),
                cursor.getString(cursor.getColumnIndex(ClientDBNameClass.COLUMN_NAME_PHONENUMBER))
                    .toString(),
                cursor.getString(cursor.getColumnIndex(ClientDBNameClass.COLUMN_NAME_EMAIL))
                    .toString(),
                cursor.getInt(cursor.getColumnIndex(ClientDBNameClass.COLUMN_NAME_APPROVE))
            )
            dataList.add(dataObj)

        }

        cursor.close()
        return dataList
    }

    fun updateItem(client: Client, id: String) {
        val selection = BaseColumns._ID + "=$id"
        val values = ContentValues().apply {
            put(ClientDBNameClass.COLUMN_NAME_BANK, client.bank)
            put(ClientDBNameClass.COLUMN_NAME_LOGIN, client.login)
            put(ClientDBNameClass.COLUMN_NAME_PASSWORD, client.password)
            put(ClientDBNameClass.COLUMN_NAME_NAME, client.name)
            put(ClientDBNameClass.COLUMN_NAME_SURNAME, client.surname)
            put(ClientDBNameClass.COLUMN_NAME_PHONENUMBER, client.phoneNumber)
            put(ClientDBNameClass.COLUMN_NAME_EMAIL, client.email)
            put(ClientDBNameClass.COLUMN_NAME_APPROVE, client.approved)
        }
        db?.update(ClientDBNameClass.TABLE_NAME, values, selection, null)
    }

    fun getClientFromDB(login: String): Client? {
        var itemToReturn: Client? = null
        val cursor = db?.query(ClientDBNameClass.TABLE_NAME, null, null, null, null, null, null)
        while (cursor?.moveToNext()!!) {
            if (cursor.getString(cursor.getColumnIndex(ClientDBNameClass.COLUMN_NAME_LOGIN))
                    .toString() == login
            ) {
                itemToReturn = Client(
                    cursor.getInt(cursor.getColumnIndex(BaseColumns._ID)),
                    cursor.getString(cursor.getColumnIndex(ClientDBNameClass.COLUMN_NAME_BANK))
                        .toString(),
                    cursor.getString(cursor.getColumnIndex(ClientDBNameClass.COLUMN_NAME_LOGIN))
                        .toString(),
                    cursor.getString(cursor.getColumnIndex(ClientDBNameClass.COLUMN_NAME_PASSWORD))
                        .toString(),
                    cursor.getString(cursor.getColumnIndex(ClientDBNameClass.COLUMN_NAME_NAME))
                        .toString(),
                    cursor.getString(cursor.getColumnIndex(ClientDBNameClass.COLUMN_NAME_SURNAME))
                        .toString(),
                    cursor.getString(cursor.getColumnIndex(ClientDBNameClass.COLUMN_NAME_PHONENUMBER))
                        .toString(),
                    cursor.getString(cursor.getColumnIndex(ClientDBNameClass.COLUMN_NAME_EMAIL))
                        .toString(),
                    cursor.getInt(cursor.getColumnIndex(ClientDBNameClass.COLUMN_NAME_APPROVE))

                )

            }

        }
        cursor.close()
        return itemToReturn
    }

    fun closeDB() {
        clientDBHelper.close()
    }

}