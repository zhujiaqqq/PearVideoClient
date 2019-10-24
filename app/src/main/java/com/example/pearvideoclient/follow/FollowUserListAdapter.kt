package com.example.pearvideoclient.follow

import android.graphics.drawable.Drawable
import android.widget.ImageView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.example.pearvideoclient.R
import com.example.pearvideoclient.entity.MyFollowContBean
import com.example.pearvideoclient.follow.FollowPresenter.Companion.MORE_USER
import com.example.pearvideoclient.utils.GlideUtils
import java.util.*


/**
 * @Description: java类作用描述
 * @Author: jiazhu
 * @Date: 2019-08-10 21:00
 * @ClassName: FollowUserListAdapter
 */
class FollowUserListAdapter internal constructor(layoutResId: Int, data: List<MyFollowContBean.FollowUserListBean>?)
    : BaseQuickAdapter<MyFollowContBean.FollowUserListBean, BaseViewHolder>(layoutResId, data) {

    override fun convert(helper: BaseViewHolder, item: MyFollowContBean.FollowUserListBean) {
        helper.setText(R.id.tv_user_name, item.nickname)
        val ivUserImage = helper.getView<ImageView>(R.id.iv_user_img)
        if (MORE_USER == item.userId) {
            GlideUtils.load(Objects.requireNonNull<Drawable>(mContext.getDrawable(R.drawable.ic_arrow_right)), ivUserImage)
        } else {
            GlideUtils.loadCircleImage(item.pic, ivUserImage)
        }

        helper.addOnClickListener(R.id.rl_parent)
    }
}
