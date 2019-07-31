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

/**
 * @author zhujiaqqq
 * @date 2019-07-11
 */
public class FollowFragment extends Fragment {

    private View mView;
    private TextView mTvAddFollow;
    private RecyclerView mRvFollowInfoList;

    private Context mContext;

    private FollowInfoListAdapter mAdapter;

    public static FollowFragment newInstance() {
        Bundle bundle = new Bundle();
        FollowFragment fragment = new FollowFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_follow, container, false);
        return mView;
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
        mAdapter = new FollowInfoListAdapter(R.layout.adapter_follow_info_item, null);
        mRvFollowInfoList.setAdapter(mAdapter);

        mTvAddFollow.setOnClickListener(v -> {
            // TODO: 2019-07-17 跳转添加关注页面
        });
    }

    private void initView(@NonNull View view) {
        mTvAddFollow = view.findViewById(R.id.tv_add_follow);
        mRvFollowInfoList = view.findViewById(R.id.rv_follow_info_list);
    }
}
