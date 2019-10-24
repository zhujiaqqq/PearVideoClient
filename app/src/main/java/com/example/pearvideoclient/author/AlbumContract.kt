package com.example.pearvideoclient.author

import com.example.apublic.BasePresenter
import com.example.apublic.BaseView
import com.example.pearvideoclient.entity.AlbumContBean

/**
 * @Description: java类作用描述
 * @Author: jiazhu
 * @Date: 2019-08-28 19:32
 * @ClassName: AlbumContract
 */
interface AlbumContract {
    interface View : BaseView<Presenter> {
        /**
         * 显示标题
         *
         * @param name 标题
         */
        fun setTitle(name: String)

        /**
         * 显示专辑列表
         *
         * @param contList 专辑列表
         */
        fun setAlbumCont(contList: List<AlbumContBean.ContListBean>)

        /**
         * 加载更多专辑列表
         *
         * @param contList 专辑列表
         */
        fun addAlbumCont(contList: List<AlbumContBean.ContListBean>)

        /**
         * 加载更多完成
         *
         * @param b true 成功
         */
        fun finishLoad(b: Boolean)

        /**
         * 刷新完成
         *
         * @param b true 成功
         */
        fun finishRefresh(b: Boolean)
    }

    interface Presenter : BasePresenter {
        /**
         * 加载专辑
         *
         * @param albumId 专辑ID
         */
        fun loadAlbumConts(albumId: String)

        /**
         * 加载更多专辑
         */
        fun loadMoreAlbumConts()

        /**
         * 刷新专辑
         */
        fun refreshAlbumConts()
    }
}
