package com.example.pearvideoclient.author

import android.graphics.Color
import android.os.Bundle
import android.os.Message
import android.view.Gravity
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.example.apublic.LocalHandler
import com.example.pearvideoclient.MyApplication
import com.example.pearvideoclient.R
import com.example.pearvideoclient.author.AuthorPresenter.Companion.ALBUMS
import com.example.pearvideoclient.author.AuthorPresenter.Companion.CONT
import com.example.pearvideoclient.author.AuthorPresenter.Companion.HOME
import com.example.pearvideoclient.author.AuthorPresenter.Companion.POST
import com.example.pearvideoclient.author.fragment.UserAlbumsFragment
import com.example.pearvideoclient.author.fragment.UserContsFragment
import com.example.pearvideoclient.author.fragment.UserHomeFragment
import com.example.pearvideoclient.author.fragment.UserPostFragment
import com.example.pearvideoclient.content.ContentPresenter.FOLLOW_USER
import com.example.pearvideoclient.content.ContentPresenter.UN_FOLLOW_USER
import com.example.pearvideoclient.entity.*
import com.example.pearvideoclient.utils.GlideUtils
import com.example.pearvideoclient.utils.MyToast
import com.example.pearvideoclient.utils.StatusBarUtil
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.google.android.material.tabs.TabLayout
import java.util.*

/**
 * @Description: java类作用描述
 * @Author: jiazhu
 * @Date: 2019-08-11 22:29
 * @ClassName: AuthorActivity
 */
class AuthorActivity : AppCompatActivity(), LocalHandler.IHandler, AuthorContract.View {
    private var mIvUserBackground: ImageView? = null
    private var mIvUserImg: ImageView? = null
    private var mTvUserSignature: TextView? = null
    private var mTvAttention: TextView? = null
    private var mTlTabLayout: TabLayout? = null
    private var mVpPager: ViewPager? = null
    private var mToolbarLayout: CollapsingToolbarLayout? = null

    /**
     * userConts-视频
     * userHome-动态
     * userAlbums-专辑
     * userPost-评论
     */
    private lateinit var mFragments: MutableList<Fragment>
    private lateinit var mTitle: List<String>

    private val mLocalHandler = LocalHandler(this)

