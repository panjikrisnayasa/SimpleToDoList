package com.panjikrisnayasa.simpletodolist.view

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.panjikrisnayasa.simpletodolist.R
import com.panjikrisnayasa.simpletodolist.util.SharedPrefManager

class InputNameActivity : AppCompatActivity() {

    private lateinit var mEditName: EditText
    private lateinit var mButtonNext: Button
    private lateinit var mSharedPref: SharedPrefManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_input_name)

        mSharedPref = SharedPrefManager.getInstance(this)

        if (mSharedPref.isNameInputted()) {
            moveToToDoListActivity()
        }

        mEditName = findViewById(R.id.edit_input_name)
        mButtonNext = findViewById(R.id.button_input_next)
        mButtonNext.setOnClickListener {
            val name = mEditName.text.toString().trim()
            mSharedPref.setName(name)
            moveToToDoListActivity()
        }
    }

    private fun moveToToDoListActivity() {
        val toDoListIntent = Intent(this, ToDoListActivity::class.java)
        startActivity(toDoListIntent)
    }
}