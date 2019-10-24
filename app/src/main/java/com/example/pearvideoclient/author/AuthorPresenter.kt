package com.example.pearvideoclient.author

import androidx.annotation.StringDef
import com.example.apublic.LocalHandler
import com.example.pearvideoclient.Api
import com.example.pearvideoclient.Constants
import com.example.pearvideoclient.Constants.SUCCESS
import com.example.pearvideoclient.author.AuthorActivity.Companion.MSG_INIT_VIEWPAGER
import com.example.pearvideoclient.entity.UserConts
import com.example.pearvideoclient.entity.UserInfoBean
import com.example.pearvideoclient.http.RetrofitManager
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.util.*

/**
 * @Description: java类作用描述
 * @Author: jiazhu
 * @Date: 2019-08-13 14:10
 * @ClassName: AuthorPresenter
 */
class AuthorPresenter internal constructor(private val mView: AuthorContract.View, private val mHandler: LocalHandler, private val userId: String) : AuthorContract.Presenter {

    private val mCompositeDisposable = CompositeDisposable()
    private lateinit var userHomeStart: String
    private lateinit var userContStart: String
    private lateinit var userAlbumsStart: String
    private lateinit var userPostsStart: String
    private lateinit var mPostsNextUrl: String


    init {
        mView.setPresenter(this)
    }

    override fun loadAuthorInfo(authorId: String) {

        val disposable = RetrofitManager.getInstance().createReq(Api::class.java)
                .getUserInfo(authorId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { userInfoBean ->
                    setUserInfo(userInfoBean)
                    setFragments(userInfoBean)
                }
        mCompositeDisposable.add(disposable)
    }

    override fun loadUserHomeInfo(authorId: String) {
        userHomeStart = "0"
        val disposable = loadUserHomeInfo(userHomeStart, userId, Constants.COMMON)
        mCompositeDisposable.add(disposable)
    }

    override fun refreshUserHomeList() {
        userHomeStart = "0"
        val disposable = loadUserHomeInfo(userHomeStart, userId, Constants.LOAD_REFRESH)
        mCompositeDisposable.add(disposable)
    }

    override fun loadMoreUserHomeList() {
        userHomeStart = (Integer.valueOf(userHomeStart) + 10).toString()
        val disposable = loadUserHomeInfo(userHomeStart, userId, Constants.LOAD_MORE)
        mCompositeDisposable.add(disposable)
    }

    override fun loadUserContsInfo(authorId: String) {
        userContStart = "0"
        val disposable = loadUserContsInfo(userContStart, authorId, Constants.COMMON)
        mCompositeDisposable.add(disposable)
    }

    override fun refreshUserContsList() {
        userContStart = "0"
        val disposable = loadUserContsInfo(userContStart, userId, Constants.LOAD_REFRESH)
        mCompositeDisposable.add(disposable)
    }

    override fun loadMoreUserContsList() {
        userContStart = (Integer.valueOf(userContStart) + 10).toString()
        val disposable = loadUserContsInfo(userContStart, userId, Constants.LOAD_MORE)
        mCompositeDisposable.add(disposable)
    }

    override fun loadUserAlbumsInfo(authorId: String) {
        userAlbumsStart = "0"
        val disposable = loadUserAlbumsInfo(userAlbumsStart, authorId, Constants.COMMON)
        mCompositeDisposable.add(disposable)
    }

    override fun refreshUserAlbumsList() {
        userAlbumsStart = "0"
        val disposable = loadUserAlbumsInfo(userAlbumsStart, userId, Constants.LOAD_REFRESH)
        mCompositeDisposable.add(disposable)
    }

    override fun loadMoreUserAlbumsList() {
        userAlbumsStart = (Integer.valueOf(userAlbumsStart) + 10).toString()
        val disposable = loadUserAlbumsInfo(userAlbumsStart, userId, Constants.LOAD_MORE)
        mCompositeDisposable.add(disposable)
    }

    override fun loadUserPostsInfo(authorId: String) {
        userPostsStart = "0"
        val disposable = loadUserPostsInfo(userPostsStart, authorId, null, Constants.COMMON)
        mCompositeDisposable.add(disposable)
    }

    override fun refreshUserPostsList() {
        userPostsStart = "0"
        val disposable = loadUserPostsInfo(userPostsStart, userId, null, Constants.LOAD_REFRESH)
        mCompositeDisposable.add(disposable)
    }

    override fun loadMoreUserPostsList() {
        userPostsStart = (Integer.valueOf(userPostsStart) + 10).toString()
        val score = mPostsNextUrl.substring(mPostsNextUrl.indexOf("score=") + 6)
        val disposable = loadUserPostsInfo(userPostsStart, userId, score, Constants.LOAD_MORE)
        mCompositeDisposable.add(disposable)
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

    /**
     * 加载动态fragment数据
     *
     * @param start    索引
     * @param authorId id
     * @return disposable
     */
    private fun loadUserHomeInfo(start: String, authorId: String, @Constants.LoadType loadType: Int): Disposable {
        return RetrofitManager.getInstance().createReq(Api::class.java)
                .getUserHome(start, authorId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ authorHomeBean ->
                    val dataList = authorHomeBean.dataList
                    if (loadType == Constants.LOAD_REFRESH || loadType == Constants.COMMON) {
                        mView.setUserHomeData(dataList)
                    } else if (loadType == Constants.LOAD_MORE) {
                        mView.loadMoreUserHomeData(dataList)
                    }
                }, {
                    if (loadType == Constants.LOAD_MORE) {
                        mView.loadMoreFinish(HOME, false)
                    } else if (loadType == Constants.LOAD_REFRESH) {
                        mView.loadRefreshFinish(HOME, false)
                    }
                }, {
                    if (loadType == Constants.LOAD_MORE) {
                        mView.loadMoreFinish(HOME, true)
                    } else if (loadType == Constants.LOAD_REFRESH) {
                        mView.loadRefreshFinish(HOME, true)
                    }
                })

    }

    /**
     * 加载视频fragment数据
     *
     * @param start    索引
     * @param authorId id
     * @param loadType 加载类型
     * @return disposable
     */
    private fun loadUserContsInfo(start: String, authorId: String, @Constants.LoadType loadType: Int): Disposable {
        return RetrofitManager.getInstance().createReq(Api::class.java)
                .getUserConts(start, authorId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ userConts ->
                    when (loadType) {
                        Constants.COMMON -> {
                            setHotConts(userConts)
                            setNewConts(userConts)
                        }
                        Constants.LOAD_REFRESH -> setNewConts(userConts)
                        Constants.LOAD_MORE -> loadMoreNewConts(userConts)
                    }
                }, {
                    if (loadType == Constants.LOAD_MORE) {
                        mView.loadMoreFinish(CONT, false)
                    } else if (loadType == Constants.LOAD_REFRESH) {
                        mView.loadRefreshFinish(CONT, false)
                    }
                }, {
                    if (loadType == Constants.LOAD_MORE) {
                        mView.loadMoreFinish(CONT, true)
                    } else if (loadType == Constants.LOAD_REFRESH) {
                        mView.loadRefreshFinish(CONT, true)
                    }
                })
    }

