package com.example.pearvideoclient.mine

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.widget.NestedScrollView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.pearvideoclient.MyApplication
import com.example.pearvideoclient.R
import com.example.pearvideoclient.entity.LoginBean
import com.example.pearvideoclient.entity.UserInfoBean
import com.example.pearvideoclient.utils.GlideUtils
import com.example.pearvideoclient.utils.MyToast
import com.example.pearvideoclient.view.MenuPopWindow
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.wang.avi.AVLoadingIndicatorView

/**
 * @author zhujiaqqq
 * @date 2019-07-10
 */
class MineFragment : Fragment(), MineContract.View {


    private var mTvUserName: TextView? = null
    private var mTvUserSingle: TextView? = null
    private var mTvLogin: TextView? = null
    private var mTvUserDone: TextView? = null
    private var mIvSmallUserImg: ImageView? = null
    private var mIvUserImg: ImageView? = null
    private var mIvUserBackground: ImageView? = null
    private var mIvClose: ImageView? = null
    private var mRvFollowList: RecyclerView? = null
    private var mRlMainContent: RelativeLayout? = null
    private var mRlMyInfo: RelativeLayout? = null
    private var mRlLogin: RelativeLayout? = null
    private var mEtTeleNum: EditText? = null
    private var mEtVarCode: EditText? = null
    private var mLlEdit: LinearLayout? = null
    private var mLlVieMyFollow: LinearLayout? = null
    private var mLoadingView: AVLoadingIndicatorView? = null

    private var mPresenter: MineContract.Presenter? = null

    private val window: MenuPopWindow? = null

    private var mViewBottomSheetBehavior: BottomSheetBehavior<View>? = null
    private var mScrollView: NestedScrollView? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_mine, container, false)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        MyApplication.getRefWatcher(activity!!)!!.watch(this)
        mPresenter!!.unsubscribe()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView(view)
        initData()
    }

    private fun initView(view: View) {
        mScrollView = view.findViewById(R.id.scroll_view)
        mViewBottomSheetBehavior = BottomSheetBehavior.from(mScrollView!!)
        mScrollView!!.viewTreeObserver.addOnGlobalLayoutListener { mViewBottomSheetBehavior!!.setPeekHeight(mScrollView!!.height) }

        mTvUserName = view.findViewById(R.id.tv_user_name)
        mTvUserSingle = view.findViewById(R.id.tv_user_single)
        mTvLogin = view.findViewById(R.id.tv_login)
        mTvUserDone = view.findViewById(R.id.tv_user_done)

        mIvUserImg = view.findViewById(R.id.iv_user_img)
        mIvClose = view.findViewById(R.id.iv_close)
        mIvUserBackground = view.findViewById(R.id.iv_user_background)
        mIvSmallUserImg = view.findViewById(R.id.iv_small_user_image)

        mRvFollowList = view.findViewById(R.id.rv_follow_list)

        mEtTeleNum = view.findViewById(R.id.et_tele_num)
        mEtVarCode = view.findViewById(R.id.et_var_code)

        mLlEdit = view.findViewById(R.id.ll_edit)
        mLlVieMyFollow = view.findViewById(R.id.view_my_follow)

        mRlLogin = view.findViewById(R.id.view_login)
        mRlMainContent = view.findViewById(R.id.main_content)
        mRlMyInfo = view.findViewById(R.id.view_my_info)

        mLoadingView = view.findViewById(R.id.loading_view)
    }

    override fun onResume() {
        super.onResume()
        mPresenter!!.subscribe()
    }

    private fun showPopWindow() {

        //        if (window == null) {
        //            window = new MenuPopWindow(getActivity(), windowListener);
        //        }
        //        window.showAtLocation(mRlMainContent, Gravity.BOTTOM, 0, 0);
    }

    private fun showBottom() {
        mViewBottomSheetBehavior!!.state = BottomSheetBehavior.STATE_COLLAPSED
        //根据状态不同显示隐藏
        if (mViewBottomSheetBehavior!!.state == BottomSheetBehavior.STATE_HIDDEN) {
            mViewBottomSheetBehavior!!.setState(BottomSheetBehavior.STATE_COLLAPSED)
        } else if (mViewBottomSheetBehavior!!.state == BottomSheetBehavior.STATE_COLLAPSED) {
            mViewBottomSheetBehavior!!.state = BottomSheetBehavior.STATE_HIDDEN
        }
    }

    private fun initData() {

        mTvLogin!!.setOnClickListener {
            mPresenter!!.login(mEtTeleNum!!.text.toString().trim { it <= ' ' },
                    mEtVarCode!!.text.toString().trim { it <= ' ' })
        }

        mIvClose!!.setOnClickListener { showBottom() }


        mLlEdit!!.setOnClickListener {
            // TODO: 2019-07-21 点击跳转修改资料页面
        }

        mTvLogin!!.setOnClickListener {
            mPresenter!!.login(mEtTeleNum!!.text.toString(),
                    mEtVarCode!!.text.toString())
        }
    }


    override fun setPresenter(presenter: MineContract.Presenter) {
        this.mPresenter = presenter
    }

    override fun showLoading() {
        mLoadingView!!.show()
    }

    override fun cancelLoading() {
        mLoadingView!!.hide()
    }

    override fun showErrorToast(loadingFail: String) {
        MyToast.getInstance(MyApplication.instance).show(loadingFail, 3000)
    }


    override fun showUserInfo(userInfoBean: LoginBean.UserInfoBean) {
        mTvUserName!!.text = userInfoBean.nickname
        mTvUserSingle!!.text = userInfoBean.signature
        GlideUtils.loadCircleImage(userInfoBean.images[0].filePath, mIvUserImg!!)
    }

    override fun showUserInfo(userInfoBean: UserInfoBean) {
        mTvUserName!!.text = userInfoBean.userInfo.nickname
        mTvUserSingle!!.text = userInfoBean.userInfo.signature
        GlideUtils.loadCircleImage(userInfoBean.userInfo.pic, mIvUserImg!!)
    }

    override fun showCloseIcon(isShow: Boolean) {
        mIvClose!!.visibility = if (isShow) View.VISIBLE else View.GONE
    }

    override fun showLoginLayout(isShow: Boolean) {
        mRlLogin!!.visibility = if (isShow) View.VISIBLE else View.GONE
    }

    override fun showMyFollowLayout(isShow: Boolean) {
        mLlVieMyFollow!!.visibility = if (isShow) View.VISIBLE else View.GONE
    }

    override fun showPopWindow(isShow: Boolean) {
        if (isShow) {
            showPopWindow()
        } else {
            window!!.dismiss()
        }
    }

    companion object {

        fun newInstance(): MineFragment {
            val args = Bundle()
            val fragment = MineFragment()
            fragment.arguments = args
            return fragment
        }
    }


}
