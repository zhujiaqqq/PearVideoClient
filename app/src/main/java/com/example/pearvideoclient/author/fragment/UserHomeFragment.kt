package com.example.pearvideoclient.author.fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.chad.library.adapter.base.BaseQuickAdapter
import com.example.pearvideoclient.MyApplication
import com.example.pearvideoclient.R
import com.example.pearvideoclient.author.AuthorActivity
import com.example.pearvideoclient.entity.AuthorHomeBean
import com.scwang.smartrefresh.layout.SmartRefreshLayout

/**
 * @Description: java类作用描述
 * @Author: jiazhu
 * @Date: 2019-08-13 15:45
 * @ClassName: UserHomeFragment
 */
class UserHomeFragment : Fragment() {

    private var mRefreshLayout: SmartRefreshLayout? = null
    private var mRvUserHomeList: RecyclerView? = null

    private var mUserHomeAdapter: UserHomeAdapter? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_user_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView(view)
        initData()
    }

    private fun initView(view: View) {
        mRefreshLayout = view.findViewById(R.id.refresh_layout)
        mRvUserHomeList = view.findViewById(R.id.rv_user_home_list)
    }

    private fun initData() {
        val context = activity

        mRvUserHomeList!!.layoutManager = LinearLayoutManager(
                context, LinearLayoutManager.VERTICAL, false
        )
        mUserHomeAdapter = UserHomeAdapter(R.layout.adapter_user_home_item, null)
        mRvUserHomeList!!.adapter = mUserHomeAdapter
        mUserHomeAdapter!!.setOnItemChildClickListener { _, view, _ ->
            when (view.id) {
                R.id.ll_comment -> {
                }
                R.id.ll_favour -> {
                }
                R.id.ll_share -> {
                }
                else -> {
                }
            }// TODO: 2019-08-29 评论跳转
            // TODO: 2019-08-29 star
            // TODO: 2019-08-29 分享
        }

        mRefreshLayout!!.setOnRefreshListener { (activity as AuthorActivity).userHomeRefresh() }
        mRefreshLayout!!.setOnLoadMoreListener { (activity as AuthorActivity).userHomeLoadMore() }
    }


    fun loadDataList(dataList: List<AuthorHomeBean.DataListBean>) {
        mUserHomeAdapter!!.replaceData(dataList)
    }

    fun loadMoreDataList(dataList: List<AuthorHomeBean.DataListBean>) {
        mUserHomeAdapter!!.addData(dataList)
    }

    fun loadMoreFinish(isSuccess: Boolean) {
        mRefreshLayout!!.finishLoadMore(isSuccess)
    }

    fun loadRefreshFinish(isSuccess: Boolean) {
        mRefreshLayout!!.finishRefresh(isSuccess)
    }

    override fun onDestroy() {
        super.onDestroy()
        MyApplication.getRefWatcher(activity!!)!!.watch(this)
    }

    companion object {

        fun newInstance(): UserHomeFragment {
            val bundle = Bundle()
            val fragment = UserHomeFragment()
            fragment.arguments = bundle
            return fragment
        }
    }
}
