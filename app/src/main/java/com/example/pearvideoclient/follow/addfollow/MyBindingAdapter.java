package com.example.pearvideoclient.follow.addfollow;

import android.widget.ImageView;
import android.widget.TextView;

import androidx.databinding.BindingAdapter;

import com.example.pearvideoclient.MyApplication;
import com.example.pearvideoclient.R;
import com.example.pearvideoclient.entity.FollowUsersBean;
import com.example.pearvideoclient.utils.GlideUtils;

/**
 * @Description: java类作用描述
 * @Author: jiazhu
 * @Date: 2019-09-03 19:48
 * @ClassName: MyBindingAdapter
 */
public class MyBindingAdapter {
    @BindingAdapter("myText")
    public static void myText(TextView view, String isFollow) {
        if ("0".equals(isFollow)) {
            view.setText(FollowUsersBean.UserBean.FOLLOW);
        } else {
            view.setText(FollowUsersBean.UserBean.FOLLOWED);
        }
    }

    @BindingAdapter("myBackground")
    public static void myBackground(TextView view, String isFollow) {
        if ("0".equals(isFollow)) {
            view.setBackground(MyApplication.getInstance().getDrawable(R.drawable.bg_round_50_yellow));
        } else {
            view.setBackground(MyApplication.getInstance().getDrawable(R.drawable.bg_round_f2));
        }
    }

    @BindingAdapter("myImageView")
    public static void myImageView(ImageView view, String pic) {
        GlideUtils.loadCircleImage(pic, view);
    }
}