    /**
     * 加载专辑fragment数据
     *
     * @param start    索引
     * @param authorId ID
     * @param loadType 加载类型
     * @return disposable
     */
    private fun loadUserAlbumsInfo(start: String, authorId: String, @Constants.LoadType loadType: Int): Disposable {
        return RetrofitManager.getInstance().createReq(Api::class.java)
                .getUserAlbums(start, authorId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ userAlbumsBean ->
                    val albumList = userAlbumsBean.albumList

                    if (loadType == Constants.COMMON || loadType == Constants.LOAD_REFRESH) {
                        mView.setAlbumsList(albumList)
                    } else if (loadType == Constants.LOAD_MORE) {
                        mView.loadMoreUserAlbums(albumList)
                    }
                }, {
                    if (loadType == Constants.LOAD_MORE) {
                        mView.loadMoreFinish(ALBUMS, false)
                    } else if (loadType == Constants.LOAD_REFRESH) {
                        mView.loadRefreshFinish(ALBUMS, false)
                    }
                }, {
                    if (loadType == Constants.LOAD_MORE) {
                        mView.loadMoreFinish(ALBUMS, true)
                    } else if (loadType == Constants.LOAD_REFRESH) {
                        mView.loadRefreshFinish(ALBUMS, true)
                    }
                })
    }

    private fun loadUserPostsInfo(start: String,
                                  userId: String,
                                  score: String?,
                                  @Constants.LoadType loadType: Int): Disposable {
        return RetrofitManager.getInstance().createReq(Api::class.java)
                .getUserPosts(start, userId, score)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ userPostsBean ->
                    mPostsNextUrl = userPostsBean.nextUrl
                    val postList = userPostsBean.postList

                    if (loadType == Constants.COMMON || loadType == Constants.LOAD_REFRESH) {
                        mView.setPostsList(postList)
                    } else if (loadType == Constants.LOAD_MORE) {
                        mView.loadMorePostsList(postList)
                    }
                }, {
                    if (loadType == Constants.LOAD_REFRESH) {
                        mView.loadRefreshFinish(POST, false)
                    } else if (loadType == Constants.LOAD_MORE) {
                        mView.loadMoreFinish(POST, false)
                    }
                }, {
                    if (loadType == Constants.LOAD_REFRESH) {
                        mView.loadRefreshFinish(POST, true)
                    } else if (loadType == Constants.LOAD_MORE) {
                        mView.loadMoreFinish(POST, true)
                    }
                })
    }

    private fun loadMoreNewConts(userConts: UserConts) {
        val contList = userConts.contList
        mView.loadMoreNewConts(contList)
    }

    private fun setNewConts(userConts: UserConts) {
        val contList = userConts.contList
        mView.setNewConts(contList)
    }

    private fun setHotConts(userConts: UserConts) {
        val hotList = userConts.hotList
        mView.setHotConts(hotList)
    }

    private fun setUserInfo(userInfoBean: UserInfoBean) {
        val userInfo = userInfoBean.userInfo
        mView.setAuthorTitle(userInfo)
    }

    private fun setFragments(userInfoBean: UserInfoBean) {
        val titles = ArrayList<String>()
        titles.add("动态")
        titles.add("视频")
        if (HAS_ALBUM == userInfoBean.userInfo.hasAlbum) {
            titles.add("专辑")
        }
        titles.add("评论")

        mView.setFragments(titles)
        mHandler.sendEmptyMessage(MSG_INIT_VIEWPAGER)
    }

    override fun subscribe() {
        mCompositeDisposable.clear()
        loadAuthorInfo(userId)
    }

    override fun unsubscribe() {
        mCompositeDisposable.clear()
    }


    @kotlin.annotation.Retention(AnnotationRetention.SOURCE)
    @StringDef(value = [HOME, CONT, ALBUMS, POST])
    internal annotation class PageType

    companion object {
        const val HAS_ALBUM = "1"

        const val HOME = "动态"
        const val CONT = "视频"
        const val ALBUMS = "专辑"
        const val POST = "评论"
    }

}
