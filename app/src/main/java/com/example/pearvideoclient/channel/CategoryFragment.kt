package com.example.pearvideoclient.channel

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.example.apublic.CommonCallBack
import com.example.pearvideoclient.Constants
import com.example.pearvideoclient.R
import com.example.pearvideoclient.channel.adapter.ContListAdapter
import com.example.pearvideoclient.entity.ContEntity
import com.scwang.smartrefresh.layout.api.RefreshLayout
import kotlinx.android.synthetic.main.fragment_category_conts.*

import java.util.ArrayList

/**
 * @Description: java类作用描述
 * @Author: jiazhu
 * @Date: 2019-08-29 15:51
 * @ClassName: CategoryFragment
 */
class CategoryFragment : Fragment() {
    private val mContListAdapter = ContListAdapter(ArrayList())

    private var refreshCallBack: CommonCallBack<Int, Unit>? = null

    fun setRefreshCallBack(refreshCallBack: CommonCallBack<Int, Unit>) {
        this.refreshCallBack = refreshCallBack
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_category_conts, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initData()
    }

    private fun initData() {
        val activity = activity as AppCompatActivity?
        rv_category_cont_list.layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
        rv_category_cont_list.adapter = mContListAdapter

        refresh_layout.setOnRefreshListener {
            refreshCallBack?.todo(Constants.LOAD_REFRESH)
        }

        refresh_layout.setOnLoadMoreListener {
            refreshCallBack?.todo(Constants.LOAD_MORE)
        }

    }

    fun replaceData(data: List<ContEntity>) {
        mContListAdapter.replaceData(data)
    }

    fun addData(data: List<ContEntity>) {
        mContListAdapter.addData(data)
    }

    fun loadMoreFinish(isSuccess: Boolean) {
        refresh_layout.finishLoadMore(isSuccess)
    }

    fun refreshFinish(isSuccess: Boolean) {
        refresh_layout.finishRefresh(isSuccess)
    }

    fun hasData(): Boolean {
        return mContListAdapter.data.size > 0
    }

    companion object {
        @JvmStatic
        fun newInstance(name: String, categoryId: String): CategoryFragment {
            val bundle = Bundle()
            bundle.putString("name", name)
            bundle.putString("categoryId", categoryId)
            val fragment = CategoryFragment()
            fragment.arguments = bundle
            return fragment
        }
    }
}
