package com.example.pearvideoclient.content

import android.widget.ImageView
import android.widget.RelativeLayout

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.example.pearvideoclient.R
import com.example.pearvideoclient.entity.content.AbstractConts
import com.example.pearvideoclient.entity.content.HotConts
import com.example.pearvideoclient.entity.content.RelateConts
import com.example.pearvideoclient.utils.GlideUtils

/**
 * @Description: java类作用描述
 * @Author: jiazhu
 * @Date: 2019-07-29 21:45
 * @ClassName: RelatedVideoAdapter
 */
class RelatedVideoAdapter(layoutResId: Int, data: List<AbstractConts>?) : BaseQuickAdapter<AbstractConts, BaseViewHolder>(layoutResId, data) {
    private var listener: MyListener? = null

    fun setListener(listener: MyListener) {
        this.listener = listener
    }

    override fun convert(helper: BaseViewHolder, item: AbstractConts) {
        if (item is RelateConts) {
            helper.setText(R.id.tv_video_name, item.name)
            helper.setText(R.id.tv_user_name, item.userInfo.nickname)
            val ivVideoImg = helper.getView<ImageView>(R.id.iv_video_img)
            GlideUtils.load(item.pic, ivVideoImg)
            val rlParent = helper.getView<RelativeLayout>(R.id.rl_parent)
            rlParent.setOnClickListener {
                if (listener != null) {
                    listener!!.onClick(item.contId)
                }
            }
        } else if (item is HotConts) {
            helper.setText(R.id.tv_video_name, item.name)
            helper.setText(R.id.tv_user_name, item.userInfo.nickname)
            val ivVideoImg = helper.getView<ImageView>(R.id.iv_video_img)
            GlideUtils.load(item.pic, ivVideoImg)
            val rlParent = helper.getView<RelativeLayout>(R.id.rl_parent)
            rlParent.setOnClickListener {
                if (listener != null) {
                    listener!!.onClick(item.contId)
                }
            }
        }

    }

    interface MyListener {
        /**
         * 点击时间
         *
         * @param contId 内容id
         */
        fun onClick(contId: String)
    }
}
