package com.example.pearvideoclient.home

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import com.google.android.material.tabs.TabLayout
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import androidx.appcompat.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.apublic.CommonCallBack

import com.example.pearvideoclient.Constants
import com.example.pearvideoclient.MyApplication
import com.example.pearvideoclient.R
import com.example.pearvideoclient.author.FixPagerAdapter
import com.example.pearvideoclient.entity.CityListBean
import com.example.pearvideoclient.entity.LocalContEntity
import com.example.pearvideoclient.entity.NewsEntity
import com.example.pearvideoclient.entity.RecommendEntity
import com.example.pearvideoclient.home.HomePresenter.Companion.CITY
import com.example.pearvideoclient.home.HomePresenter.Companion.NEWS
import com.example.pearvideoclient.home.HomePresenter.Companion.RECOMMEND
import com.example.pearvideoclient.home.fragment.CityFragment
import com.example.pearvideoclient.home.fragment.RecommendFragment
import com.example.pearvideoclient.home.fragment.VientianeFragment
import com.example.pearvideoclient.utils.MyToast

import java.util.ArrayList

/**
 * @author zhujiaqqq
 * @date 2019-07-11
 */
class HomeFragment : Fragment(), HomeContract.View {
    private var mTabLayout: TabLayout? = null
    private var mViewPager: ViewPager? = null

    /**
     * 万象 VientianeFragment
     * 推荐 RecommendFragment
     * 城市 CityFragment
     */
    private var mFragments: MutableList<Fragment>? = null
    private var mTitle: MutableList<String>? = null

    private var mActivity: AppCompatActivity? = null

    private var mPresenter: HomeContract.Presenter? = null
    private var mVientianeFragment: VientianeFragment? = null
    private var mRecommendFragment: RecommendFragment? = null
    private var mCityFragment: CityFragment? = null

    private var mCurrentCity: CityListBean.ChannelBean? = null

