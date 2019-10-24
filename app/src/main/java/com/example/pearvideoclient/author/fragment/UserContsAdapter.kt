package com.example.pearvideoclient.author.fragment

import android.app.Activity
import android.content.Intent
import androidx.core.app.ActivityOptionsCompat
import androidx.core.util.Pair
import androidx.core.view.ViewCompat
import android.text.TextUtils
import android.view.View
import android.widget.ImageView
import android.widget.TextView

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.example.pearvideoclient.R
import com.example.pearvideoclient.content.ContentActivity
import com.example.pearvideoclient.entity.UserConts
import com.example.pearvideoclient.utils.GlideUtils

/**
 * @Description: java类作用描述
 * @Author: jiazhu
 * @Date: 2019-08-16 11:56
 * @ClassName: UserContsAdapter
 */
class UserContsAdapter internal constructor(layoutResId: Int, data: List<UserConts.ContListBean>?)
    : BaseQuickAdapter<UserConts.ContListBean, BaseViewHolder>(layoutResId, data) {

    override fun convert(helper: BaseViewHolder, item: UserConts.ContListBean) {
        helper.setText(R.id.tv_video_name, item.name)
                .setText(R.id.tv_video_author_duration, item.userInfo.nickname + " | " + item.duration)
        if (!TextUtils.isEmpty(item.cornerLabelDesc)) {
            helper.setText(R.id.tv_single_broadcast, item.cornerLabelDesc)
            helper.getView<View>(R.id.tv_single_broadcast).visibility = View.VISIBLE
        } else {
            helper.getView<View>(R.id.tv_single_broadcast).visibility = View.GONE
        }

        val ivVideoImg = helper.getView<ImageView>(R.id.iv_video_img)
        GlideUtils.loadWithPlaceHolder(item.pic, ivVideoImg, null, null)

        val textView = helper.getView<TextView>(R.id.tv_video_name)
        textView.transitionName = "textView"

        helper.getView<View>(R.id.rl_parent).setOnClickListener {
            val intent = Intent(mContext, ContentActivity::class.java)
            intent.putExtra("contId", item.contId)
            intent.putExtra("userId", item.userInfo.userId)
            val namePair = Pair<View, String>(textView, ViewCompat.getTransitionName(textView))

            val option = ActivityOptionsCompat.makeSceneTransitionAnimation(
                    mContext as Activity, namePair)
            mContext.startActivity(intent, option.toBundle())
        }
    }
}
