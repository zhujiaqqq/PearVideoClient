package com.example.pearvideoclient.author

import com.example.apublic.BasePresenter
import com.example.apublic.BaseView
import com.example.pearvideoclient.entity.*

/**
 * @Description: java类作用描述
 * @Author: jiazhu
 * @Date: 2019-08-13 11:52
 * @ClassName: AuthorContract
 */
interface AuthorContract {

    interface View : BaseView<Presenter> {
        /**
         * 设置fragments
         *
         * @param title fragment的标题
         */
        fun setFragments(title: List<String>)

        /**
         * 设置作者的头部信息
         *
         * @param infoBean 作者信息
         */
        fun setAuthorTitle(infoBean: InfoBean)

        /**
         * 设置动态页面数据
         *
         * @param dataList 数据
         */
        fun setUserHomeData(dataList: List<AuthorHomeBean.DataListBean>)

        /**
         * 加载更多动态fragment数据
         *
         * @param dataList 数据
         */
        fun loadMoreUserHomeData(dataList: List<AuthorHomeBean.DataListBean>)

        /**
         * 加载更多完成
         *
         * @param type      页面类型
         * @param isSuccess 成功
         */
        fun loadMoreFinish(@AuthorPresenter.PageType type: String, isSuccess: Boolean)

        /**
         * 刷新完成
         *
         * @param type      页面类型
         * @param isSuccess 成功
         */
        fun loadRefreshFinish(@AuthorPresenter.PageType type: String, isSuccess: Boolean)

        /**
         * 加载最热视频列表
         *
         * @param hotList 最热视频列表
         */
        fun setHotConts(hotList: List<UserConts.ContListBean>)

        /**
         * 加载最新视频列表
         *
         * @param contList 最新视频列表
         */
        fun setNewConts(contList: List<UserConts.ContListBean>)

        /**
         * 加载更多最新视频列表
         *
         * @param contList 最新视频列表
         */
        fun loadMoreNewConts(contList: List<UserConts.ContListBean>)

        /**
         * 加载专辑列表
         *
         * @param albumList 专辑列表
         */
        fun setAlbumsList(albumList: List<UserAlbumsBean.AlbumListBean>)

        /**
         * 加载更多专辑列表
         *
         * @param albumList 专辑列表
         */
        fun loadMoreUserAlbums(albumList: List<UserAlbumsBean.AlbumListBean>)

        /**
         * 加载评论列表
         *
         * @param postsList 评论列表
         */
        fun setPostsList(postsList: List<UserPostsBean.PostListBean>)

        /**
         * 加载更多评论列表
         *
         * @param postsList 评论列表
         */
        fun loadMorePostsList(postsList: List<UserPostsBean.PostListBean>)

        /**
         * 切换关注状态
         */
        fun toggleAttention()
    }

    interface Presenter : BasePresenter {
        /**
         * 获取作者信息
         *
         * @param authorId 作者id
         */
        fun loadAuthorInfo(authorId: String)

        /**
         * 加载动态fragment页面数据
         *
         * @param authorId 作者ID
         */
        fun loadUserHomeInfo(authorId: String)

        /**
         * 刷新动态fragment页面数据
         */
        fun refreshUserHomeList()

        /**
         * 加载更多动态fragment页面数据
         */
        fun loadMoreUserHomeList()

        /**
         * 加载视频fragment页面数据
         *
         * @param authorId 作者ID
         */
        fun loadUserContsInfo(authorId: String)

        /**
         * 刷新视频fragment页面数据
         */
        fun refreshUserContsList()

        /**
         * 加载更多视频fragment页面数据
         */
        fun loadMoreUserContsList()

        /**
         * 加载专辑 fragment 页面数据
         *
         * @param authorId 作者ID
         */
        fun loadUserAlbumsInfo(authorId: String)

        /**
         * 刷新专辑 fragment页面数据
         */
        fun refreshUserAlbumsList()

        /**
         * 加载更多专辑fragment页面数据
         */
        fun loadMoreUserAlbumsList()

        /**
         * 加载评论fragment 页面数据
         *
         * @param authorId 作者ID
         */
        fun loadUserPostsInfo(authorId: String)

        /**
         * 刷新评论fragment页面数据
         */
        fun refreshUserPostsList()

        /**
         * 加载更多评论fragment页面数据
         */
        fun loadMoreUserPostsList()

        /**
         * 关注/取关
         *
         * @param opt    1：关注  2：取关
         * @param userId 用户ID
         */
        fun toOptUserFollow(opt: String, userId: String)
    }

}
