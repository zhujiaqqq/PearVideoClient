package com.example.pearvideoclient.mine

import com.example.apublic.BasePresenter
import com.example.apublic.BaseView
import com.example.pearvideoclient.entity.LoginBean
import com.example.pearvideoclient.entity.UserInfoBean

/**
 * @Description: java类作用描述
 * @Author: jiazhu
 * @Date: 2019-07-21 15:50
 * @ClassName: MineContract
 */
interface MineContract {
    interface View : BaseView<Presenter> {
        /**
         * 显示用户信息
         *
         * @param userInfoBean 用户信息
         */
        fun showUserInfo(userInfoBean: LoginBean.UserInfoBean)

        /**
         * 显示用户信息
         *
         * @param userInfoBean 用户信息
         */
        fun showUserInfo(userInfoBean: UserInfoBean)

        /**
         * 显示Close图标
         *
         * @param isShow 显示：true
         */
        fun showCloseIcon(isShow: Boolean)

        /**
         * 显示登录布局
         *
         * @param isShow 显示：true
         */
        fun showLoginLayout(isShow: Boolean)

        /**
         * 显示关注布局
         *
         * @param isShow 显示：true
         */
        fun showMyFollowLayout(isShow: Boolean)

        /**
         * 展示popWindow
         *
         * @param isShow 显示：true
         */
        fun showPopWindow(isShow: Boolean)

    }

    interface Presenter : BasePresenter {
        /**
         * 获取历史数据
         */
        fun loadReadHisList()

        /**
         * 登录
         *
         * @param loginName 用户名
         * @param pwd       密码
         */
        fun login(loginName: String, pwd: String)

        /**
         * 获取用户信息
         *
         * @param userId 用户id
         */
        fun getUserInfo(userId: String?)

        /**
         * 获取关注列表
         */
        fun loadFollowList()

        /**
         * 刷新用户状态
         *
         * @param lastSysTime
         */
        fun getMsgMask(lastSysTime: String)
    }
}
