package com.example.pearvideoclient.channel;

import com.example.pearvideoclient.Api;
import com.example.pearvideoclient.Constants;
import com.example.pearvideoclient.http.RetrofitManager;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * @Description: java类作用描述
 * @Author: jiazhu
 * @Date: 2019-07-18 23:24
 * @ClassName: ChannelPresenter
 */
public class ChannelPresenter implements ChannelContract.Presenter {

    private ChannelContract.View mView;
    private CompositeDisposable mCompositeDisposable;
    private String currentCategoryId;
    private String currentIndex;

    public ChannelPresenter(ChannelContract.View view) {
        this.mView = view;
        this.mView.setPresenter(this);
        this.mCompositeDisposable = new CompositeDisposable();
    }

    @Override
    public void loadCategory() {
        mCompositeDisposable.clear();
        mView.showLoading();
        Disposable subscribe = RetrofitManager.getInstance().createReq(Api.class).getCategory()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(categoryBean -> mView.showCategoryList(categoryBean.getCategoryList()),
                        throwable -> mView.cancelLoading(),
                        () -> mView.cancelLoading());
        mCompositeDisposable.add(subscribe);
    }

    @Override
    public void loadCategoryConts(String hotPageidx, String categoryId, String start) {
        currentCategoryId = categoryId;
        currentIndex = start;
        Disposable disposable = loadCategoryConts(hotPageidx,
                currentCategoryId,
                currentIndex,
                Constants.COMMON);
        mCompositeDisposable.add(disposable);
    }

    @Override
    public void loadCategoryContsMore() {
        currentIndex = String.valueOf(Integer.valueOf(currentIndex) + 10);
        Disposable disposable = loadCategoryConts("",
                currentCategoryId,
                currentIndex,
                Constants.LOAD_MORE);
        mCompositeDisposable.add(disposable);
    }

    @Override
    public void loadCategoryContsRefresh() {
        currentIndex = "0";
        Disposable disposable = loadCategoryConts("1",
                currentCategoryId,
                currentIndex,
                Constants.LOAD_REFRESH);
        mCompositeDisposable.add(disposable);
    }

    @Override
    public void subscribe() {
        loadCategory();

    }

    @Override
    public void unsubscribe() {
        mCompositeDisposable.clear();
    }

    private Disposable loadCategoryConts(String hotPageidx,
                                         String categoryId,
                                         String start,
                                         @Constants.LoadType int type) {
        mView.showLoading();
        return RetrofitManager.getInstance()
                .createReq(Api.class)
                .getCategoryConts(hotPageidx, categoryId, start)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(categoryContsBean -> {
                    if (type == Constants.COMMON || type == Constants.LOAD_REFRESH) {
//                            如果是普通情况或者刷新的情况
                        mView.showList(categoryContsBean.getContList());
                    } else if (type == Constants.LOAD_MORE) {
//                            如果是加载更多的情况
                        mView.loadMoreList(categoryContsBean.getContList());
                    }
                }, throwable -> {
                    mView.cancelLoading();
                    if (type == Constants.LOAD_MORE) {
                        mView.loadMoreFinish(false);
                    } else if (type == Constants.LOAD_REFRESH) {
                        mView.loadRefreshFinish(false);
                    }
                }, () -> {
                    mView.cancelLoading();
                    if (type == Constants.LOAD_MORE) {
                        mView.loadMoreFinish(true);
                    } else if (type == Constants.LOAD_REFRESH) {
                        mView.loadRefreshFinish(true);
                    }
                });

    }

}
