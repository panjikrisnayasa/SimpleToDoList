package com.panjikrisnayasa.simpletodolist.view

import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.panjikrisnayasa.simpletodolist.R
import com.panjikrisnayasa.simpletodolist.database.DatabaseContract
import com.panjikrisnayasa.simpletodolist.database.ToDoListHelper
import com.panjikrisnayasa.simpletodolist.model.ToDoList
import java.text.SimpleDateFormat
import java.util.*

class CreateToDoActivity : AppCompatActivity(), View.OnClickListener {

    companion object {
        const val EXTRA_TO_DO_LIST = "extra_to_do_list"
        const val EXTRA_POSITION = "extra_position"
        const val REQUEST_ADD = 100
        const val RESULT_ADD = 101
    }

    private lateinit var mEditTitle: EditText
    private lateinit var mEditDescription: EditText
    private lateinit var mEditImage: EditText
    private lateinit var mButtonBrowse: Button
    private lateinit var mButtonSubmit: Button
    private lateinit var toDoListHelper: ToDoListHelper
    private var toDoList: ToDoList? = null
    private var position: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_to_do)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        toDoListHelper = ToDoListHelper.getInstance(this)

        mEditTitle = findViewById(R.id.edit_create_title)
        mEditDescription = findViewById(R.id.edit_create_description)
        mEditImage = findViewById(R.id.edit_create_image)
        mButtonBrowse = findViewById(R.id.button_create_browse)
        mButtonSubmit = findViewById(R.id.button_create_submit)
        mButtonBrowse.setOnClickListener(this)
        mButtonSubmit.setOnClickListener(this)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.button_create_browse -> {

            }
            R.id.button_create_submit -> {
                val title = mEditTitle.text.toString().trim()
                val description = mEditDescription.text.toString().trim()
//                val image = mEditImage.text.toString().trim()

                if (title.isBlank() || description.isBlank()) {
                    Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
                    return
                }

                Log.d("hyp", "title = $title")
                toDoList = ToDoList()
                toDoList?.title = title
                toDoList?.description = description
//                toDoList?.image = image

                Log.d("hyp", "toDoList.title = ${toDoList?.title}")

                val intent = Intent()
                intent.putExtra(EXTRA_TO_DO_LIST, toDoList)
                intent.putExtra(EXTRA_POSITION, position)

                val values = ContentValues()
                values.put(DatabaseContract.ToDoListColumns.TITLE, title)
                values.put(DatabaseContract.ToDoListColumns.DESCRIPTION, description)
//                values.put(DatabaseContract.ToDoListColumns.IMAGE, image)

                toDoList?.date = getCurrentDate()
                values.put(DatabaseContract.ToDoListColumns.DATE, getCurrentDate())

                val result = toDoListHelper.insert(values)
                if (result > 0) {
                    toDoList?.id = result.toInt()
                    setResult(RESULT_ADD, intent)
                    finish()
                } else {
                    Toast.makeText(this, "Failed to add To Do List", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun getCurrentDate(): String {
        val dateFormat = SimpleDateFormat("dd MMMM H a", Locale.getDefault())
        val date = Date()
        return dateFormat.format(date)
    }
}