package com.example.pearvideoclient.follow;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.transition.DrawableCrossFadeFactory;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.pearvideoclient.R;
import com.example.pearvideoclient.entity.bean.MyFollowContBean;

import java.util.List;

/**
 * @author zhujiaqqq
 * @date 2019-07-15
 */
public class FollowInfoListAdapter extends BaseQuickAdapter<MyFollowContBean.DataListBean, BaseViewHolder> {

    private RequestOptions placeholder;
    private final DrawableCrossFadeFactory drawableCrossFadeFactory;

    public FollowInfoListAdapter(int layoutResId, @Nullable List<MyFollowContBean.DataListBean> data) {
        super(layoutResId, data);

        placeholder = new RequestOptions().placeholder(R.drawable.ic_placeholder);
        drawableCrossFadeFactory = new DrawableCrossFadeFactory.Builder(300).setCrossFadeEnabled(true).build();
    }

    @Override
    protected void convert(BaseViewHolder helper, MyFollowContBean.DataListBean item) {
        helper.setText(R.id.tv_author_name, item.getContInfo().getUserInfo().getNickname())
                .setText(R.id.tv_video_name, item.getContInfo().getName())
                .setText(R.id.tv_like, String.format(mContext.getString(R.string.like_count), item.getContInfo().getCommentTimes()));
        ImageView ivAuthorImg = helper.getView(R.id.iv_author_img);
        ImageView ivVideoImg = helper.getView(R.id.iv_video_img);

        Glide.with(mContext).asBitmap()
                .apply(RequestOptions.bitmapTransform(new CircleCrop())
                        .diskCacheStrategy(DiskCacheStrategy.NONE))
                .load(item.getContInfo().getUserInfo().getPic())
                .into(ivAuthorImg);

        Glide.with(mContext)
                .load(item.getContInfo().getPic())
                .apply(placeholder)
                .transition(DrawableTransitionOptions.with(drawableCrossFadeFactory))
                .into(ivVideoImg);


    }
}
