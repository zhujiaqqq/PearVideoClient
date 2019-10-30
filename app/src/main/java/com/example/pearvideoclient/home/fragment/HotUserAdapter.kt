package com.example.pearvideoclient.home.fragment

import android.widget.ImageView

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.example.pearvideoclient.R
import com.example.pearvideoclient.entity.LocalContsBean
import com.example.pearvideoclient.utils.GlideUtils

/**
 * @Description: java类作用描述
 * @Author: jiazhu
 * @Date: 2019-08-23 10:39
 * @ClassName: HotUserAdapter
 */
class HotUserAdapter(layoutResId: Int, data: List<LocalContsBean.DataListBean.User>?) : BaseQuickAdapter<LocalContsBean.DataListBean.User, BaseViewHolder>(layoutResId, data) {

    override fun convert(helper: BaseViewHolder, item: LocalContsBean.DataListBean.User) {
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
