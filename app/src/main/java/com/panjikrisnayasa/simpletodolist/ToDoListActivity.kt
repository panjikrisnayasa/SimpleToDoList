package com.panjikrisnayasa.simpletodolist

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ToDoListActivity : AppCompatActivity() {

    private lateinit var mRecyclerView: RecyclerView
    private lateinit var mAdapter: ToDoListAdapter
    private lateinit var mSharedPref: SharedPrefManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_to_do_list)

        mSharedPref = SharedPrefManager.getInstance(this)

        supportActionBar?.title = String.format("Hi, %s", mSharedPref.getName())

        showRecyclerView()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_to_do_list, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_create_to_do -> {
                val createToDo = Intent(this, CreateToDoActivity::class.java)
                startActivity(createToDo)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun showRecyclerView() {
        mRecyclerView = findViewById(R.id.recycler_to_do_list)
        mRecyclerView.layoutManager = LinearLayoutManager(this)
        mRecyclerView.setHasFixedSize(true)
        mAdapter = ToDoListAdapter()
        mRecyclerView.adapter = mAdapter
    }
}