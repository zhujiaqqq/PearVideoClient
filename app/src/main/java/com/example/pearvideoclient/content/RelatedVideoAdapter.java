package com.example.pearvideoclient.content;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.pearvideoclient.R;
import com.example.pearvideoclient.entity.bean.content.RelateConts;

import java.util.List;

/**
 * @Description: java类作用描述
 * @Author: jiazhu
 * @Date: 2019-07-29 21:45
 * @ClassName: RelatedVideoAdapter
 */
public class RelatedVideoAdapter extends BaseQuickAdapter<RelateConts, BaseViewHolder> {
    private MyListener listener;

    public void setListener(MyListener listener) {
        this.listener = listener;
    }

    public RelatedVideoAdapter(int layoutResId, @Nullable List<RelateConts> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, RelateConts item) {
        helper.setText(R.id.tv_video_name, item.getName());
        helper.setText(R.id.tv_user_name, item.getUserInfo().getNickname());
        ImageView ivVideoImg = helper.getView(R.id.iv_video_img);
        Glide.with(mContext).load(item.getPic()).into(ivVideoImg);
        RelativeLayout rlParent = helper.getView(R.id.rl_parent);
        rlParent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onClick(item.getContId());
                }
            }
        });
    }

    public interface MyListener {
        /**
         * 点击时间
         *
         * @param contId 内容id
         */
        void onClick(String contId);
    }
}
