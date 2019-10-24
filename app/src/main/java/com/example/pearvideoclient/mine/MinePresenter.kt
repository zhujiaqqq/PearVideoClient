package com.example.pearvideoclient.mine

import android.content.Context
import android.text.TextUtils
import android.util.Log
import com.example.pearvideoclient.Api
import com.example.pearvideoclient.Constants.SUCCESS
import com.example.pearvideoclient.http.RetrofitManager
import com.example.pearvideoclient.utils.Md5Util
import com.example.pearvideoclient.utils.SharedPreferencesHelper
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

/**
 * @Description: java类作用描述
 * @Author: jiazhu
 * @Date: 2019-07-24 17:46
 * @ClassName: MinePresenter
 */
class MinePresenter(private val mView: MineContract.View, context: Context) : MineContract.Presenter {
    private val mCompositeDisposable: CompositeDisposable
    private val helper: SharedPreferencesHelper

    private var userId: Int? = null

    init {
        this.mView.setPresenter(this)
        this.mCompositeDisposable = CompositeDisposable()
        this.helper = SharedPreferencesHelper(context, "user_info")
        userId = helper.getSharedPreference("userId", 0) as Int?
    }

    override fun loadReadHisList() {
        mView.showLoading()
        val d = RetrofitManager.getInstance().createReq(Api::class.java)
                .myReadHisList
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { myReadHisListBean ->
                            if (NOT_LOGIN == myReadHisListBean.resultMsg) {
                                mView.showLoginLayout(true)
                            } else {
                                mView.showLoginLayout(false)
                                getUserInfo(userId!!.toString())
                                mView.showPopWindow(true)
                            }
                        },
                        { mView.cancelLoading() },
                        { mView.cancelLoading() })
        mCompositeDisposable.add(d)
    }

    override fun login(loginName: String, pwd: String) {
        mView.showLoading()
        if (TextUtils.isEmpty(loginName) || TextUtils.isEmpty(pwd)) {
            mView.cancelLoading()
            return
        }

        val pwdMd5 = Md5Util.EncodeByMD5(pwd)
        val d = RetrofitManager.getInstance().createReq(Api::class.java).login(loginName, pwdMd5)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ loginBean ->
                    if (SUCCESS == loginBean.resultMsg) {
                        mView.showLoginLayout(false)
                        val userInfo = loginBean.userInfo
                        mView.showUserInfo(userInfo)
                    }
                },
                        { mView.cancelLoading() },
                        { mView.cancelLoading() })

        mCompositeDisposable.add(d)
    }


    override fun getUserInfo(userId: String?) {
        mView.showLoading()
        val disposable = RetrofitManager.getInstance().createReq(Api::class.java).getUserInfo(userId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { userInfoBean -> mView.showUserInfo(userInfoBean) },
                        { mView.cancelLoading() },
                        { mView.cancelLoading() })

        mCompositeDisposable.add(disposable)

    }

    override fun loadFollowList() {

    }

    override fun getMsgMask(lastSysTime: String) {
        mView.showLoading()
        val disposable = RetrofitManager.getInstance().createReq(Api::class.java)
                .getMsgMark(lastSysTime)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ msgMarkBean ->
                    Log.d(TAG, "accept: $msgMarkBean")
                    if (msgMarkBean.data.userId != null) {
                        userId = msgMarkBean.data.userId
                        helper.put("userId", userId!!)
                    }
                },
                        { mView.cancelLoading() },
                        { mView.cancelLoading() })
        mCompositeDisposable.add(disposable)
    }

    override fun subscribe() {
        loadReadHisList()
        getMsgMask("0")

    }

    override fun unsubscribe() {
        mCompositeDisposable.dispose()
    }

    companion object {
        private const val TAG = "MinePresenter"
        private const val NOT_LOGIN = "未登录"
    }
}

