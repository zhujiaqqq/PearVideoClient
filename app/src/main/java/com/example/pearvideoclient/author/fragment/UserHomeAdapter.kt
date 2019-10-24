package com.example.pearvideoclient.author.fragment

import android.app.Activity
import android.content.Intent
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.app.ActivityOptionsCompat.makeSceneTransitionAnimation
import androidx.core.util.Pair
import androidx.core.view.ViewCompat
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.example.pearvideoclient.R
import com.example.pearvideoclient.content.ContentActivity
import com.example.pearvideoclient.entity.AuthorHomeBean
import com.example.pearvideoclient.utils.GlideUtils

/**
 * @Description: java类作用描述
 * @Author: jiazhu
 * @Date: 2019-08-14 10:49
 * @ClassName: UserHomeAdapter
 */
class UserHomeAdapter internal constructor(layoutResId: Int, data: List<AuthorHomeBean.DataListBean>?) :
        BaseQuickAdapter<AuthorHomeBean.DataListBean, BaseViewHolder>(layoutResId, data) {

    override fun convert(helper: BaseViewHolder, item: AuthorHomeBean.DataListBean) {
        helper.setText(R.id.tv_author_name, item.contInfo.userInfo.nickname)
                .setText(R.id.tv_upload_time,
                        mContext.getString(R.string.upload_time, item.pubTime))
                .setText(R.id.tv_video_name,
                        item.contInfo.name)
                .setText(R.id.tv_like,
                        String.format(mContext.getString(R.string.like_count), item.contInfo.commentTimes))
        val ivAuthorImg = helper.getView<ImageView>(R.id.iv_author_img)
        val ivVideoImg = helper.getView<ImageView>(R.id.iv_video_img)


        val textView = helper.getView<TextView>(R.id.tv_video_name)
        textView.transitionName = "textView"

        helper.getView<View>(R.id.rl_parent).setOnClickListener {
            val intent = Intent(mContext, ContentActivity::class.java)
            intent.putExtra("contId", item.contInfo.contId)
            intent.putExtra("userId", item.contInfo.userInfo.userId)
            val namePair = Pair<View, String>(textView, ViewCompat.getTransitionName(textView))

            val option = makeSceneTransitionAnimation(
                    mContext as Activity, namePair)
            mContext.startActivity(intent, option.toBundle())
        }

        helper.addOnClickListener(R.id.ll_favour)
                .addOnClickListener(R.id.ll_comment)
                .addOnClickListener(R.id.ll_share)

        GlideUtils.loadCircleImage(item.contInfo.userInfo.pic, ivAuthorImg)
        GlideUtils.loadWithPlaceHolder(item.contInfo.pic, ivVideoImg, null, null)
    }
}
