package com.example.pearvideoclient.entity;

import com.chad.library.adapter.base.entity.MultiItemEntity;

/**
 * @Description: java类作用描述
 * @Author: jiazhu
 * @Date: 2019-08-22 09:54
 * @ClassName: RecommendEntity
 */
public class RecommendEntity implements MultiItemEntity {
    public static final int NODE_TYPE_1 = 1;
    public static final int NODE_TYPE_13 = 13;
    public static final int NODE_TYPE_4 = 4;

    private RecommendBean.DataListBean mDataBean;
    private int itemType;

    public void setItemType(int itemType) {
        this.itemType = itemType;
    }

    public RecommendBean.DataListBean getDataBean() {
        return mDataBean;
    }

    public void setDataBean(RecommendBean.DataListBean dataBean) {
        mDataBean = dataBean;
    }

    @Override
    public int getItemType() {
        return itemType;
    }
}
