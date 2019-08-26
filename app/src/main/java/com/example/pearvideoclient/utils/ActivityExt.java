package com.example.pearvideoclient.utils;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;

/**
 * @Description: java类作用描述
 * @Author: jiazhu
 * @Date: 2019-08-25 15:49
 * @ClassName: ActivityExt
 */
public class ActivityExt {
    private final Activity mActivity;
    private final ViewGroup mViewGroup;

    public ActivityExt(Activity activity) {
        mActivity = activity;
        mViewGroup = activity.findViewById(android.R.id.content);
    }

    public void addContentView(View view, ViewGroup.LayoutParams params) {
        mViewGroup.addView(view, params);
    }

    public void removeContentView(View view) {
        mViewGroup.removeView(view);
    }
}
