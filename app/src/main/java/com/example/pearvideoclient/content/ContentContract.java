package com.example.pearvideoclient.content;

import android.view.animation.Animation;

import com.example.pearvideoclient.BasePresenter;
import com.example.pearvideoclient.BaseView;
import com.example.pearvideoclient.entity.bean.content.Content;
import com.example.pearvideoclient.entity.bean.content.RelateConts;
import com.example.pearvideoclient.entity.bean.content.Tags;
import com.example.pearvideoclient.entity.bean.content.Videos;

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
         *
         * @param isShow
         */
        void showController(boolean isShow);

        /**
         *
         * @param animation
         */
        void viewDoAnimation(Animation animation);

    }

    interface Presenter extends BasePresenter {
        /**
         * 加载页面信息
         */
        void loadContent(String id);

        /**
         * 加Star
         */
        void star();

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
         * 停止
         */
        void stop();

        /**
         * 暂停
         */
        void pause();

        /**
         * 播放
         */
        void play();

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
    }
}
