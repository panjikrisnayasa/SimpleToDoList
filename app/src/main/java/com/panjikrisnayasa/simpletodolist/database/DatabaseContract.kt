package com.panjikrisnayasa.simpletodolist.database

import android.provider.BaseColumns

internal class DatabaseContract {
    internal class ToDoListColumns : BaseColumns {
        companion object {
            const val TABLE_NAME = "toDoListColumns"
            const val _ID = "_id"
            const val TITLE = "title"
            const val DESCRIPTION = "description"
            const val DATE = "date"
            const val IMAGE = "image"
        }
    }
}