package com.example.belajarfisika

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    private lateinit var button: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.logintheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button = findViewById(R.id.btn_1)
        button.setOnClickListener {
            val Intent = Intent(this@MainActivity, HomeActivity::class.java)
            startActivity(Intent)
        }
    }
}