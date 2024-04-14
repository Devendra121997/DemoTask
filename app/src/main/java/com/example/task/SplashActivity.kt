package com.example.task

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.launch

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
                super.onCreate(savedInstanceState)
                setContentView(R.layout.splash_activity)

                // Load animation
                val anim = AnimationUtils.loadAnimation(this, R.anim.fade_in)
                findViewById<TextView>(R.id.text_view).startAnimation(anim)


                Handler(Looper.getMainLooper()).postDelayed({
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }, 3000)
            }
}


