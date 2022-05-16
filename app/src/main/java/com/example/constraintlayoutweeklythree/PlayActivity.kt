package com.example.constraintlayoutweeklythree


import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.SeekBar
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.constraintlayoutweeklythree.util.ShareHelper
import kotlinx.android.synthetic.main.activity_play.*
import java.util.*


class PlayActivity : AppCompatActivity(), SeekBar.OnSeekBarChangeListener {
    var index = 0
    var play = 0
    var oldRand = 0
    private val arrayMusic: MutableList<String> =
        mutableListOf("Angel (feat. Julie Elven)", "Demons", "In The End")
    private val arrayInfoMusic: MutableList<String> =
        mutableListOf("R. Armando Morabito", "Imagine dragons", "Linkin Park")
    private val arrayLike: MutableList<Int> =
        mutableListOf(0, 0, 0)
    private val arrayDisLike: MutableList<Int> =
        mutableListOf(0, 0, 0)
    private val arrayImage =
        mutableListOf(R.drawable.image, R.drawable.imagine, R.drawable.linkin)
    private val arrayRangeMusic: MutableList<Int> = mutableListOf(327, 184, 219)

    private val random = Random()
    private fun rand(): Int {
        return random.nextInt(arrayMusic.size)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val startTime = System.nanoTime()
        setContentView(R.layout.activity_play)
        val totalTime = System.nanoTime() - startTime
        Log.d("MyLog", "Отрисовка плеера: $totalTime")
        actionBarSetting()
        title = "Сейчас играет"
        seekBar.min = 0
        seekBar.max = arrayRangeMusic[0]
        seekBar.setOnSeekBarChangeListener(this)
        tvMusic.text = arrayMusic[0]
        tvExecutor.text = arrayInfoMusic[0]
        tvEnd.text = timeToString(arrayRangeMusic[0].toLong())
        ivImage.setImageResource(arrayImage[0])
        reactionButton()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_music, menu)
        return super.onCreateOptionsMenu(menu)
    }

    private fun reactionButton() {
        btnNext.setOnClickListener {
            if (btnRandom.isSelected) {
                do {
                    oldRand = rand()
                } while (index == oldRand)
                index = oldRand
            } else index++
            if (index == arrayMusic.size) index = 0
            tvMusic.text = arrayMusic[index]
            tvExecutor.text = arrayInfoMusic[index]
            ivImage.setImageResource(arrayImage[index])
            btnLike.isSelected = arrayLike[index] == 1
            btnNotInt.isSelected = arrayDisLike[index] == 1
            seekBar.max = arrayRangeMusic[index]
            tvEnd.text = timeToString(arrayRangeMusic[index].toLong())
            seekBar.progress = 0
        }
        btnPrevious.setOnClickListener {
            index--
            if (index < 0) index = arrayMusic.size - 1
            tvMusic.text = arrayMusic[index]
            tvExecutor.text = arrayInfoMusic[index]
            ivImage.setImageResource(arrayImage[index])
            btnLike.isSelected = arrayLike[index] == 1
            btnNotInt.isSelected = arrayDisLike[index] == 1
            seekBar.max = arrayRangeMusic[index]
            tvEnd.text = timeToString(arrayRangeMusic[index].toLong())
            seekBar.progress = 0
        }
        btnPlay.setOnClickListener {
            btnPlay.isSelected = btnPlay.isSelected != true
            play = if (play == 0) {
                btnPlay.setImageResource(R.drawable.ic_baseline_pause_circle_filled_24)
                1
            } else {
                btnPlay.setImageResource(R.drawable.ic_baseline_play_circle_24)
                0
            }
        }
        btnRepeat.setOnClickListener {
            btnRepeat.isSelected = btnRepeat.isSelected != true
        }
        btnNotInt.setOnClickListener {
            btnNotInt.isSelected = btnNotInt.isSelected != true
            if (arrayDisLike[index] == 0) {
                arrayDisLike[index] = 1
                btnLike.isSelected = false
                arrayLike[index] = 0
            } else {
                btnLike.isSelected = false
                arrayDisLike[index] = 0
            }
        }
        btnTimer.setOnClickListener {
            btnTimer.isSelected = btnTimer.isSelected != true
        }
        btnHQ.setOnClickListener {
            btnHQ.isSelected = btnHQ.isSelected != true
        }
        btnRandom.setOnClickListener {
            btnRandom.isSelected = btnRandom.isSelected != true
        }
        btnLike.setOnClickListener {
            btnLike.isSelected = btnLike.isSelected != true
            if (arrayLike[index] == 0) {
                arrayLike[index] = 1
                btnNotInt.isSelected = false
                arrayDisLike[index] = 0
            } else {
                arrayLike[index] = 0
                btnNotInt.isSelected = false
            }
        }
        btnShare.setOnClickListener {
            startActivity(
                Intent.createChooser(
                    ShareHelper.shareMusic(arrayMusic[index], arrayInfoMusic[index]),
                    "Share by"
                )
            )
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