package com.example.pearvideoclient.follow.addfollow

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.pearvideoclient.R
import com.example.pearvideoclient.databinding.AdapterAddFollowUserItemBinding
import com.example.pearvideoclient.entity.FollowUsersBean
import java.util.*

/**
 * @Description: java类作用描述
 * @Author: jiazhu
 * @Date: 2019-09-03 17:52
 * @ClassName: AddFollowUserAdapter
 */
class AddFollowUserAdapter(private val mUserClickCallback: UserClickCallback?)
    : RecyclerView.Adapter<AddFollowUserAdapter.UserViewHolder>() {
    private var mUserBeans: MutableList<FollowUsersBean.UserBean>? = null

    fun serUserLists(userBeans: List<FollowUsersBean.UserBean>) {
        if (mUserBeans == null) {
            mUserBeans = ArrayList()
        } else {
            mUserBeans!!.clear()
        }
        mUserBeans!!.addAll(userBeans)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val binding = DataBindingUtil.inflate<AdapterAddFollowUserItemBinding>(LayoutInflater.from(parent.context),
                R.layout.adapter_add_follow_user_item, parent, false)
        binding.callback = mUserClickCallback
        return UserViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.mBinding.userBean = mUserBeans!![position]
    }

    override fun getItemCount(): Int {
        return if (mUserBeans == null) 0 else mUserBeans!!.size
    }

     class UserViewHolder(val mBinding: AdapterAddFollowUserItemBinding) : RecyclerView.ViewHolder(mBinding.root)
}
