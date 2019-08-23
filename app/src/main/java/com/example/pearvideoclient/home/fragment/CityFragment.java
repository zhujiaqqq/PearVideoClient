package com.example.pearvideoclient.home.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.pearvideoclient.CommonCallBack;
import com.example.pearvideoclient.Constants;
import com.example.pearvideoclient.R;
import com.example.pearvideoclient.entity.LocalContEntity;
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

    private CommonCallBack<Integer, Void> mRefreshCallBack;

    public void setRefreshCallBack(CommonCallBack<Integer, Void> refreshCallBack) {
        mRefreshCallBack = refreshCallBack;
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
        Context context = getActivity();
        mRvCityList.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false));
        mCityAdapter = new CityAdapter(new ArrayList<>());
        mRvCityList.setAdapter(mCityAdapter);

        mRefreshLayout.setOnLoadMoreListener(refreshLayout -> {
            if (mRefreshCallBack != null) {
                mRefreshCallBack.todo(Constants.LOAD_MORE);
            }
        });
        mRefreshLayout.setOnRefreshListener(refreshLayout -> {
        });
        if (mRefreshCallBack != null) {
            mRefreshCallBack.todo(Constants.LOAD_REFRESH);
        }
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
