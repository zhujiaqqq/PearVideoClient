package com.example.videolib.viewmodel

import android.app.Application
import android.view.SurfaceHolder
import androidx.annotation.NonNull
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.apublic.LocalHandler
import com.example.videolib.model.record.KtIRecorderController
import com.example.videolib.model.record.KtOnRecordListener
import com.example.videolib.model.record.KtRecorderControllerImpl

class KtRecordVideoViewModel(application: Application) : AndroidViewModel(application) {
    lateinit var isRecord: MutableLiveData<Boolean>
    lateinit var isCompleted: MutableLiveData<Boolean>

    val videoPaths = ArrayList<String>()
    var isFirst = true
    lateinit var mRecorderController: KtIRecorderController
    lateinit var mHandler: LocalHandler

    fun initRecorderController(@NonNull holder: SurfaceHolder,
                               @NonNull handler: LocalHandler,
                               @NonNull listener: KtOnRecordListener) {
        mHandler = handler
        mRecorderController = KtRecorderControllerImpl(holder, mHandler, listener)
        holder.addCallback(mRecorderController)
        holder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS)

        isRecord = MutableLiveData(false)

        isCompleted= MutableLiveData(false)
    }

    fun toggleCamera() {
        mRecorderController.toggleCamera()
    }

    fun freeCameraResource() {
        mRecorderController.freeCameraResource()
    }

    fun startRecorder() {
        mRecorderController.startRecorder()
    }

    fun stopRecorder() {
        val path = mRecorderController.stopRecorder()
        videoPaths.add(path!!)
    }

    fun cancelRecorder() {
        if (videoPaths.size > 0) {
            videoPaths.removeAt(videoPaths.size - 1)
        }
        if (videoPaths.size == 0) {
            isCompleted.value = false
        }
    }
}