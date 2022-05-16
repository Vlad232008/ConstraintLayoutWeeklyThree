package com.example.constraintlayoutweeklythree

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem

class Calculator : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_ActivityThree)
        super.onCreate(savedInstanceState)
        val startTime = System.nanoTime()
        setContentView(R.layout.activity_calculator)
        val totalTime = System.nanoTime() - startTime
        Log.d("MyLog", "Отрисовка калькулятора: $totalTime")
        actionBarSetting()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun actionBarSetting() {
        val ab = supportActionBar
        ab?.setDisplayHomeAsUpEnabled(true)
    }
}