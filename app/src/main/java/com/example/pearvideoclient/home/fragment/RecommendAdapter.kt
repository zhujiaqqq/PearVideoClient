package com.example.pearvideoclient.home.fragment

import android.content.Intent
import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.example.pearvideoclient.R
import com.example.pearvideoclient.content.ContentActivity
import com.example.pearvideoclient.entity.RecommendEntity
import com.example.pearvideoclient.utils.GlideUtils
import com.example.pearvideoclient.view.video.VideoBanner
import java.util.*

/**
 * @Description: java类作用描述
 * @Author: jiazhu
 * @Date: 2019-08-22 10:00
 * @ClassName: RecommendAdapter
 */
class RecommendAdapter
/**
 * Same as QuickAdapter#QuickAdapter(Context,int) but with
 * some initialization data.
 *
 * @param data A new list is created out of this one to avoid mutable list
 */
internal constructor(data: List<RecommendEntity>) : BaseMultiItemQuickAdapter<RecommendEntity, BaseViewHolder>(data) {
    init {
        addItemType(RecommendEntity.NODE_TYPE_1, R.layout.adapter_recommend_type_1)
        addItemType(RecommendEntity.NODE_TYPE_13, R.layout.adapter_recommend_type_13)
        addItemType(RecommendEntity.NODE_TYPE_4, R.layout.adapter_recommend_type_4)
    }

    override fun convert(helper: BaseViewHolder, item: RecommendEntity) {
        when (helper.itemViewType) {
            RecommendEntity.NODE_TYPE_1 -> convertType1(helper, item)
            RecommendEntity.NODE_TYPE_4 -> convertType4(helper, item)
            RecommendEntity.NODE_TYPE_13 -> convertType13(helper, item)
            else -> {
            }
        }
    }

    private fun convertType4(helper: BaseViewHolder, item: RecommendEntity) {
        val dataBean = item.dataBean
        val contList = dataBean.contList
        helper.setText(R.id.tv_node_name, dataBean.nodeName)
        helper.addOnClickListener(R.id.rl_more)

        val rvContList = helper.getView<RecyclerView>(R.id.rv_cont_list)
        rvContList.layoutManager = LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false)
        val contListAdapter = ContListAdapter(R.layout.adapter_cont_item, contList)
        rvContList.adapter = contListAdapter
        contListAdapter.setOnItemChildClickListener { _, view, position ->
            if (view.id == R.id.rl_parent) {
                val contListBeanX = contList[position]
                val intent = Intent(mContext, ContentActivity::class.java)
                intent.putExtra("contId", contListBeanX.contId)
                intent.putExtra("userId", contListBeanX.userInfo.userId)
                mContext.startActivity(intent)
            }
        }
    }

    private fun convertType13(helper: BaseViewHolder, item: RecommendEntity) {
        val cont = item.dataBean.contList[0]
        helper.setText(R.id.tv_video_name, cont.name)
                .setText(R.id.tv_user_name, cont.userInfo.nickname)
                .setText(R.id.tv_star, cont.praiseTimes)
                .setText(R.id.tv_post, cont.commentTimes)

        helper.addOnClickListener(R.id.iv_more)
                .addOnClickListener(R.id.tv_post)
                .addOnClickListener(R.id.tv_star)
                .addOnClickListener(R.id.iv_share)
                .addOnClickListener(R.id.rl_parent)

        val ivVideoImg = helper.getView<ImageView>(R.id.iv_video_img)
        val ivUserImg = helper.getView<ImageView>(R.id.iv_user_img)
        GlideUtils.loadCircleImage(cont.userInfo.pic, ivUserImg)
        GlideUtils.loadWithPlaceHolder(cont.pic, ivVideoImg, null, null)

        helper.getView<View>(R.id.rl_parent).setOnClickListener {
            val intent = Intent(mContext, ContentActivity::class.java)
            val contBean = item.dataBean.contList[0]
            intent.putExtra("contId", contBean.contId)
            intent.putExtra("userId", contBean.userInfo.userId)
            mContext.startActivity(intent)
        }
    }

    private fun convertType1(helper: BaseViewHolder, item: RecommendEntity) {
        val videoBanner = helper.getView<VideoBanner>(R.id.video_banner)
        val contList = item.dataBean.contList
        val urls = ArrayList<String>()
        for (cont in contList) {
            urls.add(cont.coverVideo)
        }
        videoBanner.setDataList(urls)
        videoBanner.startBanner()
        videoBanner.startAutoPlay()
    }
}
