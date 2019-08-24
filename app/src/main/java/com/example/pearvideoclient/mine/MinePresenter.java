package com.example.pearvideoclient.mine;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.example.pearvideoclient.Api;
import com.example.pearvideoclient.entity.LoginBean;
import com.example.pearvideoclient.http.RetrofitManager;
import com.example.pearvideoclient.utils.Md5Util;
import com.example.pearvideoclient.utils.SharedPreferencesHelper;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import static com.example.pearvideoclient.Constants.SUCCESS;

/**
 * @Description: java类作用描述
 * @Author: jiazhu
 * @Date: 2019-07-24 17:46
 * @ClassName: MinePresenter
 */
public class MinePresenter implements MineContract.Presenter {
    private static final String TAG = "MinePresenter";
    private static final String NOT_LOGIN = "未登录";

    private MineContract.View mView;
    private CompositeDisposable mCompositeDisposable;
    private SharedPreferencesHelper helper;

    private Integer userId;

    public MinePresenter(MineContract.View view, Context context) {
        this.mView = view;
        this.mView.setPresenter(this);
        this.mCompositeDisposable = new CompositeDisposable();
        this.helper = new SharedPreferencesHelper(context, "user_info");
        userId = (Integer) helper.getSharedPreference("userId", new Integer(0));
    }

    @Override
    public void loadReadHisList() {
        mView.showLoading();
        Disposable d = RetrofitManager.getInstance().createReq(Api.class)
                .getMyReadHisList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        myReadHisListBean -> {
                            if (NOT_LOGIN.equals(myReadHisListBean.getResultMsg())) {
                                mView.showLoginLayout(true);
                            } else {
                                mView.showLoginLayout(false);
                                getUserInfo(userId.toString());
                                mView.showPopWindow(true);
                            }
                        },
                        throwable -> mView.cancelLoading(),
                        () -> mView.cancelLoading());
        mCompositeDisposable.add(d);
    }

    @Override
    public void login(final String loginName, final String pwd) {
        mView.showLoading();
        if (TextUtils.isEmpty(loginName) || TextUtils.isEmpty(pwd)) {
            mView.cancelLoading();
            return;
        }

        String pwdMd5 = Md5Util.EncodeByMD5(pwd);
        Disposable d = RetrofitManager.getInstance().createReq(Api.class).login(loginName, pwdMd5)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(loginBean -> {
                            if (SUCCESS.equals(loginBean.getResultMsg())) {
                                mView.showLoginLayout(false);
                                LoginBean.UserInfoBean userInfo = loginBean.getUserInfo();
                                mView.showUserInfo(userInfo);
                            }
                        },
                        throwable -> mView.cancelLoading(),
                        () -> mView.cancelLoading());

        mCompositeDisposable.add(d);
    }


    @Override
    public void getUserInfo(String userId) {
        if (userId == null) {
            return;
        }
        mView.showLoading();
        Disposable disposable = RetrofitManager.getInstance().createReq(Api.class).getUserInfo(userId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        userInfoBean -> mView.showUserInfo(userInfoBean),
                        throwable -> mView.cancelLoading(),
                        () -> mView.cancelLoading());

        mCompositeDisposable.add(disposable);

    }

    @Override
    public void loadFollowList() {

    }

    @Override
    public void getMsgMask(String lastSysTime) {
        mView.showLoading();
        Disposable disposable = RetrofitManager.getInstance().createReq(Api.class)
                .getMsgMark(lastSysTime)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(msgMarkBean -> {
                            Log.d(TAG, "accept: " + msgMarkBean.toString());
                            if (msgMarkBean.getData().getUserId() != null) {
                                userId = msgMarkBean.getData().getUserId();
                                helper.put("userId", userId);
                            }
                        },
                        throwable -> mView.cancelLoading(),
                        () -> mView.cancelLoading());
        mCompositeDisposable.add(disposable);
    }

    @Override
    public void subscribe() {
        loadReadHisList();
        getMsgMask("0");

    }

    @Override
    public void unsubscribe() {
        mCompositeDisposable.dispose();
    }
}

