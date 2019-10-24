package com.example.pearvideoclient.utils

import android.annotation.SuppressLint
import android.content.Context
import android.os.CountDownTimer
import android.os.Handler
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import android.widget.Toast

import com.example.pearvideoclient.R


/**
 * @Description: java类作用描述
 * @Author: jiazhu
 * @Date: 2019-08-24 15:33
 * @ClassName: MyToast
 */
class MyToast private constructor(context: Context, private val mHandler: Handler = Handler()) {
    private var mToast: Toast? = null
    private val mToastContent: TextView
    private var mCanceled: Boolean = false

    init {

        val layout = LayoutInflater.from(context)
                .inflate(R.layout.my_toast_layout, null, false)
        mToastContent = layout.findViewById(R.id.tv_toast_content)
        if (mToast == null) {
            mToast = Toast(context)
        }
        mToast!!.setGravity(Gravity.CENTER, 0, 0)
        mToast!!.duration = Toast.LENGTH_LONG
        mToast!!.view = layout
    }

    fun show(text: String, duration: Int) {
        val timeCount = TimeCount(duration.toLong(), 1000)
        mToastContent.text = text
        if (mCanceled) {
            timeCount.start()
            mCanceled = false
            showUntilCancel()
        }
    }

    private fun showUntilCancel() {
        if (mCanceled) {
            return
        }
        mToast!!.show()
        mHandler.postDelayed({ this.showUntilCancel() }, 3000)
    }

    internal inner class TimeCount
    /**
     * @param millisInFuture    The number of millis in the future from the call
     * to [.start] until the countdown is done and [.onFinish]
     * is called.
     * @param countDownInterval The interval along the way to receive
     * [.onTick] callbacks.
     */
    (millisInFuture: Long, countDownInterval: Long) : CountDownTimer(millisInFuture, countDownInterval) {

        override fun onTick(millisUntilFinished: Long) {
            hide()
        }

        override fun onFinish() {

        }
    }

    private fun hide() {
        if (mToast != null) {
            mToast!!.cancel()
        }
        mCanceled = true

    }

    companion object {
        @SuppressLint("StaticFieldLeak")
        private var instance: MyToast? = null
        @JvmStatic
        fun getInstance(context: Context): MyToast {
            if (instance == null) {
                instance = MyToast(context)
            }
            return instance as MyToast
        }
    }


}
