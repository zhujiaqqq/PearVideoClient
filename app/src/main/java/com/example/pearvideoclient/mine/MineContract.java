package com.example.pearvideoclient.mine;

import com.example.pearvideoclient.BasePresenter;
import com.example.pearvideoclient.BaseView;
import com.example.pearvideoclient.entity.LoginBean;
import com.example.pearvideoclient.entity.UserInfoBean;

/**
 * @Description: java类作用描述
 * @Author: jiazhu
 * @Date: 2019-07-21 15:50
 * @ClassName: MineContract
 */
public interface MineContract {
    interface View extends BaseView<Presenter> {
        /**
         * 显示用户信息
         *
         * @param userInfoBean 用户信息
         */
        void showUserInfo(LoginBean.UserInfoBean userInfoBean);

        /**
         * 显示用户信息
         *
         * @param userInfoBean 用户信息
         */
        void showUserInfo(UserInfoBean userInfoBean);

        /**
         * 显示Close图标
         *
         * @param isShow 显示：true
         */
        void showCloseIcon(boolean isShow);

        /**
         * 显示登录布局
         *
         * @param isShow 显示：true
         */
        void showLoginLayout(boolean isShow);

        /**
         * 显示关注布局
         *
         * @param isShow 显示：true
         */
        void showMyFollowLayout(boolean isShow);

        /**
         * 展示popWindow
         *
         * @param isShow 显示：true
         */
        void showPopWindow(boolean isShow);

    }

    interface Presenter extends BasePresenter {
        /**
         * 获取历史数据
         */
        void loadReadHisList();

        /**
         * 登录
         *
         * @param loginName 用户名
         * @param pwd       密码
         */
        void login(String loginName, String pwd);

        /**
         * 获取用户信息
         *
         * @param userId 用户id
         */
        void getUserInfo(String userId);

        /**
         * 获取关注列表
         */
        void loadFollowList();

        /**
         * 刷新用户状态
         *
         * @param lastSysTime
         */
        void getMsgMask(String lastSysTime);
    }
}
