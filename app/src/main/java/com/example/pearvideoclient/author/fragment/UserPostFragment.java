package com.example.pearvideoclient.author.fragment;

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

import com.example.pearvideoclient.R;
import com.example.pearvideoclient.author.AuthorActivity;
import com.example.pearvideoclient.entity.UserPostsBean;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: java类作用描述
 * @Author: jiazhu
 * @Date: 2019-08-13 15:45
 * @ClassName: UserPostFragment
 */
public class UserPostFragment extends Fragment {
    private SmartRefreshLayout mRefreshLayout;
    private RecyclerView mRvUserPostList;

    private UserPostAdapter mUserPostAdapter;
    private Context mContext;

    public static UserPostFragment newInstance() {
        Bundle bundle = new Bundle();
        UserPostFragment fragment = new UserPostFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_post, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        initData();
    }

    private void initView(View view) {
        mRefreshLayout = view.findViewById(R.id.refresh_layout);
        mRvUserPostList = view.findViewById(R.id.rv_user_post_list);
    }

    private void initData() {
        mContext = getActivity();
        mRvUserPostList.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        mUserPostAdapter = new UserPostAdapter(R.layout.adapter_user_post_item, new ArrayList<>());
        mRvUserPostList.setAdapter(mUserPostAdapter);
        mRefreshLayout.setOnLoadMoreListener(refreshLayout -> ((AuthorActivity) getActivity()).userPostsLoadMore());
        mRefreshLayout.setOnRefreshListener(refreshLayout -> ((AuthorActivity) getActivity()).userPostsRefresh());
        addHeadView();
    }

    /**
     * 添加headerView
     */
    private void addHeadView() {
        View headerView = getLayoutInflater().inflate(R.layout.item_post_head_layout, null);
        headerView.setLayoutParams(new DrawerLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        headerView.setOnClickListener(v -> {
            // TODO: 2019-08-29 评论
        });
        mUserPostAdapter.addHeaderView(headerView);
    }

    public void loadPostsList(List<UserPostsBean.PostListBean> postsList) {
        mUserPostAdapter.replaceData(postsList);
    }

    public void loadMorePostsList(List<UserPostsBean.PostListBean> postsList) {
        mUserPostAdapter.addData(postsList);
    }

    public void loadRefreshFinish(boolean isSuccess) {
        mRefreshLayout.finishRefresh(isSuccess);
    }

    public void loadMoreFinish(boolean isSuccess) {
        mRefreshLayout.finishLoadMore(isSuccess);
    }
}
