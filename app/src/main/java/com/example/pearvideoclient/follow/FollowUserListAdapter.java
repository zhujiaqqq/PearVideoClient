package com.example.pearvideoclient.follow;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.pearvideoclient.R;
import com.example.pearvideoclient.entity.MyFollowContBean;
import com.example.pearvideoclient.utils.GlideUtils;

import java.util.List;
import java.util.Objects;

import static com.example.pearvideoclient.follow.FollowPresenter.MORE_USER;

/**
 * @Description: java类作用描述
 * @Author: jiazhu
 * @Date: 2019-08-10 21:00
 * @ClassName: FollowUserListAdapter
 */
public class FollowUserListAdapter extends BaseQuickAdapter<MyFollowContBean.FollowUserListBean, BaseViewHolder> {
    FollowUserListAdapter(int layoutResId, @Nullable List<MyFollowContBean.FollowUserListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, MyFollowContBean.FollowUserListBean item) {
        helper.setText(R.id.tv_user_name, item.getNickname());
        ImageView ivUserImage = helper.getView(R.id.iv_user_img);
        if (MORE_USER.equals(item.getUserId())) {
            GlideUtils.load(Objects.requireNonNull(mContext.getDrawable(R.drawable.ic_arrow_right)), ivUserImage);
        } else {
            GlideUtils.loadCircleImage(item.getPic(), ivUserImage);
        }

        helper.addOnClickListener(R.id.rl_parent);
    }
}
