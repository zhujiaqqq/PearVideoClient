package com.example.pearvideoclient.channel;

import com.example.pearvideoclient.BasePresenter;
import com.example.pearvideoclient.BaseView;
import com.example.pearvideoclient.entity.bean.CategoryBean;
import com.example.pearvideoclient.entity.bean.CategoryContsBean;

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
         * @param beans 列表数据
         */
        void showList(List<CategoryContsBean.ContListBean> beans);

        /**
         * 加载分类数据
         *
         * @param beans 分类数据
         */
        void showCategoryList(ArrayList<CategoryBean.CategoryListBean> beans);

        /**
         * 加载更多列表数据
         *
         * @param beans 列表数据
         */
        void loadMoreList(List<CategoryContsBean.ContListBean> beans);

        /**
         * 完成加载更多
         *
         * @param isSuccess true：成功
         */
        void loadMoreFinish(boolean isSuccess);

        /**
         * 完成刷新
         *
         * @param isSuccess true：成功
         */
        void loadRefreshFinish(boolean isSuccess);
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
        void loadCategoryConts(String hotPageidx,
                               String categoryId,
                               String start);

        /**
         * 获取更多列表
         */
        void loadCategoryContsMore();

        /**
         * 刷新列表
         */
        void loadCategoryContsRefresh();

    }
}
