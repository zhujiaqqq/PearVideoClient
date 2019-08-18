package com.example.pearvideoclient;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author jiazhu
 */
public class Constants {
    public static final String BASE_URL = "http://app.pearvideo.com/";
    public static final String SUCCESS = "SUCCESS";

    @Retention(value = RetentionPolicy.SOURCE)
    @IntDef(value = {COMMON, LOAD_MORE, LOAD_REFRESH})
    public @interface LoadType {

    }

    public static final int COMMON = 0;
    public static final int LOAD_MORE = 1;
    public static final int LOAD_REFRESH = 2;
}
