package com.example.pearvideoclient.author;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Message;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.apublic.LocalHandler;
import com.example.pearvideoclient.MyApplication;
import com.example.pearvideoclient.R;
import com.example.pearvideoclient.author.fragment.UserAlbumsFragment;
import com.example.pearvideoclient.author.fragment.UserContsFragment;
import com.example.pearvideoclient.author.fragment.UserHomeFragment;
import com.example.pearvideoclient.author.fragment.UserPostFragment;
import com.example.pearvideoclient.entity.AuthorHomeBean;
import com.example.pearvideoclient.entity.UserAlbumsBean;
import com.example.pearvideoclient.entity.UserConts;
import com.example.pearvideoclient.entity.UserInfoBean;
import com.example.pearvideoclient.entity.UserPostsBean;
import com.example.pearvideoclient.utils.GlideUtils;
import com.example.pearvideoclient.utils.MyToast;
import com.example.pearvideoclient.utils.StatusBarUtil;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import static com.example.pearvideoclient.author.AuthorPresenter.ALBUMS;
import static com.example.pearvideoclient.author.AuthorPresenter.CONT;
import static com.example.pearvideoclient.author.AuthorPresenter.HOME;
import static com.example.pearvideoclient.author.AuthorPresenter.POST;
import static com.example.pearvideoclient.content.ContentPresenter.FOLLOW_USER;
import static com.example.pearvideoclient.content.ContentPresenter.UN_FOLLOW_USER;

/**
 * @Description: java类作用描述
 * @Author: jiazhu
 * @Date: 2019-08-11 22:29
 * @ClassName: AuthorActivity
 */
public class AuthorActivity extends AppCompatActivity implements LocalHandler.IHandler, AuthorContract.View {
    public static final int MSG_INIT_VIEWPAGER = 1;
    private ImageView mIvUserBackground;
    private ImageView mIvUserImg;
    private TextView mTvUserSignature;
    private TextView mTvAttention;
    private TabLayout mTlTabLayout;
    private ViewPager mVpPager;
    private CollapsingToolbarLayout mToolbarLayout;

    /**
     * userConts-视频
     * userHome-动态
     * userAlbums-专辑
     * userPost-评论
     */
    private List<Fragment> mFragments;
    private List<String> mTitle;

    private LocalHandler mLocalHandler = new LocalHandler(this);

    private AuthorContract.Presenter mPresenter;
    private String userId;
    private UserContsFragment mUserContsFragment;
    private UserHomeFragment mUserHomeFragment;
    private UserPostFragment mUserPostFragment;
    private UserAlbumsFragment mUserAlbumsFragment;
    /**
     * 关注标志
     */
    private boolean isAttention;

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

