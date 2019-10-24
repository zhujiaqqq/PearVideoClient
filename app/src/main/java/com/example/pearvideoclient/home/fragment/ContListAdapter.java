package com.example.pearvideoclient.home.fragment;

import androidx.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.pearvideoclient.R;
import com.example.pearvideoclient.entity.RecommendBean;
import com.example.pearvideoclient.utils.GlideUtils;

import java.util.List;

/**
 * @Description: java类作用描述
 * @Author: jiazhu
 * @Date: 2019-08-22 16:44
 * @ClassName: ContListAdapter
 */
public class ContListAdapter extends BaseQuickAdapter<RecommendBean.DataListBean.ContListBeanX, BaseViewHolder> {
    public ContListAdapter(int layoutResId, @Nullable List<RecommendBean.DataListBean.ContListBeanX> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, RecommendBean.DataListBean.ContListBeanX item) {
        helper.setText(R.id.tv_video_name, item.getName())
                .setText(R.id.tv_video_author_duration, item.getUserInfo().getNickname() + " | " + item.getDuration());

        ImageView ivVideoImg = helper.getView(R.id.iv_video_img);
        GlideUtils.INSTANCE.loadWithPlaceHolder(item.getPic(), ivVideoImg, null, null);
        helper.addOnClickListener(R.id.rl_parent);
    }
}
