package com.example.pearvideoclient.follow.addfollow

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.pearvideoclient.R
import com.example.pearvideoclient.databinding.AdapterDomainItemBinding
import com.example.pearvideoclient.entity.DomainListBean

/**
 * @Description: java类作用描述
 * @Author: jiazhu
 * @Date: 2019-09-02 17:32
 * @ClassName: AddFollowCategoryAdapter
 */
class AddFollowCategoryAdapter(private val mDomainClickCallback: DomainClickCallback?)
    : RecyclerView.Adapter<AddFollowCategoryAdapter.DomainViewHolder>() {
    var domainLists: List<DomainListBean.DomainBean>? = null
        set(domainLists) = if (this.domainLists == null) {
            field = domainLists
            notifyItemRangeChanged(0, domainLists!!.size)
        } else {
            val result = DiffUtil.calculateDiff(object : DiffUtil.Callback() {
                override fun getOldListSize(): Int {
                    return this@AddFollowCategoryAdapter.domainLists!!.size
                }

                override fun getNewListSize(): Int {
                    return domainLists!!.size
                }

                override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                    return this@AddFollowCategoryAdapter.domainLists!![oldItemPosition].domainId == domainLists!![newItemPosition].domainId
                }

                override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                    val newDomain = domainLists!![newItemPosition]
                    val oldDomain = domainLists[oldItemPosition]
                    return newDomain.domainId == oldDomain.domainId && newDomain.name == oldDomain.name
                }
            })
            field = domainLists
            result.dispatchUpdatesTo(this)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DomainViewHolder {
        val binding = DataBindingUtil.inflate<AdapterDomainItemBinding>(LayoutInflater.from(parent.context),
                R.layout.adapter_domain_item, parent, false)
        binding.callback = mDomainClickCallback
        return DomainViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DomainViewHolder, position: Int) {
        holder.mBinding.domain = domainLists!![position]
    }

    override fun getItemCount(): Int {
        return if (domainLists == null) 0 else domainLists!!.size
    }

    class DomainViewHolder(val mBinding: AdapterDomainItemBinding) : RecyclerView.ViewHolder(mBinding.root)
}
