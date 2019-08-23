package com.example.pearvideoclient.entity;

import com.chad.library.adapter.base.entity.MultiItemEntity;

/**
 * @Description: java类作用描述
 * @Author: jiazhu
 * @Date: 2019-08-23 09:58
 * @ClassName: LocalContEntity
 */
public class LocalContEntity implements MultiItemEntity {
    public static final int ITEM_TYPE_13 = 13;
    public static final int ITEM_TYPE_17 = 17;

    private int itemType;

    private LocalContsBean.DataListBean cont;

    @Override
    public int getItemType() {
        return 0;
    }

    public LocalContsBean.DataListBean getCont() {
        return cont;
    }

    public void setItemType(int itemType) {
        this.itemType = itemType;
    }

    public void setCont(LocalContsBean.DataListBean cont) {
        this.cont = cont;
    }
}
