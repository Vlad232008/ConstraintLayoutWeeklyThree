package com.example.constraintlayoutweeklythree

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val btnOne: Button = findViewById(R.id.btnOne)
        btnOne.setOnClickListener {
            startActivity(Intent(this,PlayActivity::class.java))
        }
        btnTwo.setOnClickListener {
            startActivity(Intent(this,ActivityContacts::class.java))
        }
        btnThree.setOnClickListener {
            startActivity(Intent(this,Calculator::class.java))
        }
        btnFour.setOnClickListener {
            startActivity(Intent(this,BeginActivity::class.java))
        }
    }
}