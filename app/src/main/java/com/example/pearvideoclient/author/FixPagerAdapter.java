package com.example.pearvideoclient.author;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: java类作用描述
 * @Author: jiazhu
 * @Date: 2019-08-13 11:29
 * @ClassName: FixPagerAdapter
 */
public class FixPagerAdapter extends FragmentStatePagerAdapter {

    private List<String> mTitles = new ArrayList<>();
    private List<Fragment> mFragments;

    public FixPagerAdapter(FragmentManager fm, List<Fragment> fragments) {
        super(fm);
        this.mFragments = fragments;
    }

    public void setTitles(List<String> titles) {
        this.mTitles = titles;
    }

    @Override
    public Fragment getItem(int i) {
        return mFragments.get(i);
    }

    @Override
    public int getCount() {
        return mFragments == null ? 0 : mFragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (mTitles != null && !mTitles.isEmpty()) {
            return mTitles.get(position);
        }
        return null;
    }
}
