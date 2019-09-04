package com.example.pearvideoclient.home.fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.pearvideoclient.CommonCallBack;
import com.example.pearvideoclient.Constants;
import com.example.pearvideoclient.MyApplication;
import com.example.pearvideoclient.R;
import com.example.pearvideoclient.entity.NewsEntity;
import com.scwang.smartrefresh.layout.api.RefreshLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: java类作用描述
 * @Author: jiazhu
 * @Date: 2019-08-21 15:03
 * @ClassName: VientianeFragment
 */
public class VientianeFragment extends Fragment {

    private RecyclerView mRvVientianeList;
    private RefreshLayout mRefreshLayout;

    private VientianeAdapter mVientianeAdapter;

    private CommonCallBack<Integer, Void> refreshCallBack;

    public static VientianeFragment newInstance() {
        Bundle bundle = new Bundle();
        VientianeFragment fragment = new VientianeFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_vientiane, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        initData();
    }

    private void initView(View view) {
        mRvVientianeList = view.findViewById(R.id.rv_vientiane_list);
        mRefreshLayout = view.findViewById(R.id.refresh_layout);
    }

    private void initData() {
        Context context = getActivity();
        mRefreshLayout.setOnRefreshListener(refreshLayout -> {
            if (refreshCallBack != null) {
                refreshCallBack.todo(Constants.LOAD_REFRESH);
            }

        });
        mRefreshLayout.setOnLoadMoreListener(refreshLayout -> {
            if (refreshCallBack != null) {
                refreshCallBack.todo(Constants.LOAD_MORE);
            }
        });

        mRvVientianeList.setLayoutManager(new LinearLayoutManager(context));
        mVientianeAdapter = new VientianeAdapter(new ArrayList<>());
        mRvVientianeList.setAdapter(mVientianeAdapter);
    }

    public void setVientianeList(List<NewsEntity> data) {
        mVientianeAdapter.replaceData(data);
    }

    public void loadMoreVientianeList(List<NewsEntity> data) {
        mVientianeAdapter.addData(data);
    }

    public void refreshFinish(boolean isSuccess) {
        mRefreshLayout.finishRefresh(isSuccess);
    }

    public void loadMoreFinish(boolean isSuccess) {
        mRefreshLayout.finishLoadMore(isSuccess);
    }

    public void setRefreshCallBack(CommonCallBack<Integer, Void> refreshCallBack) {
        this.refreshCallBack = refreshCallBack;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        MyApplication.getRefWatcher(getActivity()).watch(this);
    }
}
