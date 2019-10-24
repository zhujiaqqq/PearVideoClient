package com.example.pearvideoclient.channel

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager.widget.ViewPager
import com.example.apublic.CommonCallBack
import com.example.pearvideoclient.Constants
import com.example.pearvideoclient.MyApplication
import com.example.pearvideoclient.R
import com.example.pearvideoclient.author.FixPagerAdapter
import com.example.pearvideoclient.entity.CategoryBean
import com.example.pearvideoclient.entity.ContEntity
import com.example.pearvideoclient.utils.MyToast
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.fragment_channel.*

/**
 * @Description: java类作用描述
 * @Author: jiazhu
 * @Date: 2019-07-11 21:24
 * @ClassName: ChannelFragment
 */
class ChannelFragment : Fragment(), ChannelContract.View {

    private var mPresenter: ChannelContract.Presenter? = null

    private var mContext: FragmentActivity? = null

    private var mFragments = ArrayList<CategoryFragment>()

    private var mTitles = ArrayList<String>()

    private var currentCategoryList: ArrayList<CategoryBean.CategoryListBean>? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_channel, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initData()
    }


    private fun initData() {
        mContext = activity
        iv_sort.setOnClickListener {
            val intent = Intent(mContext, ReSortCategoryActivity::class.java)
            intent.putParcelableArrayListExtra("categoryList", currentCategoryList)
            startActivityForResult(intent, 1)
        }

        initTabLayout()
        mPresenter!!.subscribe()
    }

    private fun initTabLayout() {
        tl_category_list.tabMode = TabLayout.MODE_SCROLLABLE
        tl_category_list.setTabTextColors(Color.parseColor("#999999"), Color.BLACK)
        tl_category_list.setSelectedTabIndicatorColor(Color.parseColor("#FBE04C"))
        tl_category_list.setupWithViewPager(vp_pager)
        tl_category_list.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                vp_pager.currentItem = tab.position
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
        createFragmentsAndTitles()

        val fixPagerAdapter = FixPagerAdapter(childFragmentManager, mFragments)
        fixPagerAdapter.setTitles(mTitles)
        vp_pager.adapter = fixPagerAdapter
        vp_pager.offscreenPageLimit = mFragments.size
        vp_pager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
                //
            }

            override fun onPageSelected(position: Int) {
                if (!mFragments[position].hasData()) {
                    mPresenter!!.loadCategoryConts(1, currentCategoryList!![position].categoryId, 0)
                }
            }

            override fun onPageScrollStateChanged(state: Int) {
                //
            }
        })
        vp_pager.currentItem = 1
    }

    private fun createFragmentsAndTitles() {
        if (mTitles.size != 0) {
            mTitles.clear()
        }

        if (mFragments.size != 0) {
            mFragments.clear()
        }

        for (categoryBean in currentCategoryList!!) {
            mTitles.add(categoryBean.name)
            val fragment = CategoryFragment.newInstance(categoryBean.name, categoryBean.categoryId)
            fragment.setRefreshCallBack (CommonCallBack {
                when (it) {
                    Constants.LOAD_REFRESH ->
                        mPresenter!!.loadCategoryContsRefresh(categoryBean.categoryId)
                    Constants.LOAD_MORE ->
                        mPresenter!!.loadCategoryContsMore(categoryBean.categoryId)
                }
            });
            mFragments.add(fragment)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == 0 && requestCode == 1) {
            val categoryList = data!!.getParcelableArrayListExtra<CategoryBean.CategoryListBean>("categoryList")
            showCategoryList(categoryList)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        mPresenter!!.unsubscribe()
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

    override fun showCategoryList(beans: ArrayList<CategoryBean.CategoryListBean>) {
        currentCategoryList = beans
        initViewPager()
    }

    override fun showList(beans: List<ContEntity>, categoryId: String) {
        val position = getPosition(categoryId)
        mFragments[position].replaceData(beans)
    }

    override fun loadMoreList(beans: List<ContEntity>, categoryId: String) {
        val position = getPosition(categoryId)
        mFragments[position].addData(beans)
    }

    override fun loadMoreFinish(isSuccess: Boolean, categoryId: String) {
        val position = getPosition(categoryId)
        mFragments[position].loadMoreFinish(isSuccess)
    }

    override fun loadRefreshFinish(isSuccess: Boolean, categoryId: String) {
        val position = getPosition(categoryId)
        mFragments[position].refreshFinish(isSuccess)

    }

    override fun setPresenter(presenter: ChannelContract.Presenter) {
        mPresenter = presenter
    }

    private fun getPosition(categoryId: String): Int {
        var position = 0
        for (i in currentCategoryList!!.indices) {
            if (currentCategoryList!![i].categoryId == categoryId) {
                position = i
                break
            }
        }
        return position
    }

    companion object {
        @JvmStatic
        fun newInstance(): ChannelFragment {
            val bundle = Bundle()
            val fragment = ChannelFragment()
            fragment.arguments = bundle
            return fragment
        }
    }
}
