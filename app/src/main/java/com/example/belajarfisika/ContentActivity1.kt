package com.example.belajarfisika

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager

class ContentActivity1 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_content1)

        val viewPager: ViewPager = findViewById(R.id.viewPager)
        val adapter = MyPagerAdapter(supportFragmentManager)
        viewPager.adapter = adapter
    }

    fun openNextActivity1(view: View) {
        onBackPressed()
    }

    private class MyPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

        override fun getItem(position: Int): Fragment {
            return when (position) {
                0 -> ExplanationFragment1()
                1 -> CalculatorFragment1()
                else -> throw IllegalArgumentException("Invalid position")
            }
        }

        override fun getCount(): Int {
            return 2 // Jumlah slide yang ingin ditampilkan
        }
    }
}