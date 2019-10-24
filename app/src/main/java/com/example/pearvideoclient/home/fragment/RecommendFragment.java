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

import com.example.apublic.CommonCallBack;
import com.example.pearvideoclient.Constants;
import com.example.pearvideoclient.MyApplication;
import com.example.pearvideoclient.R;
import com.example.pearvideoclient.entity.RecommendEntity;
import com.scwang.smartrefresh.layout.api.RefreshLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: java类作用描述
 * @Author: jiazhu
 * @Date: 2019-08-21 15:03
 * @ClassName: RecommendFragment
 */
public class RecommendFragment extends Fragment {

    private RefreshLayout mRefreshLayout;
    private RecyclerView mRvRecommendList;

    private RecommendAdapter mRecommendAdapter;

    private CommonCallBack<Integer, Void> refreshCallBack;

    public static RecommendFragment newInstance() {
        Bundle bundle = new Bundle();
        RecommendFragment fragment = new RecommendFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_recommend, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        initData();
    }

    private void initView(View view) {
        mRefreshLayout = view.findViewById(R.id.refresh_layout);
        mRvRecommendList = view.findViewById(R.id.rv_recommend_list);
    }

    private void initData() {
        Context context = getActivity();
        mRvRecommendList.setLayoutManager(new LinearLayoutManager(context));
        mRecommendAdapter = new RecommendAdapter(new ArrayList<>());
        mRvRecommendList.setAdapter(mRecommendAdapter);

        mRefreshLayout.setOnLoadMoreListener(refreshLayout -> {
            if (refreshCallBack != null) {
                refreshCallBack.todo(Constants.LOAD_MORE);
            }
        });
        mRefreshLayout.setOnRefreshListener(refreshLayout -> {
            if (refreshCallBack != null) {
                refreshCallBack.todo(Constants.LOAD_REFRESH);
            }
        });
    }

    public void replaceData(List<RecommendEntity> list) {
        mRecommendAdapter.replaceData(list);
    }

    public void addData(List<RecommendEntity> list) {
        mRecommendAdapter.addData(list);
    }

    public void loadMoreFinish(boolean isSuccess) {
        mRefreshLayout.finishLoadMore(isSuccess);
    }

    public void refreshFinish(boolean isSuccess) {
        mRefreshLayout.finishRefresh(isSuccess);
    }

    public void setRefreshCallBack(CommonCallBack<Integer, Void> refreshCallBack) {
        this.refreshCallBack = refreshCallBack;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        MyApplication.Companion.getRefWatcher(getActivity()).watch(this);
    }
}