    private var mPresenter: AuthorContract.Presenter? = null
    private lateinit var userId: String
    private lateinit var mUserContsFragment: UserContsFragment
    private lateinit var mUserHomeFragment: UserHomeFragment
    private lateinit var mUserPostFragment: UserPostFragment
    private lateinit var mUserAlbumsFragment: UserAlbumsFragment
    /**
     * 关注标志
     */
    private var isAttention: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_author)

        initView()
        initData()
    }


    private fun initView() {
        //
        StatusBarUtil.setTranslucentStatus(this)

        mIvUserBackground = findViewById(R.id.iv_user_background)
        mIvUserImg = findViewById(R.id.iv_user_img)
        mTvUserSignature = findViewById(R.id.tv_user_signature)
        mTvAttention = findViewById(R.id.tv_attention)
        mTlTabLayout = findViewById(R.id.tl_tab_layout)
        mVpPager = findViewById(R.id.vp_pager)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        mToolbarLayout = findViewById(R.id.toolbar_layout)
        mToolbarLayout!!.setExpandedTitleColor(Color.WHITE)
        mToolbarLayout!!.setCollapsedTitleTextColor(Color.WHITE)
        mToolbarLayout!!.expandedTitleGravity = Gravity.CENTER
        mToolbarLayout!!.collapsedTitleGravity = Gravity.CENTER
        setSupportActionBar(toolbar)

    }

    private fun initData() {

        val intent = intent ?: return
        userId = intent.getStringExtra("userId")

        AuthorPresenter(this, mLocalHandler, userId)

        mTvAttention!!.setOnClickListener {
            mPresenter!!.toOptUserFollow(if (isAttention) UN_FOLLOW_USER else FOLLOW_USER, userId)
            isAttention = !isAttention
        }


        mTlTabLayout!!.tabMode = TabLayout.MODE_FIXED
        mTlTabLayout!!.setTabTextColors(Color.parseColor("#999999"), Color.BLACK)
        mTlTabLayout!!.setSelectedTabIndicatorColor(Color.parseColor("#FBE04C"))
        mTlTabLayout!!.setupWithViewPager(mVpPager)
        mTlTabLayout!!.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                mVpPager!!.currentItem = tab.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {
                //
            }

            override fun onTabReselected(tab: TabLayout.Tab) {
                //
            }
        })

        mPresenter!!.subscribe()
    }

    override fun onDestroy() {
        if (mPresenter != null) {
            mPresenter!!.unsubscribe()
            mPresenter = null
        }

        super.onDestroy()
    }

    private fun initViewPager() {
        val mFixPagerAdapter = FixPagerAdapter(supportFragmentManager, mFragments)
        mFixPagerAdapter.setTitles(mTitle)
        mVpPager!!.adapter = mFixPagerAdapter
        mVpPager!!.offscreenPageLimit = mFragments.size
        mVpPager!!.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(i: Int, v: Float, i1: Int) {
                //
            }

            override fun onPageSelected(i: Int) {
                val maxTabSize = 4
                when (i) {
                    0 -> mPresenter!!.loadUserHomeInfo(userId)
                    1 -> mPresenter!!.loadUserContsInfo(userId)
                    2 -> if (mFragments.size == maxTabSize) {
                        mPresenter!!.loadUserAlbumsInfo(userId)
                    } else {
                        mPresenter!!.loadUserPostsInfo(userId)
                    }
                    3 -> mPresenter!!.loadUserPostsInfo(userId)
                    else -> {
                    }
                }
            }

            override fun onPageScrollStateChanged(i: Int) {
                //
            }
        })
        mVpPager!!.currentItem = 1
    }

    override fun handlerMessage(msg: Message) {
        if (msg.what == MSG_INIT_VIEWPAGER) {
            initViewPager()
        }
    }

    override fun setFragments(title: List<String>) {
        mFragments = ArrayList()
        mTitle = title
        for (s in mTitle) {
            when (s) {
                "视频" -> {
                    mUserContsFragment = UserContsFragment.newInstance()
                    mFragments.add(mUserContsFragment)
                }
                "动态" -> {
                    mUserHomeFragment = UserHomeFragment.newInstance()
                    mFragments.add(mUserHomeFragment)
                }
                "评论" -> {
                    mUserPostFragment = UserPostFragment.newInstance()
                    mFragments.add(mUserPostFragment)
                }
                "专辑" -> {
                    mUserAlbumsFragment = UserAlbumsFragment.newInstance()
                    mFragments.add(mUserAlbumsFragment)
                }
                else -> {
                }
            }
        }
    }

    override fun setAuthorTitle(infoBean: InfoBean) {
        mToolbarLayout!!.title = infoBean.nickname
        mTvUserSignature!!.text = infoBean.signature
        GlideUtils.load(infoBean.backgroundImg, mIvUserBackground!!)
        mIvUserBackground!!.imageAlpha = 150
        GlideUtils.loadCircleImage(infoBean.pic, mIvUserImg!!)
    }

    override fun setUserHomeData(dataList: List<AuthorHomeBean.DataListBean>) {
        mUserHomeFragment.loadDataList(dataList)
    }

    override fun loadMoreUserHomeData(dataList: List<AuthorHomeBean.DataListBean>) {
        mUserHomeFragment.loadMoreDataList(dataList)
    }

    override fun loadMoreFinish(@AuthorPresenter.PageType type: String, isSuccess: Boolean) {
        when (type) {
            HOME -> mUserHomeFragment.loadMoreFinish(isSuccess)
            ALBUMS -> mUserAlbumsFragment.loadMoreFinish(isSuccess)
            CONT -> mUserContsFragment.loadMoreFinish(isSuccess)
            POST -> mUserPostFragment.loadMoreFinish(isSuccess)
            else -> {
            }
        }
    }

    override fun loadRefreshFinish(@AuthorPresenter.PageType type: String, isSuccess: Boolean) {
        when (type) {
            HOME -> mUserHomeFragment.loadRefreshFinish(isSuccess)
            ALBUMS -> mUserAlbumsFragment.loadRefreshFinish(isSuccess)
            CONT -> mUserContsFragment.loadRefreshFinish(isSuccess)
            POST -> mUserPostFragment.loadRefreshFinish(isSuccess)
            else -> {
            }
        }
    }

    override fun setHotConts(hotList: List<UserConts.ContListBean>) {
        mUserContsFragment.loadHotConts(hotList)
    }

    override fun setNewConts(contList: List<UserConts.ContListBean>) {
        mUserContsFragment.loadNewConts(contList)
    }

    override fun loadMoreNewConts(contList: List<UserConts.ContListBean>) {
        mUserContsFragment.loadMoreNewConts(contList)
    }

    override fun setAlbumsList(albumList: List<UserAlbumsBean.AlbumListBean>) {
        mUserAlbumsFragment.loadAlbumsList(albumList)
    }

    override fun loadMoreUserAlbums(albumList: List<UserAlbumsBean.AlbumListBean>) {
        mUserAlbumsFragment.loadMoreAlbumsList(albumList)
    }

    override fun setPostsList(postsList: List<UserPostsBean.PostListBean>) {
        mUserPostFragment.loadPostsList(postsList)
    }

    override fun loadMorePostsList(postsList: List<UserPostsBean.PostListBean>) {
        mUserPostFragment.loadMorePostsList(postsList)
    }

    override fun toggleAttention() {
        mTvAttention!!.text = if (isAttention) "已关注" else "关注"
        mTvAttention!!.background = if (isAttention)
            getDrawable(R.drawable.bg_round_f2)
        else
            getDrawable(R.drawable.bg_round_50_yellow)
    }

    override fun setPresenter(presenter: AuthorContract.Presenter) {
        this.mPresenter = presenter
    }

    override fun showLoading() {
        //
    }

    override fun cancelLoading() {
        //
    }

    override fun showErrorToast(loadingFail: String) {
        MyToast.getInstance(MyApplication.instance).show(loadingFail, 3000)
    }

    fun userHomeRefresh() {
        mPresenter!!.refreshUserHomeList()
    }

    fun userHomeLoadMore() {
        mPresenter!!.loadMoreUserHomeList()
    }

    fun userContsRefresh() {
        mPresenter!!.refreshUserContsList()
    }

    fun userContsLoadMore() {
        mPresenter!!.loadMoreUserContsList()
    }

    fun userAlbumsLoadMore() {
        mPresenter!!.loadMoreUserAlbumsList()
    }

    fun userAlbumsRefresh() {
        mPresenter!!.refreshUserAlbumsList()
    }

    fun userPostsRefresh() {
        mPresenter!!.refreshUserPostsList()
    }

    fun userPostsLoadMore() {
        mPresenter!!.loadMoreUserPostsList()
    }

    companion object {
        const val MSG_INIT_VIEWPAGER = 1
    }
}
