package com.example.pearvideoclient.author.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pearvideoclient.MyApplication
import com.example.pearvideoclient.R
import com.example.pearvideoclient.author.AuthorActivity
import com.example.pearvideoclient.entity.UserPostsBean
import com.scwang.smartrefresh.layout.SmartRefreshLayout
import java.util.*

/**
 * @Description: java类作用描述
 * @Author: jiazhu
 * @Date: 2019-08-13 15:45
 * @ClassName: UserPostFragment
 */
class UserPostFragment : Fragment() {
    private var mRefreshLayout: SmartRefreshLayout? = null
    private var mRvUserPostList: RecyclerView? = null

    private var mUserPostAdapter: UserPostAdapter? = null
    private var mContext: Context? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_post, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView(view)
        initData()
    }

    private fun initView(view: View) {
        mRefreshLayout = view.findViewById(R.id.refresh_layout)
        mRvUserPostList = view.findViewById(R.id.rv_user_post_list)
    }

    private fun initData() {
        mContext = activity
        mRvUserPostList!!.layoutManager = LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false)
        mUserPostAdapter = UserPostAdapter(R.layout.adapter_user_post_item, ArrayList())
        mRvUserPostList!!.adapter = mUserPostAdapter
        mRefreshLayout!!.setOnLoadMoreListener { (activity as AuthorActivity).userPostsLoadMore() }
        mRefreshLayout!!.setOnRefreshListener { (activity as AuthorActivity).userPostsRefresh() }
        addHeadView()
    }

    /**
     * 添加headerView
     */
    private fun addHeadView() {
        val headerView = layoutInflater.inflate(R.layout.item_post_head_layout, null)
        headerView.layoutParams = DrawerLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        headerView.setOnClickListener {
            // TODO: 2019-08-29 评论
        }
        mUserPostAdapter!!.addHeaderView(headerView)
    }

    fun loadPostsList(postsList: List<UserPostsBean.PostListBean>) {
        mUserPostAdapter!!.replaceData(postsList)
    }

    fun loadMorePostsList(postsList: List<UserPostsBean.PostListBean>) {
        mUserPostAdapter!!.addData(postsList)
    }

    fun loadRefreshFinish(isSuccess: Boolean) {
        mRefreshLayout!!.finishRefresh(isSuccess)
    }

    fun loadMoreFinish(isSuccess: Boolean) {
        mRefreshLayout!!.finishLoadMore(isSuccess)
    }

    override fun onDestroy() {
        super.onDestroy()
        MyApplication.getRefWatcher(activity!!)!!.watch(this)
    }

    companion object {
        @JvmStatic
        fun newInstance(): UserPostFragment {
            val bundle = Bundle()
            val fragment = UserPostFragment()
            fragment.arguments = bundle
            return fragment
        }
    }
}
