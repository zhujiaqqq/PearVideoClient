package com.example.pearvideoclient.channel;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.material.tabs.TabLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.pearvideoclient.MyApplication;
import com.example.pearvideoclient.R;
import com.example.pearvideoclient.entity.CategoryBean;
import com.example.pearvideoclient.entity.CategoryContsBean;
import com.example.pearvideoclient.utils.MyToast;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.wang.avi.AVLoadingIndicatorView;

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

    private AVLoadingIndicatorView mLoadingView;
    private TabLayout mTlCategoryList;
    private RecyclerView mRvCategoryConts;
    private SmartRefreshLayout mRefreshLayout;
    private ImageView mIvSort;

    private CategoryContsAdapter mCategoryContsAdapter;
    private ArrayList<CategoryBean.CategoryListBean> currentCategoryList;

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
        mContext = getActivity();

        mPresenter.subscribe();
    }

    private void initView(View view) {
        mLoadingView = view.findViewById(R.id.loading_view);
        mTlCategoryList = view.findViewById(R.id.tl_category_list);
        mRvCategoryConts = view.findViewById(R.id.rv_category_conts_list);
        mRefreshLayout = view.findViewById(R.id.refresh_layout);
        mIvSort = view.findViewById(R.id.iv_sort);
    }

    private void initData() {

        mRvCategoryConts.setLayoutManager(new GridLayoutManager(mContext, 2));
        mCategoryContsAdapter = new CategoryContsAdapter(R.layout.adapter_category_conts_item, new ArrayList<>());
        mRvCategoryConts.setAdapter(mCategoryContsAdapter);

        mTlCategoryList.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mPresenter.loadCategoryConts("1", (String) tab.getTag(), "0");
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

        mRefreshLayout.setOnRefreshListener(refreshLayout -> mPresenter.loadCategoryContsRefresh());
        mRefreshLayout.setOnLoadMoreListener(refreshLayout -> mPresenter.loadCategoryContsMore());

        mIvSort.setOnClickListener(v -> {
            Intent intent = new Intent(mContext, ReSortCategoryActivity.class);
            intent.putParcelableArrayListExtra("categoryList", currentCategoryList);
            startActivityForResult(intent, 1);
        });
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
    public void showList(List<CategoryContsBean.ContListBean> beans) {
        mCategoryContsAdapter.replaceData(beans);
    }

    @Override
    public void showLoading() {
        mLoadingView.show();
    }

    @Override
    public void cancelLoading() {
        mLoadingView.hide();
    }

    @Override
    public void showErrorToast(String loadingFail) {
        MyToast.getInstance(MyApplication.getInstance()).show(loadingFail, 3000);
    }

    @Override
    public void showCategoryList(ArrayList<CategoryBean.CategoryListBean> beans) {
        currentCategoryList = beans;
        mTlCategoryList.removeAllTabs();
        for (CategoryBean.CategoryListBean bean : beans) {
            TabLayout.Tab tab = mTlCategoryList.newTab().setText(bean.getName()).setTag(bean.getCategoryId());
            mTlCategoryList.addTab(tab);
        }
    }

    @Override
    public void loadMoreList(List<CategoryContsBean.ContListBean> beans) {
        mCategoryContsAdapter.addData(beans);
    }

    @Override
    public void loadMoreFinish(boolean isSuccess) {
        mRefreshLayout.finishLoadMore(isSuccess);
    }

    @Override
    public void loadRefreshFinish(boolean isSuccess) {
        mRefreshLayout.finishRefresh(isSuccess);
    }

    @Override
    public void setPresenter(ChannelContract.Presenter presenter) {
        mPresenter = presenter;
    }
}
