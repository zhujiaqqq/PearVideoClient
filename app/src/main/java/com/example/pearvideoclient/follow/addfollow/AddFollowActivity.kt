package com.example.pearvideoclient.follow.addfollow

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.apublic.CommonCallBack

import com.example.pearvideoclient.MyApplication
import com.example.pearvideoclient.R
import com.example.pearvideoclient.databinding.ActivityAddFollowBinding
import com.example.pearvideoclient.entity.DomainListBean
import com.example.pearvideoclient.entity.FollowUsersBean

/**
 * @Description: java类作用描述
 * @Author: jiazhu
 * @Date: 2019-09-02 16:34
 * @ClassName: AddFollowActivity
 */
class AddFollowActivity : AppCompatActivity() {

    private var binding: ActivityAddFollowBinding? = null
    private var mCategoryAdapter: AddFollowCategoryAdapter? = null
    private var mUserAdapter: AddFollowUserAdapter? = null
    private var mViewModel: AddFollowViewModel? = null
    private val mDomainClickCallback = object : DomainClickCallback {
        override fun onClick(bean: DomainListBean.DomainBean) {
            changeDomainList(bean)
            binding!!.isLoadingUser = true
            mViewModel!!.getUsers(bean.domainId, bean.type, CommonCallBack {
                mUserAdapter!!.serUserLists(it)
                binding!!.isLoadingUser = false
            })
        }
    }

    private var mUserClickCallback: UserClickCallback = object : UserClickCallback {
        override fun click(bean: FollowUsersBean.UserBean) {

        }
    }

    /**
     * 修改列表被选择的状态
     *
     * @param bean 当前选中的item
     */
    private fun changeDomainList(bean: DomainListBean.DomainBean) {
        val domainLists = mCategoryAdapter!!.domainLists
        for (domainBean in domainLists!!) {
            domainBean.isChecked = bean.domainId == domainBean.domainId
        }
        mCategoryAdapter!!.notifyDataSetChanged()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_follow)

        initData()
    }

    private fun initData() {
        mCategoryAdapter = AddFollowCategoryAdapter(mDomainClickCallback)
        binding!!.rvCategoryList.adapter = mCategoryAdapter

        mUserAdapter = AddFollowUserAdapter(mUserClickCallback)
        binding!!.rvUserList.adapter = mUserAdapter

        binding!!.isLoading = true

        mViewModel = AddFollowViewModel(MyApplication.instance)
        mViewModel!!.getDomainList(CommonCallBack { domainBeans ->
            mCategoryAdapter!!.domainLists = domainBeans
            val bean = domainBeans[0]
            mViewModel!!.getUsers(bean.domainId, bean.type, CommonCallBack(mUserAdapter!!::serUserLists))
            binding!!.isLoading = false
        })
    }

    override fun onDestroy() {
        mViewModel!!.dispose()
        mViewModel = null
        super.onDestroy()
    }
}
