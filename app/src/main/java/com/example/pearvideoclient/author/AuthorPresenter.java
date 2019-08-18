package com.example.pearvideoclient.author;

import android.support.annotation.StringDef;

import com.example.pearvideoclient.Api;
import com.example.pearvideoclient.Constants;
import com.example.pearvideoclient.LocalHandler;
import com.example.pearvideoclient.entity.AuthorHomeBean;
import com.example.pearvideoclient.entity.UserAlbumsBean;
import com.example.pearvideoclient.entity.UserConts;
import com.example.pearvideoclient.entity.UserInfoBean;
import com.example.pearvideoclient.entity.UserPostsBean;
import com.example.pearvideoclient.http.RetrofitManager;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
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
    private String userHomeStart;
    private String userContsStart;
    private String userAlbumsStast;


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

    @Override
    public void loadUserHomeInfo(String authorId) {
        userHomeStart = "0";
        Disposable disposable = loadUserHomeInfo(userHomeStart, userId, Constants.COMMON);
        mCompositeDisposable.add(disposable);
    }

    @Override
    public void refreshUserHomeList() {
        userHomeStart = "0";
        Disposable disposable = loadUserHomeInfo(userHomeStart, userId, Constants.LOAD_REFRESH);
        mCompositeDisposable.add(disposable);
    }

    @Override
    public void loadMoreUserHomeList() {
        userHomeStart = String.valueOf(Integer.valueOf(userHomeStart) + 10);
        Disposable disposable = loadUserHomeInfo(userHomeStart, userId, Constants.LOAD_MORE);
        mCompositeDisposable.add(disposable);
    }

    @Override
    public void loadUserContsInfo(String authorId) {
        userContsStart = "0";
        Disposable disposable = loadUserContsInfo(userContsStart, authorId, Constants.COMMON);
        mCompositeDisposable.add(disposable);
    }

    @Override
    public void refreshUserContsList() {
        userContsStart = "0";
        Disposable disposable = loadUserContsInfo(userContsStart, userId, Constants.LOAD_REFRESH);
        mCompositeDisposable.add(disposable);
    }

    @Override
    public void loadMoreUserContsList() {
        userContsStart = String.valueOf(Integer.valueOf(userContsStart) + 10);
        Disposable disposable = loadUserContsInfo(userContsStart, userId, Constants.LOAD_MORE);
        mCompositeDisposable.add(disposable);
    }

    @Override
    public void loadUserAlbumsInfo(String authorId) {
        userAlbumsStast = "0";
        Disposable disposable = loadUserAlbumsInfo(userAlbumsStast, authorId, Constants.COMMON);
        mCompositeDisposable.add(disposable);
    }

    @Override
    public void refreshUserAlbumsList() {
        userAlbumsStast = "0";
        Disposable disposable = loadUserAlbumsInfo(userAlbumsStast, userId, Constants.LOAD_REFRESH);
        mCompositeDisposable.add(disposable);
    }

    @Override
    public void loadMoreUserAlbumsList() {
        userAlbumsStast = String.valueOf(Integer.valueOf(userAlbumsStast) + 10);
        Disposable disposable = loadUserAlbumsInfo(userAlbumsStast, userId, Constants.LOAD_MORE);
        mCompositeDisposable.add(disposable);
    }

    @Override
    public void loadUserPostsInfo(String authorId) {

    }

    @Override
    public void refreshUserPostsList() {

    }

    @Override
    public void loadMoreUserPostsList() {

    }

    /**
     * 加载动态fragment数据
     *
     * @param start    索引
     * @param authorId id
     * @return
     */
    private Disposable loadUserHomeInfo(String start, String authorId, @Constants.LoadType int loadType) {
        return RetrofitManager.getInstance().createReq(Api.class)
                .getUserHome(start, authorId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(authorHomeBean -> {
                    List<AuthorHomeBean.DataListBean> dataList = authorHomeBean.getDataList();
                    if (loadType == Constants.LOAD_REFRESH || loadType == Constants.COMMON) {
                        mView.setUserHomeData(dataList);
                    } else if (loadType == Constants.LOAD_MORE) {
                        mView.loadMoreUserHomeData(dataList);
                    }
                }, throwable -> {
                    if (loadType == Constants.LOAD_MORE) {
                        mView.loadMoreFinish(HOME, false);
                    } else if (loadType == Constants.LOAD_REFRESH) {
                        mView.loadRefreshFinish(HOME, false);
                    }
                }, () -> {
                    if (loadType == Constants.LOAD_MORE) {
                        mView.loadMoreFinish(HOME, true);
                    } else if (
                            loadType == Constants.LOAD_REFRESH) {
                        mView.loadRefreshFinish(HOME, true);
                    }
                });

    }

    /**
     * 加载视频fragment数据
     *
     * @param start    索引
     * @param authorId id
     * @param loadType 加载类型
     * @return
     */
    private Disposable loadUserContsInfo(String start, String authorId, @Constants.LoadType int loadType) {
        return RetrofitManager.getInstance().createReq(Api.class)
                .getUserConts(start, authorId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(userConts -> {
                    if (loadType == Constants.COMMON) {
                        setHotConts(userConts);
                        setNewConts(userConts);
                    } else if (loadType == Constants.LOAD_REFRESH) {
                        setNewConts(userConts);
                    } else if (loadType == Constants.LOAD_MORE) {
                        loadMoreNewConts(userConts);
                    }
                }, throwable -> {
                    if (loadType == Constants.LOAD_MORE) {
                        mView.loadMoreFinish(CONTS, false);
                    } else if (loadType == Constants.LOAD_REFRESH) {
                        mView.loadRefreshFinish(CONTS, false);
                    }
                }, () -> {
                    if (loadType == Constants.LOAD_MORE) {
                        mView.loadMoreFinish(CONTS, true);
                    } else if (loadType == Constants.LOAD_REFRESH) {
                        mView.loadRefreshFinish(CONTS, true);
                    }
                });
    }

    /**
     * 加载专辑fragment数据
     *
     * @param start    索引
     * @param authorId ID
     * @param loadType 加载类型
     * @return
     */
    private Disposable loadUserAlbumsInfo(String start, String authorId, @Constants.LoadType int loadType) {
        return RetrofitManager.getInstance().createReq(Api.class)
                .getUserAlbums(start, authorId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(userAlbumsBean -> {
                    List<UserAlbumsBean.AlbumListBean> albumList = userAlbumsBean.getAlbumList();

                    if (loadType == Constants.COMMON ||
                            loadType == Constants.LOAD_REFRESH) {
                        mView.setAlbumsList(albumList);
                    } else if (loadType == Constants.LOAD_MORE) {
                        mView.loadMoreUserAlbums(albumList);
                    }
                }, throwable -> {
                    if (loadType == Constants.LOAD_MORE) {
                        mView.loadMoreFinish(ALBUMS, false);
                    } else if (loadType == Constants.LOAD_REFRESH) {
                        mView.loadRefreshFinish(ALBUMS, false);
                    }
                }, () -> {
                    if (loadType == Constants.LOAD_MORE) {
                        mView.loadMoreFinish(ALBUMS, true);
                    } else if (loadType == Constants.LOAD_REFRESH) {
                        mView.loadRefreshFinish(ALBUMS, true);
                    }
                });
    }

    private Disposable loadUserPostsInfo(String start, String userId, String score, @Constants.LoadType int loadType) {
        return RetrofitManager.getInstance().createReq(Api.class)
                .getUserPosts(start, userId, score)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<UserPostsBean>() {
                    @Override
                    public void accept(UserPostsBean userPostsBean) throws Exception {

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
    }

    private void loadMoreNewConts(UserConts userConts) {
        List<UserConts.ContListBean> contList = userConts.getContList();
        mView.loadMoreNewConts(contList);
    }

    private void setNewConts(UserConts userConts) {
        List<UserConts.ContListBean> contList = userConts.getContList();
        mView.setNewConts(contList);
    }

    private void setHotConts(UserConts userConts) {
        List<UserConts.ContListBean> hotList = userConts.getHotList();
        mView.setHotConts(hotList);
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
        mHandler.sendEmptyMessage(MSG_INIT_VIEWPAGER);
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


    @Retention(value = RetentionPolicy.SOURCE)
    @StringDef(value = {HOME, CONTS, ALBUMS, POST})
    @interface PageType {

    }

    static final String HOME = "动态";
    static final String CONTS = "视频";
    static final String ALBUMS = "专辑";
    static final String POST = "评论";

}
