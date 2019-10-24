package com.example.pearvideoclient.author

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.pearvideoclient.MyApplication
import com.example.pearvideoclient.R
import com.example.pearvideoclient.entity.AlbumContBean
import com.example.pearvideoclient.utils.MyToast
import kotlinx.android.synthetic.main.activity_album.*
import java.util.*

/**
 * @Description: java类作用描述
 * @Author: jiazhu
 * @Date: 2019-08-28 19:14
 * @ClassName: AlbumActivity
 */
class AlbumActivity : AppCompatActivity(), AlbumContract.View {


    private var mAlbumAdapter: AlbumAdapter? = null

    private var mPresenter: AlbumContract.Presenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_album)
        initView()
        initData()
    }

    private fun initView() {
        iv_back.setOnClickListener { finish() }
    }

    private fun initData() {
        val albumId = intent.getStringExtra(ALBUM_ID)

        AlbumPresenter(this)

        rv_album_list.layoutManager = GridLayoutManager(this, 2)
        mAlbumAdapter = AlbumAdapter(R.layout.adapter_category_conts_item, ArrayList())
        rv_album_list.adapter = mAlbumAdapter

        refresh_layout.setOnLoadMoreListener { mPresenter!!.loadMoreAlbumConts() }
        refresh_layout.setOnRefreshListener { mPresenter!!.refreshAlbumConts() }

        mPresenter!!.subscribe()
        mPresenter!!.loadAlbumConts(albumId)
    }

    override fun setPresenter(presenter: AlbumContract.Presenter) {
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

    override fun setTitle(name: String) {
        tv_title.text = name
    }

    override fun setAlbumCont(contList: List<AlbumContBean.ContListBean>) {
        mAlbumAdapter!!.replaceData(contList)
    }

    override fun addAlbumCont(contList: List<AlbumContBean.ContListBean>) {
        mAlbumAdapter!!.addData(contList)
    }

    override fun finishLoad(b: Boolean) {
        refresh_layout.finishLoadMore(b)
    }

    override fun finishRefresh(b: Boolean) {
        refresh_layout.finishRefresh(b)
    }

    companion object {
        const val ALBUM_ID = "albumId"
    }
}
