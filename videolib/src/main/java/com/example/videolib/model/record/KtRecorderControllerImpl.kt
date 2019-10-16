package com.example.videolib.model.record

import android.hardware.Camera
import android.media.CamcorderProfile
import android.media.MediaRecorder
import android.os.Environment
import android.view.SurfaceHolder
import com.example.apublic.LocalHandler
import com.example.apublic.SingleThreadPooler
import java.io.File
import java.lang.Exception

class KtRecorderControllerImpl(private var mHolder: SurfaceHolder,
                               private var mHandler: LocalHandler,
                               private var mOnRecordListener: KtOnRecordListener)
    : MediaRecorder.OnErrorListener, KtIRecorderController {


    companion object {

        const val MAX_TIME = 600

        const val MSG_REFRESH_TIME = 1;

        const val MSG_FINISH_RECORD = 2;
    }

    private var mFrontIndex: Int = 0
    private var mBackIndex: Int = 0

    private var mFrontOrientation: Int = 0
    private var mBackOrientation: Int = 0

    private var isFront: Boolean = false

    private var isRecord: Boolean = false

    private var mCamera: Camera? = null

    private var mRecordFile: File? = null

    private var mMediaRecorder: MediaRecorder? = null


    override fun onError(mr: MediaRecorder?, what: Int, extra: Int) {
        mr!!.reset()
    }

    override fun initCamera() {
        getCameraInfo()
        if (mCamera != null) {
            freeCameraResource()
        }
        try {
            mCamera = Camera.open(if (isFront) mFrontIndex else mBackIndex)
            if (mCamera == null) {
                return
            }
            initParameters()
            mCamera!!.setDisplayOrientation(mBackOrientation)
            mCamera!!.setPreviewDisplay(mHolder)
            mCamera!!.startPreview()
            mCamera!!.unlock()
        } catch (e: Exception) {
            e.printStackTrace()
            freeCameraResource()
        }
    }

    private fun initParameters() {
        val parameters = mCamera!!.parameters
        val camcorderProfile = CamcorderProfile.get(CamcorderProfile.QUALITY_1080P)
        parameters.setPreviewSize(camcorderProfile.videoFrameWidth, camcorderProfile.videoFrameHeight)
        val focusModes = parameters.supportedFocusModes
        if (focusModes.contains("continuous-video")) {
            parameters.focusMode = Camera.Parameters.FOCUS_MODE_CONTINUOUS_VIDEO
        }
        mCamera!!.parameters = parameters
    }

    override fun startRecorder() {
        isRecord = true
        createRecordDir()
        try {
            initRecord(if (isFront) mFrontOrientation else mBackOrientation)
            SingleThreadPooler.getInstance().doTast {
                var count = 0
                while (isRecord) {
                    if (count == MAX_TIME) {
                        mHandler.sendEmptyMessage(MSG_FINISH_RECORD)
                        break
                    } else {
                        mOnRecordListener.onRecordProgress(count++)
                        try {
                            Thread.sleep(100)
                        } catch (e: InterruptedException) {
                            e.printStackTrace()
                        }
                    }
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun initRecord(i: Int) {
        try {
            if (mMediaRecorder == null) {
                mMediaRecorder = MediaRecorder()
                mMediaRecorder!!.setOnErrorListener(this)
            } else {
                mMediaRecorder!!.reset()
            }

            mMediaRecorder!!.setCamera(mCamera)
            mMediaRecorder!!.setPreviewDisplay(mHolder.surface)

            mMediaRecorder!!.setVideoSource(MediaRecorder.VideoSource.CAMERA)
            mMediaRecorder!!.setAudioSource(MediaRecorder.AudioSource.MIC)

            mMediaRecorder!!.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4)

            val profile = CamcorderProfile.get(CamcorderProfile.QUALITY_1080P)
            mMediaRecorder!!.setVideoSize(profile.videoFrameWidth, profile.videoFrameHeight)

            mMediaRecorder!!.setAudioEncodingBitRate(44100)

            if (profile.videoBitRate > 2 * 1024 * 1024) {
                mMediaRecorder!!.setVideoEncodingBitRate(281024 * 1024)
            } else {
                mMediaRecorder!!.setVideoEncodingBitRate(profile.videoBitRate)
            }

            mMediaRecorder!!.setVideoFrameRate(profile.videoFrameRate)
            mMediaRecorder!!.setAudioEncoder(MediaRecorder.AudioEncoder.AAC)
            mMediaRecorder!!.setVideoEncoder(MediaRecorder.VideoEncoder.H264)
            mMediaRecorder!!.setOrientationHint(i)
            mMediaRecorder!!.setOutputFile(mRecordFile!!.getAbsolutePath())
            mMediaRecorder!!.prepare()
            mMediaRecorder!!.start()
        } catch (e: Exception) {
            e.printStackTrace()
        }


    }

    private fun createRecordDir() {
        val sampleDir = File(Environment.getExternalStorageDirectory().path + "/temp")
        if (!sampleDir.exists()) {
            sampleDir.mkdir()
        }
        mRecordFile = File.createTempFile(System.currentTimeMillis().toString(), ".mp4", sampleDir)
    }

    override fun stopRecorder(): String? {
        isRecord = false
        stopRecord()
        releaseRecord()
        mOnRecordListener.onRecordFinish()
        mCamera!!.lock()
        return mRecordFile?.absolutePath
    }

    private fun releaseRecord() {
        mMediaRecorder?.release()
        mMediaRecorder == null
    }

    private fun stopRecord() {
        mMediaRecorder?.setOnErrorListener(null)
        mMediaRecorder?.setPreviewDisplay(null)
        mMediaRecorder!!.stop()

    }

    override fun freeCameraResource() {
        if (mCamera != null) {
            mCamera!!.setPreviewCallback(null)
            mCamera!!.stopPreview()
            mCamera!!.lock()
            mCamera!!.release()
            mCamera = null
        }
    }

    override fun toggleCamera() {
        isFront = !isFront
        initCamera()
    }

    override fun surfaceChanged(holder: SurfaceHolder?, format: Int, width: Int, height: Int) {
        if (mHolder.surface == null) {
            return
        }
        try {
            mCamera!!.stopPreview()
            mCamera!!.setDisplayOrientation(mBackOrientation)
            mCamera!!.startPreview()
            mCamera!!.setPreviewDisplay(holder)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun surfaceDestroyed(holder: SurfaceHolder?) {
        freeCameraResource()
    }

    override fun surfaceCreated(holder: SurfaceHolder?) {
        initCamera()
    }

    /**
     * 获取camera信息
     */
    private fun getCameraInfo() {
        val cameraInfo = Camera.CameraInfo()
        val cameras = Camera.getNumberOfCameras()

        for (i in 0 until cameras) {
            Camera.getCameraInfo(i, cameraInfo)
            if (cameraInfo.facing == Camera.CameraInfo.CAMERA_FACING_FRONT) {
                mFrontIndex = i
                mFrontOrientation = cameraInfo.orientation
            } else if (cameraInfo.facing == Camera.CameraInfo.CAMERA_FACING_BACK) {
                mBackIndex = i
                mBackOrientation = cameraInfo.orientation
            }
        }
    }

}