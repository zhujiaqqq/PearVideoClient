package com.example.pearvideoclient.http;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.telephony.TelephonyManager;

import com.example.pearvideoclient.MyApplication;
import com.example.pearvideoclient.utils.SharedPreferencesHelper;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @author zhujiaqqq
 * @date 2019-07-17
 */
public class HeaderInterceptor implements Interceptor {

    private SharedPreferencesHelper helper;

    public HeaderInterceptor() {
        this.helper = new SharedPreferencesHelper(MyApplication.getInstance(), "user_info");
    }

    @NotNull
    @Override
    public Response intercept(@NotNull Chain chain) throws IOException {
        Request request = chain.request();
        Request.Builder builder = request.newBuilder();

        builder.addHeader("X-Location", "118.792317%2C31.911538%7C%E4%B8%AD%E5%9B%BD%2C%E6%B1%9F%E8%8B%8F%E7%9C%81%2C%E5%8D%97%E4%BA%AC%E5%B8%82%2C%E6%B1%9F%E5%AE%81%E5%8C%BA");
        builder.addHeader("X-Client-Version", "5.6.1");
        builder.addHeader("X-Channel-Code", "lsp-xm");
        builder.addHeader("X-Client-Agent", getDeviceInfo());
        builder.addHeader("X-IMSI", getImsi(MyApplication.getInstance()));
        builder.addHeader("X-Long-Token", "");
        builder.addHeader("X-Platform-Version", getSystemVersion());
        builder.addHeader("X-Client-Hash", "c0e36761d546f84c3c89f80c1462ebab");
        builder.addHeader("X-User-ID", gerUserId());
        builder.addHeader("X-Platform-Type", "2");
        builder.addHeader("X-Client-ID", getDeviceId(MyApplication.getInstance()));
        builder.addHeader("X-Serial-Num", "1561166399");
        builder.addHeader("X-Tingyun-Id", "kfsGvaR2FEg;c=2;r=1025885723;");
        builder.removeHeader("User-Agent").addHeader("User-Agent", "okhttp/3.2.0");
        request = builder.build();

        return chain.proceed(request);
    }

    private String gerUserId() {
        Integer userId = (Integer) helper.getSharedPreference("userId", 0);
        if (userId == 0) {
            return "";
        }
        return String.valueOf(userId);
    }

    private static String getDeviceId(Context ctx) {
        try {
            TelephonyManager tm = (TelephonyManager) ctx.getSystemService(Activity.TELEPHONY_SERVICE);
            if (tm != null) {
                if (ActivityCompat.checkSelfPermission(ctx, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
                    return "869677025167391";
                }
                return tm.getDeviceId();
            }
            return "869677025167391";
        } catch (Exception e) {
            e.printStackTrace();
            return "869677025167391";
        }

    }

    private static String getSystemVersion() {
        return Build.VERSION.RELEASE == null ? "" : Build.VERSION.RELEASE;
    }

    private static String getImsi(Context ctx) {
        try {
            TelephonyManager tm = (TelephonyManager) ctx.getSystemService(Activity.TELEPHONY_SERVICE);
            if (tm != null) {
                if (ActivityCompat.checkSelfPermission(ctx, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
                    return "46001";
                }
                return tm.getSubscriberId().substring(0, 5);
            }
            return "46001";
        } catch (Exception e) {
            e.printStackTrace();
            return "46001";
        }

    }

    private static String getDeviceInfo() {
        String board = Build.MANUFACTURER == null ? "" : Build.MANUFACTURER;
        String model = Build.MODEL == null ? "" : Build.MODEL;
        String version = Build.VERSION.RELEASE == null ? "" : Build.VERSION.RELEASE;
        return board + "_" + model + "_" + version;
    }
}
