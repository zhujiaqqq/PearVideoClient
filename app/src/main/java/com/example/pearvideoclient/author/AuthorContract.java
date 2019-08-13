package com.example.pearvideoclient.author;

import com.example.pearvideoclient.BasePresenter;
import com.example.pearvideoclient.BaseView;
import com.example.pearvideoclient.entity.bean.UserInfoBean;

import java.util.List;

/**
 * @Description: java类作用描述
 * @Author: jiazhu
 * @Date: 2019-08-13 11:52
 * @ClassName: AuthorContract
 */
public interface AuthorContract {

    interface View extends BaseView<Presenter> {
        /**
         * 设置fragments
         *
         * @param title fragment的标题
         */
        void setFragments(List<String> title);

        /**
         * 设置作者的头部信息
         *
         * @param infoBean 作者信息
         */
        void setAuthorTitle(UserInfoBean.InfoBean infoBean);
    }

    interface Presenter extends BasePresenter {
        /**
         * 获取作者信息
         *
         * @param authorId 作者id
         */
        void loadAuthorInfo(String authorId);

    }

}
