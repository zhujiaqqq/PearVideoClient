package com.example.pearvideoclient.author.fragment;

import android.app.Activity;
import android.content.Intent;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.util.Pair;
import androidx.core.view.ViewCompat;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.pearvideoclient.R;
import com.example.pearvideoclient.content.ContentActivity;
import com.example.pearvideoclient.entity.UserConts;
import com.example.pearvideoclient.utils.GlideUtils;

import java.util.List;

/**
 * @Description: java类作用描述
 * @Author: jiazhu
 * @Date: 2019-08-16 11:56
 * @ClassName: UserContsAdapter
 */
public class UserContsAdapter extends
        BaseQuickAdapter<UserConts.ContListBean, BaseViewHolder> {

    UserContsAdapter(int layoutResId, @Nullable List<UserConts.ContListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, UserConts.ContListBean item) {
        helper.setText(R.id.tv_video_name, item.getName())
                .setText(R.id.tv_video_author_duration, item.getUserInfo().getNickname() + " | " + item.getDuration());
        if (!TextUtils.isEmpty(item.getCornerLabelDesc())) {
            helper.setText(R.id.tv_single_broadcast, item.getCornerLabelDesc());
            helper.getView(R.id.tv_single_broadcast).setVisibility(View.VISIBLE);
        } else {
            helper.getView(R.id.tv_single_broadcast).setVisibility(View.GONE);
        }

        ImageView ivVideoImg = helper.getView(R.id.iv_video_img);
        GlideUtils.loadWithPlaceHolder(item.getPic(), ivVideoImg, null, null);

        TextView textView = helper.getView(R.id.tv_video_name);
        textView.setTransitionName("textView");

        helper.getView(R.id.rl_parent).setOnClickListener(v -> {
            Intent intent = new Intent(mContext, ContentActivity.class);
            intent.putExtra("contId", item.getContId());
            intent.putExtra("userId", item.getUserInfo().getUserId());
            Pair<View, String> namePair = new Pair<>(textView, ViewCompat.getTransitionName(textView));

            ActivityOptionsCompat option = ActivityOptionsCompat.makeSceneTransitionAnimation(
                    (Activity) mContext, namePair);
            mContext.startActivity(intent, option.toBundle());
        });
    }
}
