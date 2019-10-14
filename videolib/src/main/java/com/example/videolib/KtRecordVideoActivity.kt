package com.example.videolib

import android.os.Bundle
import android.os.Message
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.apublic.LocalHandler
import com.example.videolib.databinding.ActivityRecordVideoBinding
import com.example.videolib.model.record.KtOnRecordListener
import com.example.videolib.model.record.KtRecorderControllerImpl.Companion.MSG_FINISH_RECORD
import com.example.videolib.model.record.KtRecorderControllerImpl.Companion.MSG_REFRESH_TIME
import com.example.videolib.viewmodel.KtRecordVideoViewModel

class KtRecordVideoActivity : AppCompatActivity(), LocalHandler.IHandler {
    override fun handlerMessage(msg: Message?) {
        if (msg!!.what == MSG_REFRESH_TIME) {
            mBinding.tvRecordTime.text = countRecord(msg.arg1)
            mBinding.pbProgress.progress = msg.arg1 / 6
        } else if (msg.what == MSG_FINISH_RECORD) {
            mRecordViewModel.isRecord.value = false
        }

    }

    private lateinit var mBinding: ActivityRecordVideoBinding

    private lateinit var mRecordViewModel: KtRecordVideoViewModel

    private val mHandler = LocalHandler(this)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_record_video)
        mRecordViewModel = ViewModelProviders.of(this).get(KtRecordVideoViewModel::class.java)
        initData()
    }

    private fun initData() {
        val holder = mBinding.surfaceView.holder
        mRecordViewModel.initRecorderController(holder, mHandler, object : KtOnRecordListener {
            override fun onRecordFinish() {
                Toast.makeText(this@KtRecordVideoActivity, "finish", Toast.LENGTH_SHORT).show()
            }

            override fun onRecordProgress(progress: Int) {
                val message = mHandler.obtainMessage()
                message.what = MSG_REFRESH_TIME
                message.arg1 = progress
                mHandler.sendMessage(message)
            }
        })

        mRecordViewModel.isRecord.observe(this, Observer {
            mBinding.ivRecord.isSelected = it
            mBinding.tvRecordTime.visibility = if (it) View.VISIBLE else View.GONE
            mBinding.ivClose.visibility = if (it) View.GONE else View.VISIBLE
            mBinding.ivCameraToggle.visibility = if (it) View.GONE else View.VISIBLE
            if (it) {
                mRecordViewModel.startRecorder()
                mRecordViewModel.isCompleted.value = !it
            } else {
                if (mRecordViewModel.isFirst) {
                    mRecordViewModel.isFirst = false
                } else {
                    mRecordViewModel.stopRecorder()
                    mRecordViewModel.isCompleted.value = true
                }
            }
        })

        mRecordViewModel.isCompleted.observe(this, Observer {
            mBinding.ivCancel.visibility = if (it) View.VISIBLE else View.GONE
            mBinding.ivConfirm.visibility = if (it) View.VISIBLE else View.GONE
        })

        mBinding.ivRecord.setOnClickListener {
            mRecordViewModel.isRecord.value = !mRecordViewModel.isRecord.value!!
        }

        mBinding.ivClose.setOnClickListener {
            mRecordViewModel.freeCameraResource()
            finish()
        }

        mBinding.ivCameraToggle.setOnClickListener { mRecordViewModel.toggleCamera() }

        mBinding.ivCancel.setOnClickListener { mRecordViewModel.cancelRecorder() }
    }

    fun countRecord(time: Int): String {
        val second = time / 10
        val lSecond = time % 10
        return getString(R.string.tv_show_time, second, lSecond)
    }

}