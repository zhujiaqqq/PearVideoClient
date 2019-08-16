package com.example.pearvideoclient.author;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.example.pearvideoclient.LocalHandler;
import com.example.pearvideoclient.R;
import com.example.pearvideoclient.author.fragment.UserAlbumsFragment;
import com.example.pearvideoclient.author.fragment.UserContsFragment;
import com.example.pearvideoclient.author.fragment.UserHomeFragment;
import com.example.pearvideoclient.author.fragment.UserPostFragment;
import com.example.pearvideoclient.entity.bean.AuthorHomeBean;
import com.example.pearvideoclient.entity.bean.UserConts;
import com.example.pearvideoclient.entity.bean.UserInfoBean;
import com.example.pearvideoclient.utils.StatusBarUtil;

import java.util.ArrayList;
import java.util.List;

import static com.example.pearvideoclient.author.AuthorPresenter.ALBUMS;
import static com.example.pearvideoclient.author.AuthorPresenter.CONTS;
import static com.example.pearvideoclient.author.AuthorPresenter.HOME;
import static com.example.pearvideoclient.author.AuthorPresenter.POST;

/**
 * @Description: java类作用描述
 * @Author: jiazhu
 * @Date: 2019-08-11 22:29
 * @ClassName: AuthorActivity
 */
public class AuthorActivity extends AppCompatActivity implements LocalHandler.IHandler, AuthorContract.View {
    public static final int MSG_INIT_VIEWPAGER = 1;
    private ImageView mIvUserBackgroud;
    private ImageView mIvUserImg;
    private TextView mTvUserSignature;
    private TextView mTvAttention;
    private TabLayout mTlTabLayout;
    private ViewPager mVpPager;
    private Toolbar mToolbar;
    private CollapsingToolbarLayout mToolbarLayout;

    /**
     * userConts-视频
     * userHome-动态
     * userAlbums-专辑
     * userPost-评论
     */
    private List<Fragment> mFragments;
    private List<String> mTitle;

    private LocalHandler mHandler = new LocalHandler(this);

