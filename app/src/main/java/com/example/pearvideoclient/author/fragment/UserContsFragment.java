package com.example.pearvideoclient.author.fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.pearvideoclient.MyApplication;
import com.example.pearvideoclient.R;
import com.example.pearvideoclient.author.AuthorActivity;
import com.example.pearvideoclient.entity.UserConts;
import com.scwang.smartrefresh.layout.api.RefreshLayout;

import java.util.List;

/**
 * @Description: java类作用描述
 * @Author: jiazhu
 * @Date: 2019-08-13 15:45
 * @ClassName: UserContsFragment
 */
public class UserContsFragment extends Fragment {

    private RecyclerView mRvHotList;
    private RecyclerView mRvNewList;
    private RefreshLayout mRefreshLayout;
    private TextView mTvHot;

    private UserContsAdapter mHotAdapter;
    private UserContsAdapter mNewAdapter;

    public static UserContsFragment newInstance() {
        Bundle bundle = new Bundle();
        UserContsFragment fragment = new UserContsFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_user_conts, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initView(view);
        initData();
    }

    private void initView(View view) {
        mRvHotList = view.findViewById(R.id.rv_hot_list);
        mRvNewList = view.findViewById(R.id.rv_new_list);
        mRefreshLayout = view.findViewById(R.id.refresh_layout);
        mTvHot = view.findViewById(R.id.tv_hot);
    }

    private void initData() {
        Context context = getActivity();

        mRvHotList.setLayoutManager(new GridLayoutManager(context, 2));
        mHotAdapter = new UserContsAdapter(R.layout.adapter_category_conts_item, null);
        mRvHotList.setAdapter(mHotAdapter);

        mRvNewList.setLayoutManager(new GridLayoutManager(context, 2));
        mNewAdapter = new UserContsAdapter(R.layout.adapter_category_conts_item, null);
        mRvNewList.setAdapter(mNewAdapter);
        mRefreshLayout.setEnableRefresh(false);
        mRefreshLayout.setOnRefreshListener(refreshLayout -> ((AuthorActivity) getActivity()).userContsRefresh());
        mRefreshLayout.setOnLoadMoreListener(refreshLayout -> ((AuthorActivity) getActivity()).userContsLoadMore());
    }

    public void loadHotConts(List<UserConts.ContListBean> hotList) {
        if (hotList.isEmpty()) {
            mRvHotList.setVisibility(View.GONE);
            mTvHot.setVisibility(View.GONE);
            return;
        }
        mHotAdapter.replaceData(hotList);
    }

    public void loadNewConts(List<UserConts.ContListBean> contList) {
        mNewAdapter.replaceData(contList);
    }

    public void loadMoreNewConts(List<UserConts.ContListBean> contList) {
        mNewAdapter.addData(contList);
    }

    public void loadRefreshFinish(boolean isSuccess) {
        mRefreshLayout.finishRefresh(isSuccess);
    }

    public void loadMoreFinish(boolean isSuccess) {
        mRefreshLayout.finishLoadMore(isSuccess);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        MyApplication.getRefWatcher(getActivity()).watch(this);
    }
}
