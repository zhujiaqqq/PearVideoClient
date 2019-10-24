package com.example.pearvideoclient.author

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import java.util.*

/**
 * @Description: java类作用描述
 * @Author: jiazhu
 * @Date: 2019-08-13 11:29
 * @ClassName: FixPagerAdapter
 */
class FixPagerAdapter(fm: FragmentManager, private val mFragments: List<Fragment>?)
    : FragmentStatePagerAdapter(fm) {

    private var mTitles: List<String>? = ArrayList()

    fun setTitles(titles: List<String>) {
        this.mTitles = titles
    }

    override fun getItem(i: Int): Fragment {
        return mFragments!![i]
    }

    override fun getCount(): Int {
        return mFragments?.size ?: 0
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return if (mTitles != null && !mTitles!!.isEmpty()) {
            mTitles!![position]
        } else null
    }
}
