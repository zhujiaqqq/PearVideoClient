package com.example.pearvideoclient.channel;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.pearvideoclient.Constants;
import com.example.pearvideoclient.MyApplication;
import com.example.pearvideoclient.R;
import com.example.pearvideoclient.author.FixPagerAdapter;
import com.example.pearvideoclient.entity.CategoryBean;
import com.example.pearvideoclient.entity.ContEntity;
import com.example.pearvideoclient.utils.MyToast;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: java类作用描述
 * @Author: jiazhu
 * @Date: 2019-07-11 21:24
 * @ClassName: ChannelFragment
 */
public class ChannelFragment extends Fragment implements ChannelContract.View {

    private ChannelContract.Presenter mPresenter;

    private Context mContext;

    private TabLayout mTlCategoryList;
    private ViewPager mViewPager;
    private ImageView mIvSort;

    private List<Fragment> mFragments;
    private List<String> mTitles;

    private ArrayList<CategoryBean.CategoryListBean> currentCategoryList;
    private AppCompatActivity mActivity;

    public static ChannelFragment newInstance() {
        Bundle bundle = new Bundle();
        ChannelFragment fragment = new ChannelFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_channel, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        initData();
    }

    private void initView(View view) {
        mTlCategoryList = view.findViewById(R.id.tl_category_list);
        mIvSort = view.findViewById(R.id.iv_sort);
        mViewPager = view.findViewById(R.id.vp_pager);
    }

    private void initData() {
        mContext = getActivity();
        mActivity = (AppCompatActivity) getActivity();
        mIvSort.setOnClickListener(v -> {
            Intent intent = new Intent(mContext, ReSortCategoryActivity.class);
            intent.putParcelableArrayListExtra("categoryList", currentCategoryList);
            startActivityForResult(intent, 1);
        });

        initTabLayout();
        mPresenter.subscribe();
    }

    private void initTabLayout() {
        mTlCategoryList.setTabMode(TabLayout.MODE_SCROLLABLE);
        mTlCategoryList.setTabTextColors(Color.parseColor("#999999"), Color.BLACK);
        mTlCategoryList.setSelectedTabIndicatorColor(Color.parseColor("#FBE04C"));
        mTlCategoryList.setupWithViewPager(mViewPager);
        mTlCategoryList.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
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
        createFragmentsAndTitles();

        FixPagerAdapter fixPagerAdapter = new FixPagerAdapter(getChildFragmentManager(), mFragments);
        fixPagerAdapter.setTitles(mTitles);
        mViewPager.setAdapter(fixPagerAdapter);
        mViewPager.setOffscreenPageLimit(mFragments.size());
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                //
            }

            @Override
            public void onPageSelected(int position) {
                if (!((CategoryFragment) mFragments.get(position)).hasData()) {
                    mPresenter.loadCategoryConts(1, currentCategoryList.get(position).getCategoryId(), 0);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                //
            }
        });
        mViewPager.setCurrentItem(1);
    }

    private void createFragmentsAndTitles() {
        if (mTitles == null) {
            mTitles = new ArrayList<>();
        } else {
            mTitles.clear();
        }

        if (mFragments == null) {
            mFragments = new ArrayList<>();
        } else {
            mFragments.clear();
        }

        for (CategoryBean.CategoryListBean categoryBean : currentCategoryList) {
            mTitles.add(categoryBean.getName());
            CategoryFragment fragment = CategoryFragment.newInstance(categoryBean.getName(), categoryBean.getCategoryId());
            fragment.setRefreshCallBack(integer -> {
                if (Constants.LOAD_REFRESH == integer) {
                    mPresenter.loadCategoryContsRefresh(categoryBean.getCategoryId());
                } else if (Constants.LOAD_MORE == integer) {
                    mPresenter.loadCategoryContsMore(categoryBean.getCategoryId());
                }
                return null;
            });
            mFragments.add(fragment);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 0 && requestCode == 1) {
            ArrayList<CategoryBean.CategoryListBean> categoryList = data.getParcelableArrayListExtra("categoryList");
            showCategoryList(categoryList);
        }

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mPresenter.unsubscribe();
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

    @Override
    public void showCategoryList(ArrayList<CategoryBean.CategoryListBean> beans) {
        currentCategoryList = beans;
        initViewPager();
    }

    @Override
    public void showList(List<ContEntity> beans, String categoryId) {
        int position = getPosition(categoryId);
        ((CategoryFragment) mFragments.get(position)).replaceData(beans);
    }

    @Override
    public void loadMoreList(List<ContEntity> beans, String categoryId) {
        int position = getPosition(categoryId);
        ((CategoryFragment) mFragments.get(position)).addData(beans);
    }

    @Override
    public void loadMoreFinish(boolean isSuccess, String categoryId) {
        int position = getPosition(categoryId);
        ((CategoryFragment) mFragments.get(position)).loadMoreFinish(isSuccess);
    }

    @Override
    public void loadRefreshFinish(boolean isSuccess, String categoryId) {
        int position = getPosition(categoryId);
        ((CategoryFragment) mFragments.get(position)).refreshFinish(isSuccess);

    }

    @Override
    public void setPresenter(ChannelContract.Presenter presenter) {
        mPresenter = presenter;
    }

    private int getPosition(String categoryId) {
        int position = 0;
        for (int i = 0; i < currentCategoryList.size(); i++) {
            if (currentCategoryList.get(i).getCategoryId().equals(categoryId)) {
                position = i;
                break;
            }
        }
        return position;
    }
}
