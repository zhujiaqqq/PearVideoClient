package com.example.pearvideoclient.follow.addfollow;

import com.example.pearvideoclient.entity.DomainListBean;

/**
 * @Description: java类作用描述
 * @Author: jiazhu
 * @Date: 2019-09-03 17:18
 * @ClassName: DomainClickCallback
 */
public interface DomainClickCallback {
    /**
     * 点击事件
     *
     * @param bean domain
     */
    void onClick(DomainListBean.DomainBean bean);
}
