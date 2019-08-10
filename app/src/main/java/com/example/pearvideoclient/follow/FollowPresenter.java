package com.example.pearvideoclient.follow;

import com.example.pearvideoclient.Api;
import com.example.pearvideoclient.entity.bean.MyFollowContBean;
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


    public FollowPresenter(FollowContract.View view) {
        this.mView = view;
        mCompositeDisposable = new CompositeDisposable();
        this.mView.setPresenter(this);
    }

    public FollowPresenter() {
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
        mView.showLoading();
        Disposable disposable = RetrofitManager.getInstance().createReq(Api.class).myFollowContList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(myFollowContBean -> {
                            loadFollowUsers(myFollowContBean);
                            loadFollowData(myFollowContBean);
                            List<MyFollowContBean.HotUserListBean> hotUserList = myFollowContBean.getHotUserList();
                        }, throwable -> mView.cancelLoading(),
                        () -> mView.cancelLoading());
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
}
