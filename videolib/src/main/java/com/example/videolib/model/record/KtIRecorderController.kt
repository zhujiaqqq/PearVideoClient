package com.example.videolib.model.record

import android.view.SurfaceHolder

interface KtIRecorderController : SurfaceHolder.Callback {
    /**
     * 初始化camera
     */
    fun initCamera()

    /**
     * 开始录制
     */
    fun startRecorder()

    /**
     * 停止录制
     */
    fun stopRecorder(): String?

    /**
     * 释放camera资源
     */
    fun freeCameraResource()

    /**
     * 切换前后摄像头
     */
    fun toggleCamera()


}