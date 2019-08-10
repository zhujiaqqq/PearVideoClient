package com.example.pearvideoclient;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

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

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private BottomNavigationView mBvNavigation;
    private FrameLayout mFlFrame;

    private List<Fragment> mFragments;
    private int lastIndex;
    private ChannelPresenter channelPresenter;
    private MinePresenter minePresenter;
    private FollowPresenter followPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
    }

    private void initView() {
        mBvNavigation = findViewById(R.id.bv_navigation);
        mFlFrame = findViewById(R.id.fl_frame);

//        BottomNavigationViewHelper.disableShiftMode(mBvNavigation);
        mBvNavigation.setItemIconTintList(null);
        mBvNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
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
            }
        });
    }

    private void initData() {
        mFragments = new ArrayList<>();
        HomeFragment homeFragment = HomeFragment.newInstance();
        mFragments.add(homeFragment);

        ChannelFragment channelFragment = ChannelFragment.newInstance();
        channelPresenter = new ChannelPresenter(channelFragment);
        mFragments.add(channelFragment);

        ScreenFragment screenFragment = ScreenFragment.newInstance();
        mFragments.add(screenFragment);

        FollowFragment followFragment = FollowFragment.newInstance();
        followPresenter = new FollowPresenter(followFragment);
        mFragments.add(followFragment);

        MineFragment mineFragment = MineFragment.newInstance();
        minePresenter = new MinePresenter(mineFragment, this);
        mFragments.add(mineFragment);

        setFragmentPosition(0);
    }

    private void setFragmentPosition(int position) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        Fragment currentFragment = mFragments.get(position);
        Fragment lastFragment = mFragments.get(lastIndex);
        lastIndex = position;
        transaction.hide(lastFragment);
        if (!currentFragment.isAdded()) {
            getSupportFragmentManager().beginTransaction().remove(currentFragment).commit();
            transaction.add(R.id.fl_frame, currentFragment);
        }
        transaction.show(currentFragment);
        transaction.commit();
    }
}
