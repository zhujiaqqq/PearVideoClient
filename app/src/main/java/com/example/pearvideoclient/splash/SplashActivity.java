package com.example.pearvideoclient.splash;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.widget.TextView;

import com.example.apublic.LocalHandler;
import com.example.pearvideoclient.MainActivity;
import com.example.pearvideoclient.R;

/**
 * @Description: java类作用描述
 * @Author: jiazhu
 * @Date: 2019-08-21 11:26
 * @ClassName: SplashActivity
 */
public class SplashActivity extends AppCompatActivity implements LocalHandler.IHandler {
    private static final int MSG_FINISH_SPLASH = 0x1;
    private LocalHandler mHandler = new LocalHandler(this);

    private TextView mTvJump;

    private int deadline = 6;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        initView();
    }

    private void initView() {
        mTvJump = findViewById(R.id.tv_jump);
        mTvJump.setOnClickListener(v -> jumpToMain());
        mTvJump.setText(getString(R.string.tv_jump, deadline));
    }

    @Override
    protected void onResume() {
        super.onResume();
        mHandler.sendEmptyMessageDelayed(MSG_FINISH_SPLASH, 1000);
    }

    @Override
    public void handlerMessage(Message msg) {
        if (msg.what == MSG_FINISH_SPLASH) {
            deadline--;
            if (deadline == 0) {
                jumpToMain();
            } else {
                mTvJump.setText(getString(R.string.tv_jump, deadline));
                mHandler.sendEmptyMessageDelayed(MSG_FINISH_SPLASH, 1000);
            }
        }

    }

    private void jumpToMain() {
        mHandler.removeMessages(MSG_FINISH_SPLASH);
        Intent intent = new Intent(SplashActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
