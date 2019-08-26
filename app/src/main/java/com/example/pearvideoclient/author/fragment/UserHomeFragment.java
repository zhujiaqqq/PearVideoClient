package com.example.pearvideoclient.author.fragment;

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

import com.example.pearvideoclient.R;
import com.example.pearvideoclient.author.AuthorActivity;
import com.example.pearvideoclient.entity.AuthorHomeBean;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.List;

/**
 * @Description: java类作用描述
 * @Author: jiazhu
 * @Date: 2019-08-13 15:45
 * @ClassName: UserHomeFragment
 */
public class UserHomeFragment extends Fragment {

    private SmartRefreshLayout mRefreshLayout;
    private RecyclerView mRvUserHomeList;

    private UserHomeAdapter mUserHomeAdapter;

    public static UserHomeFragment newInstance() {
        Bundle bundle = new Bundle();
        UserHomeFragment fragment = new UserHomeFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_user_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initView(view);
        initData();
    }

    private void initView(@NonNull View view) {
        mRefreshLayout = view.findViewById(R.id.refresh_layout);
        mRvUserHomeList = view.findViewById(R.id.rv_user_home_list);
    }

    private void initData() {
        Context context = getActivity();

        mRvUserHomeList.setLayoutManager(new LinearLayoutManager(
                context, LinearLayoutManager.VERTICAL, false
        ));
        mUserHomeAdapter = new UserHomeAdapter(R.layout.adapter_user_home_item, null);
        mRvUserHomeList.setAdapter(mUserHomeAdapter);

        mRefreshLayout.setOnRefreshListener(refreshLayout -> ((AuthorActivity) getActivity()).userHomeRefresh());
        mRefreshLayout.setOnLoadMoreListener(refreshLayout -> ((AuthorActivity) getActivity()).userHomeLoadMore());
    }


    public void loadDataList(List<AuthorHomeBean.DataListBean> dataList) {
        mUserHomeAdapter.replaceData(dataList);
    }

    public void loadMoreDataList(List<AuthorHomeBean.DataListBean> dataList) {
        mUserHomeAdapter.addData(dataList);
    }

    public void loadMoreFinish(boolean isSuccess) {
        mRefreshLayout.finishLoadMore(isSuccess);
    }

    public void loadRefreshFinish(boolean isSuccess) {
        mRefreshLayout.finishRefresh(isSuccess);
    }
}
