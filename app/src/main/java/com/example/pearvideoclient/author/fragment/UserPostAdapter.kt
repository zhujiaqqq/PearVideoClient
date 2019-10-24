package com.example.pearvideoclient.author.fragment

import android.widget.ImageView

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.example.pearvideoclient.R
import com.example.pearvideoclient.entity.UserPostsBean
import com.example.pearvideoclient.utils.GlideUtils

/**
 * @Description: java类作用描述
 * @Author: jiazhu
 * @Date: 2019-08-18 14:44
 * @ClassName: UserPostAdapter
 */
class UserPostAdapter internal constructor(layoutResId: Int, data: List<UserPostsBean.PostListBean>?) : BaseQuickAdapter<UserPostsBean.PostListBean, BaseViewHolder>(layoutResId, data) {

    override fun convert(helper: BaseViewHolder, item: UserPostsBean.PostListBean) {
        helper.setText(R.id.tv_commit_times, item.commentTimes)
                .setText(R.id.tv_post, item.name)
                .setText(R.id.tv_user_name, item.userInfo.nickname)
                .setText(R.id.tv_pub_time, item.pubTime)
        val ivUserImg = helper.getView<ImageView>(R.id.iv_user_img)
        GlideUtils.loadCircleImage(item.userInfo.pic, ivUserImg)
    }
}
