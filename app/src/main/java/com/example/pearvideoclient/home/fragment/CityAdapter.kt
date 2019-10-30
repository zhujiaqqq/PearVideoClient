package com.example.pearvideoclient.home.fragment

import androidx.core.app.ActivityOptionsCompat
import androidx.core.util.Pair
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import android.app.Activity
import android.content.Intent
import android.view.View
import android.widget.ImageView
import android.widget.TextView

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.example.pearvideoclient.R
import com.example.pearvideoclient.content.ContentActivity
import com.example.pearvideoclient.entity.LocalContEntity
import com.example.pearvideoclient.entity.LocalContsBean
import com.example.pearvideoclient.utils.GlideUtils

import androidx.core.app.ActivityOptionsCompat.makeSceneTransitionAnimation

/**
 * @Description: java类作用描述
 * @Author: jiazhu
 * @Date: 2019-08-23 09:49
 * @ClassName: CityAdapter
 */
class CityAdapter
/**
 * Same as QuickAdapter#QuickAdapter(Context,int) but with
 * some initialization data.
 *
 * @param data A new list is created out of this one to avoid mutable list
 */
internal constructor(data: List<LocalContEntity>) : BaseMultiItemQuickAdapter<LocalContEntity, BaseViewHolder>(data) {
    init {
        addItemType(LocalContEntity.ITEM_TYPE_13, R.layout.adapter_city_type_13)
        addItemType(LocalContEntity.ITEM_TYPE_17, R.layout.adapter_city_type_17)
    }

    override fun convert(helper: BaseViewHolder, item: LocalContEntity) {
        when (helper.itemViewType) {
            LocalContEntity.ITEM_TYPE_13 -> convert13(helper, item)
            LocalContEntity.ITEM_TYPE_17 -> convert17(helper, item)
            else -> {
            }
        }
    }

    private fun convert13(helper: BaseViewHolder, item: LocalContEntity) {
        val contListBean = item.cont.contList[0]
        helper.setText(R.id.tv_star, contListBean.praiseTimes)
                .setText(R.id.tv_post, contListBean.commentTimes)
                .setText(R.id.tv_local, contListBean.geo.showName)
                .setText(R.id.tv_video_name, contListBean.name)
        val ivVideoImg = helper.getView<ImageView>(R.id.iv_video_img)
        GlideUtils.loadWithPlaceHolder(contListBean.pic, ivVideoImg, null, null)

        helper.addOnClickListener(R.id.iv_share)
                .addOnClickListener(R.id.iv_more)
                .addOnClickListener(R.id.tv_star)
                .addOnClickListener(R.id.tv_post)

        val textView = helper.getView<TextView>(R.id.tv_video_name)
        textView.transitionName = "textView"
        helper.getView<View>(R.id.rl_parent).setOnClickListener { v ->
            val intent = Intent(mContext, ContentActivity::class.java)
            val bean = item.cont.contList[0]
            intent.putExtra("contId", bean.contId)
            intent.putExtra("userId", bean.userInfo.userId)
            val namePair = Pair<View, String>(textView, ViewCompat.getTransitionName(textView))

            val option = makeSceneTransitionAnimation(
                    mContext as Activity, namePair)
            mContext.startActivity(intent, option.toBundle())
        }
    }

    private fun convert17(helper: BaseViewHolder, item: LocalContEntity) {
        val cont = item.cont
        val userList = cont.userList
        helper.setText(R.id.tv_hot, cont.nodeName)

        val rvHotUserList = helper.getView<RecyclerView>(R.id.rv_hot_user_list)
        rvHotUserList.layoutManager = LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false)
        val hotUserAdapter = HotUserAdapter(R.layout.adapter_user_hot_item, userList)
        hotUserAdapter.setOnItemClickListener { adapter, view, position ->
            if (view.id == R.id.tv_follow) {
                // TODO: 2019-08-23 关注
                //
            }
        }
        rvHotUserList.adapter = hotUserAdapter
    }
}
