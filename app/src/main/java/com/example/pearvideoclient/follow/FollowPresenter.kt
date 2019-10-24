package com.example.pearvideoclient.follow

import com.example.pearvideoclient.Api
import com.example.pearvideoclient.Constants
import com.example.pearvideoclient.entity.MyFollowContBean
import com.example.pearvideoclient.http.RetrofitManager

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

/**
 * @Description: java类作用描述
 * @Author: jiazhu
 * @Date: 2019-08-10 20:36
 * @ClassName: FollowPresenter
 */
class FollowPresenter(private val mView: FollowContract.View) : FollowContract.Presenter {
    private val mCompositeDisposable = CompositeDisposable()
    /**
     * 我关注的页数索引
     */
    private lateinit var start: String


    init {
        this.mView.setPresenter(this)
    }

    override fun subscribe() {
        mCompositeDisposable.clear()
        loadMyFollowList()
    }

    override fun unsubscribe() {
        mCompositeDisposable.dispose()
    }

    override fun loadMyFollowList() {
        start = "0"
        val disposable = loadMyFollowList(start, Constants.COMMON)
        mCompositeDisposable.add(disposable)
    }

    override fun loadMoreMyFollowList() {
        start = (Integer.valueOf(start) + 10).toString()
        val disposable = loadMyFollowList(start, Constants.LOAD_MORE)
        mCompositeDisposable.add(disposable)
    }

    override fun refreshMyFollowList() {
        start = "0"
        val disposable = loadMyFollowList(start, Constants.LOAD_REFRESH)
        mCompositeDisposable.add(disposable)
    }

    private fun loadFollowData(myFollowContBean: MyFollowContBean) {
        val dataList = myFollowContBean.dataList
        mView.showFollowData(dataList)
    }

    private fun loadFollowUsers(myFollowContBean: MyFollowContBean) {

        val followUserList = myFollowContBean.followUserList
        val moreUser = MyFollowContBean.FollowUserListBean()
        moreUser.userId = MORE_USER

        followUserList.add(moreUser)
        mView.showFollowUser(followUserList)
    }

    private fun loadMyFollowList(start: String, @Constants.LoadType loadType: Int): Disposable {
        mView.showLoading()
        return RetrofitManager.getInstance().createReq(Api::class.java).myFollowContList(start)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { bean ->
                            if (loadType == Constants.LOAD_REFRESH || loadType == Constants.COMMON) {
                                loadFollowUsers(bean)
                                loadFollowData(bean)
                            } else if (loadType == Constants.LOAD_MORE) {
                                mView.loadMoreFollowData(bean)
                            }
                        },
                        {
                            mView.cancelLoading()
                            if (loadType == Constants.LOAD_MORE) {
                                mView.loadMoreFinish(false)
                            } else if (loadType == Constants.LOAD_REFRESH) {
                                mView.loadRefreshFinish(false)
                            }
                        },
                        {
                            mView.cancelLoading()
                            if (loadType == Constants.LOAD_MORE) {
                                mView.loadMoreFinish(true)
                            } else if (loadType == Constants.LOAD_REFRESH) {
                                mView.loadRefreshFinish(true)
                            }
                        })
    }

    companion object {
        const val MORE_USER = "more_user"
    }
}
