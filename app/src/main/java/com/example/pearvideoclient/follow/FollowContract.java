package com.example.pearvideoclient.follow;

import com.example.pearvideoclient.BasePresenter;
import com.example.pearvideoclient.BaseView;
import com.example.pearvideoclient.entity.bean.MyFollowContBean;

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
    }

    interface Presenter extends BasePresenter {
        /**
         * 获取我关注的用户信息
         */
        void loadMyFollowList();
    }
}
