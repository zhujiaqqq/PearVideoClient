package com.example.pearvideoclient.home.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.apublic.CommonCallBack
import com.example.pearvideoclient.Constants
import com.example.pearvideoclient.MyApplication
import com.example.pearvideoclient.R
import com.example.pearvideoclient.entity.NewsEntity
import kotlinx.android.synthetic.main.fragment_vientiane.*
import java.util.*

/**
 * @Description: java类作用描述
 * @Author: jiazhu
 * @Date: 2019-08-21 15:03
 * @ClassName: VientianeFragment
 */
class VientianeFragment : Fragment() {

    private var mVientianeAdapter: VientianeAdapter? = null

    private var refreshCallBack: CommonCallBack<Int, Unit>? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_vientiane, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initData()
    }

    private fun initData() {
        val context = activity
        refresh_layout.setOnRefreshListener {
            if (refreshCallBack != null) {
                refreshCallBack!!.todo(Constants.LOAD_REFRESH)
            }

        }
        refresh_layout.setOnLoadMoreListener {
            if (refreshCallBack != null) {
                refreshCallBack!!.todo(Constants.LOAD_MORE)
            }
        }

        rv_vientiane_list.layoutManager = LinearLayoutManager(context)
        mVientianeAdapter = VientianeAdapter(ArrayList())
        rv_vientiane_list.adapter = mVientianeAdapter
    }

    fun setVientianeList(data: List<NewsEntity>) {
        mVientianeAdapter!!.replaceData(data)
    }

    fun loadMoreVientianeList(data: List<NewsEntity>) {
        mVientianeAdapter!!.addData(data)
    }

    fun refreshFinish(isSuccess: Boolean) {
        refresh_layout.finishRefresh(isSuccess)
    }

    fun loadMoreFinish(isSuccess: Boolean) {
        refresh_layout.finishLoadMore(isSuccess)
    }

    fun setRefreshCallBack(refreshCallBack: CommonCallBack<Int, Unit>) {
        this.refreshCallBack = refreshCallBack
    }

    override fun onDestroy() {
        super.onDestroy()
        MyApplication.getRefWatcher(activity!!)!!.watch(this)
    }

    companion object {

        fun newInstance(): VientianeFragment {
            val bundle = Bundle()
            val fragment = VientianeFragment()
            fragment.arguments = bundle
            return fragment
        }
    }
}
