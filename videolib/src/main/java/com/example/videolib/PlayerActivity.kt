package com.example.videolib

import android.os.Bundle
import android.os.Environment
import android.os.Message
import android.view.View
import android.widget.SeekBar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.apublic.LocalHandler
import com.example.apublic.SingleThreadPooler
import com.example.videolib.viewmodel.KtPlayVideoViewModel
import com.example.videolib.viewmodel.KtPlayVideoViewModel.Companion.MSG_HIDE_CONTROLLER
import com.example.videolib.viewmodel.KtPlayVideoViewModel.Companion.MSG_REFRESH_PROGRESS
import com.example.videolib.viewmodel.KtPlayVideoViewModel.Companion.MSG_SHOW_CONTROLLER
import com.example.videolib.viewmodel.KtPlayVideoViewModel.Companion.STATUS_IDLE
import com.example.videolib.viewmodel.KtPlayVideoViewModel.Companion.STATUS_PAUSE
import com.example.videolib.viewmodel.KtPlayVideoViewModel.Companion.STATUS_PLAYING
import com.example.videolib.viewmodel.KtPlayVideoViewModel.Companion.STATUS_STOP
import kotlinx.android.synthetic.main.activity_player.*

class PlayerActivity : AppCompatActivity(), LocalHandler.IHandler {
    val localHandler = LocalHandler(this)


    override fun handlerMessage(msg: Message?) {
        if (msg!!.what == MSG_SHOW_CONTROLLER) {
            group.visibility = View.VISIBLE
            localHandler.sendEmptyMessageDelayed(MSG_HIDE_CONTROLLER, 3000)
        } else if (msg.what == MSG_HIDE_CONTROLLER) {
            group.visibility = View.GONE
        } else if (msg.what == MSG_REFRESH_PROGRESS) {
            val time = msg.arg1
            seek_bar.progress = time
            tv_current_time.text = showTime(time)
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player)
        initData()
    }

    private fun initData() {
        val path = Environment.getExternalStorageDirectory().path + "/test.mp4"
        val viewModel = ViewModelProviders.of(this).get(KtPlayVideoViewModel::class.java)

        viewModel.init(path, localHandler, surface_view.holder)
        viewModel.playStatus.observe(this, Observer {
            when (it) {
                STATUS_STOP -> {
                    tv_current_time.text = showTime(0)
                    tv_total_time.text = showTime(viewModel.duration)
                    seek_bar.progress = 0
                    seek_bar.max = viewModel.duration
                }
                STATUS_PAUSE -> {
                    iv_player.setImageResource(R.drawable.ic_play_circle_outline_24dp)
                }
                STATUS_IDLE -> {
                    iv_screen.visibility = View.VISIBLE
                }
                STATUS_PLAYING -> {
                    iv_screen.visibility = View.GONE
                    group.visibility = View.VISIBLE
                    iv_player.setImageResource(R.drawable.ic_pause_circle_outline_24dp)

                }
            }
        })

        seek_bar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                if (fromUser) {
                    seekBar!!.progress = progress
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                localHandler.removeCallbacksAndMessages(null)
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                viewModel.seekTo(seekBar!!.progress)
                localHandler.sendEmptyMessage(MSG_SHOW_CONTROLLER)
            }
        })


        SingleThreadPooler.getInstance().doTast {
            while (true) {
                Thread.sleep(200)
                val obtainMessage = localHandler.obtainMessage()
                obtainMessage.what = MSG_REFRESH_PROGRESS
                obtainMessage.arg1 = seek_bar.progress + 200
                localHandler.sendMessage(obtainMessage)
            }
        }

        iv_player.setOnClickListener {
            if (viewModel.playStatus.value != STATUS_PLAYING) {
                viewModel.play()
            } else {
                viewModel.pause()
            }
        }

        surface_view.setOnClickListener {
            localHandler.removeMessages(MSG_HIDE_CONTROLLER)
            localHandler.sendEmptyMessage(MSG_SHOW_CONTROLLER)
        }

        val aspect = viewModel.reSizeImageView(iv_screen)
        surface_view.setAspect(aspect)
    }

    private fun showTime(i: Int): String? {
        val second = i / 1000
        return getString(R.string.tv_show_time_2, second / 60, second % 60)
    }
}
