package com.example.task

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DetailsActvity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.details_activity)
        val text= findViewById<TextView>(R.id.idTextView)
        val postId = intent.extras?.getString("postId", "")
        text.text= postId.toString()

    }
}