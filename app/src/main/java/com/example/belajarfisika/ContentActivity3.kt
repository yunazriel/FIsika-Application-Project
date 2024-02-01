package com.example.belajarfisika

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager

class ContentActivity3 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_content3)

        val viewPager: ViewPager = findViewById(R.id.slidecontent3)
        val adapter = MyPagerAdapter(supportFragmentManager)
        viewPager.adapter = adapter
    }

    fun openNextActivity3(view: View) {
        onBackPressed()
    }

    private class MyPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

        override fun getItem(position: Int): Fragment {
            return when (position) {
                0 -> ExplanationFragment3()
                1 -> CalculatorFragment3()
                else -> throw IllegalArgumentException("Invalid position")
            }
        }

        override fun getCount(): Int {
            return 2
        }
    }
}