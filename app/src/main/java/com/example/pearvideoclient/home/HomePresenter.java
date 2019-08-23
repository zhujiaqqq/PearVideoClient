package com.example.pearvideoclient.home;

import android.support.annotation.StringDef;

import com.example.pearvideoclient.Api;
import com.example.pearvideoclient.Constants;
import com.example.pearvideoclient.entity.NewsBean;
import com.example.pearvideoclient.entity.NewsEntity;
import com.example.pearvideoclient.entity.RecommendBean;
import com.example.pearvideoclient.entity.RecommendEntity;
import com.example.pearvideoclient.http.RetrofitManager;

import org.jetbrains.annotations.NotNull;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import static com.example.pearvideoclient.entity.NewsEntity.TYPE_BIG;
import static com.example.pearvideoclient.entity.NewsEntity.TYPE_SMALL;

/**
 * @Description: java类作用描述
 * @Author: jiazhu
 * @Date: 2019-08-21 14:42
 * @ClassName: HomePresenter
 */
public class HomePresenter implements HomeContract.Presenter {
    private HomeContract.View mView;
    private CompositeDisposable mCompositeDisposable;
    private String mFilterIds;

    private int newsStart = 0;
    private int recommendStart = 0;


    HomePresenter(HomeContract.View view) {
        mView = view;
        mView.setPresenter(this);
        mCompositeDisposable = new CompositeDisposable();
    }

    @Override
    public void subscribe() {
        mCompositeDisposable.clear();
    }

    @Override
    public void unsubscribe() {
        mCompositeDisposable.dispose();
    }

    @Override
    public void loadVientianeList() {
        Disposable disposable = loadNewsList(Constants.COMMON);
        mCompositeDisposable.add(disposable);
    }

    @NotNull
    private Disposable loadNewsList(@Constants.LoadType int loadType) {
        return RetrofitManager.getInstance().createReq(Api.class)
                .getNewsList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(this::convertNewsData)
                .subscribe(newsEntities -> mView.showVientianeList(newsEntities), throwable -> {
                    if (Constants.LOAD_REFRESH == loadType) {
                        mView.refreshFinish(false, NEWS);
                    }
                }, () -> {
                    if (Constants.LOAD_REFRESH == loadType) {
                        mView.refreshFinish(false, NEWS);
                    }
                });
    }

    private List<NewsEntity> convertNewsData(NewsBean newsBean) {
        String nextUrl = newsBean.getNextUrl();
        mFilterIds = nextUrl.substring(nextUrl.indexOf("filterIds=") + 9, nextUrl.indexOf('&'));
        List<NewsBean.DataListBean> dataList = newsBean.getDataList();
        List<NewsEntity> newsEntities = new ArrayList<>();
        for (int i = 0; i < dataList.size(); i++) {
            NewsEntity entity = new NewsEntity();
            entity.setDataEntity(dataList.get(i));
            if ("1".equals(dataList.get(i).getNewsInfo().getCardType())) {
                entity.setItemType(TYPE_BIG);
            } else {
                entity.setItemType(TYPE_SMALL);
            }

            newsEntities.add(entity);
        }
        return newsEntities;
    }

    @Override
    public void refreshVientianeList() {
        newsStart = 0;
        Disposable disposable = loadNewsList(Constants.LOAD_REFRESH);
        mCompositeDisposable.add(disposable);
    }

    @Override
    public void loadMoreVientianeList() {
        newsStart += 10;
        Disposable disposable = loadVientianeList(mFilterIds, newsStart, newsStart - 4);
        mCompositeDisposable.add(disposable);
    }

    @Override
    public void loadRecommendList() {
        Disposable disposable = loadRecommendList(Constants.COMMON);
        mCompositeDisposable.add(disposable);
    }

    @Override
    public void refreshRecommendList() {
        recommendStart = 0;
        Disposable disposable = loadRecommendList(Constants.LOAD_REFRESH);
        mCompositeDisposable.add(disposable);
    }

    @Override
    public void loadMoreRecommendList() {
        recommendStart += 10;
        Disposable disposable = RetrofitManager.getInstance().createReq(Api.class)
                .getHome(1, "110100", recommendStart)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(this::convertRecommendData)
                .subscribe(recommendEntities -> mView.loadMoreRecommendList(recommendEntities)
                        , throwable -> mView.loadMoreFinish(false, RECOMMEND)
                        , () -> mView.loadMoreFinish(true, RECOMMEND));
        mCompositeDisposable.add(disposable);
    }

    private Disposable loadRecommendList(@Constants.LoadType int loadType) {
        return RetrofitManager.getInstance().createReq(Api.class)
                .getHome(1, "110100")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(this::convertRecommendData)
                .subscribe(recommendEntities -> mView.showRecommendList(recommendEntities)
                        , throwable -> {
                            if (Constants.LOAD_REFRESH == loadType) {
                                mView.refreshFinish(false, RECOMMEND);
                            }
                        }
                        , () -> mView.refreshFinish(true, RECOMMEND));
    }

    @NotNull
    private List<RecommendEntity> convertRecommendData(RecommendBean recommendBean) {
        List<RecommendEntity> recommendEntities = new ArrayList<>();
        List<RecommendBean.DataListBean> dataList = recommendBean.getDataList();
        for (RecommendBean.DataListBean dataListBean : dataList) {
            RecommendEntity entity = new RecommendEntity();
            entity.setDataBean(dataListBean);
            String nodeType = dataListBean.getNodeType();
            if ("11".equals(nodeType)) {
                continue;
            }
            entity.setItemType(Integer.valueOf(nodeType));
            recommendEntities.add(entity);
        }
        return recommendEntities;
    }

    private Disposable loadVientianeList(String filterIds, int start, int pstart) {
        return RetrofitManager.getInstance().createReq(Api.class)
                .getNewsList(filterIds, start, pstart)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(this::convertNewsData)
                .subscribe(newsEntities -> mView.loadMoreVientianeList(newsEntities), throwable -> mView.loadMoreFinish(false, NEWS), () -> mView.loadMoreFinish(true, NEWS));
    }

    @Retention(value = RetentionPolicy.SOURCE)
    @StringDef(value = {NEWS, RECOMMEND, CITY})
    @interface PageType {

    }

    static final String NEWS = "万象";
    static final String RECOMMEND = "推荐";
    static final String CITY = "城市";


}
