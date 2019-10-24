package com.example.pearvideoclient.author

import android.app.Activity
import android.content.Intent
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.app.ActivityOptionsCompat
import androidx.core.util.Pair
import androidx.core.view.ViewCompat

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.example.pearvideoclient.R
import com.example.pearvideoclient.content.ContentActivity
import com.example.pearvideoclient.entity.AlbumContBean
import com.example.pearvideoclient.utils.GlideUtils

/**
 * @Description: java类作用描述
 * @Author: jiazhu
 * @Date: 2019-08-28 20:07
 * @ClassName: AlbumAdapter
 */
class AlbumAdapter internal constructor(layoutResId: Int, data: List<AlbumContBean.ContListBean>?)
    : BaseQuickAdapter<AlbumContBean.ContListBean, BaseViewHolder>(layoutResId, data) {

    override fun convert(helper: BaseViewHolder, item: AlbumContBean.ContListBean) {
        helper.setText(R.id.tv_video_name, item.name)
                .setText(R.id.tv_video_author_duration, item.userInfo.nickname + " | " + item.duration)
        val ivVideoImg = helper.getView<ImageView>(R.id.iv_video_img)
        helper.getView<View>(R.id.tv_single_broadcast).visibility = View.GONE

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
