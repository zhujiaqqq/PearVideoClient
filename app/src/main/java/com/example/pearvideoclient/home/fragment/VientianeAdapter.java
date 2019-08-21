package com.example.pearvideoclient.home.fragment;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.pearvideoclient.R;
import com.example.pearvideoclient.entity.NewsBean;
import com.example.pearvideoclient.entity.NewsEntity;
import com.example.pearvideoclient.utils.GlideUtils;

import java.util.List;

/**
 * @Description: java类作用描述
 * @Author: jiazhu
 * @Date: 2019-08-21 15:41
 * @ClassName: VientianeAdapter
 */
public class VientianeAdapter extends BaseMultiItemQuickAdapter<NewsEntity, BaseViewHolder> {
    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public VientianeAdapter(List<NewsEntity> data) {
        super(data);
        addItemType(NewsEntity.TYPE_BIG, R.layout.adapter_vientiane_big_item);
        addItemType(NewsEntity.TYPE_SMALL, R.layout.adapter_vientiane_small_item);
    }

    @Override
    protected void convert(BaseViewHolder helper, NewsEntity item) {
        NewsBean.DataListBean bean = item.getDataEntity();
        TextView tvTopping = helper.getView(R.id.tv_topping);
        ImageView ivVideoImg = helper.getView(R.id.iv_video_img);
        helper.setText(R.id.tv_video_name, bean.getNewsInfo().getName())
                .setText(R.id.tv_user_name, bean.getNewsInfo().getAuthorName());
        if (mContext.getString(R.string.tv_topping).equals(bean.getNewsInfo().getCornerLabelDesc())) {
            tvTopping.setVisibility(View.VISIBLE);
        } else {
            tvTopping.setVisibility(View.GONE);
        }
        GlideUtils.loadWithPlaceHolder(bean.getNewsInfo().getPic(), ivVideoImg, null, null);
    }
}
