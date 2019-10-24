package com.example.pearvideoclient.follow

import com.example.apublic.BasePresenter
import com.example.apublic.BaseView
import com.example.pearvideoclient.entity.MyFollowContBean

/**
 * @Description: java类作用描述
 * @Author: jiazhu
 * @Date: 2019-08-10 20:37
 * @ClassName: FollowContract
 */
interface FollowContract {
    interface View : BaseView<Presenter> {
        /**
         * 显示我的关注的用户
         *
         * @param list 关注用户列表
         */
        fun showFollowUser(list: List<MyFollowContBean.FollowUserListBean>)

        /**
         * 显示我的关注的视频
         *
         * @param list 关注的用户的视频列表
         */
        fun showFollowData(list: List<MyFollowContBean.DataListBean>)

        /**
         * 加载更多完成
         *
         * @param isSuccess 成功
         */
        fun loadMoreFinish(isSuccess: Boolean)

        /**
         * 刷新完成
         *
         * @param isSuccess 成功
         */
        fun loadRefreshFinish(isSuccess: Boolean)

        /**
         * 加载更多数据
         *
         * @param bean 数据
         */
        fun loadMoreFollowData(bean: MyFollowContBean)
    }

    interface Presenter : BasePresenter {
        /**
         * 获取我关注的用户信息
         */
        fun loadMyFollowList()

        /**
         * 加载更多我的关注
         */
        fun loadMoreMyFollowList()

        /**
         * 刷新我的关注
         */
        fun refreshMyFollowList()
    }
}
