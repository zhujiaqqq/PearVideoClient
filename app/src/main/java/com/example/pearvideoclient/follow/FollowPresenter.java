package com.example.pearvideoclient.follow;

import com.example.pearvideoclient.Api;
import com.example.pearvideoclient.Constants;
import com.example.pearvideoclient.entity.MyFollowContBean;
import com.example.pearvideoclient.http.RetrofitManager;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * @Description: java类作用描述
 * @Author: jiazhu
 * @Date: 2019-08-10 20:36
 * @ClassName: FollowPresenter
 */
public class FollowPresenter implements FollowContract.Presenter {
    public static final String MORE_USER = "more_user";
    private FollowContract.View mView;
    private CompositeDisposable mCompositeDisposable;
    /**
     * 我关注的页数索引
     */
    private String start;


    public FollowPresenter(FollowContract.View view) {
        this.mView = view;
        mCompositeDisposable = new CompositeDisposable();
        this.mView.setPresenter(this);
    }

    @Override
    public void subscribe() {
        mCompositeDisposable.clear();
        loadMyFollowList();
    }

    @Override
    public void unsubscribe() {
        mCompositeDisposable.dispose();
    }

    @Override
    public void loadMyFollowList() {
        start = "0";
        Disposable disposable = loadMyFollowList(start, Constants.COMMON);
        mCompositeDisposable.add(disposable);
    }

    @Override
    public void loadMoreMyFollowList() {
        start = String.valueOf(Integer.valueOf(start) + 10);
        Disposable disposable = loadMyFollowList(start, Constants.LOAD_MORE);
        mCompositeDisposable.add(disposable);
    }

    @Override
    public void refreshMyFollowList() {
        start = "0";
        Disposable disposable = loadMyFollowList(start, Constants.LOAD_REFRESH);
        mCompositeDisposable.add(disposable);
    }

    private void loadFollowData(MyFollowContBean myFollowContBean) {
        List<MyFollowContBean.DataListBean> dataList = myFollowContBean.getDataList();
        mView.showFollowData(dataList);
    }

    private void loadFollowUsers(MyFollowContBean myFollowContBean) {

        List<MyFollowContBean.FollowUserListBean> followUserList = myFollowContBean.getFollowUserList();
        MyFollowContBean.FollowUserListBean moreUser = new MyFollowContBean.FollowUserListBean();
        moreUser.setUserId(MORE_USER);

        followUserList.add(moreUser);
        mView.showFollowUser(followUserList);
    }

    private Disposable loadMyFollowList(String start, @Constants.LoadType int loadType) {
        mView.showLoading();
        return RetrofitManager.getInstance().createReq(Api.class).myFollowContList(start)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        bean -> {
                            if (loadType == Constants.LOAD_REFRESH
                                    || loadType == Constants.COMMON) {
                                loadFollowUsers(bean);
                                loadFollowData(bean);
                            } else if (loadType == Constants.LOAD_MORE) {
                                mView.loadMoreFollowData(bean);
                            }
                        },
                        throwable -> {
                            mView.cancelLoading();
                            if (loadType == Constants.LOAD_MORE) {
                                mView.loadMoreFinish(false);
                            } else if (loadType == Constants.LOAD_REFRESH) {
                                mView.loadRefreshFinish(false);
                            }
                        },
                        () -> {
                            mView.cancelLoading();
                            if (loadType == Constants.LOAD_MORE) {
                                mView.loadMoreFinish(true);
                            } else if (
                                    loadType == Constants.LOAD_REFRESH) {
                                mView.loadRefreshFinish(true);
                            }
                        });
    }
}
