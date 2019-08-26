package com.example.pearvideoclient.screen;

import com.example.pearvideoclient.Api;
import com.example.pearvideoclient.entity.PaikeFineVideoBean;
import com.example.pearvideoclient.http.RetrofitManager;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * @Description: java类作用描述
 * @Author: jiazhu
 * @Date: 2019-08-26 15:06
 * @ClassName: ScreenPresenter
 */
public class ScreenPresenter implements ScreenContract.Presenter {
    private ScreenContract.View mView;
    private CompositeDisposable mCompositeDisposable;

    public ScreenPresenter(ScreenContract.View view) {
        mView = view;
        mView.setPresenter(this);
        mCompositeDisposable = new CompositeDisposable();
    }

    @Override
    public void loadPaikeFineVideos() {
        Disposable disposable = RetrofitManager.getInstance().createReq(Api.class)
                .getPaikeFineVideos()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(paikeFineVideoBean -> {
                    List<PaikeFineVideoBean.VideoBean> videoList = paikeFineVideoBean.getVideoList();
                    PaikeFineVideoBean.ConfigInfoBean configInfo = paikeFineVideoBean.getConfigInfo();
                    mView.showPaikeVideos(videoList);
                    mView.showPaikeInfo(configInfo);
                });
        mCompositeDisposable.add(disposable);
    }

    @Override
    public void subscribe() {
        mCompositeDisposable.clear();
        loadPaikeFineVideos();
    }

    @Override
    public void unsubscribe() {
        mCompositeDisposable.dispose();
    }
}
