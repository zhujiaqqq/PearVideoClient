package com.example.pearvideoclient.follow.addfollow

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.apublic.CommonCallBack
import com.example.pearvideoclient.Api
import com.example.pearvideoclient.Constants.SUCCESS
import com.example.pearvideoclient.entity.DomainListBean
import com.example.pearvideoclient.entity.FollowUsersBean
import com.example.pearvideoclient.http.RetrofitManager
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

/**
 * @Description: java类作用描述
 * @Author: jiazhu
 * @Date: 2019-09-02 16:48
 * @ClassName: AddFollowViewModel
 */
class AddFollowViewModel(application: Application) : AndroidViewModel(application) {
    private val mCompositeDisposable = CompositeDisposable()

    fun getDomainList(callBack: CommonCallBack<List<DomainListBean.DomainBean>, Unit>) {
        val disposable = RetrofitManager.getInstance().createReq(Api::class.java)
                .getDomainList("ALL")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map<List<DomainListBean.DomainBean>> { this.changeDomainList(it) }
                .subscribe { callBack.todo(it) }
        mCompositeDisposable.add(disposable)
    }

    private fun changeDomainList(domainListBean: DomainListBean): List<DomainListBean.DomainBean> {
        val domainList = domainListBean.domainList
        for (domainBean in domainList) {
            domainBean.type = 3
        }
        domainList[0].isChecked = true
        return domainList
    }

    fun getUsers(domain: String, type: Int?,
                 callBack: CommonCallBack<List<FollowUsersBean.UserBean>, Unit>) {
        val disposable = RetrofitManager.getInstance().createReq(Api::class.java)
                .getUsers(domain, "", type)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map<List<FollowUsersBean.UserBean>> { it.userList }
                .subscribe { callBack.todo(it) }

        mCompositeDisposable.add(disposable)
    }


    fun toOptUserFollow(opt: String, userId: String) {
        val disposable = RetrofitManager.getInstance().createReq(Api::class.java).optUserFollow(opt, userId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { userFollowBean ->
                    if (SUCCESS == userFollowBean.resultMsg) {

                    }
                }
        mCompositeDisposable.add(disposable)
    }

    fun dispose() {
        mCompositeDisposable.dispose()
    }


}
