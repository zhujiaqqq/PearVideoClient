package com.example.videolib.viewmodel;

import android.app.Application;
import android.view.SurfaceHolder;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.apublic.LocalHandler;
import com.example.videolib.model.record.IRecorderController;
import com.example.videolib.model.record.OnRecordListener;
import com.example.videolib.model.record.RecorderControllerImpl;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: java类作用描述
 * @Author: jiazhu
 * @Date: 2019-10-08 19:30
 * @ClassName: RecordVideoViewModel
 */
public class RecordVideoViewModel extends AndroidViewModel {

    private MutableLiveData<Boolean> isRecord;
    private MutableLiveData<Boolean> isCompleted;

    private List<String> videoPaths;

    private boolean isFirst = true;

    private IRecorderController mRecorderController;

    private LocalHandler mHandler;

    public boolean isFirst() {
        return isFirst;
    }

    public void setFirst(boolean first) {
        isFirst = first;
    }

    public MutableLiveData<Boolean> getIsRecord() {
        if (isRecord == null) {
            isRecord = new MutableLiveData<>();
            isRecord.setValue(Boolean.FALSE);
        }
        return isRecord;
    }

    public MutableLiveData<Boolean> getIsCompleted() {
        if (isCompleted == null) {
            isCompleted = new MutableLiveData<>();
            isCompleted.setValue(Boolean.FALSE);
        }
        return isCompleted;
    }


    public RecordVideoViewModel(@NonNull Application application) {
        super(application);
        videoPaths = new ArrayList<>();
    }

    public void initRecorderController(@NonNull SurfaceHolder holder,
                                       @NonNull LocalHandler handler,
                                       @Nullable OnRecordListener listener) {
        mHandler = handler;
        mRecorderController = new RecorderControllerImpl(
                holder, mHandler, listener
        );
        holder.addCallback(mRecorderController);
        holder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
    }

    public void toggleCamera() {
        mRecorderController.toggleCamera();
    }

    public void freeCameraResource() {
        mRecorderController.freeCameraResource();
    }

    public void startRecorder() {
        mRecorderController.startRecorder();
    }

    public void stopRecorder() {
        String path = mRecorderController.stopRecorder();
        videoPaths.add(path);
    }

    public void cancelRecorder() {
        if (videoPaths.size() > 0) {
            videoPaths.remove(videoPaths.size() - 1);
        }

        if (videoPaths.size() == 0) {
            isCompleted.setValue(false);
        }
    }
}
