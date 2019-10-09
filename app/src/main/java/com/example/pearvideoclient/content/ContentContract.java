package com.example.pearvideoclient.content;

import com.example.apublic.BasePresenter;
import com.example.apublic.BaseView;
import com.example.pearvideoclient.entity.content.Content;
import com.example.pearvideoclient.entity.content.HotConts;
import com.example.pearvideoclient.entity.content.RelateConts;
import com.example.pearvideoclient.entity.content.Tags;
import com.example.pearvideoclient.entity.content.Videos;

import java.util.List;

/**
 * @Description: java类作用描述
 * @Author: jiazhu
 * @Date: 2019-07-28 10:48
 * @ClassName: ContentContract
 */
public interface ContentContract {
    interface View extends BaseView<Presenter> {
        /**
         * 显示视频信息
         *
         * @param content 信息
         */
        void showVideoInfo(Content content);

        /**
         * 加载视频
         *
         * @param videos 视频列表
         */
        void showVideo(List<Videos> videos);

        /**
         * 加载相关视频列表
         *
         * @param relateConts 相关视频列表
         */
        void showRelatedVideos(List<RelateConts> relateConts);

        /**
         * 加载热门视频列表
         *
         * @param hotConts 热门视频列表
         */
        void showHotVideos(List<HotConts> hotConts);

        /**
         * 加载标签列表
         *
         * @param tags 标签列表
         */
        void showTags(List<Tags> tags);

        /**
         * 显示详情
         *
         * @param isShow true：显示
         */
        void showDetail(boolean isShow);

        /**
         * 显示视频控制器
         */
        void showOrHideController();

        /**
         * 显示star
         *
         * @param isStar 是否star
         */
        void showStar(boolean isStar);

        /**
         * 切换关注状态
         */
        void toggleAttention();
    }

    interface Presenter extends BasePresenter {
        /**
         * 加载页面信息
         *
         * @param id ContId
         */
        void loadContent(String id);

        /**
         * 加Star
         *
         * @param contId ContId
         */
        void star(String contId);

        /**
         * 收藏
         */
        void collect();

        /**
         * 缓存
         */
        void download();

        /**
         * 关注
         */
        void attention();

        /**
         * 全屏
         */
        void fullScreen();

        /**
         * 切换详情状态
         */
        void toggleDetail();

        /**
         * 显示视频控制器
         */
        void showPlayController();

        /**
         * 隐藏视频控制器
         */
        void hidePlayController();

        /**
         * 关注/取关
         *
         * @param opt    1：关注  2：取关
         * @param userId 用户ID
         */
        void toOptUserFollow(String opt, String userId);

    }
}
