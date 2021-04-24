package com.panjikrisnayasa.simpletodolist.model

data class ToDoList (
    var id: Int? = null,
    var title: String? = null,
    var description: String? = null,
    var date: String? = null,
    var image: String? = null
)