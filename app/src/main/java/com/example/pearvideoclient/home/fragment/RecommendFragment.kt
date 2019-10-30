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
import com.example.pearvideoclient.entity.RecommendEntity
import kotlinx.android.synthetic.main.fragment_recommend.*
import java.util.*

/**
 * @Description: java类作用描述
 * @Author: jiazhu
 * @Date: 2019-08-21 15:03
 * @ClassName: RecommendFragment
 */
class RecommendFragment : Fragment() {
    private var mRecommendAdapter: RecommendAdapter? = null

    private var refreshCallBack: CommonCallBack<Int, Unit>? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_recommend, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initData()
    }

    private fun initData() {
        val context = activity
        rv_recommend_list.layoutManager = LinearLayoutManager(context)
        mRecommendAdapter = RecommendAdapter(ArrayList())
        rv_recommend_list.adapter = mRecommendAdapter

        refresh_layout.setOnLoadMoreListener {
            if (refreshCallBack != null) {
                refreshCallBack!!.todo(Constants.LOAD_MORE)
            }
        }
        refresh_layout.setOnRefreshListener {
            if (refreshCallBack != null) {
                refreshCallBack!!.todo(Constants.LOAD_REFRESH)
            }
        }
    }

    fun replaceData(list: List<RecommendEntity>) {
        mRecommendAdapter!!.replaceData(list)
    }

    fun addData(list: List<RecommendEntity>) {
        mRecommendAdapter!!.addData(list)
    }

    fun loadMoreFinish(isSuccess: Boolean) {
        refresh_layout.finishLoadMore(isSuccess)
    }

    fun refreshFinish(isSuccess: Boolean) {
        refresh_layout.finishRefresh(isSuccess)
    }

    fun setRefreshCallBack(refreshCallBack: CommonCallBack<Int, Unit>) {
        this.refreshCallBack = refreshCallBack
    }

    override fun onDestroy() {
        super.onDestroy()
        MyApplication.getRefWatcher(activity!!)!!.watch(this)
    }

    companion object {

        fun newInstance(): RecommendFragment {
            val bundle = Bundle()
            val fragment = RecommendFragment()
            fragment.arguments = bundle
            return fragment
        }
    }
}
