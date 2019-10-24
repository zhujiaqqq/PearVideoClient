package com.example.pearvideoclient.screen

import android.widget.ImageView

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.example.pearvideoclient.R
import com.example.pearvideoclient.entity.PaikeFineVideoBean
import com.example.pearvideoclient.utils.GlideUtils

/**
 * @Description: java类作用描述
 * @Author: jiazhu
 * @Date: 2019-08-26 11:32
 * @ClassName: VideoAdapter
 */
class VideoAdapter internal constructor(layoutResId: Int, data: List<PaikeFineVideoBean.VideoBean>?)
    : BaseQuickAdapter<PaikeFineVideoBean.VideoBean, BaseViewHolder>(layoutResId, data) {

    override fun convert(helper: BaseViewHolder, item: PaikeFineVideoBean.VideoBean) {
        helper.setText(R.id.tv_video_name, item.title)
                .setText(R.id.tv_video_author_duration, item.nickname + " | " + item.duration)
                .setText(R.id.tv_award, item.award)
        val ivVideoImg = helper.getView<ImageView>(R.id.iv_video_img)
        GlideUtils.loadWithPlaceHolder(item.pic, ivVideoImg, null, null)

        helper.addOnClickListener(R.id.rl_parent)
    }
}