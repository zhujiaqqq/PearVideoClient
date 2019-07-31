package com.example.pearvideoclient.channel;

import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.pearvideoclient.R;
import com.example.pearvideoclient.entity.bean.CategoryBean;
import com.example.pearvideoclient.entity.bean.CategoryContsBean;

import java.util.Collections;
import java.util.List;

/**
 * @author zhujiaqqq
 * @date 2019-07-18
 */
public class CategoryContsAdapter
        extends BaseQuickAdapter<CategoryContsBean.ContListBean, BaseViewHolder> {

    private MyListener listener;

    public void setListener(MyListener listener) {
        this.listener = listener;
    }

    public CategoryContsAdapter(int layoutResId, @Nullable List<CategoryContsBean.ContListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, CategoryContsBean.ContListBean item) {
        ImageView ivVideoImg = helper.getView(R.id.iv_video_img);
        Glide.with(mContext).load(item.getPic()).into(ivVideoImg);
        helper.setText(R.id.tv_video_name, item.getName());
        helper.setText(R.id.tv_video_author_duration, item.getUserInfo().getNickname() + " | " + item.getDuration());
        if (!TextUtils.isEmpty(item.getCornerLabelDesc())) {
            helper.setText(R.id.tv_single_broadcast, item.getCornerLabelDesc());
            helper.getView(R.id.tv_single_broadcast).setVisibility(View.VISIBLE);
        } else {
            helper.getView(R.id.tv_single_broadcast).setVisibility(View.GONE);
        }

        helper.getView(R.id.rl_parent).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onClick(item);
                }
            }
        });
    }

    public interface MyListener {
        /**
         * 点击事件
         *
         * @param bean item
         */
        void onClick(CategoryContsBean.ContListBean bean);
    }

}
