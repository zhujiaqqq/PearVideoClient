package com.example.pearvideoclient.view.video;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: java类作用描述
 * @Author: jiazhu
 * @Date: 2019-08-22 10:39
 * @ClassName: VideoBannerAdapter
 */
public class VideoBannerAdapter extends PagerAdapter {

    private List<View> mList;

    public void setList(List<View> list) {
        mList = list;
        notifyDataSetChanged();
    }

    public VideoBannerAdapter(List<View> list) {
        if (list == null) {
            list = new ArrayList<>();
        }
        mList = list;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = mList.get(position);
        container.addView(view);
        return view;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == o;
    }


}
