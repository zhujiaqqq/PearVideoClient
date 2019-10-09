package com.example.pearvideoclient.follow;

import com.example.apublic.BasePresenter;
import com.example.apublic.BaseView;
import com.example.pearvideoclient.entity.MyFollowContBean;

import java.util.List;

/**
 * @Description: java类作用描述
 * @Author: jiazhu
 * @Date: 2019-08-10 20:37
 * @ClassName: FollowContract
 */
public interface FollowContract {
    interface View extends BaseView<Presenter> {
        /**
         * 显示我的关注的用户
         *
         * @param list 关注用户列表
         */
        void showFollowUser(List<MyFollowContBean.FollowUserListBean> list);

        /**
         * 显示我的关注的视频
         *
         * @param list 关注的用户的视频列表
         */
        void showFollowData(List<MyFollowContBean.DataListBean> list);

        /**
         * 加载更多完成
         *
         * @param isSuccess 成功
         */
        void loadMoreFinish(boolean isSuccess);

        /**
         * 刷新完成
         *
         * @param isSuccess 成功
         */
        void loadRefreshFinish(boolean isSuccess);

        /**
         * 加载更多数据
         *
         * @param bean 数据
         */
        void loadMoreFollowData(MyFollowContBean bean);
    }

    interface Presenter extends BasePresenter {
        /**
         * 获取我关注的用户信息
         */
        void loadMyFollowList();

        /**
         * 加载更多我的关注
         */
        void loadMoreMyFollowList();

        /**
         * 刷新我的关注
         */
        void refreshMyFollowList();
    }
}
