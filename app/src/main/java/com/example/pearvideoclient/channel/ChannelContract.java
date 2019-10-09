package com.example.pearvideoclient.channel;

import com.example.apublic.BasePresenter;
import com.example.apublic.BaseView;
import com.example.pearvideoclient.entity.CategoryBean;
import com.example.pearvideoclient.entity.ContEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: java类作用描述
 * @Author: jiazhu
 * @Date: 2019-07-18 23:24
 * @ClassName: ChannelContract
 */
public interface ChannelContract {
    interface View extends BaseView<Presenter> {
        /**
         * 显示列表页面
         *
         * @param beans      列表数据
         * @param categoryId ID
         */
        void showList(List<ContEntity> beans, String categoryId);

        /**
         * 加载分类数据
         *
         * @param beans 分类数据
         */
        void showCategoryList(ArrayList<CategoryBean.CategoryListBean> beans);

        /**
         * 加载更多列表数据
         *
         * @param beans      列表数据
         * @param categoryId ID
         */
        void loadMoreList(List<ContEntity> beans, String categoryId);

        /**
         * 完成加载更多
         *
         * @param isSuccess  true：成功
         * @param categoryId ID
         */
        void loadMoreFinish(boolean isSuccess, String categoryId);

        /**
         * 完成刷新
         *
         * @param isSuccess  true：成功
         * @param categoryId ID
         */
        void loadRefreshFinish(boolean isSuccess, String categoryId);
    }

    interface Presenter extends BasePresenter {
        /**
         * 获取分类数据
         */
        void loadCategory();

        /**
         * 获取列表数据
         *
         * @param hotPageidx 热门数据
         * @param categoryId 分类编码
         * @param start      页码索引
         */
        void loadCategoryConts(int hotPageidx,
                               String categoryId,
                               int start);

        /**
         * 获取更多列表
         *
         * @param categoryId ID
         */
        void loadCategoryContsMore(String categoryId);

        /**
         * 刷新列表
         *
         * @param categoryId ID
         */
        void loadCategoryContsRefresh(String categoryId);

    }
}
