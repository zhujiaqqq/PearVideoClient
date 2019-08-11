package com.example.pearvideoclient.follow;

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
import android.widget.TextView;

import com.example.pearvideoclient.R;
import com.example.pearvideoclient.entity.bean.MyFollowContBean;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.List;

/**
 * @author zhujiaqqq
 * @date 2019-07-11
 */
public class FollowFragment extends Fragment implements FollowContract.View {

    private TextView mTvAddFollow;
    private RecyclerView mRvFollowInfoList;
    private RecyclerView mRvFollowUserList;
    private SmartRefreshLayout mRefreshLayout;

    private Context mContext;

    private FollowInfoListAdapter mFollowInfoListAdapter;
    private FollowUserListAdapter mFollowUserListAdapter;
    private FollowContract.Presenter mPresenter;

    public static FollowFragment newInstance() {
        Bundle bundle = new Bundle();
        FollowFragment fragment = new FollowFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_follow, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initView(view);

        initData();
    }

    private void initData() {
        mContext = getActivity();
        mRvFollowInfoList.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        mFollowInfoListAdapter = new FollowInfoListAdapter(R.layout.adapter_follow_info_item, null);
        mRvFollowInfoList.setAdapter(mFollowInfoListAdapter);

        mRvFollowUserList.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
        mFollowUserListAdapter = new FollowUserListAdapter(R.layout.adapter_follow_user_item, null);
        mFollowUserListAdapter.setOnItemChildClickListener((adapter, view, position) -> {
            switch (view.getId()) {
                case R.id.rl_parent:
                    if (adapter instanceof FollowUserListAdapter) {
                        MyFollowContBean.FollowUserListBean userBean = ((FollowUserListAdapter) adapter).getData().get(position);
                        String userId = userBean.getUserId();
                        // TODO: 2019-08-04 跳转用户信息页面
//                    Intent intent = new Intent(ContentActivity.this, AuthorActivity.class);
//                    intent.putExtra("userId", userId);
//                    mContent.startActivity(intent);
                    }
                    break;
                default:
                    break;
            }
        });
        mRvFollowUserList.setAdapter(mFollowUserListAdapter);

        mTvAddFollow.setOnClickListener(v -> {
            // TODO: 2019-07-17 跳转添加关注页面
        });

        mPresenter.loadMyFollowList();

        mRefreshLayout.setOnLoadMoreListener(refreshLayout -> {
            mPresenter.loadMoreMyFollowList();
        });
        mRefreshLayout.setOnRefreshListener(refreshLayout -> {
            mPresenter.refreshMyFollowList();
        });
    }

    private void initView(@NonNull View view) {
        mTvAddFollow = view.findViewById(R.id.tv_add_follow);
        mRvFollowInfoList = view.findViewById(R.id.rv_follow_info_list);
        mRvFollowUserList = view.findViewById(R.id.rl_follow_user_list);
        mRefreshLayout = view.findViewById(R.id.refresh_layout);
    }

    @Override
    public void setPresenter(FollowContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void cancelLoading() {

    }

    @Override
    public void showFollowUser(List<MyFollowContBean.FollowUserListBean> list) {
        mFollowUserListAdapter.replaceData(list);
    }

    @Override
    public void showFollowData(List<MyFollowContBean.DataListBean> list) {
        mFollowInfoListAdapter.replaceData(list);
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
    public void loadMoreFollowData(MyFollowContBean bean) {
        List<MyFollowContBean.DataListBean> dataList = bean.getDataList();
        mFollowInfoListAdapter.addData(dataList);
    }
}
