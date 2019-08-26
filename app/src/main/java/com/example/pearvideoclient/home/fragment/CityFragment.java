package com.example.pearvideoclient.home.fragment;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.pearvideoclient.CommonCallBack;
import com.example.pearvideoclient.Constants;
import com.example.pearvideoclient.R;
import com.example.pearvideoclient.entity.LocalContEntity;
import com.example.pearvideoclient.utils.ScreenUtils;
import com.scwang.smartrefresh.layout.api.RefreshLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: java类作用描述
 * @Author: jiazhu
 * @Date: 2019-08-21 15:03
 * @ClassName: CityFragment
 */
public class CityFragment extends Fragment {

    private RefreshLayout mRefreshLayout;
    private RecyclerView mRvCityList;

    private CityAdapter mCityAdapter;

    private CommonCallBack<Integer, Void> mCityFragmentCallBack;
    private Context mContext;

    public void setCityFragmentCallBack(CommonCallBack<Integer, Void> cityFragmentCallBack) {
        mCityFragmentCallBack = cityFragmentCallBack;
    }

    public static CityFragment newInstance() {
        Bundle bundle = new Bundle();
        CityFragment fragment = new CityFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_city, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        initData();
    }

    private void initView(View view) {
        mRefreshLayout = view.findViewById(R.id.refresh_layout);
        mRvCityList = view.findViewById(R.id.rv_city_list);
    }

    private void initData() {
        mContext = getActivity();
        mRvCityList.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        mCityAdapter = new CityAdapter(new ArrayList<>());
        mRvCityList.setAdapter(mCityAdapter);

        mRefreshLayout.setOnLoadMoreListener(refreshLayout -> {
            if (mCityFragmentCallBack != null) {
                mCityFragmentCallBack.todo(Constants.LOAD_MORE);
            }
        });
        mRefreshLayout.setOnRefreshListener(refreshLayout -> {
        });
        if (mCityFragmentCallBack != null) {
            mCityFragmentCallBack.todo(Constants.LOAD_REFRESH);
        }

        addHeaderView();
    }

    /**
     * 添加headerView
     */
    private void addHeaderView() {
        View headerView = getLayoutInflater().inflate(R.layout.item_city_header_layout, null);
        headerView.setLayoutParams(new DrawerLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ScreenUtils.dip2px(mContext, 48)));
        headerView.setOnClickListener(v -> {
            if (mCityFragmentCallBack != null) {
                mCityFragmentCallBack.todo(Constants.HEADER_CLICK);
            }
        });
        mCityAdapter.setHeaderView(headerView);
    }

    public void addData(List<LocalContEntity> data) {
        mCityAdapter.addData(data);
    }

    public void replaceData(List<LocalContEntity> data) {
        mCityAdapter.replaceData(data);
    }

    public void loadMoreFinish(boolean isSuccess) {
        mRefreshLayout.finishLoadMore(isSuccess);
    }

    public void refreshFinish(boolean isSuccess) {
        mRefreshLayout.finishRefresh(isSuccess);
    }
}
