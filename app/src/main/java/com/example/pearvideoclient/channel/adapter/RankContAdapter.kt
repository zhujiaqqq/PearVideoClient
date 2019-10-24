package com.example.pearvideoclient.channel.adapter

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
import com.example.pearvideoclient.entity.CategoryContsBean
import com.example.pearvideoclient.utils.GlideUtils

/**
 * @Description: java类作用描述
 * @Author: jiazhu
 * @Date: 2019-08-30 11:29
 * @ClassName: RankContAdapter
 */
class RankContAdapter(layoutResId: Int, data: List<CategoryContsBean.ContListBean>?) :
        BaseQuickAdapter<CategoryContsBean.ContListBean, BaseViewHolder>(layoutResId, data) {

    override fun convert(helper: BaseViewHolder, item: CategoryContsBean.ContListBean) {
        helper.setText(R.id.tv_video_name, item.name)
                .setText(R.id.tv_video_author_duration, item.userInfo.nickname + " | " + item.duration)
                .setText(R.id.tv_rank, (helper.adapterPosition + 1).toString() + "")

        if (helper.adapterPosition > 2) {
            helper.setVisible(R.id.tv_rank, false)
        } else {
            helper.setVisible(R.id.tv_rank, true)
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
