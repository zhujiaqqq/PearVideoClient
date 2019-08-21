package com.example.pearvideoclient.entity;

import com.chad.library.adapter.base.entity.MultiItemEntity;

/**
 * @Description: java类作用描述
 * @Author: jiazhu
 * @Date: 2019-08-21 15:51
 * @ClassName: NewsEntity
 */
public class NewsEntity implements MultiItemEntity {
    public static final int TYPE_BIG = 1;
    public static final int TYPE_SMALL = 2;
    @Override
    public int getItemType() {
        return this.itemType;
    }

    private int itemType;

    private NewsBean.DataListBean dataEntity;

    public void setItemType(int itemType) {
        this.itemType = itemType;
    }

    public NewsBean.DataListBean getDataEntity() {
        return dataEntity;
    }

    public void setDataEntity(NewsBean.DataListBean dataEntity) {
        this.dataEntity = dataEntity;
    }
}
