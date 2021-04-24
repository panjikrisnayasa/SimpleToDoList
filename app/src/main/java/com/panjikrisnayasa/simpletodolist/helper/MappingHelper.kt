package com.panjikrisnayasa.simpletodolist.helper

import android.database.Cursor
import com.panjikrisnayasa.simpletodolist.database.DatabaseContract
import com.panjikrisnayasa.simpletodolist.model.ToDoList

object MappingHelper {

    fun mapCursorToArrayList(toDoListCursor: Cursor): ArrayList<ToDoList> {
        val toDoList = ArrayList<ToDoList>()
        toDoListCursor.moveToFirst()
        if (toDoListCursor.isFirst) {
            do {
                val id =
                    toDoListCursor.getInt(toDoListCursor.getColumnIndexOrThrow(DatabaseContract.ToDoListColumns._ID))
                val title =
                    toDoListCursor.getString(toDoListCursor.getColumnIndexOrThrow(DatabaseContract.ToDoListColumns.TITLE))
                val description =
                    toDoListCursor.getString(toDoListCursor.getColumnIndexOrThrow(DatabaseContract.ToDoListColumns.DESCRIPTION))
                val date =
                    toDoListCursor.getString(toDoListCursor.getColumnIndexOrThrow(DatabaseContract.ToDoListColumns.DATE))
                val image =
                    toDoListCursor.getString(toDoListCursor.getColumnIndexOrThrow(DatabaseContract.ToDoListColumns.IMAGE))
                toDoList.add(ToDoList(id, title, description, date, image))
            } while (toDoListCursor.moveToNext())
        }
        return toDoList
    }
}