package com.example.pearvideoclient.author;

import com.example.pearvideoclient.BasePresenter;
import com.example.pearvideoclient.BaseView;
import com.example.pearvideoclient.entity.bean.AuthorHomeBean;
import com.example.pearvideoclient.entity.bean.UserInfoBean;

import java.util.List;

/**
 * @Description: java类作用描述
 * @Author: jiazhu
 * @Date: 2019-08-13 11:52
 * @ClassName: AuthorContract
 */
public interface AuthorContract {

    interface View extends BaseView<Presenter> {
        /**
         * 设置fragments
         *
         * @param title fragment的标题
         */
        void setFragments(List<String> title);

        /**
         * 设置作者的头部信息
         *
         * @param infoBean 作者信息
         */
        void setAuthorTitle(UserInfoBean.InfoBean infoBean);

        /**
         * 设置动态页面数据
         *
         * @param dataList 数据
         */
        void setUserHomeData(List<AuthorHomeBean.DataListBean> dataList);

        /**
         * 加载更多动态fragment数据
         *
         * @param dataList 数据
         */
        void loadMoreUserHomeData(List<AuthorHomeBean.DataListBean> dataList);

        /**
         * 加载更多完成
         *
         * @param type      页面类型
         * @param isSuccess 成功
         */
        void loadMoreFinish(AuthorPresenter.PageType type, boolean isSuccess);

        /**
         * 刷新完成
         *
         * @param type      页面类型
         * @param isSuccess 成功
         */
        void loadRefreshFinish(AuthorPresenter.PageType type, boolean isSuccess);
    }

    interface Presenter extends BasePresenter {
        /**
         * 获取作者信息
         *
         * @param authorId 作者id
         */
        void loadAuthorInfo(String authorId);

        /**
         * 加载动态fragment页面数据
         *
         * @param authorId 作者ID
         */
        void loadUserHomeInfo(String authorId);

        /**
         * 刷新动态fragment页面数据
         */
        void refreshUserHomeList();

        /**
         * 加载更多动态fragment页面数据
         */
        void loadMoreUserHomeList();
    }

}
