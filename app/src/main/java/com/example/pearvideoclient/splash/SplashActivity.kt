package com.example.pearvideoclient.splash

import android.content.Intent
import android.os.Bundle
import android.os.Message
import androidx.appcompat.app.AppCompatActivity

import android.widget.TextView

import com.example.apublic.LocalHandler
import com.example.pearvideoclient.MainActivity
import com.example.pearvideoclient.R
import kotlinx.android.synthetic.main.activity_splash.*

/**
 * @Description: java类作用描述
 * @Author: jiazhu
 * @Date: 2019-08-21 11:26
 * @ClassName: SplashActivity
 */
class SplashActivity : AppCompatActivity(), LocalHandler.IHandler {
    private val mHandler = LocalHandler(this)

    private var deadline = 3

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        initView()
    }

    private fun initView() {
        tv_jump.setOnClickListener { jumpToMain() }
        tv_jump.text = getString(R.string.tv_jump, deadline)
    }

    override fun onResume() {
        super.onResume()
        mHandler.sendEmptyMessageDelayed(MSG_FINISH_SPLASH, 1000)
    }

    override fun handlerMessage(msg: Message) {
        if (msg.what == MSG_FINISH_SPLASH) {
            deadline--
            if (deadline == 0) {
                jumpToMain()
            } else {
                tv_jump.text = getString(R.string.tv_jump, deadline)
                mHandler.sendEmptyMessageDelayed(MSG_FINISH_SPLASH, 1000)
            }
        }

    }

    private fun jumpToMain() {
        mHandler.removeMessages(MSG_FINISH_SPLASH)
        val intent = Intent(this@SplashActivity, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    companion object {
        const val MSG_FINISH_SPLASH = 0x1
    }
}
