package com.example.pearvideoclient;

import android.os.Bundle;

import com.example.annotations.BindPath;
import com.example.route.Route;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.appcompat.app.AppCompatActivity;

import android.view.KeyEvent;

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
@BindPath(value = "app/main")
public class MainActivity extends AppCompatActivity {

    private List<Fragment> mFragments;
    private int mLastIndex;
    private BottomNavigationView mBvNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
    }

    private void initView() {
        mBvNavigation = findViewById(R.id.bv_navigation);

        mBvNavigation.setItemIconTintList(null);
        mBvNavigation.setOnNavigationItemSelectedListener(menuItem -> {
            switch (menuItem.getItemId()) {
                case R.id.home:
                    setFragmentPosition(0);
                    break;
                case R.id.channel:
                    setFragmentPosition(1);
                    break;
                case R.id.screen:
//                    setFragmentPosition(2);
                    Route.getInstance().jumpActivity( "member/main", null);
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

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (mLastIndex != 0) {
                mBvNavigation.setSelectedItemId(R.id.home);
                return true;
            } else {
                return super.onKeyDown(keyCode, event);
            }
        }
        return super.onKeyDown(keyCode, event);
    }
}
