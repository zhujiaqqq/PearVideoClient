package com.example.videolib

import android.graphics.Bitmap
import android.media.AudioAttributes
import android.media.MediaMetadataRetriever
import android.media.MediaPlayer
import android.os.Bundle
import android.os.Environment
import android.os.Message
import android.util.Log
import android.view.SurfaceHolder
import android.view.View
import android.widget.ImageView
import android.widget.SeekBar
import androidx.appcompat.app.AppCompatActivity
import com.example.apublic.LocalHandler
import com.example.apublic.SingleThreadPooler
import kotlinx.android.synthetic.main.activity_player.*

class PlayerActivity : AppCompatActivity(), SurfaceHolder.Callback, LocalHandler.IHandler {

    companion object {
        const val MSG_SHOW_CONTROLLER = 1
        const val MSG_HIDE_CONTROLLER = 2
        const val MSG_REFRESH_PROGRESS = 3
        const val TAG = "PlayerActivity"


    }

    private lateinit var mPath: String
    private var mMediaPlayer: MediaPlayer? = null
    private val localHandler = LocalHandler(this)
    private var isPlaying: Boolean = false
    private var duration: Int = 0
    private var life: Boolean = false


    override fun surfaceChanged(holder: SurfaceHolder?, format: Int, width: Int, height: Int) {

    }


    override fun surfaceDestroyed(holder: SurfaceHolder?) {
        if (mMediaPlayer != null) {
            mMediaPlayer!!.release()
        }
    }

    override fun surfaceCreated(holder: SurfaceHolder?) {
    }

    override fun handlerMessage(msg: Message?) {
        if (msg!!.what == MSG_SHOW_CONTROLLER) {
            group.visibility = View.VISIBLE
            localHandler.sendEmptyMessageDelayed(MSG_HIDE_CONTROLLER, 3000)
        } else if (msg.what == MSG_HIDE_CONTROLLER) {
            group.visibility = View.GONE
        } else if (msg.what == MSG_REFRESH_PROGRESS) {
            if (isPlaying) {
                val time = msg.arg1
                seek_bar.progress = time
                tv_current_time.text = showTime(time)
            }
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player)
        initData()
    }

    override fun onDestroy() {
        life = false
        if (mMediaPlayer != null) {
            mMediaPlayer!!.release()
        }
        super.onDestroy()
    }

    private fun initData() {
        mPath = Environment.getExternalStorageDirectory().path + "/test.mp4"

        val aspect = showThumbImage(mPath, iv_screen)
        surface_view.setAspect(aspect)

        val holder = surface_view.holder
        holder.addCallback(this)
        startPlayer(holder)
        SingleThreadPooler.getInstance().doTast {
            life = true
            while (life) {
                Thread.sleep(200)
                val obtainMessage = localHandler.obtainMessage()
                obtainMessage.what = MSG_REFRESH_PROGRESS
                obtainMessage.arg1 = seek_bar.progress + 200
                localHandler.sendMessage(obtainMessage)
            }
        }
        iv_player.setOnClickListener {
            if (isPlaying) {
                videoPause()
            } else {
                videoPlay()
            }
        }

        surface_view.setOnClickListener {
            localHandler.removeMessages(MSG_HIDE_CONTROLLER)
            localHandler.sendEmptyMessage(MSG_SHOW_CONTROLLER)
        }
    }

    private fun videoPlay() {
        iv_screen.visibility = View.GONE
        group.visibility = View.VISIBLE
        mMediaPlayer!!.start()
        iv_player.setImageResource(R.drawable.ic_pause_circle_outline_24dp)
        isPlaying = true
        seek_bar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                if (fromUser) {
                    seekBar!!.progress = progress
                }
                Log.i(TAG, "onProgressChanged, progress: $progress")
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                localHandler.removeCallbacksAndMessages(null)
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                mMediaPlayer!!.seekTo(seekBar!!.progress)
                localHandler.sendEmptyMessage(MSG_SHOW_CONTROLLER)
            }
        })
    }

    private fun videoPause() {
        mMediaPlayer!!.pause()
        iv_player.setImageResource(R.drawable.ic_play_circle_outline_24dp)
        isPlaying = false
    }

    private fun startPlayer(holder: SurfaceHolder?) {
        mMediaPlayer = MediaPlayer()
        mMediaPlayer!!.setAudioAttributes(AudioAttributes.Builder().build())
        mMediaPlayer!!.setDataSource(mPath)
        mMediaPlayer!!.setOnPreparedListener {
            if (holder != null) {
                mMediaPlayer!!.setDisplay(holder)
            }
            isPlaying = false
            duration = it.duration

            tv_current_time.text = showTime(0)
            tv_total_time.text = showTime(duration)

            seek_bar.progress = 0
            seek_bar.max = duration
        }

        mMediaPlayer!!.setOnCompletionListener {
            it.release()
        }

        mMediaPlayer!!.setOnSeekCompleteListener {
        }

        mMediaPlayer!!.prepareAsync()
    }

    private fun showTime(i: Int): String? {
        val second = i / 1000
        return getString(R.string.tv_show_time_2, second / 60, second % 60)
    }

    /**
     * 展示预览图
     */
    private fun showThumbImage(mPath: String, imageView: ImageView): Double {
        val bitmap: Bitmap
        val media = MediaMetadataRetriever()
        if (mPath.startsWith("http://") ||
                mPath.startsWith("https://") ||
                mPath.startsWith("widevine://")) {
            media.setDataSource(mPath, HashMap<String, String>())
            bitmap = media.getFrameAtTime(0, MediaMetadataRetriever.OPTION_CLOSEST_SYNC)
        } else {
            media.setDataSource(mPath)
            bitmap = media.frameAtTime
        }

        var width = bitmap.width
        var height = bitmap.height

        val aspect: Double = (width.toDouble() / height.toDouble())
        val oldAspect: Double = (imageView.width.toDouble() / imageView.height.toDouble())
        val aspectDiff = aspect / oldAspect - 1
        if (Math.abs(aspectDiff) > 0.01) {
            if (aspectDiff > 0) {
                height = (width / aspect).toInt()
            } else {
                width = (height * aspect).toInt()
            }
            val layoutParams = imageView.layoutParams
            layoutParams.height = height
            layoutParams.width = width
            imageView.layoutParams = layoutParams
        }
        imageView.setImageBitmap(bitmap)
        return aspect

    }
}
