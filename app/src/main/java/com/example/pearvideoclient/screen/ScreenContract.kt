package com.example.pearvideoclient.screen

import com.example.apublic.BasePresenter
import com.example.apublic.BaseView
import com.example.pearvideoclient.entity.PaikeFineVideoBean

/**
 * @Description: java类作用描述
 * @Author: jiazhu
 * @Date: 2019-08-26 15:02
 * @ClassName: ScreenContract
 */
interface ScreenContract {
    interface View : BaseView<Presenter> {
        fun showPaikeVideos(data: List<PaikeFineVideoBean.VideoBean>)

        fun showPaikeInfo(data: PaikeFineVideoBean.ConfigInfoBean)
    }

    interface Presenter : BasePresenter {
        fun loadPaikeFineVideos()
    }
}
