package com.example.pearvideoclient.home

import androidx.annotation.StringDef
import com.example.pearvideoclient.Api
import com.example.pearvideoclient.Constants
import com.example.pearvideoclient.entity.*
import com.example.pearvideoclient.entity.NewsEntity.TYPE_BIG
import com.example.pearvideoclient.entity.NewsEntity.TYPE_SMALL
import com.example.pearvideoclient.http.RetrofitManager
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.util.*

/**
 * @Description: java类作用描述
 * @Author: jiazhu
 * @Date: 2019-08-21 14:42
 * @ClassName: HomePresenter
 */
class HomePresenter internal constructor(private val mView: HomeContract.View) : HomeContract.Presenter {
    private val mCompositeDisposable: CompositeDisposable
    private var mFilterIds: String? = null

    private var newsStart = 0
    private var recommendStart = 0
    private var cityStart = 0
    private var mCity: CityListBean.ChannelBean? = null
    private var mCityList: CityListBean? = null


    init {
        mView.setPresenter(this)
        mCompositeDisposable = CompositeDisposable()
    }

    override fun subscribe() {
        mCompositeDisposable.clear()
    }

    override fun unsubscribe() {
        mCompositeDisposable.dispose()
    }

    override fun loadVientianeList() {
        val disposable = loadNewsList(Constants.COMMON)
        mCompositeDisposable.add(disposable)
    }

    private fun loadNewsList(@Constants.LoadType loadType: Int): Disposable {
        return RetrofitManager.getInstance().createReq(Api::class.java)
                .newsList
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map<List<NewsEntity>> { this.convertNewsData(it) }
                .subscribe({ newsEntities -> mView.showVientianeList(newsEntities) },
                        {
                            if (Constants.LOAD_REFRESH == loadType) {
                                mView.refreshFinish(false, NEWS)
                            }
                        }, {
                    if (Constants.LOAD_REFRESH == loadType) {
                        mView.refreshFinish(false, NEWS)
                    }
                })
    }

    private fun convertNewsData(newsBean: NewsBean): List<NewsEntity> {
        val nextUrl = newsBean.nextUrl
        mFilterIds = nextUrl.substring(nextUrl.indexOf("filterIds=") + 9, nextUrl.indexOf('&'))
        val dataList = newsBean.dataList
        val newsEntities = ArrayList<NewsEntity>()
        for (i in dataList.indices) {
            val entity = NewsEntity()
            entity.dataEntity = dataList[i]
            if ("1" == dataList[i].newsInfo.cardType) {
                entity.itemType = TYPE_BIG
            } else {
                entity.itemType = TYPE_SMALL
            }

            newsEntities.add(entity)
        }
        return newsEntities
    }

    override fun refreshVientianeList() {
        newsStart = 0
        val disposable = loadNewsList(Constants.LOAD_REFRESH)
        mCompositeDisposable.add(disposable)
    }

    override fun loadMoreVientianeList() {
        newsStart += 10
        val disposable = loadVientianeList(mFilterIds, newsStart, newsStart - 4)
        mCompositeDisposable.add(disposable)
    }

    override fun loadRecommendList() {
        val disposable = loadRecommendList(Constants.COMMON)
        mCompositeDisposable.add(disposable)
    }

    override fun refreshRecommendList() {
        recommendStart = 0
        val disposable = loadRecommendList(Constants.LOAD_REFRESH)
        mCompositeDisposable.add(disposable)
    }

    override fun loadMoreRecommendList() {
        recommendStart += 10
        val disposable = RetrofitManager.getInstance().createReq(Api::class.java)
                .getHome(1, "320100", recommendStart)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map<List<RecommendEntity>> { this.convertRecommendData(it) }
                .subscribe({ recommendEntities -> mView.loadMoreRecommendList(recommendEntities) },
                        { mView.loadMoreFinish(false, RECOMMEND) },
                        { mView.loadMoreFinish(true, RECOMMEND) })
        mCompositeDisposable.add(disposable)
    }

    override fun refreshCityContsList(currentCity: CityListBean.ChannelBean) {
        mCity = currentCity
        cityStart = 0
        val disposable = loadCityContsList(Constants.LOAD_REFRESH)
        mCompositeDisposable.add(disposable)
    }

    override fun loadMoreCityContsList() {
        cityStart += 10
        val disposable = RetrofitManager.getInstance().createReq(Api::class.java)
                .getLocalChannelConts(mCity!!.channelCode, cityStart)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map<List<LocalContEntity>> { this.convertCityConts(it) }
                .subscribe({ localContEntities -> mView.loadMoreCityContsList(localContEntities) },
                        { mView.loadMoreFinish(false, CITY) },
                        { mView.loadMoreFinish(true, CITY) })
        mCompositeDisposable.add(disposable)
    }

