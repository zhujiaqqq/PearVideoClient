package com.example.pearvideoclient.entity;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.util.List;

/**
 * @Description: java类作用描述
 * @Author: jiazhu
 * @Date: 2019-08-30 10:12
 * @ClassName: ContEntity
 */
public class ContEntity implements MultiItemEntity {
    public static final int TYPE_HOT_CONT = 0;
    public static final int TYPE_HOT_TAG = 1;
    public static final int TYPE_RANK_CONT = 2;
    public static final int TYPE_HOT_USER = 3;
    public static final int TYPE_NEW_CONT = 4;


    private int itemType;
    private List<CategoryContsBean.ContListBean> mContListBeans;
    private List<CategoryContsBean.HotTagListBean> mHotTagListBeans;
    private List<CategoryContsBean.HotUserBean> mHotUserBeans;

    public List<CategoryContsBean.ContListBean> getContListBeans() {
        return mContListBeans;
    }

    public void setContListBeans(List<CategoryContsBean.ContListBean> contListBeans) {
        mContListBeans = contListBeans;
    }

    public List<CategoryContsBean.HotTagListBean> getHotTagListBeans() {
        return mHotTagListBeans;
    }

    public void setHotTagListBeans(List<CategoryContsBean.HotTagListBean> hotTagListBeans) {
        mHotTagListBeans = hotTagListBeans;
    }

    public List<CategoryContsBean.HotUserBean> getHotUserBeans() {
        return mHotUserBeans;
    }

    public void setHotUserBeans(List<CategoryContsBean.HotUserBean> hotUserBeans) {
        mHotUserBeans = hotUserBeans;
    }

    public void setItemType(int itemType) {
        this.itemType = itemType;
    }

    @Override
    public int getItemType() {
        return itemType;
    }

}
