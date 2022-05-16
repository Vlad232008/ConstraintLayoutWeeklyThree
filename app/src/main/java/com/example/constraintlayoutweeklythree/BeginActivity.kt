package com.example.constraintlayoutweeklythree

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.core.graphics.toColor

class BeginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_ActivityThree)
        super.onCreate(savedInstanceState)
        val startTime = System.nanoTime()
        setContentView(R.layout.activity_begin)
        val totalTime = System.nanoTime() - startTime
        Log.d("MyLog", "Отрисовка входа: $totalTime")
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