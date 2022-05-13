package com.example.constraintlayoutweeklythree.util

import android.content.Intent

object ShareHelper {
    fun shareMusic(song: String, info: String): Intent {
        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "text/plane"
        intent.apply {
            putExtra(Intent.EXTRA_TEXT, makeShareText(song,info))
        }
        return intent
    }
    private fun makeShareText(song:String, info: String): String{
        val sBuilder = StringBuilder()
        sBuilder.append(song)
        sBuilder.append("\n")
        sBuilder.append(info)
        return sBuilder.toString()
    }
}