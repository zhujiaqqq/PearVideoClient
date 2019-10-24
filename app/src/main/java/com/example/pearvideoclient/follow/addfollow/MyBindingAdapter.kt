package com.example.pearvideoclient.follow.addfollow

import android.widget.ImageView
import android.widget.TextView

import androidx.databinding.BindingAdapter

import com.example.pearvideoclient.MyApplication
import com.example.pearvideoclient.R
import com.example.pearvideoclient.entity.FollowUsersBean
import com.example.pearvideoclient.utils.GlideUtils

/**
 * @Description: java类作用描述
 * @Author: jiazhu
 * @Date: 2019-09-03 19:48
 * @ClassName: MyBindingAdapter
 */
object MyBindingAdapter {
    @BindingAdapter("myText")
    fun myText(view: TextView, isFollow: String) {
        if ("0" == isFollow) {
            view.text = FollowUsersBean.UserBean.FOLLOW
        } else {
            view.text = FollowUsersBean.UserBean.FOLLOWED
        }
    }

    @BindingAdapter("myBackground")
    fun myBackground(view: TextView, isFollow: String) {
        if ("0" == isFollow) {
            view.background = MyApplication.instance.getDrawable(R.drawable.bg_round_50_yellow)
        } else {
            view.background = MyApplication.instance.getDrawable(R.drawable.bg_round_f2)
        }
    }

    @BindingAdapter("myImageView")
    fun myImageView(view: ImageView, pic: String) {
        GlideUtils.loadCircleImage(pic, view)
    }
}
