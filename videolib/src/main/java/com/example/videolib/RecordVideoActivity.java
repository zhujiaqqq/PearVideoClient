package com.example.videolib;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.os.Message;
import android.view.SurfaceHolder;
import android.view.View;
import android.widget.Toast;

import com.example.annotations.BindPath;
import com.example.apublic.LocalHandler;
import com.example.videolib.databinding.ActivityRecordVideoBinding;
import com.example.videolib.model.record.OnRecordListener;
import com.example.videolib.viewmodel.RecordVideoViewModel;

import static com.example.videolib.model.record.RecorderControllerImpl.MSG_FINISH_RECORD;
import static com.example.videolib.model.record.RecorderControllerImpl.MSG_REFRESH_TIME;

/**
 * 录制视频界面
 *
 * @author jiazhu
 */
@BindPath("videolib/recordVideo")
public class RecordVideoActivity extends AppCompatActivity implements LocalHandler.IHandler {

    private ActivityRecordVideoBinding mBinding;
    private RecordVideoViewModel mRecordViewModel;

    private LocalHandler mHandler = new LocalHandler(this);
    private OnRecordListener mListener = new OnRecordListener() {
        @Override
        public void onRecordFinish() {
            Toast.makeText(RecordVideoActivity.this, "finish", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onRecordProgress(int progress) {
            Message message = mHandler.obtainMessage();
            message.what = MSG_REFRESH_TIME;
            message.arg1 = progress;
            mHandler.sendMessage(message);
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record_video);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_record_video);
        mRecordViewModel = ViewModelProviders.of(this).get(RecordVideoViewModel.class);

        initData();
    }

    private void initData() {

        SurfaceHolder holder = mBinding.surfaceView.getHolder();
        mRecordViewModel.initRecorderController(holder, mHandler, mListener);

        mRecordViewModel.getIsRecord().observe(this, aBoolean -> {

            mBinding.ivRecord.setSelected(aBoolean);
            mBinding.tvRecordTime.setVisibility(aBoolean ? View.VISIBLE : View.GONE);
            mBinding.ivClose.setVisibility(aBoolean ? View.GONE : View.VISIBLE);
            mBinding.ivCameraToggle.setVisibility(aBoolean ? View.GONE : View.VISIBLE);
            if (aBoolean) {
                mRecordViewModel.startRecorder();
                mRecordViewModel.getIsCompleted().setValue(Boolean.FALSE);
            } else {
                if (mRecordViewModel.isFirst()) {
                    mRecordViewModel.setFirst(false);
                } else {
                    mRecordViewModel.stopRecorder();
                    mRecordViewModel.getIsCompleted().setValue(Boolean.TRUE);
                }
            }
        });

        mRecordViewModel.getIsCompleted().observe(this, aBoolean -> {
            mBinding.ivCancel.setVisibility(aBoolean ? View.VISIBLE : View.GONE);
            mBinding.ivConfirm.setVisibility(aBoolean ? View.VISIBLE : View.GONE);
        });


        mBinding.ivRecord.setOnClickListener(v -> {
            Boolean value = mRecordViewModel.getIsRecord().getValue();
            mRecordViewModel.getIsRecord().setValue(!value);
        });

        mBinding.ivClose.setOnClickListener(v -> {
            mRecordViewModel.freeCameraResource();
            finish();
        });

        mBinding.ivCameraToggle.setOnClickListener(v -> mRecordViewModel.toggleCamera());

        mBinding.ivCancel.setOnClickListener(v -> mRecordViewModel.cancelRecorder());
    }

    @Override
    public void handlerMessage(Message msg) {
        if (msg.what == MSG_REFRESH_TIME) {
            mBinding.tvRecordTime.setText((countRecordTime(msg.arg1)));
            mBinding.pbProgress.setProgress(msg.arg1 / 6);
        } else if (msg.what == MSG_FINISH_RECORD) {
            mRecordViewModel.getIsRecord().setValue(false);
        }
    }

    private String countRecordTime(int time) {
        int second = time / 10;
        int lSecond = time % 10;
        return getString(R.string.tv_show_time, second, lSecond);
    }
}
