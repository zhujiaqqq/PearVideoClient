package com.example.pearvideoclient.home;

import com.example.apublic.BasePresenter;
import com.example.apublic.BaseView;
import com.example.pearvideoclient.entity.CityListBean;
import com.example.pearvideoclient.entity.LocalContEntity;
import com.example.pearvideoclient.entity.NewsEntity;
import com.example.pearvideoclient.entity.RecommendEntity;

import java.util.List;

public interface HomeContract {
    interface View extends BaseView<Presenter> {
        /**
         * 显示万象页面数据
         *
         * @param data 列表数据
         */
        void showVientianeList(List<NewsEntity> data);

        /**
         * 显示更多万象页面数据
         *
         * @param data 列表数据
         */
        void loadMoreVientianeList(List<NewsEntity> data);

        /**
         * 显示推荐页面数据
         *
         * @param data 列表数据
         */
        void showRecommendList(List<RecommendEntity> data);

        /**
         * 显示更多推荐页面数据
         *
         * @param data 列表数据
         */
        void loadMoreRecommendList(List<RecommendEntity> data);

        /**
         * 显示城市页面数据
         *
         * @param data 列表数据
         */
        void showCityContsList(List<LocalContEntity> data);

        /**
         * 显示更多城市页面数据
         *
         * @param data 列表数据
         */
        void loadMoreCityContsList(List<LocalContEntity> data);

        /**
         * 加载更多完成
         *
         * @param isSuccess true 成功
         * @param pageType  页面
         */
        void loadMoreFinish(boolean isSuccess, @HomePresenter.PageType String pageType);

        /**
         * 刷新完成
         *
         * @param isSuccess true 成功
         * @param pageType  页面
         */
        void refreshFinish(boolean isSuccess, @HomePresenter.PageType String pageType);

        /**
         * 跳转城市列表页面
         *
         * @param cityListBean 城市列表bean
         */
        void toCityList(CityListBean cityListBean);
    }

    interface Presenter extends BasePresenter {
        /**
         * 加载万象页面数据
         */
        void loadVientianeList();

        /**
         * 刷新万象页面数据
         */
        void refreshVientianeList();

        /**
         * 加载更多万象页面数据
         */
        void loadMoreVientianeList();

        /**
         * 加载推荐页面数据
         */
        void loadRecommendList();

        /**
         * 刷新推荐页面数据
         */
        void refreshRecommendList();

        /**
         * 加载更多推荐页面数据
         */
        void loadMoreRecommendList();

        /**
         * 刷新城市页面数据
         */
        void refreshCityContsList(CityListBean.ChannelBean currentCity);

        /**
         * 加载更多城市页面数据
         */
        void loadMoreCityContsList();

        /**
         * 加载城市页面数据
         */
        void loadCityContsList(CityListBean.ChannelBean currentCity);

        /**
         * 获取城市数据
         */
        void loadLocalChannel();
    }
}
