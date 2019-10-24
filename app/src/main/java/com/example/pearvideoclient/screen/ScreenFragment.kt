package com.example.pearvideoclient.screen

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.pearvideoclient.MyApplication
import com.example.pearvideoclient.R
import com.example.pearvideoclient.entity.PaikeFineVideoBean
import com.example.pearvideoclient.utils.MyToast
import com.example.route.Route
import kotlinx.android.synthetic.main.fragment_screen.*
import java.util.*

/**
 * @author zhujiaqqq
 * @date 2019-07-11
 */
class ScreenFragment : Fragment(), ScreenContract.View {

    private var mContext: Context? = null
    private var mVideoAdapter: VideoAdapter? = null

    private var mPresenter: ScreenContract.Presenter? = null

    private val mClickListener = View.OnClickListener { v ->
        when (v.id) {
            R.id.tv_start_video -> Route.getInstance().jumpActivity("videolib/recordVideo", null)
            R.id.tv_see_detail -> {
            }
            R.id.tv_remark_list -> {
            }
            R.id.tv_my_video -> {
            }
            else -> {
            }
        }
    }
    private var mConfigInfo: PaikeFineVideoBean.ConfigInfoBean? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_screen, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initData()
    }

    private fun initView() {
        tv_start_video.setOnClickListener(mClickListener)
        tv_see_detail.setOnClickListener(mClickListener)
        tv_remark_list.setOnClickListener(mClickListener)
        tv_my_video.setOnClickListener(mClickListener)
    }

    private fun initData() {
        ScreenPresenter(this)
        mContext = activity
        rv_video_list.layoutManager = GridLayoutManager(mContext, 2)
        mVideoAdapter = VideoAdapter(R.layout.adapter_paike_video_item, ArrayList<PaikeFineVideoBean.VideoBean>())
        mVideoAdapter!!.setOnItemChildClickListener { _, view, _ ->
            if (view.id == R.id.rl_parent) {
                // TODO: 2019-08-26

            }
        }
        rv_video_list.adapter = mVideoAdapter
        mPresenter!!.subscribe()
    }

    override fun onDestroy() {
        mPresenter!!.unsubscribe()
        super.onDestroy()
        MyApplication.getRefWatcher(activity!!)!!.watch(this)
    }

    override fun showPaikeVideos(data: List<PaikeFineVideoBean.VideoBean>) {
        mVideoAdapter!!.replaceData(data)
    }

    override fun showPaikeInfo(data: PaikeFineVideoBean.ConfigInfoBean) {
        mConfigInfo = data
        iv_video_img.setImageResource(R.drawable.ic_paike_img)
    }

    override fun setPresenter(presenter: ScreenContract.Presenter) {
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

    companion object {

        fun newInstance(): ScreenFragment {
            val bundle = Bundle()
            val fragment = ScreenFragment()
            fragment.arguments = bundle
            return fragment
        }
    }
}
