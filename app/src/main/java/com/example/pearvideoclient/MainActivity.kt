package com.example.pearvideoclient

import android.os.Bundle
import android.view.KeyEvent
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.annotations.BindPath
import com.example.pearvideoclient.channel.ChannelFragment
import com.example.pearvideoclient.channel.ChannelPresenter
import com.example.pearvideoclient.follow.FollowFragment
import com.example.pearvideoclient.follow.FollowPresenter
import com.example.pearvideoclient.home.HomeFragment
import com.example.pearvideoclient.mine.MineFragment
import com.example.pearvideoclient.mine.MinePresenter
import com.example.pearvideoclient.screen.ScreenFragment
import kotlinx.android.synthetic.main.activity_main.*

/**
 * @Description: java类作用描述
 * @Author: jiazhu
 * @Date: 2019-08-10 15:45
 * @ClassName: MainActivity
 */
@BindPath(value = "app/main")
class MainActivity : AppCompatActivity() {

    private var mFragments = ArrayList<Fragment>()

    private var mLastIndex: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
        initData()
    }

    private fun initView() {

        bv_navigation.itemIconTintList = null
        bv_navigation.setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.home -> setFragmentPosition(0)
                R.id.channel -> setFragmentPosition(1)
                R.id.screen -> setFragmentPosition(2)
                R.id.follow -> setFragmentPosition(3)
                R.id.mine -> setFragmentPosition(4)
                else -> {
                }
            }
            true
        }
    }

    private fun initData() {
        val homeFragment = HomeFragment.newInstance()
        mFragments.add(homeFragment)

        val channelFragment = ChannelFragment.newInstance()
        ChannelPresenter(channelFragment)
        mFragments.add(channelFragment)

        val screenFragment = ScreenFragment.newInstance()
        mFragments.add(screenFragment)

        val followFragment = FollowFragment.newInstance()
        FollowPresenter(followFragment)
        mFragments.add(followFragment)

        val mineFragment = MineFragment.newInstance()
        MinePresenter(mineFragment, this)
        mFragments.add(mineFragment)

        setFragmentPosition(0)
    }

    private fun setFragmentPosition(position: Int) {
        val transaction = supportFragmentManager.beginTransaction()
        val currentFragment = mFragments[position]
        val lastFragment = mFragments[mLastIndex]
        mLastIndex = position
        transaction.hide(lastFragment)
        if (!currentFragment.isAdded) {
            supportFragmentManager.beginTransaction().remove(currentFragment).commit()
            transaction.add(R.id.fl_frame, currentFragment)
        }
        transaction.show(currentFragment)
        transaction.commit()
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (mLastIndex != 0) {
                bv_navigation.selectedItemId = R.id.home
                return true
            } else {
                return super.onKeyDown(keyCode, event)
            }
        }
        return super.onKeyDown(keyCode, event)
    }
}
