package com.example.pearvideoclient;

import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.example.pearvideoclient.channel.ChannelFragment;
import com.example.pearvideoclient.channel.ChannelPresenter;
import com.example.pearvideoclient.follow.FollowFragment;
import com.example.pearvideoclient.follow.FollowPresenter;
import com.example.pearvideoclient.home.HomeFragment;
import com.example.pearvideoclient.mine.MineFragment;
import com.example.pearvideoclient.mine.MinePresenter;
import com.example.pearvideoclient.screen.ScreenFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: java类作用描述
 * @Author: jiazhu
 * @Date: 2019-08-10 15:45
 * @ClassName: MainActivity
 */
public class MainActivity extends AppCompatActivity {

    private List<Fragment> mFragments;
    private int mLastIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
    }

    private void initView() {
        BottomNavigationView bvNavigation = findViewById(R.id.bv_navigation);

        bvNavigation.setItemIconTintList(null);
        bvNavigation.setOnNavigationItemSelectedListener(menuItem -> {
            switch (menuItem.getItemId()) {
                case R.id.home:
                    setFragmentPosition(0);
                    break;
                case R.id.channel:
                    setFragmentPosition(1);
                    break;
                case R.id.screen:
                    setFragmentPosition(2);
                    break;
                case R.id.follow:
                    setFragmentPosition(3);
                    break;
                case R.id.mine:
                    setFragmentPosition(4);
                    break;
                default:
                    break;
            }
            return true;
        });
    }

    private void initData() {
        mFragments = new ArrayList<>();
        HomeFragment homeFragment = HomeFragment.newInstance();
        mFragments.add(homeFragment);

        ChannelFragment channelFragment = ChannelFragment.newInstance();
        new ChannelPresenter(channelFragment);
        mFragments.add(channelFragment);

        ScreenFragment screenFragment = ScreenFragment.newInstance();
        mFragments.add(screenFragment);

        FollowFragment followFragment = FollowFragment.newInstance();
        new FollowPresenter(followFragment);
        mFragments.add(followFragment);

        MineFragment mineFragment = MineFragment.newInstance();
        new MinePresenter(mineFragment, this);
        mFragments.add(mineFragment);

        setFragmentPosition(0);
    }

    private void setFragmentPosition(int position) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        Fragment currentFragment = mFragments.get(position);
        Fragment lastFragment = mFragments.get(mLastIndex);
        mLastIndex = position;
        transaction.hide(lastFragment);
        if (!currentFragment.isAdded()) {
            getSupportFragmentManager().beginTransaction().remove(currentFragment).commit();
            transaction.add(R.id.fl_frame, currentFragment);
        }
        transaction.show(currentFragment);
        transaction.commit();
    }
}
