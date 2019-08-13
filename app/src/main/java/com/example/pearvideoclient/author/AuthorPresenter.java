package com.example.pearvideoclient.author;

import com.example.pearvideoclient.Api;
import com.example.pearvideoclient.LocalHandler;
import com.example.pearvideoclient.entity.bean.UserInfoBean;
import com.example.pearvideoclient.http.RetrofitManager;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

import static com.example.pearvideoclient.author.AuthorActivity.MSG_INIT_VIEWPAGER;

/**
 * @Description: java类作用描述
 * @Author: jiazhu
 * @Date: 2019-08-13 14:10
 * @ClassName: AuthorPresenter
 */
public class AuthorPresenter implements AuthorContract.Presenter {
    public static final String HAS_ALBUM = "1";
    private AuthorContract.View mView;
    private LocalHandler mHandler;

    private CompositeDisposable mCompositeDisposable;

    private String userId;


    public AuthorPresenter(AuthorContract.View view, LocalHandler handler, String userId) {
        this.userId = userId;
        this.mView = view;
        this.mHandler = handler;
        mCompositeDisposable = new CompositeDisposable();
        mView.setPresenter(this);
    }

    @Override

    public void loadAuthorInfo(String authorId) {

        Disposable disposable = RetrofitManager.getInstance().createReq(Api.class)
                .getUserInfo(authorId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<UserInfoBean>() {
                    @Override
                    public void accept(UserInfoBean userInfoBean) throws Exception {
                        setUserInfo(userInfoBean);
                        setFragments(userInfoBean);

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                }, new Action() {
                    @Override
                    public void run() throws Exception {

                    }
                });
        mCompositeDisposable.add(disposable);
    }

    private void setUserInfo(UserInfoBean userInfoBean) {
        UserInfoBean.InfoBean userInfo = userInfoBean.getUserInfo();
        mView.setAuthorTitle(userInfo);
    }

    private void setFragments(UserInfoBean userInfoBean) {
        List<String> titles = new ArrayList<>();
        titles.add("动态");
        titles.add("视频");
        if (HAS_ALBUM.equals(userInfoBean.getUserInfo().getHasAlbum())) {
            titles.add("专辑");
        }
        titles.add("评论");

        mView.setFragments(titles);
    }

    @Override
    public void subscribe() {
        mCompositeDisposable.clear();
        loadAuthorInfo(userId);
    }

    @Override
    public void unsubscribe() {
        mCompositeDisposable.clear();
    }


}