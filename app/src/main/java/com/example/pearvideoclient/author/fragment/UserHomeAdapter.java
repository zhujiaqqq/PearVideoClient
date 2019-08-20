package com.example.pearvideoclient.author.fragment;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.pearvideoclient.R;
import com.example.pearvideoclient.entity.AuthorHomeBean;
import com.example.pearvideoclient.utils.GlideUtils;

import java.util.List;

/**
 * @Description: java类作用描述
 * @Author: jiazhu
 * @Date: 2019-08-14 10:49
 * @ClassName: UserHomeAdapter
 */
public class UserHomeAdapter extends
        BaseQuickAdapter<AuthorHomeBean.DataListBean, BaseViewHolder> {

    public UserHomeAdapter(int layoutResId, @Nullable List<AuthorHomeBean.DataListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, AuthorHomeBean.DataListBean item) {
        helper.setText(R.id.tv_author_name, item.getContInfo().getUserInfo().getNickname())
                .setText(R.id.tv_upload_time,
                        mContext.getString(R.string.upload_time, item.getPubTime()))
                .setText(R.id.tv_video_name,
                        item.getContInfo().getName())
                .setText(R.id.tv_like,
                        String.format(mContext.getString(R.string.like_count), item.getContInfo().getCommentTimes()));
        ImageView ivAuthorImg = helper.getView(R.id.iv_author_img);
        ImageView ivVideoImg = helper.getView(R.id.iv_video_img);

        GlideUtils.loadCircleImage(item.getContInfo().getUserInfo().getPic(), ivAuthorImg);
        GlideUtils.loadWithPlaceHolder(item.getContInfo().getPic(), ivVideoImg, null, null);
    }
}
