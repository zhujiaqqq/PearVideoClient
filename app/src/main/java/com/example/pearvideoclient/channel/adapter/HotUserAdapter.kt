package com.example.pearvideoclient.channel.adapter

import android.widget.ImageView

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.example.pearvideoclient.R
import com.example.pearvideoclient.entity.CategoryContsBean
import com.example.pearvideoclient.utils.GlideUtils

/**
 * @Description: java类作用描述
 * @Author: jiazhu
 * @Date: 2019-08-30 15:01
 * @ClassName: HotUserAdapter
 */
class HotUserAdapter(layoutResId: Int, data: List<CategoryContsBean.HotUserBean>?) :
        BaseQuickAdapter<CategoryContsBean.HotUserBean, BaseViewHolder>(layoutResId, data) {

    override fun convert(helper: BaseViewHolder, item: CategoryContsBean.HotUserBean) {
        helper.setText(R.id.tv_user_name, item.nickname)
                .setText(R.id.tv_user_signature, item.signature)
        val ivUserImg = helper.getView<ImageView>(R.id.iv_user_img)
        GlideUtils.loadCircleImage(item.pic, ivUserImg)
        if ("0" == item.isFollow) {
            helper.setText(R.id.tv_follow, mContext.getString(R.string.tv_follow))
            helper.setBackgroundRes(R.id.tv_follow, R.drawable.bg_round_50_yellow)
            helper.addOnClickListener(R.id.tv_follow)
        } else {
            helper.setText(R.id.tv_follow, mContext.getString(R.string.tv_followed))
            helper.setBackgroundRes(R.id.tv_follow, R.drawable.bg_round_f2)
        }

    }
}
