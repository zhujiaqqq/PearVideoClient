package com.example.pearvideoclient.channel.adapter;

import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.pearvideoclient.R;
import com.example.pearvideoclient.entity.CategoryContsBean;
import com.example.pearvideoclient.utils.GlideUtils;

import java.util.List;

/**
 * @Description: java类作用描述
 * @Author: jiazhu
 * @Date: 2019-08-30 15:01
 * @ClassName: HotUserAdapter
 */
public class HotUserAdapter  extends BaseQuickAdapter<CategoryContsBean.HotUserBean, BaseViewHolder> {
    public HotUserAdapter(int layoutResId, @Nullable List<CategoryContsBean.HotUserBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, CategoryContsBean.HotUserBean item) {
        helper.setText(R.id.tv_user_name, item.getNickname())
                .setText(R.id.tv_user_signature, item.getSignature());
        ImageView ivUserImg = helper.getView(R.id.iv_user_img);
        GlideUtils.loadCircleImage(item.getPic(), ivUserImg);
        if ("0".equals(item.getIsFollow())) {
            helper.setText(R.id.tv_follow, mContext.getString(R.string.tv_follow));
            helper.setBackgroundRes(R.id.tv_follow, R.drawable.bg_round_50_yellow);
            helper.addOnClickListener(R.id.tv_follow);
        } else {
            helper.setText(R.id.tv_follow, mContext.getString(R.string.tv_followed));
            helper.setBackgroundRes(R.id.tv_follow, R.drawable.bg_round_f2);
        }

    }
}