    private AuthorContract.Presenter mPresenter;
    private String userId;
    private UserContsFragment userContsFragment;
    private UserHomeFragment userHomeFragment;
    private UserPostFragment userPostFragment;
    private UserAlbumsFragment userAlbumsFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_author);

        initView();
        initData();
    }


    private void initView() {
        //
        StatusBarUtil.setTranslucentStatus(this);

        mIvUserBackgroud = findViewById(R.id.iv_user_background);
        mIvUserImg = findViewById(R.id.iv_user_img);
        mTvUserSignature = findViewById(R.id.tv_user_signature);
        mTvAttention = findViewById(R.id.tv_attention);
        mTlTabLayout = findViewById(R.id.tl_tab_layout);
        mVpPager = findViewById(R.id.vp_pager);
        mToolbar = findViewById(R.id.toolbar);
        mToolbarLayout = findViewById(R.id.toolbar_layout);
        mToolbarLayout.setExpandedTitleColor(Color.WHITE);
        mToolbarLayout.setCollapsedTitleTextColor(Color.WHITE);
        mToolbarLayout.setExpandedTitleGravity(Gravity.CENTER);
        mToolbarLayout.setCollapsedTitleGravity(Gravity.CENTER);
        setSupportActionBar(mToolbar);

    }

    private void initData() {

        Intent intent = getIntent();
        if (intent == null) {
            return;
        }
        userId = intent.getStringExtra("userId");

        new AuthorPresenter(this, mHandler, userId);

        mTvAttention.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: 2019-08-13 调用关注
            }
        });


        mTlTabLayout.setTabMode(TabLayout.MODE_FIXED);
        mTlTabLayout.setTabTextColors(Color.parseColor("#999999"), Color.BLACK);
        mTlTabLayout.setSelectedTabIndicatorColor(Color.parseColor("#FBE04C"));
        mTlTabLayout.setupWithViewPager(mVpPager);
        mTlTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mVpPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                //
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                //
            }
        });

        mPresenter.subscribe();
    }

    @Override
    protected void onDestroy() {
        if (mPresenter != null) {
            mPresenter.unsubscribe();
            mPresenter = null;
        }

        super.onDestroy();
    }

    private void initViewPager() {
        FixPagerAdapter mFixPagerAdapter = new FixPagerAdapter(getSupportFragmentManager(), mFragments);
        mFixPagerAdapter.setTitles(mTitle);
        mVpPager.setAdapter(mFixPagerAdapter);
        mVpPager.setOffscreenPageLimit(mFragments.size());
        mVpPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {
                //
            }

            @Override
            public void onPageSelected(int i) {
                switch (i) {
                    case 0:
                        mPresenter.loadUserHomeInfo(userId);
                        break;
                    case 1:
                        mPresenter.loadUserContsInfo(userId);
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {
                //
            }
        });
        mVpPager.setCurrentItem(1);
    }

    @Override
    public void handlerMessage(Message msg) {
        if (msg.what == MSG_INIT_VIEWPAGER) {
            initViewPager();
        }
    }

    @Override
    public void setFragments(List<String> title) {
        if (mFragments == null) {
            mFragments = new ArrayList<>();
        } else {
            mFragments.clear();
        }
        mTitle = title;
        for (String s : mTitle) {
            switch (s) {
                case "视频":
                    userContsFragment = UserContsFragment.newInstance();
                    mFragments.add(userContsFragment);
                    break;
                case "动态":
                    userHomeFragment = UserHomeFragment.newInstance();
                    mFragments.add(userHomeFragment);
                    break;
                case "评论":
                    userPostFragment = UserPostFragment.newInstance();
                    mFragments.add(userPostFragment);
                    break;
                case "专辑":
                    userAlbumsFragment = UserAlbumsFragment.newInstance();
                    mFragments.add(userAlbumsFragment);
                    break;
                default:
                    break;
            }
        }
    }

    @Override
    public void setAuthorTitle(UserInfoBean.InfoBean infoBean) {
        mToolbarLayout.setTitle(infoBean.getNickname());
        mTvUserSignature.setText(infoBean.getSignature());
        Glide.with(this).load(infoBean.getBackgroundImg()).into(mIvUserBackgroud);
        mIvUserBackgroud.setImageAlpha(150);
        Glide.with(this).asBitmap()
                .apply(RequestOptions.bitmapTransform(new CircleCrop())
                        .diskCacheStrategy(DiskCacheStrategy.NONE)).load(infoBean.getPic())
                .into(mIvUserImg);
    }

    @Override
    public void setUserHomeData(List<AuthorHomeBean.DataListBean> dataList) {
        userHomeFragment.loadDataList(dataList);
    }

    @Override
    public void loadMoreUserHomeData(List<AuthorHomeBean.DataListBean> dataList) {
        userHomeFragment.loadMoreDataList(dataList);
    }

    @Override
    public void loadMoreFinish(@AuthorPresenter.PageType String type, boolean isSuccess) {
        switch (type) {
            case HOME:
                userHomeFragment.loadMoreFinish(isSuccess);
                break;
            case POST:
                break;
            case CONTS:
                userContsFragment.loadMoreFinish(isSuccess);
                break;
            case ALBUMS:
                break;
            default:
                break;
        }
    }

    @Override
    public void loadRefreshFinish(@AuthorPresenter.PageType String type, boolean isSuccess) {
        switch (type) {
            case HOME:
                userHomeFragment.loadRefreshFinish(isSuccess);
                break;
            case ALBUMS:
                break;
            case CONTS:
                userContsFragment.loadRefreshFinish(isSuccess);
                break;
            case POST:
                break;
            default:
                break;
        }
    }

    @Override
    public void setHotConts(List<UserConts.ContListBean> hotList) {
        userContsFragment.loadHotConts(hotList);
    }

    @Override
    public void setNewConts(List<UserConts.ContListBean> contList) {
        userContsFragment.loadNewConts(contList);
    }

    @Override
    public void loadMoreNewConts(List<UserConts.ContListBean> contList) {
        userContsFragment.loadMoreNewConts(contList);
    }

    @Override
    public void setPresenter(AuthorContract.Presenter presenter) {
        this.mPresenter = presenter;
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void cancelLoading() {

    }

    public void userHomeRefresh() {
        mPresenter.refreshUserHomeList();
    }

    public void userHomeLoadMore() {
        mPresenter.loadMoreUserHomeList();
    }

    public void userContsRefresh() {
        mPresenter.refreshUserContsList();
    }

    public void userContsLoadMore() {
        mPresenter.loadMoreUserContsList();
    }
}
