package com.example.pearvideoclient.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pearvideoclient.R;


/**
 * @Description: java类作用描述
 * @Author: jiazhu
 * @Date: 2019-08-24 15:33
 * @ClassName: MyToast
 */
public class MyToast {
    private Handler mHandler;
    private Toast mToast;
    private TextView mToastContent;
    @SuppressLint("StaticFieldLeak")
    private static MyToast instance;
    private boolean mCanceled;

    public static MyToast getInstance(Context context) {
        if (instance == null) {
            instance = new MyToast(context);
        }
        return instance;
    }

    private MyToast(Context context) {
        this(context, new Handler());
    }

    private MyToast(Context context, Handler handler) {
        this.mHandler = handler;

        View layout = LayoutInflater.from(context)
                .inflate(R.layout.my_toast_layout, null, false);
        mToastContent = layout.findViewById(R.id.tv_toast_content);
        if (mToast == null) {
            mToast = new Toast(context);
        }
        mToast.setGravity(Gravity.CENTER, 0, 0);
        mToast.setDuration(Toast.LENGTH_LONG);
        mToast.setView(layout);
    }

    public void show(String text, int duration) {
        TimeCount timeCount = new TimeCount(duration, 1000);
        mToastContent.setText(text);
        if (mCanceled) {
            timeCount.start();
            mCanceled = false;
            showUntilCancel();
        }
    }

    private void showUntilCancel() {
        if (mCanceled) {
            return;
        }
        mToast.show();
        mHandler.postDelayed(this::showUntilCancel, 3000);
    }

    class TimeCount extends CountDownTimer {

        /**
         * @param millisInFuture    The number of millis in the future from the call
         *                          to {@link #start()} until the countdown is done and {@link #onFinish()}
         *                          is called.
         * @param countDownInterval The interval along the way to receive
         *                          {@link #onTick(long)} callbacks.
         */
        public TimeCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            hide();
        }

        @Override
        public void onFinish() {

        }
    }

    private void hide() {
        if (mToast != null) {
            mToast.cancel();
        }
        mCanceled = true;

    }


}
