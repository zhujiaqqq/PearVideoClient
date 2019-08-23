package com.example.pearvideoclient.home;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.pearvideoclient.Constants;
import com.example.pearvideoclient.R;
import com.example.pearvideoclient.author.FixPagerAdapter;
import com.example.pearvideoclient.entity.CityListBean;
import com.example.pearvideoclient.entity.LocalContEntity;
import com.example.pearvideoclient.entity.NewsEntity;
import com.example.pearvideoclient.entity.RecommendEntity;
import com.example.pearvideoclient.home.fragment.CityFragment;
import com.example.pearvideoclient.home.fragment.RecommendFragment;
import com.example.pearvideoclient.home.fragment.VientianeFragment;

import java.util.ArrayList;
import java.util.List;

import static com.example.pearvideoclient.home.HomePresenter.CITY;
import static com.example.pearvideoclient.home.HomePresenter.NEWS;
import static com.example.pearvideoclient.home.HomePresenter.RECOMMEND;

/**
 * @author zhujiaqqq
 * @date 2019-07-11
 */
public class HomeFragment extends Fragment implements HomeContract.View {
    public static final int REQUEST_CODE = 1;
    public static final int RESULT_CODE = 2;
    private TabLayout mTabLayout;
    private ViewPager mViewPager;

    /**
     * 万象 VientianeFragment
     * 推荐 RecommendFragment
     * 城市 CityFragment
     */
    private List<Fragment> mFragments;
    private List<String> mTitle;

    private AppCompatActivity mActivity;

    private HomeContract.Presenter mPresenter;
    private VientianeFragment mVientianeFragment;
    private RecommendFragment mRecommendFragment;
    private CityFragment mCityFragment;

    public static HomeFragment newInstance() {
        Bundle bundle = new Bundle();
        HomeFragment homeFragment = new HomeFragment();
        homeFragment.setArguments(bundle);
        return homeFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        initData();
    }

    private void initView(View view) {
        mTabLayout = view.findViewById(R.id.tl_tab_layout);
        mViewPager = view.findViewById(R.id.vp_pager);
    }

    private void initData() {
        new HomePresenter(this);
        mActivity = (AppCompatActivity) getActivity();
        mPresenter.subscribe();
        initTabLayout();
        initViewPager();
    }

    private void initTabLayout() {
        mTabLayout.setTabMode(TabLayout.MODE_FIXED);
        mTabLayout.setTabTextColors(Color.parseColor("#999999"), Color.BLACK);
        mTabLayout.setSelectedTabIndicatorColor(Color.parseColor("#FBE04C"));
        mTabLayout.setupWithViewPager(mViewPager);
        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mViewPager.setCurrentItem(tab.getPosition());
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
    }

    private void initViewPager() {
        createFragment();

        FixPagerAdapter mFixPagerAdapter = new FixPagerAdapter(mActivity.getSupportFragmentManager(), mFragments);
        mFixPagerAdapter.setTitles(mTitle);
        mViewPager.setAdapter(mFixPagerAdapter);
        mViewPager.setOffscreenPageLimit(mFragments.size());
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {
                //
            }

            @Override
            public void onPageSelected(int i) {
                switch (i) {
                    case 0:
                        mPresenter.loadVientianeList();
                        break;
                    case 1:
                        mPresenter.loadRecommendList();
                        break;
                    case 2:
                        mPresenter.loadCityContsList();
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
        mViewPager.setCurrentItem(1);
    }

    /**
     * create fragment list
     */
    private void createFragment() {
        if (mTitle == null) {
            mTitle = new ArrayList<>();
        } else {
            mTitle.clear();
        }
        mTitle.add("万象");
        mTitle.add("推荐");
        mTitle.add("南京");

        if (mFragments == null) {
            mFragments = new ArrayList<>();
        } else {
            mFragments.clear();
        }
        mVientianeFragment = VientianeFragment.newInstance();
        mVientianeFragment.setRefreshCallBack(integer -> {
            if (integer == Constants.LOAD_REFRESH) {
                mPresenter.refreshVientianeList();
            } else if (integer == Constants.LOAD_MORE) {
                mPresenter.loadMoreVientianeList();
            }
            return null;
        });

        mFragments.add(mVientianeFragment);

        mRecommendFragment = RecommendFragment.newInstance();
        mRecommendFragment.setRefreshCallBack(integer -> {
            if (integer == Constants.LOAD_REFRESH) {
                mPresenter.refreshRecommendList();
            } else if (integer == Constants.LOAD_MORE) {
                mPresenter.loadMoreRecommendList();
            }
            return null;
        });
        mFragments.add(mRecommendFragment);

        mCityFragment = CityFragment.newInstance();
        mCityFragment.setCityFragmentCallBack(integer -> {
            if (integer == Constants.LOAD_REFRESH) {
                mPresenter.refreshCityContsList();
            } else if (integer == Constants.LOAD_MORE) {
                mPresenter.loadMoreCityContsList();
            } else if (integer == Constants.HEADER_CLICK) {
                mPresenter.loadLocalChannel();
            }
            return null;
        });
        mFragments.add(mCityFragment);
    }

    @Override
    public void setPresenter(HomeContract.Presenter presenter) {
        mPresenter = presenter;
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
    public void showErrorToast(String loading_fail) {

    }

    @Override
    public void showVientianeList(List<NewsEntity> data) {
        mVientianeFragment.setVientianeList(data);
    }

    @Override
    public void loadMoreVientianeList(List<NewsEntity> data) {
        mVientianeFragment.loadMoreVientianeList(data);
    }

    @Override
    public void showRecommendList(List<RecommendEntity> data) {
        mRecommendFragment.replaceData(data);
    }

    @Override
    public void loadMoreRecommendList(List<RecommendEntity> data) {
        mRecommendFragment.addData(data);
    }

    @Override
    public void showCityContsList(List<LocalContEntity> data) {
        mCityFragment.replaceData(data);
    }

    @Override
    public void loadMoreCityContsList(List<LocalContEntity> data) {
        mCityFragment.addData(data);
    }

    @Override
    public void loadMoreFinish(boolean isSuccess, String pageType) {
        switch (pageType) {
            case NEWS:
                mVientianeFragment.loadMoreFinish(isSuccess);
                break;
            case RECOMMEND:
                mRecommendFragment.loadMoreFinish(isSuccess);
                break;
            case CITY:
                mCityFragment.loadMoreFinish(isSuccess);
                break;
            default:
                break;
        }
    }

    @Override
    public void refreshFinish(boolean isSuccess, String pageType) {
        switch (pageType) {
            case NEWS:
                mVientianeFragment.refreshFinish(isSuccess);
                break;
            case RECOMMEND:
                mRecommendFragment.refreshFinish(isSuccess);
                break;
            case CITY:
                mCityFragment.refreshFinish(isSuccess);
                break;
            default:
                break;
        }
    }

    @Override
    public void toCityList(CityListBean cityListBean) {
        Intent intent = new Intent(mActivity, CityListActivity.class);
        intent.putExtra("cityList", cityListBean);
        startActivityForResult(intent, REQUEST_CODE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (REQUEST_CODE == requestCode && RESULT_CODE == resultCode) {
            data.getSerializableExtra("city");
        }
        super.onActivityResult(requestCode, resultCode, data);

    }
}
