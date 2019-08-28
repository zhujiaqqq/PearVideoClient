package com.example.pearvideoclient.author;

import com.example.pearvideoclient.BasePresenter;
import com.example.pearvideoclient.BaseView;
import com.example.pearvideoclient.entity.AuthorHomeBean;
import com.example.pearvideoclient.entity.UserAlbumsBean;
import com.example.pearvideoclient.entity.UserConts;
import com.example.pearvideoclient.entity.UserInfoBean;
import com.example.pearvideoclient.entity.UserPostsBean;

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
        void loadMoreFinish(@AuthorPresenter.PageType String type, boolean isSuccess);

        /**
         * 刷新完成
         *
         * @param type      页面类型
         * @param isSuccess 成功
         */
        void loadRefreshFinish(@AuthorPresenter.PageType String type, boolean isSuccess);

        /**
         * 加载最热视频列表
         *
         * @param hotList 最热视频列表
         */
        void setHotConts(List<UserConts.ContListBean> hotList);

        /**
         * 加载最新视频列表
         *
         * @param contList 最新视频列表
         */
        void setNewConts(List<UserConts.ContListBean> contList);

        /**
         * 加载更多最新视频列表
         *
         * @param contList 最新视频列表
         */
        void loadMoreNewConts(List<UserConts.ContListBean> contList);

        /**
         * 加载专辑列表
         *
         * @param albumList 专辑列表
         */
        void setAlbumsList(List<UserAlbumsBean.AlbumListBean> albumList);

        /**
         * 加载更多专辑列表
         *
         * @param albumList 专辑列表
         */
        void loadMoreUserAlbums(List<UserAlbumsBean.AlbumListBean> albumList);

        /**
         * 加载评论列表
         *
         * @param postsList 评论列表
         */
        void setPostsList(List<UserPostsBean.PostListBean> postsList);

        /**
         * 加载更多评论列表
         *
         * @param postsList 评论列表
         */
        void loadMorePostsList(List<UserPostsBean.PostListBean> postsList);

        /**
         * 切换关注状态
         */
        void toggleAttention();
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

        /**
         * 加载视频fragment页面数据
         *
         * @param authorId 作者ID
         */
        void loadUserContsInfo(String authorId);

        /**
         * 刷新视频fragment页面数据
         */
        void refreshUserContsList();

        /**
         * 加载更多视频fragment页面数据
         */
        void loadMoreUserContsList();

        /**
         * 加载专辑 fragment 页面数据
         *
         * @param authorId 作者ID
         */
        void loadUserAlbumsInfo(String authorId);

        /**
         * 刷新专辑 fragment页面数据
         */
        void refreshUserAlbumsList();

        /**
         * 加载更多专辑fragment页面数据
         */
        void loadMoreUserAlbumsList();

        /**
         * 加载评论fragment 页面数据
         *
         * @param authorId 作者ID
         */
        void loadUserPostsInfo(String authorId);

        /**
         * 刷新评论fragment页面数据
         */
        void refreshUserPostsList();

        /**
         * 加载更多评论fragment页面数据
         */
        void loadMoreUserPostsList();

        /**
         * 关注/取关
         *
         * @param opt    1：关注  2：取关
         * @param userId 用户ID
         */
        void toOptUserFollow(String opt, String userId);
    }

}
