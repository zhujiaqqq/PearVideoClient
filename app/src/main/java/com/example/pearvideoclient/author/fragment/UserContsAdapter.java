package com.example.pearvideoclient.author.fragment;

import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.transition.DrawableCrossFadeFactory;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.pearvideoclient.R;
import com.example.pearvideoclient.entity.bean.UserConts;

import java.util.List;

/**
 * @Description: java类作用描述
 * @Author: jiazhu
 * @Date: 2019-08-16 11:56
 * @ClassName: UserContsAdapter
 */
public class UserContsAdapter extends
        BaseQuickAdapter<UserConts.ContListBean, BaseViewHolder> {


    private final DrawableCrossFadeFactory drawableCrossFadeFactory;
    private final RequestOptions placeholder;

    public UserContsAdapter(int layoutResId, @Nullable List<UserConts.ContListBean> data) {
        super(layoutResId, data);
        placeholder = new RequestOptions().placeholder(R.drawable.ic_placeholder);
        drawableCrossFadeFactory = new DrawableCrossFadeFactory.Builder(300).setCrossFadeEnabled(true).build();

    }

    @Override
    protected void convert(BaseViewHolder helper, UserConts.ContListBean item) {
        helper.setText(R.id.tv_video_name, item.getName())
                .setText(R.id.tv_video_author_duration, item.getUserInfo().getNickname() + "|" + item.getDuration());
        if (!TextUtils.isEmpty(item.getCornerLabelDesc())) {
            helper.setText(R.id.tv_single_broadcast, item.getCornerLabelDesc());
            helper.getView(R.id.tv_single_broadcast).setVisibility(View.VISIBLE);
        } else {
            helper.getView(R.id.tv_single_broadcast).setVisibility(View.GONE);
        }

        ImageView ivVideoImg = helper.getView(R.id.iv_video_img);
        Glide.with(mContext)
                .load(item.getPic())
                .apply(placeholder)
                .transition(DrawableTransitionOptions.with(drawableCrossFadeFactory))
                .into(ivVideoImg);
    }
}
