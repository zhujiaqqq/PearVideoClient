package com.example.pearvideoclient.screen;

import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.pearvideoclient.R;
import com.example.pearvideoclient.entity.PaikeFineVideoBean;
import com.example.pearvideoclient.utils.GlideUtils;

import java.util.List;

/**
 * @Description: java类作用描述
 * @Author: jiazhu
 * @Date: 2019-08-26 11:32
 * @ClassName: VideoAdapter
 */
public class VideoAdapter extends BaseQuickAdapter<PaikeFineVideoBean.VideoBean, BaseViewHolder> {
    public VideoAdapter(int layoutResId, @Nullable List<PaikeFineVideoBean.VideoBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, PaikeFineVideoBean.VideoBean item) {
        helper.setText(R.id.tv_video_name, item.getTitle())
                .setText(R.id.tv_video_author_duration, item.getNickname() + " | " + item.getDuration())
                .setText(R.id.tv_award, item.getAward());
        ImageView ivVideImg = helper.getView(R.id.iv_video_img);
        GlideUtils.loadWithPlaceHolder(item.getPic(), ivVideImg, null, null);

        helper.addOnClickListener(R.id.rl_parent);
    }
}