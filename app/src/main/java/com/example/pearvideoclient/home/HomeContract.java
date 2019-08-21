package com.example.pearvideoclient.home;

import com.example.pearvideoclient.BasePresenter;
import com.example.pearvideoclient.BaseView;
import com.example.pearvideoclient.entity.NewsEntity;

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
    }
}
