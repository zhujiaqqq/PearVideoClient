package com.example.videolib.viewmodel

import android.app.Application
import android.graphics.Bitmap
import android.media.AudioAttributes
import android.media.MediaMetadataRetriever
import android.media.MediaPlayer
import android.view.SurfaceHolder
import android.widget.ImageView
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.apublic.LocalHandler

/**
 * @Description: java类作用描述
 * @Author: jiazhu
 * @Date: 2019-10-16 16:24
 * @ClassName: KtPlayVideoViewModel
 */
public class KtPlayVideoViewModel(application: Application) : AndroidViewModel(application), SurfaceHolder.Callback {
    companion object {

        const val MSG_SHOW_CONTROLLER = 1
        const val MSG_HIDE_CONTROLLER = 2
        const val MSG_REFRESH_PROGRESS = 3

        const val STATUS_IDLE = 0
        const val STATUS_STOP = 1
        const val STATUS_PAUSE = 2
        const val STATUS_PLAYING = 3
    }

    private lateinit var mediaPlayer: MediaPlayer

    var playStatus = MutableLiveData<Int>(STATUS_IDLE)
    var duration: Int = 0
    lateinit var path: String
    lateinit var handler: LocalHandler
    lateinit var holder: SurfaceHolder

    fun init(path: String, handler: LocalHandler, holder: SurfaceHolder) {
        this.path = path
        this.handler = handler
        this.holder = holder

        this.holder.addCallback(this)

        mediaPlayer = MediaPlayer()
        mediaPlayer.setAudioAttributes(AudioAttributes.Builder().build())
        mediaPlayer.setDataSource(path)
        mediaPlayer.setOnPreparedListener {
            it.setDisplay(holder)
            playStatus.value = STATUS_STOP
            duration = it.duration
        }
        mediaPlayer.setOnCompletionListener {
            playStatus.value = STATUS_STOP
            it.release()
        }
        mediaPlayer.setOnSeekCompleteListener {
            playStatus.value = STATUS_STOP
            it.release()
        }
        mediaPlayer.prepareAsync()
    }

    fun play() {
        mediaPlayer.start()
        playStatus.value = STATUS_PLAYING
    }

    fun pause() {
        mediaPlayer.pause()
        playStatus.value = STATUS_PAUSE
    }

    fun fullScreen() {

    }

    fun reSizeImageView(imageView: ImageView): Double {
        var height = imageView.height
        var width = imageView.width
        val thumbImage = getThumbImage(path)
        val aspect = getAspect(thumbImage)
        val oldAspect: Double = (width.toDouble() / height.toDouble())

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
        imageView.setImageBitmap(thumbImage)
        return aspect
    }

    /**
     * 获取视频关键帧图片
     */
    private fun getThumbImage(path: String): Bitmap {
        val bitmap: Bitmap
        val media = MediaMetadataRetriever()
        if (path.startsWith("http://") ||
                path.startsWith("https://") ||
                path.startsWith("widevine://")) {
            media.setDataSource(path, HashMap<String, String>())
            bitmap = media.getFrameAtTime(0, MediaMetadataRetriever.OPTION_CLOSEST_SYNC)
        } else {
            media.setDataSource(path)
            bitmap = media.frameAtTime
        }
        return bitmap
    }

    private fun getAspect(bitmap: Bitmap): Double {
        val width = bitmap.width
        val height = bitmap.height

        return (width.toDouble() / height.toDouble())
    }


    override fun surfaceChanged(holder: SurfaceHolder?, format: Int, width: Int, height: Int) {
        // do nothing
    }

    override fun surfaceDestroyed(holder: SurfaceHolder?) {
        mediaPlayer.release()
    }

    override fun surfaceCreated(holder: SurfaceHolder?) {
        // do nothing
    }

    fun seekTo(progress: Int) {
        mediaPlayer.seekTo(progress)
    }


}
