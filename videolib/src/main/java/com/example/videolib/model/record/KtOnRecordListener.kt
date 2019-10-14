package com.example.videolib.model.record

interface KtOnRecordListener {
    /**
     * 录制完成
     */
    fun onRecordFinish()

    /**
     * 录制进度
     *
     * @param progress 进度
     */
    fun onRecordProgress(process: Int)

}