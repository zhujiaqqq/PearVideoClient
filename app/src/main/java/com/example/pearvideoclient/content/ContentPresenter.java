package com.example.pearvideoclient.content;

import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.example.pearvideoclient.Api;
import com.example.pearvideoclient.MyApplication;
import com.example.pearvideoclient.R;
import com.example.pearvideoclient.http.RetrofitManager;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * @Description: java类作用描述
 * @Author: jiazhu
 * @Date: 2019-07-28 17:55
 * @ClassName: ContentPresenter
 */
public class ContentPresenter implements ContentContract.Presenter {
    public static final int MSG_HIDDEN_CONTROLLER = 0x99;
    private ContentContract.View mView;
    private Handler mHandler;
    private CompositeDisposable mCompositeDisposable;
    private boolean isShowDetail;
    private boolean isShowController;
    private long time;

    public ContentPresenter(ContentContract.View mView, Handler handler) {
        this.mView = mView;
        this.mHandler = handler;
        mView.setPresenter(this);

        mCompositeDisposable = new CompositeDisposable();
    }

    @Override
    public void loadContent(String id) {
        mView.showLoading();
        Disposable disposable = RetrofitManager.getInstance().createReq(Api.class).getContent(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        contentBean -> {
                            mView.showVideo(contentBean.getContent().getVideos());
                            mView.showDetail(false);
                            mView.showVideoInfo(contentBean.getContent());
                            mView.showRelatedVideos(contentBean.getRelateConts());
                            mView.showTags(contentBean.getContent().getTags());
                        },
                        throwable -> mView.cancelLoading(),
                        () -> mView.cancelLoading());
        mCompositeDisposable.add(disposable);
    }

    @Override
    public void star() {

    }

    @Override
    public void collect() {

    }

    @Override
    public void download() {

    }

    @Override
    public void attention() {

    }

    @Override
    public void stop() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void play() {

    }

    @Override
    public void fullScreen() {

    }

    @Override
    public void toggleDetail() {
        isShowDetail = !isShowDetail;
        mView.showDetail(isShowDetail);
    }

    @Override
    public void showPlayController() {
        if (!isShowController) {
            isShowController = true;
            mView.showController(true);
            Animation animation = AnimationUtils.loadAnimation(MyApplication.getInstance(), R.anim.show_bottom);
            mView.viewDoAnimation(animation);
        }
        mHandler.removeMessages(MSG_HIDDEN_CONTROLLER);
        mHandler.sendEmptyMessageDelayed(MSG_HIDDEN_CONTROLLER, 3000);
    }

    @Override
    public void hidePlayController() {
        isShowController = false;
        mView.showController(false);
        Animation animation = AnimationUtils.loadAnimation(MyApplication.getInstance(), R.anim.move_bottom);
        mView.viewDoAnimation(animation);
    }

    @Override
    public void subscribe() {
        hidePlayController();
    }

    @Override
    public void unsubscribe() {

    }
}
