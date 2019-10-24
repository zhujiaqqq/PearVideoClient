package com.example.pearvideoclient.author

import com.example.pearvideoclient.Api
import com.example.pearvideoclient.Constants
import com.example.pearvideoclient.http.RetrofitManager
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

/**
 * @Description: java类作用描述
 * @Author: jiazhu
 * @Date: 2019-08-28 19:34
 * @ClassName: AlbumPresenter
 */
class AlbumPresenter internal constructor(private val mView: AlbumContract.View) : AlbumContract.Presenter {
    private var start: Int = 0
    private var mAlbumId: String? = null
    private val mCompositeDisposable: CompositeDisposable

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

    override fun loadAlbumConts(albumId: String) {
        mAlbumId = albumId
        start = 0
        val disposable = loadAlbumConts(mAlbumId, start, Constants.COMMON)
        mCompositeDisposable.add(disposable)
    }

    override fun loadMoreAlbumConts() {
        start += 10
        val disposable = loadAlbumConts(mAlbumId, start, Constants.LOAD_MORE)
        mCompositeDisposable.add(disposable)
    }

    override fun refreshAlbumConts() {
        start = 0
        val disposable = loadAlbumConts(mAlbumId, start, Constants.LOAD_REFRESH)
        mCompositeDisposable.add(disposable)
    }

    /**
     * 加载专辑列表
     *
     * @param albumId  专辑ID
     * @param start    索引
     * @param loadType 加载类型
     * @return disposable
     */
    private fun loadAlbumConts(albumId: String?, start: Int, @Constants.LoadType loadType: Int): Disposable {
        return RetrofitManager.getInstance().createReq(Api::class.java)
                .getAlbumConts(albumId, start)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ albumContBean ->
                    val contList = albumContBean.contList
                    when (loadType) {
                        Constants.COMMON -> {
                            val albumInfo = albumContBean.albumInfo
                            mView.setTitle(albumInfo.name)
                            mView.setAlbumCont(contList)
                        }
                        Constants.LOAD_REFRESH -> mView.setAlbumCont(contList)
                        Constants.LOAD_MORE -> mView.addAlbumCont(contList)
                    }
                }, {
                    if (Constants.LOAD_MORE == loadType) {
                        mView.finishLoad(false)
                    } else if (Constants.LOAD_REFRESH == loadType) {
                        mView.finishRefresh(false)
                    }
                }, {
                    if (Constants.LOAD_MORE == loadType) {
                        mView.finishLoad(true)
                    } else if (Constants.LOAD_REFRESH == loadType) {
                        mView.finishRefresh(true)
                    }
                })
    }
}
