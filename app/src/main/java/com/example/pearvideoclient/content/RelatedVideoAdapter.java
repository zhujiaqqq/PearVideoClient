package com.example.pearvideoclient.content;

import androidx.annotation.Nullable;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.pearvideoclient.R;
import com.example.pearvideoclient.entity.content.AbstractConts;
import com.example.pearvideoclient.entity.content.HotConts;
import com.example.pearvideoclient.entity.content.RelateConts;
import com.example.pearvideoclient.utils.GlideUtils;

import java.util.List;

/**
 * @Description: java类作用描述
 * @Author: jiazhu
 * @Date: 2019-07-29 21:45
 * @ClassName: RelatedVideoAdapter
 */
public class RelatedVideoAdapter extends BaseQuickAdapter<AbstractConts, BaseViewHolder> {
    private MyListener listener;

    public void setListener(MyListener listener) {
        this.listener = listener;
    }

    public RelatedVideoAdapter(int layoutResId, @Nullable List<AbstractConts> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, AbstractConts item) {
        if (item instanceof RelateConts) {
            helper.setText(R.id.tv_video_name, ((RelateConts) item).getName());
            helper.setText(R.id.tv_user_name, ((RelateConts) item).getUserInfo().getNickname());
            ImageView ivVideoImg = helper.getView(R.id.iv_video_img);
            GlideUtils.load(((RelateConts) item).getPic(), ivVideoImg);
            RelativeLayout rlParent = helper.getView(R.id.rl_parent);
            rlParent.setOnClickListener(v -> {
                if (listener != null) {
                    listener.onClick(((RelateConts) item).getContId());
                }
            });
        } else if (item instanceof HotConts) {
            helper.setText(R.id.tv_video_name, ((HotConts) item).getName());
            helper.setText(R.id.tv_user_name, ((HotConts) item).getUserInfo().getNickname());
            ImageView ivVideoImg = helper.getView(R.id.iv_video_img);
            GlideUtils.load(((HotConts) item).getPic(), ivVideoImg);
            RelativeLayout rlParent = helper.getView(R.id.rl_parent);
            rlParent.setOnClickListener(v -> {
                if (listener != null) {
                    listener.onClick(((HotConts) item).getContId());
                }
            });
        }

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
