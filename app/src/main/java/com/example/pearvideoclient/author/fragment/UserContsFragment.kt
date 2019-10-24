package com.example.pearvideoclient.author.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.pearvideoclient.MyApplication
import com.example.pearvideoclient.R
import com.example.pearvideoclient.author.AuthorActivity
import com.example.pearvideoclient.entity.UserConts
import kotlinx.android.synthetic.main.fragment_user_conts.*

/**
 * @Description: java类作用描述
 * @Author: jiazhu
 * @Date: 2019-08-13 15:45
 * @ClassName: UserContsFragment
 */
class UserContsFragment : Fragment() {

    private lateinit var mHotAdapter: UserContsAdapter
    private lateinit var mNewAdapter: UserContsAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_user_conts, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initData()
    }

    private fun initData() {
        val context = activity

        rv_hot_list.layoutManager = GridLayoutManager(context, 2)
        mHotAdapter = UserContsAdapter(R.layout.adapter_category_conts_item, null)
        rv_hot_list.adapter = mHotAdapter

        rv_new_list.layoutManager = GridLayoutManager(context, 2)
        mNewAdapter = UserContsAdapter(R.layout.adapter_category_conts_item, null)
        rv_new_list.adapter = mNewAdapter
        refresh_layout.setEnableRefresh(false)
        refresh_layout.setOnRefreshListener { (activity as AuthorActivity).userContsRefresh() }
        refresh_layout.setOnLoadMoreListener { (activity as AuthorActivity).userContsLoadMore() }
    }

    fun loadHotConts(hotList: List<UserConts.ContListBean>) {
        if (hotList.isEmpty()) {
            rv_hot_list.visibility = View.GONE
            tv_hot.visibility = View.GONE
            return
        }
        mHotAdapter.replaceData(hotList)
    }

    fun loadNewConts(contList: List<UserConts.ContListBean>) {
        mNewAdapter.replaceData(contList)
    }

    fun loadMoreNewConts(contList: List<UserConts.ContListBean>) {
        mNewAdapter.addData(contList)
    }

    fun loadRefreshFinish(isSuccess: Boolean) {
        refresh_layout.finishRefresh(isSuccess)
    }

    fun loadMoreFinish(isSuccess: Boolean) {
        refresh_layout.finishLoadMore(isSuccess)
    }

    override fun onDestroy() {
        super.onDestroy()
        MyApplication.getRefWatcher(activity!!)!!.watch(this)
    }

    companion object {
        @JvmStatic
        fun newInstance(): UserContsFragment {
            val bundle = Bundle()
            val fragment = UserContsFragment()
            fragment.arguments = bundle
            return fragment
        }
    }
}
