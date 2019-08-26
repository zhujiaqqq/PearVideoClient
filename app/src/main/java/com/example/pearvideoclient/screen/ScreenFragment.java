package com.example.pearvideoclient.screen;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pearvideoclient.MyApplication;
import com.example.pearvideoclient.R;
import com.example.pearvideoclient.content.VideoPlayerIJK;
import com.example.pearvideoclient.entity.PaikeFineVideoBean;
import com.example.pearvideoclient.utils.MyToast;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhujiaqqq
 * @date 2019-07-11
 */
public class ScreenFragment extends Fragment implements ScreenContract.View {
    private VideoPlayerIJK mVideoPlayer;
    private RecyclerView mRvVideoList;
    private ImageView mIvVideoImg;

    private Context mContext;
    private VideoAdapter mVideoAdapter;

    private ScreenContract.Presenter mPresenter;

    private View.OnClickListener mClickListener = v -> {
        switch (v.getId()) {
            case R.id.tv_start_video:
                break;
            case R.id.tv_see_detail:
                break;
            case R.id.tv_remark_list:
                break;
            case R.id.tv_my_video:
                break;
            default:
                break;
        }
    };
    private PaikeFineVideoBean.ConfigInfoBean mConfigInfo;

    public static ScreenFragment newInstance() {
        Bundle bundle = new Bundle();
        ScreenFragment fragment = new ScreenFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_screen, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        initData();
    }

    private void initView(View view) {
        mVideoPlayer = view.findViewById(R.id.video_player_tech);
        mRvVideoList = view.findViewById(R.id.rv_video_list);
        mIvVideoImg = view.findViewById(R.id.iv_video_img);

        view.findViewById(R.id.tv_start_video).setOnClickListener(mClickListener);
        view.findViewById(R.id.tv_see_detail).setOnClickListener(mClickListener);
        view.findViewById(R.id.tv_remark_list).setOnClickListener(mClickListener);
        view.findViewById(R.id.tv_my_video).setOnClickListener(mClickListener);
    }

    private void initData() {
        new ScreenPresenter(this);
        mContext = getActivity();
        mRvVideoList.setLayoutManager(new GridLayoutManager(mContext, 2));
        mVideoAdapter = new VideoAdapter(R.layout.adapter_paike_video_item, new ArrayList<>());
        mVideoAdapter.setOnItemChildClickListener((adapter, view, position) -> {
            if (view.getId() == R.id.rl_parent) {
                // TODO: 2019-08-26

            }
        });
        mRvVideoList.setAdapter(mVideoAdapter);
        mPresenter.subscribe();
    }

    @Override
    public void onDestroy() {
        mPresenter.unsubscribe();
        super.onDestroy();
    }

    @Override
    public void showPaikeVideos(List<PaikeFineVideoBean.VideoBean> data) {
        mVideoAdapter.replaceData(data);
    }

    @Override
    public void showPaikeInfo(PaikeFineVideoBean.ConfigInfoBean data) {
        mConfigInfo = data;
        mIvVideoImg.setImageResource(R.drawable.ic_paike_img);
    }

    @Override
    public void setPresenter(ScreenContract.Presenter presenter) {
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
        MyToast.getInstance(MyApplication.getInstance()).show(loadingFail, 3000);
    }
}
