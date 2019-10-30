package com.example.pearvideoclient.home.fragment

import android.view.View
import android.widget.ImageView
import android.widget.TextView

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.example.pearvideoclient.R
import com.example.pearvideoclient.entity.NewsBean
import com.example.pearvideoclient.entity.NewsEntity
import com.example.pearvideoclient.utils.GlideUtils

/**
 * @Description: java类作用描述
 * @Author: jiazhu
 * @Date: 2019-08-21 15:41
 * @ClassName: VientianeAdapter
 */
class VientianeAdapter
/**
 * Same as QuickAdapter#QuickAdapter(Context,int) but with
 * some initialization data.
 *
 * @param data A new list is created out of this one to avoid mutable list
 */
(data: List<NewsEntity>) : BaseMultiItemQuickAdapter<NewsEntity, BaseViewHolder>(data) {
    init {
        addItemType(NewsEntity.TYPE_BIG, R.layout.adapter_vientiane_big_item)
        addItemType(NewsEntity.TYPE_SMALL, R.layout.adapter_vientiane_small_item)
    }

    override fun convert(helper: BaseViewHolder, item: NewsEntity) {
        val bean = item.dataEntity
        val tvTopping = helper.getView<TextView>(R.id.tv_topping)
        val ivVideoImg = helper.getView<ImageView>(R.id.iv_video_img)
        helper.setText(R.id.tv_video_name, bean.newsInfo.name)
                .setText(R.id.tv_user_name, bean.newsInfo.authorName)
        if (mContext.getString(R.string.tv_topping) == bean.newsInfo.cornerLabelDesc) {
            tvTopping.visibility = View.VISIBLE
        } else {
            tvTopping.visibility = View.GONE
        }
        GlideUtils.loadWithPlaceHolder(bean.newsInfo.pic, ivVideoImg, null, null)
    }
}
