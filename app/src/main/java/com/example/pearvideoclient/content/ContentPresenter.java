package com.example.pearvideoclient.content;

import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.example.pearvideoclient.Api;
import com.example.pearvideoclient.MyApplication;
import com.example.pearvideoclient.R;
import com.example.pearvideoclient.entity.bean.ContPraise;
import com.example.pearvideoclient.entity.bean.UserFollowBean;
import com.example.pearvideoclient.http.RetrofitManager;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

import static com.example.pearvideoclient.Constants.SUCCESS;

/**
 * @Description: java类作用描述
 * @Author: jiazhu
 * @Date: 2019-07-28 17:55
 * @ClassName: ContentPresenter
 */
public class ContentPresenter implements ContentContract.Presenter {
    public static final int MSG_HIDDEN_CONTROLLER = 0x99;
    public static final String FOLLOW_USER = "1";
    public static final String UN_FOLLOW_USER = "2";
    private ContentContract.View mView;
    private Handler mHandler;
    private CompositeDisposable mCompositeDisposable;
    private boolean isShowDetail;
    private boolean isShowController;
    private long time;

    private boolean isStar;

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
                            mView.showHotVideos(contentBean.getHotConts());
                            mView.showTags(contentBean.getContent().getTags());
                        },
                        throwable -> mView.cancelLoading(),
                        () -> mView.cancelLoading());
        mCompositeDisposable.add(disposable);
    }

    @Override
    public void star(String contId) {
        if (isStar) {
            return;
        }
        isStar = true;
        Disposable disposable = RetrofitManager.getInstance().createReq(Api.class).toStar(contId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ContPraise>() {
                    @Override
                    public void accept(ContPraise contPraise) throws Exception {
                        if (SUCCESS.equals(contPraise.getResultMsg())) {
                            mView.showStar(true);
                        }
                    }
                });
        mCompositeDisposable.add(disposable);

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
    public void toOptUserFollow(String opt, String userId) {
        Disposable disposable = RetrofitManager.getInstance().createReq(Api.class).optUserFollow(opt, userId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<UserFollowBean>() {
                    @Override
                    public void accept(UserFollowBean userFollowBean) throws Exception {
                        if (SUCCESS.equals(userFollowBean.getResultMsg())) {
                            mView.toggleAttention();
                        }
                    }
                });
        mCompositeDisposable.add(disposable);
    }

    @Override
    public void subscribe() {
        hidePlayController();
    }

    @Override
    public void unsubscribe() {

    }
}
