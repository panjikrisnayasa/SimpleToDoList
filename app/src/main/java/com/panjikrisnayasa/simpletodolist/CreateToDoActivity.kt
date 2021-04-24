package com.panjikrisnayasa.simpletodolist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class CreateToDoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_to_do)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
}