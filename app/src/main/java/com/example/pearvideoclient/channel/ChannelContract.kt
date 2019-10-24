package com.example.pearvideoclient.channel

import com.example.apublic.BasePresenter
import com.example.apublic.BaseView
import com.example.pearvideoclient.entity.CategoryBean
import com.example.pearvideoclient.entity.ContEntity
import java.util.*

/**
 * @Description: java类作用描述
 * @Author: jiazhu
 * @Date: 2019-07-18 23:24
 * @ClassName: ChannelContract
 */
interface ChannelContract {
    interface View : BaseView<Presenter> {
        /**
         * 显示列表页面
         *
         * @param beans      列表数据
         * @param categoryId ID
         */
        fun showList(beans: List<ContEntity>, categoryId: String)

        /**
         * 加载分类数据
         *
         * @param beans 分类数据
         */
        fun showCategoryList(beans: ArrayList<CategoryBean.CategoryListBean>)

        /**
         * 加载更多列表数据
         *
         * @param beans      列表数据
         * @param categoryId ID
         */
        fun loadMoreList(beans: List<ContEntity>, categoryId: String)

        /**
         * 完成加载更多
         *
         * @param isSuccess  true：成功
         * @param categoryId ID
         */
        fun loadMoreFinish(isSuccess: Boolean, categoryId: String)

        /**
         * 完成刷新
         *
         * @param isSuccess  true：成功
         * @param categoryId ID
         */
        fun loadRefreshFinish(isSuccess: Boolean, categoryId: String)
    }

    interface Presenter : BasePresenter {
        /**
         * 获取分类数据
         */
        fun loadCategory()

        /**
         * 获取列表数据
         *
         * @param hotPageidx 热门数据
         * @param categoryId 分类编码
         * @param start      页码索引
         */
        fun loadCategoryConts(hotPageidx: Int,
                              categoryId: String,
                              start: Int)

        /**
         * 获取更多列表
         *
         * @param categoryId ID
         */
        fun loadCategoryContsMore(categoryId: String)

        /**
         * 刷新列表
         *
         * @param categoryId ID
         */
        fun loadCategoryContsRefresh(categoryId: String)

    }
}
