package com.example.pearvideoclient.follow.addfollow;

import com.example.pearvideoclient.entity.FollowUsersBean;

/**
 * @Description: java类作用描述
 * @Author: jiazhu
 * @Date: 2019-09-03 17:55
 * @ClassName: UserClickCallback
 */
public interface UserClickCallback {
    /**
     * 点击
     *
     * @param bean user
     */
    void click(FollowUsersBean.UserBean bean);
}
