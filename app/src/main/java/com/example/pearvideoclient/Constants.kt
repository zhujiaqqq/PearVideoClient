package com.example.pearvideoclient

import androidx.annotation.IntDef

/**
 * @author jiazhu
 */
object Constants {
   const val BASE_URL = "http://app.pearvideo.com/"
   const val SUCCESS = "SUCCESS"
   const val HEADER_CLICK = 3

   const val COMMON = 0
   const val LOAD_MORE = 1
   const val LOAD_REFRESH = 2

    @kotlin.annotation.Retention(AnnotationRetention.SOURCE)
    @IntDef(value = [COMMON, LOAD_MORE, LOAD_REFRESH])
    annotation class LoadType
}
