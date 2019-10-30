package com.example.pearvideoclient.home

import com.example.apublic.BasePresenter
import com.example.apublic.BaseView
import com.example.pearvideoclient.entity.CityListBean
import com.example.pearvideoclient.entity.LocalContEntity
import com.example.pearvideoclient.entity.NewsEntity
import com.example.pearvideoclient.entity.RecommendEntity

interface HomeContract {
    interface View : BaseView<Presenter> {
        /**
         * 显示万象页面数据
         *
         * @param data 列表数据
         */
        fun showVientianeList(data: List<NewsEntity>)

        /**
         * 显示更多万象页面数据
         *
         * @param data 列表数据
         */
        fun loadMoreVientianeList(data: List<NewsEntity>)

        /**
         * 显示推荐页面数据
         *
         * @param data 列表数据
         */
        fun showRecommendList(data: List<RecommendEntity>)

        /**
         * 显示更多推荐页面数据
         *
         * @param data 列表数据
         */
        fun loadMoreRecommendList(data: List<RecommendEntity>)

        /**
         * 显示城市页面数据
         *
         * @param data 列表数据
         */
        fun showCityContsList(data: List<LocalContEntity>)

        /**
         * 显示更多城市页面数据
         *
         * @param data 列表数据
         */
        fun loadMoreCityContsList(data: List<LocalContEntity>)

        /**
         * 加载更多完成
         *
         * @param isSuccess true 成功
         * @param pageType  页面
         */
        fun loadMoreFinish(isSuccess: Boolean, @HomePresenter.PageType pageType: String)

        /**
         * 刷新完成
         *
         * @param isSuccess true 成功
         * @param pageType  页面
         */
        fun refreshFinish(isSuccess: Boolean, @HomePresenter.PageType pageType: String)

        /**
         * 跳转城市列表页面
         *
         * @param cityListBean 城市列表bean
         */
        fun toCityList(cityListBean: CityListBean)
    }

    interface Presenter : BasePresenter {
        /**
         * 加载万象页面数据
         */
        fun loadVientianeList()

        /**
         * 刷新万象页面数据
         */
        fun refreshVientianeList()

        /**
         * 加载更多万象页面数据
         */
        fun loadMoreVientianeList()

        /**
         * 加载推荐页面数据
         */
        fun loadRecommendList()

        /**
         * 刷新推荐页面数据
         */
        fun refreshRecommendList()

        /**
         * 加载更多推荐页面数据
         */
        fun loadMoreRecommendList()

        /**
         * 刷新城市页面数据
         */
        fun refreshCityContsList(currentCity: CityListBean.ChannelBean)

        /**
         * 加载更多城市页面数据
         */
        fun loadMoreCityContsList()

        /**
         * 加载城市页面数据
         */
        fun loadCityContsList(currentCity: CityListBean.ChannelBean)

        /**
         * 获取城市数据
         */
        fun loadLocalChannel()
    }
}
