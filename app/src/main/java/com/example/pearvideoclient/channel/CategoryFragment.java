package com.example.pearvideoclient.channel;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pearvideoclient.CommonCallBack;
import com.example.pearvideoclient.Constants;
import com.example.pearvideoclient.R;
import com.example.pearvideoclient.channel.adapter.ContListAdapter;
import com.example.pearvideoclient.entity.ContEntity;
import com.scwang.smartrefresh.layout.api.RefreshLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: java类作用描述
 * @Author: jiazhu
 * @Date: 2019-08-29 15:51
 * @ClassName: CategoryFragment
 */
public class CategoryFragment extends Fragment {
    private RecyclerView mRvCategoryContsList;
    private RefreshLayout mRefreshLayout;
    private String mName;
    private String mCategoryId;
    private ContListAdapter mContListAdapter;

    private CommonCallBack<Integer, Void> refreshCallBack;

    public void setRefreshCallBack(CommonCallBack<Integer, Void> refreshCallBack) {
        this.refreshCallBack = refreshCallBack;
    }

    public static CategoryFragment newInstance(String name, String categoryId) {
        Bundle bundle = new Bundle();
        bundle.putString("name", name);
        bundle.putString("categoryId", categoryId);
        CategoryFragment fragment = new CategoryFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_category_conts, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        initData();
    }

    private void initView(View view) {
        mRefreshLayout = view.findViewById(R.id.refresh_layout);
        mRvCategoryContsList = view.findViewById(R.id.rv_category_cont_list);
    }

    private void initData() {
        if (getArguments() != null) {
            mName = getArguments().getString("name");
            mCategoryId = getArguments().getString("categoryId");
        }

        AppCompatActivity activity = (AppCompatActivity) getActivity();
        mRvCategoryContsList.setLayoutManager(new LinearLayoutManager(activity, RecyclerView.VERTICAL, false));
        mContListAdapter = new ContListAdapter(new ArrayList<>());
        mRvCategoryContsList.setAdapter(mContListAdapter);

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
    }

    public void replaceData(List<ContEntity> data) {
        mContListAdapter.replaceData(data);
    }

    public void addData(List<ContEntity> data) {
        mContListAdapter.addData(data);
    }

    public void loadMoreFinish(boolean isSuccess) {
        mRefreshLayout.finishLoadMore(isSuccess);
    }

    public void refreshFinish(boolean isSuccess) {
        mRefreshLayout.finishRefresh(isSuccess);
    }

    public boolean hasData() {
        return mContListAdapter.getData().size() > 0;
    }
}
