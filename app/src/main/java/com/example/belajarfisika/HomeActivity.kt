package com.example.belajarfisika

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.FrameLayout

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        val myFrameLayout: FrameLayout = findViewById(R.id.content1)
        val myFrameLayout2: FrameLayout = findViewById(R.id.content2)
        val myFrameLayout3: FrameLayout = findViewById(R.id.content3)

        myFrameLayout.setOnClickListener {
            val Intent = Intent(this, ContentActivity1::class.java)
            startActivity(Intent)
        }

        myFrameLayout2.setOnClickListener {
            val Intent = Intent(this, ContentActivity2::class.java)
            startActivity(Intent)
        }

        myFrameLayout3.setOnClickListener {
            val Intent = Intent(this, ContentActivity3::class.java)
            startActivity(Intent)
        }

    }
}