package com.example.pearvideoclient.author.fragment;

import androidx.annotation.Nullable;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.util.Pair;
import androidx.core.view.ViewCompat;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.pearvideoclient.R;
import com.example.pearvideoclient.content.ContentActivity;
import com.example.pearvideoclient.entity.AuthorHomeBean;
import com.example.pearvideoclient.utils.GlideUtils;

import java.util.List;

import static androidx.core.app.ActivityOptionsCompat.makeSceneTransitionAnimation;

/**
 * @Description: java类作用描述
 * @Author: jiazhu
 * @Date: 2019-08-14 10:49
 * @ClassName: UserHomeAdapter
 */
public class UserHomeAdapter extends
        BaseQuickAdapter<AuthorHomeBean.DataListBean, BaseViewHolder> {

    UserHomeAdapter(int layoutResId, @Nullable List<AuthorHomeBean.DataListBean> data) {
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


        TextView textView = helper.getView(R.id.tv_video_name);
        textView.setTransitionName("textView");

        helper.getView(R.id.rl_parent).setOnClickListener(v -> {
            Intent intent = new Intent(mContext, ContentActivity.class);
            intent.putExtra("contId", item.getContInfo().getContId());
            intent.putExtra("userId", item.getContInfo().getUserInfo().getUserId());
            Pair<View, String> namePair = new Pair<>(textView, ViewCompat.getTransitionName(textView));

            ActivityOptionsCompat option = makeSceneTransitionAnimation(
                    (Activity) mContext, namePair);
            mContext.startActivity(intent, option.toBundle());
        });

        helper.addOnClickListener(R.id.ll_favour)
                .addOnClickListener(R.id.ll_comment)
                .addOnClickListener(R.id.ll_share);

        GlideUtils.loadCircleImage(item.getContInfo().getUserInfo().getPic(), ivAuthorImg);
        GlideUtils.loadWithPlaceHolder(item.getContInfo().getPic(), ivVideoImg, null, null);
    }
}
