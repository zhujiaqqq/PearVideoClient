package com.example.pearvideoclient.channel;

import com.example.pearvideoclient.Api;
import com.example.pearvideoclient.Constants;
import com.example.pearvideoclient.entity.CategoryContsBean;
import com.example.pearvideoclient.entity.ContEntity;
import com.example.pearvideoclient.http.RetrofitManager;
import com.example.pearvideoclient.utils.CollectionUtil;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    private Map<String, Integer> categoryMap;

    public ChannelPresenter(ChannelContract.View view) {
        this.mView = view;
        this.mView.setPresenter(this);
        this.mCompositeDisposable = new CompositeDisposable();
        categoryMap = new HashMap<>();
    }

    @Override
    public void loadCategory() {

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
    public void loadCategoryConts(int hotPageidx, String categoryId, int start) {
        categoryMap.put(categoryId, 0);
        Disposable disposable = loadCategoryConts(hotPageidx,
                categoryId,
                categoryMap.get(categoryId),
                Constants.COMMON);
        mCompositeDisposable.add(disposable);
    }

    @Override
    public void loadCategoryContsMore(String categoryId) {
        if (categoryMap.containsKey(categoryId)) {
            categoryMap.put(categoryId, categoryMap.get(categoryId) + 10);
        } else {
            categoryMap.put(categoryId, 0);
        }
        Disposable disposable = loadCategoryConts(1,
                categoryId,
                categoryMap.get(categoryId),
                Constants.LOAD_MORE);
        mCompositeDisposable.add(disposable);
    }

    @Override
    public void loadCategoryContsRefresh(String categoryId) {
        categoryMap.put(categoryId, 0);
        Disposable disposable = loadCategoryConts(1,
                categoryId,
                categoryMap.get(categoryId),
                Constants.LOAD_REFRESH);
        mCompositeDisposable.add(disposable);
    }

    @Override
    public void subscribe() {
        loadCategory();

    }

    @Override
    public void unsubscribe() {
        mCompositeDisposable.dispose();
    }

    private Disposable loadCategoryConts(int hotPageidx,
                                         String categoryId,
                                         int start,
                                         @Constants.LoadType int type) {
        mView.showLoading();
        return RetrofitManager.getInstance()
                .createReq(Api.class)
                .getCategoryConts(hotPageidx, categoryId, start)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(this::convertEntity)
                .subscribe(contEntities -> {
                    if (type == Constants.COMMON || type == Constants.LOAD_REFRESH) {
                        mView.showList(contEntities, categoryId);
                    } else if (type == Constants.LOAD_MORE) {
                        mView.loadMoreList(contEntities, categoryId);
                    }
                }, throwable -> {
                    mView.cancelLoading();
                    if (type == Constants.LOAD_MORE) {
                        mView.loadMoreFinish(false, categoryId);
                    } else if (type == Constants.LOAD_REFRESH) {
                        mView.loadRefreshFinish(false, categoryId);
                    }
                }, () -> {
                    mView.cancelLoading();
                    if (type == Constants.LOAD_MORE) {
                        mView.loadMoreFinish(true, categoryId);
                    } else if (type == Constants.LOAD_REFRESH) {
                        mView.loadRefreshFinish(true, categoryId);
                    }
                });
    }

    @NotNull
    private List<ContEntity> convertEntity(CategoryContsBean categoryContsBean) {
        List<CategoryContsBean.ContListBean> newContsList = categoryContsBean.getContList();
        List<CategoryContsBean.ContListBean> hotContsList = categoryContsBean.getHotList();
        List<CategoryContsBean.HotTagListBean> hotTagList = categoryContsBean.getHotTagList();
        List<CategoryContsBean.HotUserBean> hotUserList = categoryContsBean.getHotUserList();
        List<CategoryContsBean.ContListBean> rankContsList = categoryContsBean.getRankList();
        List<ContEntity> contEntities = new ArrayList<>();
        if (!CollectionUtil.isEmpty(hotContsList)) {
            ContEntity entity = new ContEntity();
            entity.setContListBeans(hotContsList);
            entity.setItemType(ContEntity.TYPE_HOT_CONT);
            contEntities.add(entity);
        }
        if (!CollectionUtil.isEmpty(rankContsList)) {
            ContEntity entity = new ContEntity();
            entity.setContListBeans(rankContsList);
            entity.setItemType(ContEntity.TYPE_RANK_CONT);
            contEntities.add(entity);
        }
        if (!CollectionUtil.isEmpty(hotTagList)) {
            ContEntity entity = new ContEntity();
            entity.setHotTagListBeans(hotTagList);
            entity.setItemType(ContEntity.TYPE_HOT_TAG);
            contEntities.add(entity);
        }
        if (!CollectionUtil.isEmpty(newContsList)) {
            ContEntity entity = new ContEntity();
            entity.setContListBeans(newContsList);
            entity.setItemType(ContEntity.TYPE_NEW_CONT);
            contEntities.add(entity);
        }
        if (!CollectionUtil.isEmpty(hotUserList)) {
            ContEntity entity = new ContEntity();
            entity.setHotUserBeans(hotUserList);
            entity.setItemType(ContEntity.TYPE_HOT_USER);
            contEntities.add(entity);
        }
        return contEntities;
    }

}
