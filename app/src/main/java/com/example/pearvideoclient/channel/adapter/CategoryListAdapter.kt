package com.example.pearvideoclient.channel.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.pearvideoclient.R
import com.example.pearvideoclient.channel.IOperationData
import com.example.pearvideoclient.entity.CategoryBean
import java.util.*

/**
 * @Description: java类作用描述
 * @Author: jiazhu
 * @Date: 2019-07-19 10:16
 * @ClassName: CategoryListAdapter
 */
class CategoryListAdapter(private val mContext: Context, private val mList: MutableList<CategoryBean.CategoryListBean>?) :
        RecyclerView.Adapter<CategoryListAdapter.CategoryListHolder>(), IOperationData {

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): CategoryListHolder {
        return CategoryListHolder(
                LayoutInflater.from(mContext).inflate(
                        R.layout.adapter_category_item, viewGroup, false))
    }

    override fun onBindViewHolder(holder: CategoryListHolder, position: Int) {
        val categoryListBean = mList!![position]
        holder.textView.text = categoryListBean.name
    }

    override fun getItemCount(): Int {
        return mList?.size ?: 0
    }

    inner class CategoryListHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textView: TextView = itemView.findViewById(R.id.tv_category_name)
    }

    override fun onItemMove(fromPosition: Int, toPosition: Int) {
        if (fromPosition < toPosition) {
            for (i in fromPosition until toPosition) {
                Collections.swap(mList, i, i + 1)
            }
        } else {
            for (i in fromPosition downTo toPosition + 1) {
                Collections.swap(mList, i, i - 1)
            }
        }

        notifyItemMoved(fromPosition, toPosition)
    }

    override fun onItemDismiss(position: Int) {
        //移除数据
        mList!!.removeAt(position)
        notifyItemRemoved(position)
    }
}
