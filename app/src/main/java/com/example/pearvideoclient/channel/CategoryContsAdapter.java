package com.example.pearvideoclient.channel;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v4.view.ViewCompat;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.transition.DrawableCrossFadeFactory;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.pearvideoclient.R;
import com.example.pearvideoclient.content.ContentActivity;
import com.example.pearvideoclient.entity.bean.CategoryContsBean;

import java.util.List;

/**
 * @author zhujiaqqq
 * @date 2019-07-18
 */
public class CategoryContsAdapter
        extends BaseQuickAdapter<CategoryContsBean.ContListBean, BaseViewHolder> {

    private MyListener listener;
    private RequestOptions placeholder;
    private final DrawableCrossFadeFactory drawableCrossFadeFactory;

    public void setListener(MyListener listener) {
        this.listener = listener;
    }

    public CategoryContsAdapter(int layoutResId, @Nullable List<CategoryContsBean.ContListBean> data) {
        super(layoutResId, data);
        placeholder = new RequestOptions().placeholder(R.drawable.ic_placeholder);
        drawableCrossFadeFactory = new DrawableCrossFadeFactory.Builder(300).setCrossFadeEnabled(true).build();
    }

    @Override
    protected void convert(BaseViewHolder helper, CategoryContsBean.ContListBean item) {
        ImageView ivVideoImg = helper.getView(R.id.iv_video_img);
        Glide.with(mContext)
                .load(item.getPic())
                .apply(placeholder)
                .transition(DrawableTransitionOptions.with(drawableCrossFadeFactory))
                .into(ivVideoImg);
        helper.setText(R.id.tv_video_name, item.getName());
        helper.setText(R.id.tv_video_author_duration, item.getUserInfo().getNickname() + " | " + item.getDuration());
        if (!TextUtils.isEmpty(item.getCornerLabelDesc())) {
            helper.setText(R.id.tv_single_broadcast, item.getCornerLabelDesc());
            helper.getView(R.id.tv_single_broadcast).setVisibility(View.VISIBLE);
        } else {
            helper.getView(R.id.tv_single_broadcast).setVisibility(View.GONE);
        }

        TextView textView = helper.getView(R.id.tv_video_name);
        textView.setTransitionName("textView");

        helper.getView(R.id.rl_parent).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onClick(item);

                    Intent intent = new Intent(mContext, ContentActivity.class);
                    intent.putExtra("contId", item.getContId());
                    intent.putExtra("userId", item.getUserInfo().getUserId());
                    Pair<View, String> namePair = new Pair<>(textView, ViewCompat.getTransitionName(textView));

                    ActivityOptionsCompat option = ActivityOptionsCompat.makeSceneTransitionAnimation(
                            (Activity) mContext, namePair);
                    mContext.startActivity(intent, option.toBundle());
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
