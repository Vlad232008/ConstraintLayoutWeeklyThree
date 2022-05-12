package com.example.constraintlayoutweeklythree


import android.os.Build
import android.os.Bundle
import android.view.MenuItem
import android.widget.SeekBar
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_play.*


class PlayActivity : AppCompatActivity(), SeekBar.OnSeekBarChangeListener {
    var index = 0
    private val arrayMusic: MutableList<String> =
        mutableListOf("Angel (feat. Julie Elven)", "Demons", "In The End")
    private val arrayInfoMusic: MutableList<String> =
        mutableListOf("R. Armando Morabito", "Imagine dragons", "Linkin Park")

    private val arrayImage = mutableListOf(R.drawable.image, R.drawable.imagine, R.drawable.linkin)

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_play)
        actionBarSetting()
        title = "Сейчас играет"
        seekBar.min = 0
        seekBar.max = 327
        seekBar.setOnSeekBarChangeListener(this)
        tvMusic.text = arrayMusic[0]
        tvExecutor.text = arrayInfoMusic[0]
        ivImage.setImageResource(arrayImage[0])
        btnNext.setOnClickListener {
            index++
            if (index == arrayMusic.size) index = 0
            tvMusic.text = arrayMusic[index]
            tvExecutor.text = arrayInfoMusic[index]
            ivImage.setImageResource(arrayImage[index])
        }
        btnPrevious.setOnClickListener {
            index--
            if (index < 0) index = arrayMusic.size - 1
            tvMusic.text = arrayMusic[index]
            tvExecutor.text = arrayInfoMusic[index]
            ivImage.setImageResource(arrayImage[index])
        }
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

    override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
        val time = timeToString(p1.toLong())
        tvStart.text = time
    }

    override fun onStartTrackingTouch(p0: SeekBar?) {
    }

    override fun onStopTrackingTouch(p0: SeekBar?) {
    }

    private fun timeToString(secs: Long): String {
        val min = secs / 60 % 60
        val sec = secs / 1 % 60
        return String.format("%2d:%02d", min, sec)
    }
}