        mIvUserBackground = findViewById(R.id.iv_user_background);
        mIvUserImg = findViewById(R.id.iv_user_img);
        mTvUserSignature = findViewById(R.id.tv_user_signature);
        mTvAttention = findViewById(R.id.tv_attention);
        mTlTabLayout = findViewById(R.id.tl_tab_layout);
        mVpPager = findViewById(R.id.vp_pager);
        Toolbar toolbar = findViewById(R.id.toolbar);
        mToolbarLayout = findViewById(R.id.toolbar_layout);
        mToolbarLayout.setExpandedTitleColor(Color.WHITE);
        mToolbarLayout.setCollapsedTitleTextColor(Color.WHITE);
        mToolbarLayout.setExpandedTitleGravity(Gravity.CENTER);
        mToolbarLayout.setCollapsedTitleGravity(Gravity.CENTER);
        setSupportActionBar(toolbar);

    }

    private void initData() {

        Intent intent = getIntent();
        if (intent == null) {
            return;
        }
        userId = intent.getStringExtra("userId");

        new AuthorPresenter(this, mLocalHandler, userId);

        mTvAttention.setOnClickListener(v -> {
            mPresenter.toOptUserFollow(isAttention ? UN_FOLLOW_USER : FOLLOW_USER, userId);
            isAttention = !isAttention;
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
                int maxTabSize = 4;
                switch (i) {
                    case 0:
                        mPresenter.loadUserHomeInfo(userId);
                        break;
                    case 1:
                        mPresenter.loadUserContsInfo(userId);
                        break;
                    case 2:
                        if (mFragments.size() == maxTabSize) {
                            mPresenter.loadUserAlbumsInfo(userId);
                        } else {
                            mPresenter.loadUserPostsInfo(userId);
                        }
                        break;
                    case 3:
                        mPresenter.loadUserPostsInfo(userId);
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
                    mUserContsFragment = UserContsFragment.newInstance();
                    mFragments.add(mUserContsFragment);
                    break;
                case "动态":
                    mUserHomeFragment = UserHomeFragment.newInstance();
                    mFragments.add(mUserHomeFragment);
                    break;
                case "评论":
                    mUserPostFragment = UserPostFragment.newInstance();
                    mFragments.add(mUserPostFragment);
                    break;
                case "专辑":
                    mUserAlbumsFragment = UserAlbumsFragment.newInstance();
                    mFragments.add(mUserAlbumsFragment);
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
        GlideUtils.load(infoBean.getBackgroundImg(), mIvUserBackground);
        mIvUserBackground.setImageAlpha(150);
        GlideUtils.loadCircleImage(infoBean.getPic(), mIvUserImg);
    }

    @Override
    public void setUserHomeData(List<AuthorHomeBean.DataListBean> dataList) {
        mUserHomeFragment.loadDataList(dataList);
    }

    @Override
    public void loadMoreUserHomeData(List<AuthorHomeBean.DataListBean> dataList) {
        mUserHomeFragment.loadMoreDataList(dataList);
    }

    @Override
    public void loadMoreFinish(@AuthorPresenter.PageType String type, boolean isSuccess) {
        switch (type) {
            case HOME:
                mUserHomeFragment.loadMoreFinish(isSuccess);
                break;
            case ALBUMS:
                mUserAlbumsFragment.loadMoreFinish(isSuccess);
                break;
            case CONT:
                mUserContsFragment.loadMoreFinish(isSuccess);
                break;
            case POST:
                mUserPostFragment.loadMoreFinish(isSuccess);
                break;
            default:
                break;
        }
    }

    @Override
    public void loadRefreshFinish(@AuthorPresenter.PageType String type, boolean isSuccess) {
        switch (type) {
            case HOME:
                mUserHomeFragment.loadRefreshFinish(isSuccess);
                break;
            case ALBUMS:
                mUserAlbumsFragment.loadRefreshFinish(isSuccess);
                break;
            case CONT:
                mUserContsFragment.loadRefreshFinish(isSuccess);
                break;
            case POST:
                mUserPostFragment.loadRefreshFinish(isSuccess);
                break;
            default:
                break;
        }
    }

    @Override
    public void setHotConts(List<UserConts.ContListBean> hotList) {
        mUserContsFragment.loadHotConts(hotList);
    }

    @Override
    public void setNewConts(List<UserConts.ContListBean> contList) {
        mUserContsFragment.loadNewConts(contList);
    }

    @Override
    public void loadMoreNewConts(List<UserConts.ContListBean> contList) {
        mUserContsFragment.loadMoreNewConts(contList);
    }

    @Override
    public void setAlbumsList(List<UserAlbumsBean.AlbumListBean> albumList) {
        mUserAlbumsFragment.loadAlbumsList(albumList);
    }

    @Override
    public void loadMoreUserAlbums(List<UserAlbumsBean.AlbumListBean> albumList) {
        mUserAlbumsFragment.loadMoreAlbumsList(albumList);
    }

    @Override
    public void setPostsList(List<UserPostsBean.PostListBean> postsList) {
        mUserPostFragment.loadPostsList(postsList);
    }

    @Override
    public void loadMorePostsList(List<UserPostsBean.PostListBean> postsList) {
        mUserPostFragment.loadMorePostsList(postsList);
    }

    @Override
    public void toggleAttention() {
        mTvAttention.setText(isAttention ? "已关注" : "关注");
        mTvAttention.setBackground(isAttention ?
                getDrawable(R.drawable.bg_round_f2) : getDrawable(R.drawable.bg_round_50_yellow));
    }

    @Override
    public void setPresenter(AuthorContract.Presenter presenter) {
        this.mPresenter = presenter;
    }

    @Override
    public void showLoading() {
        //
    }

    @Override
    public void cancelLoading() {
        //
    }

    @Override
    public void showErrorToast(String loadingFail) {
        MyToast.getInstance(MyApplication.getInstance()).show(loadingFail, 3000);
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

    public void userAlbumsLoadMore() {
        mPresenter.loadMoreUserAlbumsList();
    }

    public void userAlbumsRefresh() {
        mPresenter.refreshUserAlbumsList();
    }

    public void userPostsRefresh() {
        mPresenter.refreshUserPostsList();
    }

    public void userPostsLoadMore() {
        mPresenter.loadMoreUserPostsList();
    }
}
