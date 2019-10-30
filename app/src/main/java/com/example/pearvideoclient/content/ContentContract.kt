package com.example.pearvideoclient.content

import com.example.apublic.BasePresenter
import com.example.apublic.BaseView
import com.example.pearvideoclient.entity.content.Content
import com.example.pearvideoclient.entity.content.HotConts
import com.example.pearvideoclient.entity.content.RelateConts
import com.example.pearvideoclient.entity.content.Tags
import com.example.pearvideoclient.entity.content.Videos

/**
 * @Description: java类作用描述
 * @Author: jiazhu
 * @Date: 2019-07-28 10:48
 * @ClassName: ContentContract
 */
interface ContentContract {
    interface View : BaseView<Presenter> {
        /**
         * 显示视频信息
         *
         * @param content 信息
         */
        fun showVideoInfo(content: Content)

        /**
         * 加载视频
         *
         * @param videos 视频列表
         */
        fun showVideo(videos: List<Videos>)

        /**
         * 加载相关视频列表
         *
         * @param relateConts 相关视频列表
         */
        fun showRelatedVideos(relateConts: List<RelateConts>)

        /**
         * 加载热门视频列表
         *
         * @param hotConts 热门视频列表
         */
        fun showHotVideos(hotConts: List<HotConts>)

        /**
         * 加载标签列表
         *
         * @param tags 标签列表
         */
        fun showTags(tags: List<Tags>)

        /**
         * 显示详情
         *
         * @param isShow true：显示
         */
        fun showDetail(isShow: Boolean)

        /**
         * 显示视频控制器
         */
        fun showOrHideController()

        /**
         * 显示star
         *
         * @param isStar 是否star
         */
        fun showStar(isStar: Boolean)

        /**
         * 切换关注状态
         */
        fun toggleAttention()
    }

    interface Presenter : BasePresenter {
        /**
         * 加载页面信息
         *
         * @param id ContId
         */
        fun loadContent(id: String)

        /**
         * 加Star
         *
         * @param contId ContId
         */
        fun star(contId: String)

        /**
         * 收藏
         */
        fun collect()

        /**
         * 缓存
         */
        fun download()

        /**
         * 关注
         */
        fun attention()

        /**
         * 全屏
         */
        fun fullScreen()

        /**
         * 切换详情状态
         */
        fun toggleDetail()

        /**
         * 显示视频控制器
         */
        fun showPlayController()

        /**
         * 隐藏视频控制器
         */
        fun hidePlayController()

        /**
         * 关注/取关
         *
         * @param opt    1：关注  2：取关
         * @param userId 用户ID
         */
        fun toOptUserFollow(opt: String, userId: String)

    }
}
