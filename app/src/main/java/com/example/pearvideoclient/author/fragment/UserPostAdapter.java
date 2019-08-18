package com.example.pearvideoclient.author.fragment;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.pearvideoclient.R;
import com.example.pearvideoclient.entity.UserPostsBean;

import java.util.List;

/**
 * @Description: java类作用描述
 * @Author: jiazhu
 * @Date: 2019-08-18 14:44
 * @ClassName: UserPostAdapter
 */
public class UserPostAdapter extends BaseQuickAdapter<UserPostsBean.PostListBean, BaseViewHolder> {
    public UserPostAdapter(int layoutResId, @Nullable List<UserPostsBean.PostListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, UserPostsBean.PostListBean item) {
        helper.setText(R.id.tv_commit_times, item.getCommentTimes())
                .setText(R.id.tv_post, item.getName())
                .setText(R.id.tv_user_name, item.getUserInfo().getNickname())
                .setText(R.id.tv_pub_time, item.getPubTime());
        ImageView ivUserImg = helper.getView(R.id.iv_user_img);

        Glide.with(mContext).asBitmap()
                .apply(RequestOptions.bitmapTransform(new CircleCrop())
                        .diskCacheStrategy(DiskCacheStrategy.NONE))
                .load(item.getUserInfo().getPic())
                .into(ivUserImg);
    }
}
