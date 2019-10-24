package com.example.pearvideoclient.channel.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.example.pearvideoclient.R
import com.example.pearvideoclient.entity.CategoryContsBean

/**
 * @Description: java类作用描述
 * @Author: jiazhu
 * @Date: 2019-08-30 15:38
 * @ClassName: HotTagAdapter
 */
class HotTagAdapter(layoutResId: Int, data: List<CategoryContsBean.HotTagListBean>?) :
        BaseQuickAdapter<CategoryContsBean.HotTagListBean, BaseViewHolder>(layoutResId, data) {

    override fun convert(helper: BaseViewHolder, item: CategoryContsBean.HotTagListBean) {
        helper.setText(R.id.tv_tag, item.name)
    }
}
