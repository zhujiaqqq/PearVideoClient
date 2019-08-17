package com.example.pearvideoclient.author.fragment;

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

import com.example.pearvideoclient.R;
import com.example.pearvideoclient.author.AuthorActivity;
import com.example.pearvideoclient.entity.UserAlbumsBean;
import com.scwang.smartrefresh.layout.api.RefreshLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: java类作用描述
 * @Author: jiazhu
 * @Date: 2019-08-13 15:46
 * @ClassName: UserAlbumsFragment
 */
public class UserAlbumsFragment extends Fragment {
    private RefreshLayout mRefreshLayout;
    private RecyclerView mRvUserAlbumsList;

    private UserAlbumsAdapter mUserAlbumsAdapter;

    private Context mContext;

    public static UserAlbumsFragment newInstance() {
        Bundle bundle = new Bundle();
        UserAlbumsFragment fragment = new UserAlbumsFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_user_albums, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        initData();
    }

    private void initView(View view) {
        mRefreshLayout = view.findViewById(R.id.refresh_layout);
        mRvUserAlbumsList = view.findViewById(R.id.rv_user_albums_list);
    }

    private void initData() {
        mContext = getActivity();
        mRvUserAlbumsList.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));

        mUserAlbumsAdapter = new UserAlbumsAdapter(mContext, new ArrayList<>());
        mRvUserAlbumsList.setAdapter(mUserAlbumsAdapter);

        mRefreshLayout.setEnableRefresh(false);

        mRefreshLayout.setOnRefreshListener(refreshLayout -> ((AuthorActivity) getActivity()).userAlbumsRefresh());
        mRefreshLayout.setOnLoadMoreListener(refreshLayout -> ((AuthorActivity) getActivity()).userAlbumsLoadMore());
    }

    public void loadAlbumsList(List<UserAlbumsBean.AlbumListBean> albumList) {
        mUserAlbumsAdapter.replaceData(albumList);
    }

    public void loadMoreAlbumsList(List<UserAlbumsBean.AlbumListBean> albumList) {
        mUserAlbumsAdapter.addData(albumList);
    }

    public void loadMoreFinish(boolean isSuccess) {
        mRefreshLayout.finishLoadMore(isSuccess);
    }

    public void loadRefreshFinish(boolean isSuccess) {
        mRefreshLayout.finishRefresh(isSuccess);
    }
}
