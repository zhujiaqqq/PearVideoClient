package com.example.pearvideoclient.home.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.pearvideoclient.R;
import com.example.pearvideoclient.entity.LocalContEntity;
import com.example.pearvideoclient.entity.LocalContsBean;
import com.example.pearvideoclient.utils.GlideUtils;

import java.util.List;

/**
 * @Description: java类作用描述
 * @Author: jiazhu
 * @Date: 2019-08-23 09:49
 * @ClassName: CityAdapter
 */
public class CityAdapter extends BaseMultiItemQuickAdapter<LocalContEntity, BaseViewHolder> {
    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    CityAdapter(List<LocalContEntity> data) {
        super(data);
        addItemType(LocalContEntity.ITEM_TYPE_13, R.layout.adapter_city_type_13);
        addItemType(LocalContEntity.ITEM_TYPE_17, R.layout.adapter_city_type_17);
    }

    @Override
    protected void convert(BaseViewHolder helper, LocalContEntity item) {
        switch (helper.getItemViewType()) {
            case LocalContEntity.ITEM_TYPE_13:
                convert13(helper, item);
                break;
            case LocalContEntity.ITEM_TYPE_17:
                convert17(helper, item);
                break;
            default:
                break;
        }
    }

    private void convert13(BaseViewHolder helper, LocalContEntity item) {
        LocalContsBean.DataListBean.ContListBean contListBean = item.getCont().getContList().get(0);
        helper.setText(R.id.tv_star, contListBean.getPraiseTimes())
                .setText(R.id.tv_post, contListBean.getCommentTimes())
                .setText(R.id.tv_local, contListBean.getGeo().getShowName());
        ImageView ivVideoImg = helper.getView(R.id.iv_video_img);
        GlideUtils.loadWithPlaceHolder(contListBean.getPic(), ivVideoImg, null, null);

        helper.addOnClickListener(R.id.rl_parent)
                .addOnClickListener(R.id.iv_share)
                .addOnClickListener(R.id.iv_more)
                .addOnClickListener(R.id.tv_star)
                .addOnClickListener(R.id.tv_post);
    }

    private void convert17(BaseViewHolder helper, LocalContEntity item) {
        LocalContsBean.DataListBean cont = item.getCont();
        List<LocalContsBean.DataListBean.User> userList = cont.getUserList();
        helper.setText(R.id.tv_hot, cont.getNodeName());

        RecyclerView rvHotUserList = helper.getView(R.id.rv_hot_user_list);
        rvHotUserList.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
        HotUserAdapter hotUserAdapter = new HotUserAdapter(R.layout.adapter_user_hot_item, userList);
        hotUserAdapter.setOnItemClickListener((adapter, view, position) -> {
            if (view.getId() == R.id.tv_follow) {
                // TODO: 2019-08-23 关注
                //
            }
        });
        rvHotUserList.setAdapter(hotUserAdapter);
    }
}
