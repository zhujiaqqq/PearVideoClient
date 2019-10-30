package com.example.pearvideoclient.content

import android.os.Handler
import com.example.pearvideoclient.Api
import com.example.pearvideoclient.Constants.SUCCESS
import com.example.pearvideoclient.http.RetrofitManager
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

/**
 * @Description: java类作用描述
 * @Author: jiazhu
 * @Date: 2019-07-28 17:55
 * @ClassName: ContentPresenter
 */
class ContentPresenter(private val mView: ContentContract.View, private val mHandler: Handler) : ContentContract.Presenter {
    private val mCompositeDisposable: CompositeDisposable
    private var isShowDetail: Boolean = false

    private var isStar: Boolean = false

    init {
        mView.setPresenter(this)

        mCompositeDisposable = CompositeDisposable()
    }

    override fun loadContent(id: String) {
        mView.showLoading()
        val disposable = RetrofitManager.getInstance().createReq(Api::class.java).getContent(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { contentBean ->
                            mView.showVideo(contentBean.content.videos)
                            mView.showDetail(false)
                            mView.showVideoInfo(contentBean.content)
                            mView.showRelatedVideos(contentBean.relateConts)
                            mView.showHotVideos(contentBean.hotConts)
                            mView.showTags(contentBean.content.tags)
                        },
                        { mView.cancelLoading() },
                        { mView.cancelLoading() })
        mCompositeDisposable.add(disposable)
    }

    override fun star(contId: String) {
        if (isStar) {
            return
        }
        isStar = true
        val disposable = RetrofitManager.getInstance().createReq(Api::class.java).toStar(contId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { contPraise ->
                    if (SUCCESS == contPraise.resultMsg) {
                        mView.showStar(true)
                    }
                }
        mCompositeDisposable.add(disposable)

    }

    override fun collect() {
        //
    }

    override fun download() {
        //
    }

    override fun attention() {
        //
    }

    override fun fullScreen() {
        //
    }

    override fun toggleDetail() {
        isShowDetail = !isShowDetail
        mView.showDetail(isShowDetail)
    }

    override fun showPlayController() {
        mView.showOrHideController()
        mHandler.removeMessages(MSG_HIDDEN_CONTROLLER)
        mHandler.sendEmptyMessageDelayed(MSG_HIDDEN_CONTROLLER, 3000)
    }

    override fun hidePlayController() {
        mView.showOrHideController()
    }

    override fun toOptUserFollow(opt: String, userId: String) {
        val disposable = RetrofitManager.getInstance().createReq(Api::class.java).optUserFollow(opt, userId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { userFollowBean ->
                    if (SUCCESS == userFollowBean.resultMsg) {
                        mView.toggleAttention()
                    }
                }
        mCompositeDisposable.add(disposable)
    }

    override fun subscribe() {
        hidePlayController()
        mCompositeDisposable.clear()
    }

    override fun unsubscribe() {
        mCompositeDisposable.dispose()
    }

    companion object {
        const val MSG_HIDDEN_CONTROLLER = 0x99
        const val FOLLOW_USER = "1"
        const val UN_FOLLOW_USER = "2"
    }

}