    private val defaultCity = CityListBean.ChannelBean(
            "320100",
            "南京",
            "nanjing",
            "南京·生活圈",
            "1",
            "0")

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView(view)
        initData()
    }

    private fun initView(view: View) {
        mTabLayout = view.findViewById(R.id.tl_tab_layout)
        mViewPager = view.findViewById(R.id.vp_pager)
    }

    private fun initData() {
        mCurrentCity = defaultCity
        HomePresenter(this)
        mActivity = activity as AppCompatActivity?
        mPresenter!!.subscribe()
        initTabLayout()
        initViewPager()
    }

    private fun initTabLayout() {
        mTabLayout!!.tabMode = TabLayout.MODE_FIXED
        mTabLayout!!.setTabTextColors(Color.parseColor("#999999"), Color.BLACK)
        mTabLayout!!.setSelectedTabIndicatorColor(Color.parseColor("#FBE04C"))
        mTabLayout!!.setupWithViewPager(mViewPager)
        mTabLayout!!.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                mViewPager!!.currentItem = tab.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {
                //
            }

            override fun onTabReselected(tab: TabLayout.Tab) {
                //
            }
        })
    }

    private fun initViewPager() {
        createFragment()

        val fixPagerAdapter = FixPagerAdapter(childFragmentManager, mFragments)
        fixPagerAdapter.setTitles(mTitle!!)
        mViewPager!!.adapter = fixPagerAdapter
        mViewPager!!.offscreenPageLimit = mFragments!!.size
        mViewPager!!.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(i: Int, v: Float, i1: Int) {
                //
            }

            override fun onPageSelected(i: Int) {
                when (i) {
                    0 -> mPresenter!!.loadVientianeList()
                    1 -> mPresenter!!.loadRecommendList()
                    2 -> mPresenter!!.loadCityContsList(mCurrentCity!!)
                    else -> {
                    }
                }
            }

            override fun onPageScrollStateChanged(i: Int) {
                //
            }
        })
        mViewPager!!.currentItem = 1
    }

    /**
     * create fragment list
     */
    private fun createFragment() {
        if (mTitle == null) {
            mTitle = ArrayList()
        } else {
            mTitle!!.clear()
        }
        mTitle!!.add("万象")
        mTitle!!.add("推荐")
        mTitle!!.add(mCurrentCity!!.name)

        if (mFragments == null) {
            mFragments = ArrayList()
        } else {
            mFragments!!.clear()
        }
        mVientianeFragment = VientianeFragment.newInstance()
        mVientianeFragment!!.setRefreshCallBack(CommonCallBack {
            integer ->
            if (integer == Constants.LOAD_REFRESH) {
                mPresenter!!.refreshVientianeList()
            } else if (integer == Constants.LOAD_MORE) {
                mPresenter!!.loadMoreVientianeList()
            }
        })

        mFragments!!.add(mVientianeFragment!!)

        mRecommendFragment = RecommendFragment.newInstance()
        mRecommendFragment!!.setRefreshCallBack(CommonCallBack {
            integer ->
            if (integer == Constants.LOAD_REFRESH) {
                mPresenter!!.refreshRecommendList()
            } else if (integer == Constants.LOAD_MORE) {
                mPresenter!!.loadMoreRecommendList()
            }
        })

        mFragments!!.add(mRecommendFragment!!)

        mCityFragment = CityFragment.newInstance()
        mCityFragment!!.setCityFragmentCallBack(CommonCallBack {
            integer ->
            when (integer) {
                Constants.LOAD_REFRESH -> mPresenter!!.refreshCityContsList(mCurrentCity!!)
                Constants.LOAD_MORE -> mPresenter!!.loadMoreCityContsList()
                Constants.HEADER_CLICK -> mPresenter!!.loadLocalChannel()
            }
        })
        mFragments!!.add(mCityFragment!!)
    }

    override fun setPresenter(presenter: HomeContract.Presenter) {
        mPresenter = presenter
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

    override fun showVientianeList(data: List<NewsEntity>) {
        mVientianeFragment!!.setVientianeList(data)
    }

    override fun loadMoreVientianeList(data: List<NewsEntity>) {
        mVientianeFragment!!.loadMoreVientianeList(data)
    }

    override fun showRecommendList(data: List<RecommendEntity>) {
        mRecommendFragment!!.replaceData(data)
    }

    override fun loadMoreRecommendList(data: List<RecommendEntity>) {
        mRecommendFragment!!.addData(data)
    }

    override fun showCityContsList(data: List<LocalContEntity>) {
        mCityFragment!!.replaceData(data)
    }

    override fun loadMoreCityContsList(data: List<LocalContEntity>) {
        mCityFragment!!.addData(data)
    }

    override fun loadMoreFinish(isSuccess: Boolean, pageType: String) {
        when (pageType) {
            NEWS -> mVientianeFragment!!.loadMoreFinish(isSuccess)
            RECOMMEND -> mRecommendFragment!!.loadMoreFinish(isSuccess)
            CITY -> mCityFragment!!.loadMoreFinish(isSuccess)
            else -> {
            }
        }
    }

    override fun refreshFinish(isSuccess: Boolean, pageType: String) {
        when (pageType) {
            NEWS -> mVientianeFragment!!.refreshFinish(isSuccess)
            RECOMMEND -> mRecommendFragment!!.refreshFinish(isSuccess)
            CITY -> mCityFragment!!.refreshFinish(isSuccess)
            else -> {
            }
        }
    }

    override fun toCityList(cityListBean: CityListBean) {
        val intent = Intent(mActivity, CityListActivity::class.java)
        intent.putExtra("cityList", cityListBean)
        intent.putExtra("currentCity", mCurrentCity!!.name)
        startActivityForResult(intent, REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (REQUEST_CODE == requestCode && RESULT_CODE == resultCode) {
            mCurrentCity = data!!.getSerializableExtra("city") as CityListBean.ChannelBean
            mPresenter!!.loadCityContsList(mCurrentCity!!)
            val cityTab = mTabLayout!!.getTabAt(2)
            cityTab!!.text = mCurrentCity!!.name
        }
        super.onActivityResult(requestCode, resultCode, data)

    }

    companion object {
        const val REQUEST_CODE = 1
        const val RESULT_CODE = 2
        @JvmStatic
        fun newInstance(): HomeFragment {
            val bundle = Bundle()
            val homeFragment = HomeFragment()
            homeFragment.arguments = bundle
            return homeFragment
        }
    }
}
