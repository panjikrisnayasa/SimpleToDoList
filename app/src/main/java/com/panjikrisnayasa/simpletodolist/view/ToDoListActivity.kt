package com.panjikrisnayasa.simpletodolist.view

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.panjikrisnayasa.simpletodolist.R
import com.panjikrisnayasa.simpletodolist.adapter.ToDoListAdapter
import com.panjikrisnayasa.simpletodolist.database.ToDoListHelper
import com.panjikrisnayasa.simpletodolist.helper.MappingHelper
import com.panjikrisnayasa.simpletodolist.model.ToDoList
import com.panjikrisnayasa.simpletodolist.util.SharedPrefManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class ToDoListActivity : AppCompatActivity() {

    private lateinit var mTextNoList: TextView
    private lateinit var mRecyclerView: RecyclerView
    private lateinit var mAdapter: ToDoListAdapter
    private lateinit var mSharedPref: SharedPrefManager
    private lateinit var mToDoListHelper: ToDoListHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_to_do_list)

        mTextNoList = findViewById(R.id.text_to_do_list_no_list)

        mSharedPref = SharedPrefManager.getInstance(this)
        mToDoListHelper = ToDoListHelper.getInstance(this)
        mToDoListHelper.open()

        supportActionBar?.title = String.format("Hi, %s", mSharedPref.getName())

        showRecyclerView()

        loadNotesAsync()
    }

    override fun onDestroy() {
        super.onDestroy()
        mToDoListHelper.close()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_to_do_list, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_create_to_do -> {
                val createToDo = Intent(this, CreateToDoActivity::class.java)
                startActivityForResult(createToDo, CreateToDoActivity.REQUEST_ADD)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (data != null) {
            if (requestCode == CreateToDoActivity.REQUEST_ADD) {
                if (resultCode == CreateToDoActivity.RESULT_ADD) {
                    val toDoList =
                        data.getParcelableExtra<ToDoList>(CreateToDoActivity.EXTRA_TO_DO_LIST)

                    if (toDoList != null) mAdapter.addItem(toDoList)
                    mRecyclerView.smoothScrollToPosition(mAdapter.itemCount - 1)
                }
            }
        }
    }

    private fun showRecyclerView() {
        mRecyclerView = findViewById(R.id.recycler_to_do_list)
        mRecyclerView.layoutManager = LinearLayoutManager(this)
        mRecyclerView.setHasFixedSize(true)
        mAdapter = ToDoListAdapter()
        mRecyclerView.adapter = mAdapter
    }

    private fun loadNotesAsync() {
        GlobalScope.launch(Dispatchers.Main) {
            val deferredNotes = async(Dispatchers.IO) {
                val cursor = mToDoListHelper.queryAll()
                MappingHelper.mapCursorToArrayList(cursor)
            }
            val toDoList = deferredNotes.await()
            if (toDoList.size > 0) {
                mAdapter.listToDo = toDoList
                mTextNoList.visibility = View.GONE
                mRecyclerView.visibility = View.VISIBLE
            } else {
                mAdapter.listToDo = ArrayList()
                mTextNoList.visibility = View.VISIBLE
                mRecyclerView.visibility = View.GONE
            }
        }
    }
}