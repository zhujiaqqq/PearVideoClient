package com.example.route;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dalvik.system.DexFile;

/**
 * @Description: java类作用描述
 * @Author: jiazhu
 * @Date: 2019-09-19 09:29
 * @ClassName: Route
 */
public class Route {
    private static Route route = new Route();

    private Context mContext;
    private Map<String, Class<? extends Activity>> activityList;

    private Route() {
        activityList = new HashMap<>();
    }

    public static Route getInstance() {
        return route;
    }

    public void init(Application application) {
        mContext = application;
        List<String> classNames = getClassName("com.example.util");
        for (String s : classNames) {
            try {
                Class<?> aClass = Class.forName(s);
                if (IRoute.class.isAssignableFrom(aClass)) {
                    IRoute iRoute = (IRoute) aClass.newInstance();
                    iRoute.putActivity();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private List<String> getClassName(String packageName) {
        ArrayList<String> classList = new ArrayList<>();
        String path = null;
        try {
            path = mContext.getPackageManager().getApplicationInfo(mContext.getPackageName(), 0).sourceDir;
            DexFile dexFile = new DexFile(path);
            Enumeration<String> entries = dexFile.entries();
            while (entries.hasMoreElements()) {
                String name = entries.nextElement();
                if (name.contains(packageName)) {
                    classList.add(name);
                }
            }
            return classList;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void putActivity(String path, Class<? extends Activity> clazz) {
        if (TextUtils.isEmpty(path)) {
            return;
        }
        if (clazz == null) {
            return;
        }
        activityList.put(path, clazz);
    }

    public void jumpActivity(String path, Bundle bundle) {
        Class<? extends Activity> aClass = activityList.get(path);
        if (aClass == null) {
            return;
        }
        Intent intent = new Intent().setClass(mContext, aClass);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        if (bundle != null) {
            intent.putExtra("bundle", bundle);
        }
        mContext.startActivity(intent);
    }
}
