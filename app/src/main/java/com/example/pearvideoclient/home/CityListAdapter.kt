package com.example.pearvideoclient.home

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.example.pearvideoclient.R
import com.example.pearvideoclient.entity.CityEntity

/**
 * @Description: java类作用描述
 * @Author: jiazhu
 * @Date: 2019-08-24 22:52
 * @ClassName: CityListAdapter
 */
class CityListAdapter
/**
 * Same as QuickAdapter#QuickAdapter(Context,int) but with
 * some initialization data.
 *
 * @param data A new list is created out of this one to avoid mutable list
 */
(data: List<CityEntity>) : BaseMultiItemQuickAdapter<CityEntity, BaseViewHolder>(data) {
    init {
        addItemType(CityEntity.TYPE_CITY, R.layout.adapter_city_item)
        addItemType(CityEntity.TYPE_INDEX, R.layout.adapter_index_item)
    }

    override fun convert(helper: BaseViewHolder, item: CityEntity) {
        when (helper.itemViewType) {
            CityEntity.TYPE_CITY -> cityTypeConvert(helper, item)
            CityEntity.TYPE_INDEX -> indexTypeConvert(helper, item)
            else -> {
            }
        }
    }

    private fun cityTypeConvert(helper: BaseViewHolder, item: CityEntity) {
        val channelBean = item.channelBean
        helper.setText(R.id.tv_city, channelBean.name)
        helper.addOnClickListener(R.id.rl_parent)
    }

    private fun indexTypeConvert(helper: BaseViewHolder, item: CityEntity) {
        val index = item.index
        helper.setText(R.id.tv_index, index)
    }

}
