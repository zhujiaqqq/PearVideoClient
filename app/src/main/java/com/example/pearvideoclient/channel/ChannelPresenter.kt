package com.example.pearvideoclient.channel

import com.example.pearvideoclient.Api
import com.example.pearvideoclient.Constants
import com.example.pearvideoclient.entity.CategoryContsBean
import com.example.pearvideoclient.entity.ContEntity
import com.example.pearvideoclient.http.RetrofitManager
import com.example.pearvideoclient.utils.CollectionUtil
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.util.*

/**
 * @Description: java类作用描述
 * @Author: jiazhu
 * @Date: 2019-07-18 23:24
 * @ClassName: ChannelPresenter
 */
class ChannelPresenter(private val mView: ChannelContract.View) : ChannelContract.Presenter {
    private val mCompositeDisposable = CompositeDisposable()
    private val categoryMap = HashMap<String, Int>()

    init {
        this.mView.setPresenter(this)
    }

    override fun loadCategory() {

        mView.showLoading()
        val subscribe = RetrofitManager.getInstance().createReq(Api::class.java).category
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ categoryBean -> mView.showCategoryList(categoryBean.categoryList) },
                        { mView.cancelLoading() },
                        { mView.cancelLoading() })
        mCompositeDisposable.add(subscribe)
    }

    override fun loadCategoryConts(hotPageidx: Int, categoryId: String, start: Int) {
        categoryMap[categoryId] = 0
        val disposable = loadCategoryConts(hotPageidx,
                categoryId,
                categoryMap[categoryId]!!,
                Constants.COMMON)
        mCompositeDisposable.add(disposable)
    }

    override fun loadCategoryContsMore(categoryId: String) {
        if (categoryMap.containsKey(categoryId)) {
            categoryMap[categoryId] = categoryMap[categoryId]!! + 10
        } else {
            categoryMap[categoryId] = 0
        }
        val disposable = loadCategoryConts(1,
                categoryId,
                categoryMap[categoryId]!!,
                Constants.LOAD_MORE)
        mCompositeDisposable.add(disposable)
    }

    override fun loadCategoryContsRefresh(categoryId: String) {
        categoryMap[categoryId] = 0
        val disposable = loadCategoryConts(1,
                categoryId,
                categoryMap[categoryId]!!,
                Constants.LOAD_REFRESH)
        mCompositeDisposable.add(disposable)
    }

    override fun subscribe() {
        loadCategory()

    }

    override fun unsubscribe() {
        mCompositeDisposable.dispose()
    }

    private fun loadCategoryConts(hotPageidx: Int,
                                  categoryId: String,
                                  start: Int,
                                  @Constants.LoadType type: Int): Disposable {
        mView.showLoading()
        return RetrofitManager.getInstance()
                .createReq(Api::class.java)
                .getCategoryConts(hotPageidx, categoryId, start)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map<List<ContEntity>> { this.convertEntity(it) }
                .subscribe({ contEntities ->
                    if (type == Constants.COMMON || type == Constants.LOAD_REFRESH) {
                        mView.showList(contEntities, categoryId)
                    } else if (type == Constants.LOAD_MORE) {
                        mView.loadMoreList(contEntities, categoryId)
                    }
                }, {
                    mView.cancelLoading()
                    if (type == Constants.LOAD_MORE) {
                        mView.loadMoreFinish(false, categoryId)
                    } else if (type == Constants.LOAD_REFRESH) {
                        mView.loadRefreshFinish(false, categoryId)
                    }
                }, {
                    mView.cancelLoading()
                    if (type == Constants.LOAD_MORE) {
                        mView.loadMoreFinish(true, categoryId)
                    } else if (type == Constants.LOAD_REFRESH) {
                        mView.loadRefreshFinish(true, categoryId)
                    }
                })
    }

    private fun convertEntity(categoryContsBean: CategoryContsBean): List<ContEntity> {
        val newContsList = categoryContsBean.contList
        val hotContsList = categoryContsBean.hotList
        val hotTagList = categoryContsBean.hotTagList
        val hotUserList = categoryContsBean.hotUserList
        val rankContsList = categoryContsBean.rankList
        val contEntities = ArrayList<ContEntity>()
        if (!CollectionUtil.isEmpty(hotContsList)) {
            val entity = ContEntity()
            entity.contListBeans = hotContsList
            entity.itemType = ContEntity.TYPE_HOT_CONT
            contEntities.add(entity)
        }
        if (!CollectionUtil.isEmpty(rankContsList)) {
            val entity = ContEntity()
            entity.contListBeans = rankContsList
            entity.itemType = ContEntity.TYPE_RANK_CONT
            contEntities.add(entity)
        }
        if (!CollectionUtil.isEmpty(hotTagList)) {
            val entity = ContEntity()
            entity.hotTagListBeans = hotTagList
            entity.itemType = ContEntity.TYPE_HOT_TAG
            contEntities.add(entity)
        }
        if (!CollectionUtil.isEmpty(newContsList)) {
            val entity = ContEntity()
            entity.contListBeans = newContsList
            entity.itemType = ContEntity.TYPE_NEW_CONT
            contEntities.add(entity)
        }
        if (!CollectionUtil.isEmpty(hotUserList)) {
            val entity = ContEntity()
            entity.hotUserBeans = hotUserList
            entity.itemType = ContEntity.TYPE_HOT_USER
            contEntities.add(entity)
        }
        return contEntities
    }

}
