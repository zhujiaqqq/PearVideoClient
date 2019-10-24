package com.example.pearvideoclient.utils

import android.content.Context

/**
 * @Description: java类作用描述
 * @Author: jiazhu
 * @Date: 2019-08-23 21:39
 * @ClassName: ScreenUtils
 */
object ScreenUtils {
    @JvmStatic
    fun dip2px(context: Context, dipValue: Float): Int {
        val scale = context.resources.displayMetrics.density
        return (dipValue * scale + 0.5f).toInt()
    }
}
