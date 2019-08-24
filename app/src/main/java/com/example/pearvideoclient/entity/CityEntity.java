package com.example.pearvideoclient.entity;

import com.chad.library.adapter.base.entity.MultiItemEntity;

/**
 * @Description: java类作用描述
 * @Author: jiazhu
 * @Date: 2019-08-24 22:57
 * @ClassName: CityEntity
 */
public class CityEntity implements MultiItemEntity {
    public static final int TYPE_CITY = 0;
    public static final int TYPE_INDEX = 1;

    private int itemType;

    private CityListBean.ChannelBean mChannelBean;
    private String mIndex;

    public CityListBean.ChannelBean getChannelBean() {
        return mChannelBean;
    }

    public void setChannelBean(CityListBean.ChannelBean channelBean) {
        mChannelBean = channelBean;
    }

    public String getIndex() {
        return mIndex;
    }

    public void setIndex(String index) {
        mIndex = index;
    }

    @Override
    public int getItemType() {
        return itemType;
    }

    public void setItemType(int itemType) {
        this.itemType = itemType;
    }
}
