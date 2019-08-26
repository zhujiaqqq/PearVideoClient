package com.example.pearvideoclient.author.fragment;

import androidx.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.pearvideoclient.R;
import com.example.pearvideoclient.entity.UserPostsBean;
import com.example.pearvideoclient.utils.GlideUtils;

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
        GlideUtils.loadCircleImage(item.getUserInfo().getPic(), ivUserImg);
    }
}
