package com.example.pearvideoclient;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;

import com.example.route.Route;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

/**
 * @Description: java类作用描述
 * @Author: jiazhu
 * @Date: 2019-07-10
 * @ClassName: MyApplication
 */
public class MyApplication extends Application {
    private RefWatcher mRefWatcher;

    //static 代码段可以防止内存泄露
    static {
        //设置全局的Header构建器
        SmartRefreshLayout.setDefaultRefreshHeaderCreator((context, layout) -> {
            layout.setPrimaryColorsId(R.color.colorPrimary, android.R.color.white);//全局设置主题颜色
            return new ClassicsHeader(context);//.setTimeFormat(new DynamicTimeFormat("更新于 %s"));//指定为经典Header，默认是 贝塞尔雷达Header
        });
        //设置全局的Footer构建器
        SmartRefreshLayout.setDefaultRefreshFooterCreator((context, layout) -> {
            //指定为经典Footer，默认是 BallPulseFooter
            return new ClassicsFooter(context).setDrawableSize(20);
        });
    }

    private static MyApplication instance;
    private ActivityLifecycleCallbacks activityLifecycleCallbacks;

    public static MyApplication getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Route.getInstance().init(this);
        instance = this;
        activityLifecycleCallbacks = new ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
                //
            }

            @Override
            public void onActivityStarted(Activity activity) {
                //
            }

            @Override
            public void onActivityResumed(Activity activity) {
                //
            }

            @Override
            public void onActivityPaused(Activity activity) {
                //
            }

            @Override
            public void onActivityStopped(Activity activity) {
                //
            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
                //
            }

            @Override
            public void onActivityDestroyed(Activity activity) {
                //
            }
        };
        registerActivityLifecycleCallbacks(activityLifecycleCallbacks);

        CrashHandler.getInstance().init(getApplicationContext());


        mRefWatcher = setupLeakCanary();

    }

    private RefWatcher setupLeakCanary() {
        if (LeakCanary.isInAnalyzerProcess(this)) {
            return RefWatcher.DISABLED;
        }
        return LeakCanary.install(this);
    }

    public static RefWatcher getRefWatcher(Context context) {
        MyApplication leakApplication = (MyApplication) context.getApplicationContext();
        return leakApplication.mRefWatcher;
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        if (activityLifecycleCallbacks != null) {
            unregisterActivityLifecycleCallbacks(activityLifecycleCallbacks);
        }
    }
}
