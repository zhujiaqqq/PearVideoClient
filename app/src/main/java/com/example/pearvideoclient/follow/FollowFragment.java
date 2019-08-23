package com.example.pearvideoclient.follow;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.pearvideoclient.LocalHandler;
import com.example.pearvideoclient.R;
import com.example.pearvideoclient.author.AuthorActivity;
import com.example.pearvideoclient.entity.MyFollowContBean;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.List;

/**
 * @author zhujiaqqq
 * @date 2019-07-11
 */
public class FollowFragment extends Fragment implements FollowContract.View, LocalHandler.IHandler {
    private static final String TAG = "FollowFragment";
    public static final int MSG_SET_PLAY = 0x11;
    private TextView mTvAddFollow;
    private RecyclerView mRvFollowInfoList;
    private RecyclerView mRvFollowUserList;
    private SmartRefreshLayout mRefreshLayout;

    private Context mContext;

    private FollowInfoListAdapter mFollowInfoListAdapter;
    private FollowUserListAdapter mFollowUserListAdapter;
    private FollowContract.Presenter mPresenter;
    private LinearLayoutManager mLayoutManager;

    private int mPlayPosition = 0;

    private LocalHandler mHandler = new LocalHandler(this);
    private boolean mIdleState;

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

        mLayoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
        mRvFollowInfoList.setLayoutManager(mLayoutManager);
        mFollowInfoListAdapter = new FollowInfoListAdapter(R.layout.adapter_follow_info_item, null);
        mRvFollowInfoList.setAdapter(mFollowInfoListAdapter);

        mRvFollowUserList.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
        mFollowUserListAdapter = new FollowUserListAdapter(R.layout.adapter_follow_user_item, null);
        mFollowUserListAdapter.setOnItemChildClickListener((adapter, view, position) -> {
            if (view.getId() == R.id.rl_parent) {
                if (adapter instanceof FollowUserListAdapter) {
                    MyFollowContBean.FollowUserListBean userBean = ((FollowUserListAdapter) adapter).getData().get(position);
                    String userId = userBean.getUserId();
                    Intent intent = new Intent(mContext, AuthorActivity.class);
                    intent.putExtra("userId", userId);
                    mContext.startActivity(intent);
                }
            }
        });
        mRvFollowUserList.setAdapter(mFollowUserListAdapter);

        mTvAddFollow.setOnClickListener(v -> {
            // TODO: 2019-07-17 跳转添加关注页面
        });

        mPresenter.loadMyFollowList();

        mRefreshLayout.setOnLoadMoreListener(refreshLayout -> mPresenter.loadMoreMyFollowList());
        mRefreshLayout.setOnRefreshListener(refreshLayout -> mPresenter.refreshMyFollowList());

        mRvFollowInfoList.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    Log.i(TAG, "onScrollStateChanged: 停止");
                    Message message = mHandler.obtainMessage();
                    message.arg1 = mPlayPosition;
                    message.what = MSG_SET_PLAY;
                    mHandler.sendMessage(message);
                }
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (mLayoutManager != null) {
                    int firstVisibleItem = mLayoutManager.findFirstVisibleItemPosition();
                    View firstView = mLayoutManager.findViewByPosition(firstVisibleItem);
                    if (null != firstView) {
                        mIdleState = true;
                        if (dy > 0) {
                            if (firstView.getHeight() + firstView.getTop() <= firstView.getHeight() / 3) {
                                if (mLayoutManager.getChildCount() < 2) {
                                    return;
                                }
                                if (mPlayPosition == firstVisibleItem + 1) {
                                    return;
                                }
                                mPlayPosition = firstVisibleItem + 1;
                            } else {
                                if (mPlayPosition == firstVisibleItem) {
                                    return;
                                }
                                mPlayPosition = firstVisibleItem;
                            }
                        }
                        if (dy < 0) {
                            if (firstView.getHeight() + firstView.getTop() >= firstView.getHeight() / 3) {
                                if (mLayoutManager.getChildCount() < 2) {
                                    return;
                                }
                                if (mPlayPosition == firstVisibleItem) {
                                    return;
                                }
                                mPlayPosition = firstVisibleItem;
                            } else {
                                if (mPlayPosition == firstVisibleItem + 1) {
                                    return;
                                }
                                mPlayPosition = firstVisibleItem + 1;
                            }
                        }
                        Log.i(TAG, "onScrolled: " + mPlayPosition);
                    } else {
                        mIdleState = false;
                    }
                } else {
                    mIdleState = false;
                }
            }
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
        //
    }

    @Override
    public void cancelLoading() {
        //
    }

    @Override
    public void showErrorToast(String loadingFail) {
        
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

    @Override
    public void handlerMessage(Message msg) {
        if (MSG_SET_PLAY == msg.what && mIdleState) {
            mFollowInfoListAdapter.setPlay(msg.arg1);
            mHandler.removeMessages(MSG_SET_PLAY);
        }
    }
}
