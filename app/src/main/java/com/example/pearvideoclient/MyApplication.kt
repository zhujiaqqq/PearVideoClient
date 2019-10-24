package com.example.pearvideoclient

import android.app.Application
import android.content.Context
import com.example.route.Route
import com.scwang.smartrefresh.layout.SmartRefreshLayout
import com.scwang.smartrefresh.layout.footer.ClassicsFooter
import com.scwang.smartrefresh.layout.header.ClassicsHeader
import com.squareup.leakcanary.LeakCanary
import com.squareup.leakcanary.RefWatcher

/**
 * @Description: java类作用描述
 * @Author: jiazhu
 * @Date: 2019-07-10
 * @ClassName: MyApplication
 */
class MyApplication : Application() {
    private var mRefWatcher: RefWatcher? = null
    private val activityLifecycleCallbacks: ActivityLifecycleCallbacks? = null

    override fun onCreate() {
        super.onCreate()
        Route.getInstance().init(this)
        instance = this
        CrashHandler.instance.init(applicationContext)


        mRefWatcher = setupLeakCanary()

    }

    private fun setupLeakCanary(): RefWatcher {
        return if (LeakCanary.isInAnalyzerProcess(this)) {
            RefWatcher.DISABLED
        } else LeakCanary.install(this)
    }

    override fun onTerminate() {
        super.onTerminate()
        if (activityLifecycleCallbacks != null) {
            unregisterActivityLifecycleCallbacks(activityLifecycleCallbacks)
        }
    }

    companion object {

        //static 代码段可以防止内存泄露
        init {
            //设置全局的Header构建器
            SmartRefreshLayout.setDefaultRefreshHeaderCreator { context, layout ->
                layout.setPrimaryColorsId(R.color.colorPrimary, android.R.color.white)//全局设置主题颜色
                ClassicsHeader(context)//.setTimeFormat(new DynamicTimeFormat("更新于 %s"));//指定为经典Header，默认是 贝塞尔雷达Header
            }
            //设置全局的Footer构建器
            SmartRefreshLayout.setDefaultRefreshFooterCreator { context, _ ->
                //指定为经典Footer，默认是 BallPulseFooter
                ClassicsFooter(context).setDrawableSize(20f)
            }
        }

        @JvmStatic
       lateinit var instance: MyApplication

        @JvmStatic
        fun getRefWatcher(context: Context): RefWatcher? {
            val leakApplication = context.applicationContext as MyApplication
            return leakApplication.mRefWatcher
        }
    }
}
