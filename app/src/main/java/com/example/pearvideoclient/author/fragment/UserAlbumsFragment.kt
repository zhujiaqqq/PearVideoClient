package com.example.pearvideoclient.author.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pearvideoclient.MyApplication
import com.example.pearvideoclient.R
import com.example.pearvideoclient.author.AuthorActivity
import com.example.pearvideoclient.entity.UserAlbumsBean
import kotlinx.android.synthetic.main.fragment_user_albums.*
import java.util.*

/**
 * @Description: java类作用描述
 * @Author: jiazhu
 * @Date: 2019-08-13 15:46
 * @ClassName: UserAlbumsFragment
 */
class UserAlbumsFragment : Fragment() {
    private var mUserAlbumsAdapter: UserAlbumsAdapter? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_user_albums, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initData()
    }

    private fun initData() {
        val context = activity
        rv_user_albums_list.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        mUserAlbumsAdapter = UserAlbumsAdapter(context!!, ArrayList())
        rv_user_albums_list.adapter = mUserAlbumsAdapter

        refresh_layout.setEnableRefresh(false)

        refresh_layout.setOnRefreshListener { (activity as AuthorActivity).userAlbumsRefresh() }
        refresh_layout.setOnLoadMoreListener { (activity as AuthorActivity).userAlbumsLoadMore() }
    }

    fun loadAlbumsList(albumList: List<UserAlbumsBean.AlbumListBean>) {
        mUserAlbumsAdapter!!.replaceData(albumList)
    }

    fun loadMoreAlbumsList(albumList: List<UserAlbumsBean.AlbumListBean>) {
        mUserAlbumsAdapter!!.addData(albumList)
    }

    fun loadMoreFinish(isSuccess: Boolean) {
        refresh_layout.finishLoadMore(isSuccess)
    }

    fun loadRefreshFinish(isSuccess: Boolean) {
        refresh_layout.finishRefresh(isSuccess)
    }

    override fun onDestroy() {
        super.onDestroy()
        MyApplication.getRefWatcher(activity!!)!!.watch(this)
    }

    companion object {

        fun newInstance(): UserAlbumsFragment {
            val bundle = Bundle()
            val fragment = UserAlbumsFragment()
            fragment.arguments = bundle
            return fragment
        }
    }
}