    override fun loadCityContsList(currentCity: CityListBean.ChannelBean) {
        mCity = currentCity
        val disposable = loadCityContsList(Constants.COMMON)
        mCompositeDisposable.add(disposable)
    }

    override fun loadLocalChannel() {
        if (mCityList != null) {
            mView.toCityList(mCityList!!)
            return
        }
        val disposable = RetrofitManager.getInstance().createReq(Api::class.java)
                .localChannels()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ cityListBean ->
                    mCityList = cityListBean
                    mView.toCityList(mCityList!!)
                }, { mView.showErrorToast("loading Fail") })
        mCompositeDisposable.add(disposable)
    }

    private fun loadCityContsList(@Constants.LoadType loadType: Int): Disposable {
        return RetrofitManager.getInstance().createReq(Api::class.java)
                .getLocalChannelConts(mCity!!.channelCode)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map<List<LocalContEntity>> { this.convertCityConts(it) }
                .subscribe({ localContEntities -> mView.showCityContsList(localContEntities) },
                        {
                            if (Constants.LOAD_REFRESH == loadType) {
                                mView.refreshFinish(false, CITY)
                            }
                        },
                        {
                            if (Constants.LOAD_REFRESH == loadType) {
                                mView.refreshFinish(true, CITY)
                            }
                        })
    }

    private fun convertCityConts(localContsBean: LocalContsBean): List<LocalContEntity> {
        val dataList = localContsBean.dataList
        val contEntities = ArrayList<LocalContEntity>()
        for (dataListBean in dataList) {
            val nodeType: Int
            try {
                nodeType = Integer.parseInt(dataListBean.nodeType)
                if (LocalContEntity.ITEM_TYPE_13 == nodeType || LocalContEntity.ITEM_TYPE_17 == nodeType) {
                    val entity = LocalContEntity()
                    entity.itemType = nodeType
                    entity.cont = dataListBean
                    contEntities.add(entity)
                }
            } catch (e: NumberFormatException) {
                mView.showErrorToast(e.message)
            }

        }
        return contEntities
    }


    private fun loadRecommendList(@Constants.LoadType loadType: Int): Disposable {
        return RetrofitManager.getInstance().createReq(Api::class.java)
                .getHome(1, "320100")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map<List<RecommendEntity>> { this.convertRecommendData(it) }
                .subscribe({ recommendEntities -> mView.showRecommendList(recommendEntities) },
                        {
                            if (Constants.LOAD_REFRESH == loadType) {
                                mView.refreshFinish(false, RECOMMEND)
                            }
                        }, { mView.refreshFinish(true, RECOMMEND) })
    }

    private fun convertRecommendData(recommendBean: RecommendBean): List<RecommendEntity> {
        val recommendEntities = ArrayList<RecommendEntity>()
        val dataList = recommendBean.dataList
        for (dataListBean in dataList) {
            val entity = RecommendEntity()
            entity.dataBean = dataListBean
            val nodeType: Int
            try {
                nodeType = Integer.valueOf(dataListBean.nodeType)
                if (RecommendEntity.NODE_TYPE_1 == nodeType ||
                        RecommendEntity.NODE_TYPE_13 == nodeType ||
                        RecommendEntity.NODE_TYPE_4 == nodeType) {
                    entity.itemType = nodeType
                    recommendEntities.add(entity)
                }
            } catch (e: NumberFormatException) {
                mView.showErrorToast(e.message)
            }

        }
        return recommendEntities
    }

    private fun loadVientianeList(filterIds: String?, start: Int, pstart: Int): Disposable {
        return RetrofitManager.getInstance().createReq(Api::class.java)
                .getNewsList(filterIds!!, start, pstart)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map<List<NewsEntity>> { this.convertNewsData(it) }
                .subscribe({ newsEntities -> mView.loadMoreVientianeList(newsEntities) },
                        { mView.loadMoreFinish(false, NEWS) },
                        { mView.loadMoreFinish(true, NEWS) })
    }

    @kotlin.annotation.Retention(AnnotationRetention.SOURCE)
    @StringDef(value = [NEWS, RECOMMEND, CITY])
    internal annotation class PageType

    companion object {

        const val NEWS = "万象"
        const val RECOMMEND = "推荐"
        const val CITY = "城市"
    }


}
