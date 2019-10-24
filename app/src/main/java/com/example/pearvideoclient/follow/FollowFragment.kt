package com.example.pearvideoclient.follow

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Message
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.apublic.LocalHandler
import com.example.pearvideoclient.MyApplication
import com.example.pearvideoclient.R
import com.example.pearvideoclient.author.AuthorActivity
import com.example.pearvideoclient.entity.MyFollowContBean
import com.example.pearvideoclient.follow.addfollow.AddFollowActivity
import com.example.pearvideoclient.utils.MyToast
import com.scwang.smartrefresh.layout.SmartRefreshLayout

/**
 * @author zhujiaqqq
 * @date 2019-07-11
 */
class FollowFragment : Fragment(), FollowContract.View, LocalHandler.IHandler {
    private var mTvAddFollow: TextView? = null
    private var mRvFollowInfoList: RecyclerView? = null
    private var mRvFollowUserList: RecyclerView? = null
    private var mRefreshLayout: SmartRefreshLayout? = null

    private var mContext: Context? = null

    private var mFollowInfoListAdapter: FollowInfoListAdapter? = null
    private var mFollowUserListAdapter: FollowUserListAdapter? = null
    private var mPresenter: FollowContract.Presenter? = null
    private var mLayoutManager: LinearLayoutManager? = null

    private var mPlayPosition = 0

    private val mHandler = LocalHandler(this)
    private var mIdleState: Boolean = false

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_follow, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView(view)

        initData()
    }

    private fun initData() {
        mContext = activity

        mLayoutManager = LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false)
        mRvFollowInfoList!!.layoutManager = mLayoutManager
        mFollowInfoListAdapter = FollowInfoListAdapter(R.layout.adapter_follow_info_item, null)
        mRvFollowInfoList!!.adapter = mFollowInfoListAdapter

        mRvFollowUserList!!.layoutManager = LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false)
        mFollowUserListAdapter = FollowUserListAdapter(R.layout.adapter_follow_user_item, null)
        mFollowUserListAdapter!!.setOnItemChildClickListener { adapter, view, position ->
            if (view.id == R.id.rl_parent) {
                if (adapter is FollowUserListAdapter) {
                    val userBean = adapter.data[position]
                    val userId = userBean.userId
                    val intent = Intent(mContext, AuthorActivity::class.java)
                    intent.putExtra("userId", userId)
                    mContext!!.startActivity(intent)
                }
            }
        }
        mRvFollowUserList!!.adapter = mFollowUserListAdapter

        mTvAddFollow!!.setOnClickListener {
            val intent = Intent(mContext, AddFollowActivity::class.java)
            mContext!!.startActivity(intent)
        }

        mPresenter!!.loadMyFollowList()

        mRefreshLayout!!.setOnLoadMoreListener { mPresenter!!.loadMoreMyFollowList() }
        mRefreshLayout!!.setOnRefreshListener { mPresenter!!.refreshMyFollowList() }

        mRvFollowInfoList!!.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    Log.i(TAG, "onScrollStateChanged: 停止")
                    val message = mHandler.obtainMessage()
                    message.arg1 = mPlayPosition
                    message.what = MSG_SET_PLAY
                    mHandler.sendMessage(message)
                }
            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (mLayoutManager != null) {
                    val firstVisibleItem = mLayoutManager!!.findFirstVisibleItemPosition()
                    val firstView = mLayoutManager!!.findViewByPosition(firstVisibleItem)
                    if (null != firstView) {
                        mIdleState = true
                        if (dy > 0) {
                            mPlayPosition = if (firstView.height + firstView.top <= firstView.height / 3) {
                                if (mLayoutManager!!.childCount < 2) {
                                    return
                                }
                                if (mPlayPosition == firstVisibleItem + 1) {
                                    return
                                }
                                firstVisibleItem + 1
                            } else {
                                if (mPlayPosition == firstVisibleItem) {
                                    return
                                }
                                firstVisibleItem
                            }
                        }
                        if (dy < 0) {
                            mPlayPosition = if (firstView.height + firstView.top >= firstView.height / 3) {
                                if (mLayoutManager!!.childCount < 2) {
                                    return
                                }
                                if (mPlayPosition == firstVisibleItem) {
                                    return
                                }
                                firstVisibleItem
                            } else {
                                if (mPlayPosition == firstVisibleItem + 1) {
                                    return
                                }
                                firstVisibleItem + 1
                            }
                        }
                        Log.i(TAG, "onScrolled: $mPlayPosition")
                    } else {
                        mIdleState = false
                    }
                } else {
                    mIdleState = false
                }
            }
        })
    }

    private fun initView(view: View) {
        mTvAddFollow = view.findViewById(R.id.tv_add_follow)
        mRvFollowInfoList = view.findViewById(R.id.rv_follow_info_list)
        mRvFollowUserList = view.findViewById(R.id.rl_follow_user_list)
        mRefreshLayout = view.findViewById(R.id.refresh_layout)
    }

    override fun setPresenter(presenter: FollowContract.Presenter) {
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

    override fun showFollowUser(list: List<MyFollowContBean.FollowUserListBean>) {
        mFollowUserListAdapter!!.replaceData(list)
    }

    override fun showFollowData(list: List<MyFollowContBean.DataListBean>) {
        mFollowInfoListAdapter!!.replaceData(list)
    }

    override fun loadMoreFinish(isSuccess: Boolean) {
        mRefreshLayout!!.finishLoadMore(isSuccess)
    }

    override fun loadRefreshFinish(isSuccess: Boolean) {
        mRefreshLayout!!.finishRefresh(isSuccess)
    }

    override fun loadMoreFollowData(bean: MyFollowContBean) {
        val dataList = bean.dataList
        mFollowInfoListAdapter!!.addData(dataList)
    }

    override fun handlerMessage(msg: Message) {
        if (MSG_SET_PLAY == msg.what && mIdleState) {
            mFollowInfoListAdapter!!.setPlay(msg.arg1)
            mHandler.removeMessages(MSG_SET_PLAY)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        MyApplication.getRefWatcher(activity!!)!!.watch(this)
    }

    companion object {
        private const val TAG = "FollowFragment"
        const val MSG_SET_PLAY = 0x11
        @JvmStatic
        fun newInstance(): FollowFragment {
            val bundle = Bundle()
            val fragment = FollowFragment()
            fragment.arguments = bundle
            return fragment
        }
    }
}
