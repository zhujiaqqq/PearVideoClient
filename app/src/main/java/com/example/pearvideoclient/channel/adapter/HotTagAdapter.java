package com.example.pearvideoclient.channel.adapter;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.pearvideoclient.R;
import com.example.pearvideoclient.entity.CategoryContsBean;

import java.util.List;

/**
 * @Description: java类作用描述
 * @Author: jiazhu
 * @Date: 2019-08-30 15:38
 * @ClassName: HotTagAdapter
 */
public class HotTagAdapter extends BaseQuickAdapter<CategoryContsBean.HotTagListBean, BaseViewHolder> {
    public HotTagAdapter(int layoutResId, @Nullable List<CategoryContsBean.HotTagListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, CategoryContsBean.HotTagListBean item) {
        helper.setText(R.id.tv_tag, item.getName());
    }
}
