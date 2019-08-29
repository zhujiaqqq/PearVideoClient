package com.example.pearvideoclient.author;

import com.example.pearvideoclient.BasePresenter;
import com.example.pearvideoclient.BaseView;
import com.example.pearvideoclient.entity.AlbumContBean;

import java.util.List;

/**
 * @Description: java类作用描述
 * @Author: jiazhu
 * @Date: 2019-08-28 19:32
 * @ClassName: AlbumContract
 */
public interface AlbumContract {
    interface View extends BaseView<Presenter> {
        /**
         * 显示标题
         *
         * @param name 标题
         */
        void setTitle(String name);

        /**
         * 显示专辑列表
         *
         * @param contList 专辑列表
         */
        void setAlbumCont(List<AlbumContBean.ContListBean> contList);

        /**
         * 加载更多专辑列表
         *
         * @param contList 专辑列表
         */
        void addAlbumCont(List<AlbumContBean.ContListBean> contList);

        /**
         * 加载更多完成
         *
         * @param b true 成功
         */
        void finishLoad(boolean b);

        /**
         * 刷新完成
         *
         * @param b true 成功
         */
        void finishRefresh(boolean b);
    }

    interface Presenter extends BasePresenter {
        /**
         * 加载专辑
         *
         * @param albumId 专辑ID
         */
        void loadAlbumConts(String albumId);

        /**
         * 加载更多专辑
         */
        void loadMoreAlbumConts();

        /**
         * 刷新专辑
         */
        void refreshAlbumConts();
    }
}
