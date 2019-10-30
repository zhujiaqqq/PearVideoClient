package com.example.pearvideoclient.home.fragment

import android.widget.ImageView

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.example.pearvideoclient.R
import com.example.pearvideoclient.entity.RecommendBean
import com.example.pearvideoclient.utils.GlideUtils

/**
 * @Description: java类作用描述
 * @Author: jiazhu
 * @Date: 2019-08-22 16:44
 * @ClassName: ContListAdapter
 */
class ContListAdapter(layoutResId: Int, data: List<RecommendBean.DataListBean.ContListBeanX>?) : BaseQuickAdapter<RecommendBean.DataListBean.ContListBeanX, BaseViewHolder>(layoutResId, data) {

    override fun convert(helper: BaseViewHolder, item: RecommendBean.DataListBean.ContListBeanX) {
        helper.setText(R.id.tv_video_name, item.name)
                .setText(R.id.tv_video_author_duration, item.userInfo.nickname + " | " + item.duration)

        val ivVideoImg = helper.getView<ImageView>(R.id.iv_video_img)
        GlideUtils.loadWithPlaceHolder(item.pic, ivVideoImg, null, null)
        helper.addOnClickListener(R.id.rl_parent)
    }
}
