package com.example.pearvideoclient.screen

import com.example.pearvideoclient.Api
import com.example.pearvideoclient.http.RetrofitManager
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

/**
 * @Description: java类作用描述
 * @Author: jiazhu
 * @Date: 2019-08-26 15:06
 * @ClassName: ScreenPresenter
 */
class ScreenPresenter(private val mView: ScreenContract.View) : ScreenContract.Presenter {
    private val mCompositeDisposable: CompositeDisposable

    init {
        mView.setPresenter(this)
        mCompositeDisposable = CompositeDisposable()
    }

    override fun loadPaikeFineVideos() {
        val disposable = RetrofitManager.getInstance().createReq(Api::class.java)
                .paikeFineVideos
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { paikeFineVideoBean ->
                    val videoList = paikeFineVideoBean.videoList
                    val configInfo = paikeFineVideoBean.configInfo
                    mView.showPaikeVideos(videoList)
                    mView.showPaikeInfo(configInfo)
                }
        mCompositeDisposable.add(disposable)
    }

    override fun subscribe() {
        mCompositeDisposable.clear()
        loadPaikeFineVideos()
    }

    override fun unsubscribe() {
        mCompositeDisposable.dispose()
    }
}
