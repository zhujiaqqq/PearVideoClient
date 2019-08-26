package com.example.pearvideoclient.screen;

import com.example.pearvideoclient.BasePresenter;
import com.example.pearvideoclient.BaseView;
import com.example.pearvideoclient.entity.PaikeFineVideoBean;

import java.util.List;

/**
 * @Description: java类作用描述
 * @Author: jiazhu
 * @Date: 2019-08-26 15:02
 * @ClassName: ScreenContract
 */
public interface ScreenContract {
    interface View extends BaseView<Presenter> {
        void showPaikeVideos(List<PaikeFineVideoBean.VideoBean> data);

        void showPaikeInfo(PaikeFineVideoBean.ConfigInfoBean data);
    }

    interface Presenter extends BasePresenter {
        void loadPaikeFineVideos();
    }
}
