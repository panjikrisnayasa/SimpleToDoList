package com.panjikrisnayasa.simpletodolist.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.panjikrisnayasa.simpletodolist.database.DatabaseContract.ToDoListColumns.Companion.TABLE_NAME

class DatabaseHelper(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    companion object {
        private const val DATABASE_NAME = "todolist"
        private const val DATABASE_VERSION = 1
        private const val SQL_CREATE_TABLE_NOTE = "CREATE TABLE $TABLE_NAME" +
                " (${DatabaseContract.ToDoListColumns._ID} INTEGER PRIMARY KEY AUTOINCREMENT," +
                " ${DatabaseContract.ToDoListColumns.TITLE} TEXT NOT NULL," +
                " ${DatabaseContract.ToDoListColumns.DESCRIPTION} TEXT NOT NULL," +
                " ${DatabaseContract.ToDoListColumns.DATE} TEXT NOT NULL)," +
                " ${DatabaseContract.ToDoListColumns.IMAGE} TEXT NOT NULL)"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(SQL_CREATE_TABLE_NOTE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }
}