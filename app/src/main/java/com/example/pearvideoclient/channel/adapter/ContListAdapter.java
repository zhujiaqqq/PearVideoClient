package com.example.pearvideoclient.channel.adapter;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.pearvideoclient.R;
import com.example.pearvideoclient.entity.CategoryContsBean;
import com.example.pearvideoclient.entity.ContEntity;

import java.util.List;

/**
 * @Description: java类作用描述
 * @Author: jiazhu
 * @Date: 2019-08-30 10:25
 * @ClassName: ContListAdapter
 */
public class ContListAdapter extends BaseMultiItemQuickAdapter<ContEntity, BaseViewHolder> {


    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public ContListAdapter(List<ContEntity> data) {
        super(data);
        addItemType(ContEntity.TYPE_HOT_CONT, R.layout.adapter_hot_cont_item);
        addItemType(ContEntity.TYPE_HOT_TAG, R.layout.adapter_hot_tag_item);
        addItemType(ContEntity.TYPE_RANK_CONT, R.layout.adapter_rank_cont_item);
        addItemType(ContEntity.TYPE_HOT_USER, R.layout.adapter_hot_user_item);
        addItemType(ContEntity.TYPE_NEW_CONT, R.layout.adapter_new_cont_item);
    }

    @Override
    protected void convert(BaseViewHolder helper, ContEntity item) {
        switch (helper.getItemViewType()) {
            case ContEntity.TYPE_HOT_CONT:
                hotContConvert(helper, item);
                break;
            case ContEntity.TYPE_HOT_TAG:
                hotTagConvert(helper, item);
                break;
            case ContEntity.TYPE_HOT_USER:
                hotUserConvert(helper, item);
                break;
            case ContEntity.TYPE_NEW_CONT:
                newContConvert(helper, item);
                break;
            case ContEntity.TYPE_RANK_CONT:
                rankContConvert(helper, item);
                break;
            default:
                break;
        }
    }

    private void rankContConvert(BaseViewHolder helper, ContEntity item) {
        List<CategoryContsBean.ContListBean> contListBeans = item.getContListBeans();
        RecyclerView rvRankContList = helper.getView(R.id.rv_rank_cont_list);
        rvRankContList.setLayoutManager(new LinearLayoutManager(mContext, RecyclerView.HORIZONTAL, false));
        RankContAdapter rankContAdapter = new RankContAdapter(R.layout.inner_adapter_rank_cont_item, contListBeans);
        rvRankContList.setAdapter(rankContAdapter);
    }

    private void newContConvert(BaseViewHolder helper, ContEntity item) {
        List<CategoryContsBean.ContListBean> contListBeans = item.getContListBeans();
        RecyclerView rvHotList = helper.getView(R.id.rv_new_cont_list);
        rvHotList.setLayoutManager(new GridLayoutManager(mContext, 2));
        HotContAdapter hotContAdapter = new HotContAdapter(R.layout.inner_adapter_cont_item, contListBeans);
        rvHotList.setAdapter(hotContAdapter);
    }

    private void hotUserConvert(BaseViewHolder helper, ContEntity item) {
        List<CategoryContsBean.HotUserBean> hotUserBeans = item.getHotUserBeans();
        RecyclerView rvHotUserList = helper.getView(R.id.rv_hot_user_list);
        rvHotUserList.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
        HotUserAdapter hotUserAdapter = new HotUserAdapter(R.layout.adapter_user_hot_item, hotUserBeans);
        rvHotUserList.setAdapter(hotUserAdapter);
    }

    private void hotTagConvert(BaseViewHolder helper, ContEntity item) {
        List<CategoryContsBean.HotTagListBean> hotTagListBeans = item.getHotTagListBeans();
        RecyclerView rvHotTagList = helper.getView(R.id.rv_hot_tag_list);
        rvHotTagList.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
        HotTagAdapter hotTagAdapter = new HotTagAdapter(R.layout.inner_adapter_hot_tag_item, hotTagListBeans);
        rvHotTagList.setAdapter(hotTagAdapter);
    }

    private void hotContConvert(BaseViewHolder helper, ContEntity item) {
        List<CategoryContsBean.ContListBean> contListBeans = item.getContListBeans();
        RecyclerView rvHotList = helper.getView(R.id.rv_hot_list);
        rvHotList.setLayoutManager(new GridLayoutManager(mContext, 2));
        HotContAdapter hotContAdapter = new HotContAdapter(R.layout.inner_adapter_cont_item, contListBeans);
        rvHotList.setAdapter(hotContAdapter);
    }
}
