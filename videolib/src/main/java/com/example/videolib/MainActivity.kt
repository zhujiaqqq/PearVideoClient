package com.example.videolib

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun jumpToRecord(view: View) {
        val intent = Intent(this, KtRecordVideoActivity::class.java)
        startActivity(intent)
    }

    fun jumpToPlay(view: View) {
        val intent = Intent(this, PlayerActivity::class.java)
        startActivity(intent)
    }


}