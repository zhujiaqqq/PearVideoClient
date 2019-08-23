package com.example.pearvideoclient.utils;

import android.content.Context;

/**
 * @Description: java类作用描述
 * @Author: jiazhu
 * @Date: 2019-08-23 21:39
 * @ClassName: ScreenUtils
 */
public class ScreenUtils {
    private ScreenUtils() {
        // do nothing
    }

    public static int dip2px(Context context, float dipValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5F);
    }
}
