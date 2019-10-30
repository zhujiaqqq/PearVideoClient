package com.example.pearvideoclient.home.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.apublic.CommonCallBack
import com.example.pearvideoclient.Constants
import com.example.pearvideoclient.R
import com.example.pearvideoclient.entity.LocalContEntity
import com.example.pearvideoclient.utils.ScreenUtils
import kotlinx.android.synthetic.main.fragment_city.*
import java.util.*

/**
 * @Description: java类作用描述
 * @Author: jiazhu
 * @Date: 2019-08-21 15:03
 * @ClassName: CityFragment
 */
class CityFragment : Fragment() {

    private var mCityAdapter: CityAdapter? = null

    private var mCityFragmentCallBack: CommonCallBack<Int, Unit>? = null
    private var mContext: Context? = null

    fun setCityFragmentCallBack(cityFragmentCallBack: CommonCallBack<Int, Unit>) {
        mCityFragmentCallBack = cityFragmentCallBack
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_city, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initData()
    }

    private fun initData() {
        mContext = activity
        rv_city_list.layoutManager = LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false)
        mCityAdapter = CityAdapter(ArrayList())
        rv_city_list.adapter = mCityAdapter

        refresh_layout.setOnLoadMoreListener {
            if (mCityFragmentCallBack != null) {
                mCityFragmentCallBack!!.todo(Constants.LOAD_MORE)
            }
        }
        refresh_layout.setOnRefreshListener {
            if (mCityFragmentCallBack != null) {
                mCityFragmentCallBack!!.todo(Constants.LOAD_REFRESH)
            }
        }


        addHeaderView()
    }

    /**
     * 添加headerView
     */
    private fun addHeaderView() {
        val headerView = layoutInflater.inflate(R.layout.item_city_header_layout, null)
        headerView.layoutParams = DrawerLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ScreenUtils.dip2px(mContext!!, 48f))
        headerView.setOnClickListener {
            if (mCityFragmentCallBack != null) {
                mCityFragmentCallBack!!.todo(Constants.HEADER_CLICK)
            }
        }
        mCityAdapter!!.setHeaderView(headerView)
    }

    fun addData(data: List<LocalContEntity>) {
        mCityAdapter!!.addData(data)
    }

    fun replaceData(data: List<LocalContEntity>) {
        mCityAdapter!!.replaceData(data)
    }

    fun loadMoreFinish(isSuccess: Boolean) {
        refresh_layout.finishLoadMore(isSuccess)
    }

    fun refreshFinish(isSuccess: Boolean) {
        refresh_layout.finishRefresh(isSuccess)
    }

    companion object {

        fun newInstance(): CityFragment {
            val bundle = Bundle()
            val fragment = CityFragment()
            fragment.arguments = bundle
            return fragment
        }
    }
}
