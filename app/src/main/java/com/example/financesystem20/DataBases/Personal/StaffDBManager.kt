package com.example.financesystem20.DataBases.Personal

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.example.financesystem20.Entities.Staff.Administrator
import com.example.financesystem20.Entities.Staff.Manager
import com.example.financesystem20.Entities.Staff.Staff

class StaffDBManager(context: Context) {
    val staffDBHelper = StaffDBHelper(context)
    var db: SQLiteDatabase? = null

    fun openDB() {
        db = staffDBHelper.writableDatabase
    }

    fun insertToDB(
        post:String,
        bank:String,
        login: String,
        password:String,
        name: String
    ) {
        val values = ContentValues().apply {

            put(StaffDBNameClass.COLUMN_NAME_BANK, bank)
            put(StaffDBNameClass.COLUMN_NAME_LOGIN, login)
            put(StaffDBNameClass.COLUMN_NAME_PASSWORD, password)
            put(StaffDBNameClass.COLUMN_NAME_NAME, name)
            put(StaffDBNameClass.COLUMN_NAME_POST, post)
            }
        db?.insert(StaffDBNameClass.TABLE_NAME, null, values)

    }

    fun readDBData(): ArrayList<Staff> {
        val dataList = ArrayList<Staff>()
        val cursor = db?.query(StaffDBNameClass.TABLE_NAME, null, null, null, null, null, null)


        while(cursor?.moveToNext()!!){
            val dataObj= Staff(
                cursor.getString(cursor.getColumnIndex(StaffDBNameClass.COLUMN_NAME_BANK)).toString(),
                cursor.getString(cursor.getColumnIndex(StaffDBNameClass.COLUMN_NAME_LOGIN)).toString(),
                cursor.getString(cursor.getColumnIndex(StaffDBNameClass.COLUMN_NAME_PASSWORD)).toString(),
                cursor.getString(cursor.getColumnIndex(StaffDBNameClass.COLUMN_NAME_NAME)).toString(),
                cursor.getString(cursor.getColumnIndex(StaffDBNameClass.COLUMN_NAME_POST)).toString(),
            )
            dataList.add(dataObj)

        }

        cursor.close()
        return dataList
    }

//    fun getStaffFromDB(bank:String,login:String,password:String): Staff? {
//        val cursor = db?.query(StaffDBNameClass.TABLE_NAME, null, null, null, null, null, null)
//        while(cursor?.moveToNext()!!){
//            if(
//                cursor.getString(cursor.getColumnIndex(StaffDBNameClass.COLUMN_NAME_POST)).toString()=="manager" &&
//                cursor.getString(cursor.getColumnIndex(StaffDBNameClass.COLUMN_NAME_BANK)).toString()==bank &&
//                cursor.getString(cursor.getColumnIndex(StaffDBNameClass.COLUMN_NAME_LOGIN)).toString()==login &&
//                cursor.getString(cursor.getColumnIndex(StaffDBNameClass.COLUMN_NAME_PASSWORD)).toString()==password
//
//            ){
//                return Manager(
//                    cursor.getString(cursor.getColumnIndex(StaffDBNameClass.COLUMN_NAME_BANK)).toString(),
//                    cursor.getString(cursor.getColumnIndex(StaffDBNameClass.COLUMN_NAME_LOGIN)).toString(),
//                    cursor.getString(cursor.getColumnIndex(StaffDBNameClass.COLUMN_NAME_PASSWORD)).toString(),
//                    cursor.getString(cursor.getColumnIndex(StaffDBNameClass.COLUMN_NAME_NAME)).toString(),
//                    cursor.getString(cursor.getColumnIndex(StaffDBNameClass.COLUMN_NAME_POST)).toString()
//                )
//
//            }
//            if(cursor.getString(cursor.getColumnIndex(StaffDBNameClass.COLUMN_NAME_POST)).toString()=="admin"&&
//                cursor.getString(cursor.getColumnIndex(StaffDBNameClass.COLUMN_NAME_BANK)).toString()==bank &&
//                cursor.getString(cursor.getColumnIndex(StaffDBNameClass.COLUMN_NAME_LOGIN)).toString()==login &&
//                cursor.getString(cursor.getColumnIndex(StaffDBNameClass.COLUMN_NAME_PASSWORD)).toString()==password){
//                return Administrator(
//                    cursor.getString(cursor.getColumnIndex(StaffDBNameClass.COLUMN_NAME_BANK)).toString(),
//                    cursor.getString(cursor.getColumnIndex(StaffDBNameClass.COLUMN_NAME_LOGIN)).toString(),
//                    cursor.getString(cursor.getColumnIndex(StaffDBNameClass.COLUMN_NAME_PASSWORD)).toString(),
//                    cursor.getString(cursor.getColumnIndex(StaffDBNameClass.COLUMN_NAME_NAME)).toString(),
//                    cursor.getString(cursor.getColumnIndex(StaffDBNameClass.COLUMN_NAME_POST)).toString()
//                )
//            }
//
//        }
//        cursor.close()
//        return null
//    }

    fun closeDB(){
        staffDBHelper.close()
    }

}