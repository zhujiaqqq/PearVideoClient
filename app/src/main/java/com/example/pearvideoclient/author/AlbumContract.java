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

        void setTitle(String name);

        void setAlbumCont(List<AlbumContBean.ContListBean> contList);

        void addAlbumCont(List<AlbumContBean.ContListBean> contList);

        void finishLoad(boolean b);

        void finishRefresh(boolean b);
    }

    interface Presenter extends BasePresenter {
        /**
         * @param albumId
         */
        void loadAlbumConts(String albumId);

        void loadMoreAlbumConts();

        void refreshAlbumConts();
    }
}
