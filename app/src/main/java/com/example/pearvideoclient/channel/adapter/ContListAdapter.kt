package com.example.pearvideoclient.channel.adapter

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.example.pearvideoclient.R
import com.example.pearvideoclient.entity.CategoryContsBean
import com.example.pearvideoclient.entity.ContEntity

/**
 * @Description: java类作用描述
 * @Author: jiazhu
 * @Date: 2019-08-30 10:25
 * @ClassName: ContListAdapter
 */
class ContListAdapter(data: List<ContEntity>) :
        BaseMultiItemQuickAdapter<ContEntity, BaseViewHolder>(data) {
    init {
        addItemType(ContEntity.TYPE_HOT_CONT, R.layout.adapter_hot_cont_item)
        addItemType(ContEntity.TYPE_HOT_TAG, R.layout.adapter_hot_tag_item)
        addItemType(ContEntity.TYPE_RANK_CONT, R.layout.adapter_rank_cont_item)
        addItemType(ContEntity.TYPE_HOT_USER, R.layout.adapter_hot_user_item)
        addItemType(ContEntity.TYPE_NEW_CONT, R.layout.adapter_new_cont_item)
    }

    override fun convert(helper: BaseViewHolder, item: ContEntity) {
        when (helper.itemViewType) {
            ContEntity.TYPE_HOT_CONT -> hotContConvert(helper, item)
            ContEntity.TYPE_HOT_TAG -> hotTagConvert(helper, item)
            ContEntity.TYPE_HOT_USER -> hotUserConvert(helper, item)
            ContEntity.TYPE_NEW_CONT -> newContConvert(helper, item)
            ContEntity.TYPE_RANK_CONT -> rankContConvert(helper, item)
        }
    }

    private fun rankContConvert(helper: BaseViewHolder, item: ContEntity) {
        val contListBeans = item.contListBeans
        val rvRankContList = helper.getView<RecyclerView>(R.id.rv_rank_cont_list)
        rvRankContList.layoutManager = LinearLayoutManager(mContext, RecyclerView.HORIZONTAL, false)
        val rankContAdapter = RankContAdapter(R.layout.inner_adapter_rank_cont_item, contListBeans)
        rvRankContList.adapter = rankContAdapter
    }

    private fun newContConvert(helper: BaseViewHolder, item: ContEntity) {
        val contListBeans = item.contListBeans
        val rvHotList = helper.getView<RecyclerView>(R.id.rv_new_cont_list)
        rvHotList.layoutManager = GridLayoutManager(mContext, 2)
        val hotContAdapter = HotContAdapter(R.layout.inner_adapter_cont_item, contListBeans)
        rvHotList.adapter = hotContAdapter
    }

    private fun hotUserConvert(helper: BaseViewHolder, item: ContEntity) {
        val hotUserBeans = item.hotUserBeans
        val rvHotUserList = helper.getView<RecyclerView>(R.id.rv_hot_user_list)
        rvHotUserList.layoutManager = LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false)
        val hotUserAdapter = HotUserAdapter(R.layout.adapter_user_hot_item, hotUserBeans)
        rvHotUserList.adapter = hotUserAdapter
    }

    private fun hotTagConvert(helper: BaseViewHolder, item: ContEntity) {
        val hotTagListBeans = item.hotTagListBeans
        val rvHotTagList = helper.getView<RecyclerView>(R.id.rv_hot_tag_list)
        rvHotTagList.layoutManager = LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false)
        val hotTagAdapter = HotTagAdapter(R.layout.inner_adapter_hot_tag_item, hotTagListBeans)
        rvHotTagList.adapter = hotTagAdapter
    }

    private fun hotContConvert(helper: BaseViewHolder, item: ContEntity) {
        val contListBeans = item.contListBeans
        val rvHotList = helper.getView<RecyclerView>(R.id.rv_hot_list)
        rvHotList.layoutManager = GridLayoutManager(mContext, 2)
        val hotContAdapter = HotContAdapter(R.layout.inner_adapter_cont_item, contListBeans)
        rvHotList.adapter = hotContAdapter
    }
}
