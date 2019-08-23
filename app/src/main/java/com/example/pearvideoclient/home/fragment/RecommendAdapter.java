package com.example.pearvideoclient.home.fragment;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.pearvideoclient.R;
import com.example.pearvideoclient.content.ContentActivity;
import com.example.pearvideoclient.entity.RecommendBean;
import com.example.pearvideoclient.entity.RecommendEntity;
import com.example.pearvideoclient.utils.GlideUtils;
import com.example.pearvideoclient.view.VideoBanner;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: java类作用描述
 * @Author: jiazhu
 * @Date: 2019-08-22 10:00
 * @ClassName: RecommendAdapter
 */
public class RecommendAdapter extends BaseMultiItemQuickAdapter<RecommendEntity, BaseViewHolder> {
    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    RecommendAdapter(List<RecommendEntity> data) {
        super(data);
        addItemType(RecommendEntity.NODE_TYPE_1, R.layout.adapter_recommend_type_1);
        addItemType(RecommendEntity.NODE_TYPE_13, R.layout.adapter_recommend_type_13);
        addItemType(RecommendEntity.NODE_TYPE_4, R.layout.adapter_recommend_type_4);
    }

    @Override
    protected void convert(BaseViewHolder helper, RecommendEntity item) {
        switch (helper.getItemViewType()) {
            case RecommendEntity.NODE_TYPE_1:
                convertType1(helper, item);
                break;
            case RecommendEntity.NODE_TYPE_4:
                convertType4(helper, item);
                break;
            case RecommendEntity.NODE_TYPE_13:
                convertType13(helper, item);
                break;
            default:
                break;
        }
    }

    private void convertType4(BaseViewHolder helper, RecommendEntity item) {
        RecommendBean.DataListBean dataBean = item.getDataBean();
        List<RecommendBean.DataListBean.ContListBeanX> contList = dataBean.getContList();
        helper.setText(R.id.tv_node_name, dataBean.getNodeName());
        helper.addOnClickListener(R.id.rl_more);

        RecyclerView rvContList = helper.getView(R.id.rv_cont_list);
        rvContList.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
        ContListAdapter contListAdapter = new ContListAdapter(R.layout.adapter_cont_item, contList);
        rvContList.setAdapter(contListAdapter);
        contListAdapter.setOnItemChildClickListener((adapter, view, position) -> {
            if (view.getId() == R.id.rl_parent) {
                RecommendBean.DataListBean.ContListBeanX contListBeanX = contList.get(position);
                Intent intent = new Intent(mContext, ContentActivity.class);
                intent.putExtra("contId", contListBeanX.getContId());
                intent.putExtra("userId", contListBeanX.getUserInfo().getUserId());
                mContext.startActivity(intent);
            }
        });
    }

    private void convertType13(BaseViewHolder helper, RecommendEntity item) {
        RecommendBean.DataListBean.ContListBeanX cont = item.getDataBean().getContList().get(0);
        helper.setText(R.id.tv_video_name, cont.getName())
                .setText(R.id.tv_user_name, cont.getUserInfo().getNickname())
                .setText(R.id.tv_star, cont.getPraiseTimes())
                .setText(R.id.tv_post, cont.getCommentTimes());

        helper.addOnClickListener(R.id.iv_more)
                .addOnClickListener(R.id.tv_post)
                .addOnClickListener(R.id.tv_star)
                .addOnClickListener(R.id.iv_share)
                .addOnClickListener(R.id.rl_parent);

        ImageView ivVideoImg = helper.getView(R.id.iv_video_img);
        ImageView ivUserImg = helper.getView(R.id.iv_user_img);
        GlideUtils.loadCircleImage(cont.getUserInfo().getPic(), ivUserImg);
        GlideUtils.loadWithPlaceHolder(cont.getPic(), ivVideoImg, null, null);
    }

    private void convertType1(BaseViewHolder helper, RecommendEntity item) {
        VideoBanner videoBanner = helper.getView(R.id.video_banner);
        List<RecommendBean.DataListBean.ContListBeanX> contList = item.getDataBean().getContList();
        List<String> urls = new ArrayList<>();
        for (RecommendBean.DataListBean.ContListBeanX cont : contList) {
            urls.add(cont.getCoverVideo());
        }
        videoBanner.setDataList(urls);
        videoBanner.startBanner();
        videoBanner.startAutoPlay();
    }
}
