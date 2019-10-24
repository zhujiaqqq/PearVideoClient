package com.example.pearvideoclient

import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.os.Environment
import android.os.Looper
import android.os.Process
import android.util.Log
import android.widget.Toast
import java.io.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.system.exitProcess

/**
 * @author zhujiaqqq
 * @date 2019-07-11
 */
class CrashHandler private constructor() : Thread.UncaughtExceptionHandler {
    private var mDefaultHandler: Thread.UncaughtExceptionHandler? = null
    private var mContext: Context? = null
    private val infos = HashMap<String, String>()
    private val sdf = SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.CHINA)

    fun init(context: Context) {
        mContext = context
        mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler()
        Thread.setDefaultUncaughtExceptionHandler(this)
    }


    override fun uncaughtException(t: Thread, e: Throwable) {
        if (!handleException(e) && mDefaultHandler != null) {
            mDefaultHandler!!.uncaughtException(t, e)
        } else {
            if (BuildConfig.DEBUG) {
                //                debug模式下将异常打出到logcat，方便查看
                mDefaultHandler!!.uncaughtException(t, e)
            }
            try {
                Thread.sleep(3000)
            } catch (e1: InterruptedException) {
                Log.e(TAG, "uncaughtException: $e1")
            }

            Process.killProcess(Process.myPid())
            exitProcess(1)
        }
    }

    private fun handleException(ex: Throwable?): Boolean {
        if (ex == null) {
            return false
        }

        object : Thread() {
            override fun run() {
                Looper.prepare()
                Toast.makeText(mContext, "异常崩溃，正在记录日志", Toast.LENGTH_SHORT).show()
                Looper.loop()
            }
        }.start()
        //收集设备参数信息
        collectDeviceInfo(mContext!!)
        //保存日志文件
        saveCrashInfo2File(ex)
        return true
    }

    /**
     * 收集设备参数信息
     *
     * @param ctx
     */
    private fun collectDeviceInfo(ctx: Context) {
        try {
            val pm = ctx.packageManager
            val pi = pm.getPackageInfo(ctx.packageName, PackageManager.GET_ACTIVITIES)
            if (pi != null) {
                val versionName = if (pi.versionName == null) "null" else pi.versionName
                val versionCode = pi.longVersionCode.toString() + ""
                infos["versionName"] = versionName
                infos["versionCode"] = versionCode
            }
        } catch (e: PackageManager.NameNotFoundException) {
            Log.e(TAG, "an error occured when collect package info", e)
        }

        val fields = Build::class.java.declaredFields
        for (field in fields) {
            try {
                field.isAccessible = true
                infos[field.name] = field.get(null).toString()
                Log.d(TAG, field.name + " : " + field.get(null))
            } catch (e: Exception) {
                Log.e(TAG, "an error occured when collect crash info", e)
            }

        }
    }

    /**
     * 保存错误信息到文件中
     *
     * @param ex
     */
    private fun saveCrashInfo2File(ex: Throwable) {

        val sb = StringBuilder()
        for ((key, value) in infos) {
            sb.append("$key=$value\n")
        }

        val writer = StringWriter()
        val printWriter = PrintWriter(writer)
        ex.printStackTrace(printWriter)
        var cause: Throwable? = ex.cause
        while (cause != null) {
            cause.printStackTrace(printWriter)
            cause = cause.cause
        }
        printWriter.close()
        val result = writer.toString()
        sb.append(result)
        var fos: FileOutputStream? = null
        try {
            val timestamp = System.currentTimeMillis()
            val time = sdf.format(Date())
            val fileName = "/crash-$time-$timestamp.log"
            if (Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED) {
                val path = Environment.getExternalStorageDirectory().path
                val dir = File(path)
                if (!dir.exists()) {
                    dir.mkdirs()
                }
                val file = File(path + fileName)
                fos = FileOutputStream(file)
                fos.write(sb.toString().toByteArray())

            }
        } catch (e: Exception) {
            Log.e(TAG, "an error occured while writing file...", e)
        } finally {
            try {
                fos?.close()
            } catch (e: IOException) {
                Log.e(TAG, "saveCrashInfo2File: " + e.message)
            }

        }
    }

    companion object {
        private const val TAG = "CrashHandler"
        @SuppressLint("StaticFieldLeak")
        val instance = CrashHandler()
    }
}
