package com.example.pearvideoclient.follow.addfollow

import com.example.pearvideoclient.entity.DomainListBean

/**
 * @Description: java类作用描述
 * @Author: jiazhu
 * @Date: 2019-09-03 17:18
 * @ClassName: DomainClickCallback
 */
interface DomainClickCallback {
    /**
     * 点击事件
     *
     * @param bean domain
     */
    fun onClick(bean: DomainListBean.DomainBean)
}
