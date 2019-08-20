package com.example.pearvideoclient.mine;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v4.app.Fragment;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.pearvideoclient.R;
import com.example.pearvideoclient.entity.LoginBean;
import com.example.pearvideoclient.entity.UserInfoBean;
import com.example.pearvideoclient.utils.GlideUtils;
import com.example.pearvideoclient.view.MenuPopWindow;
import com.wang.avi.AVLoadingIndicatorView;

/**
 * @author zhujiaqqq
 * @date 2019-07-10
 */
public class MineFragment extends Fragment implements MineContract.View {


    private TextView mTvUserName, mTvUserSingle, mTvLogin, mTvUserDone;
    private ImageView mIvSmallUserImg, mIvUserImg, mIvUserBackground, mIvClose;
    private RecyclerView mRvFollowList;
    private RelativeLayout mRlMainContent, mRlMyInfo, mRlLogin;
    private EditText mEtTeleNum, mEtVarCode;
    private LinearLayout mLlEdit, mLlVieMyFollow;
    private AVLoadingIndicatorView mLoadingView;

    private MineContract.Presenter mPresenter;

    private MenuPopWindow window;

    private View.OnClickListener windowListener = v -> {
        switch (v.getId()) {
            case R.id.ll_my_message:
                break;
            case R.id.ll_my_favour:
                break;
            case R.id.ll_my_download:
                break;
            case R.id.tv_history:
                break;
            case R.id.tv_my_video:
                break;
            case R.id.tv_my_income:
                break;
            case R.id.tv_my_focus:
                break;
            case R.id.tv_free_video:
                break;
            case R.id.tv_setting:
                break;
            case R.id.tv_help:
                break;
            default:
                break;
        }
    };
    private BottomSheetBehavior<View> mViewBottomSheetBehavior;
    private NestedScrollView mScrollView;

    public static MineFragment newInstance() {
        Bundle args = new Bundle();
        MineFragment fragment = new MineFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_mine, container, false);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mPresenter.unsubscribe();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initView(view);
        initData();
    }

    private void initView(View view) {
        mScrollView = view.findViewById(R.id.scroll_view);
        mViewBottomSheetBehavior = BottomSheetBehavior.from(mScrollView);
        mScrollView.getViewTreeObserver().addOnGlobalLayoutListener(() -> mViewBottomSheetBehavior.setPeekHeight(mScrollView.getHeight()));

        mTvUserName = view.findViewById(R.id.tv_user_name);
        mTvUserSingle = view.findViewById(R.id.tv_user_single);
        mTvLogin = view.findViewById(R.id.tv_login);
        mTvUserDone = view.findViewById(R.id.tv_user_done);

        mIvUserImg = view.findViewById(R.id.iv_user_img);
        mIvClose = view.findViewById(R.id.iv_close);
        mIvUserBackground = view.findViewById(R.id.iv_user_background);
        mIvSmallUserImg = view.findViewById(R.id.iv_small_user_image);

        mRvFollowList = view.findViewById(R.id.rv_follow_list);

        mEtTeleNum = view.findViewById(R.id.et_tele_num);
        mEtVarCode = view.findViewById(R.id.et_var_code);

        mLlEdit = view.findViewById(R.id.ll_edit);
        mLlVieMyFollow = view.findViewById(R.id.view_my_follow);

        mRlLogin = view.findViewById(R.id.view_login);
        mRlMainContent = view.findViewById(R.id.main_content);
        mRlMyInfo = view.findViewById(R.id.view_my_info);

        mLoadingView = view.findViewById(R.id.loading_view);
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.subscribe();
    }

    private void showPopWindow() {

//        if (window == null) {
//            window = new MenuPopWindow(getActivity(), windowListener);
//        }
//        window.showAtLocation(mRlMainContent, Gravity.BOTTOM, 0, 0);
    }

    private void showBottom() {
        mViewBottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
        //根据状态不同显示隐藏
        if (mViewBottomSheetBehavior.getState() == BottomSheetBehavior.STATE_HIDDEN) {
            mViewBottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
        } else if (mViewBottomSheetBehavior.getState() == BottomSheetBehavior.STATE_COLLAPSED) {
            mViewBottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
        }
    }

    private void initData() {

        mTvLogin.setOnClickListener(
                v -> mPresenter.login(mEtTeleNum.getText().toString().trim(),
                        mEtVarCode.getText().toString().trim()));

        mIvClose.setOnClickListener(
                v -> showBottom());


        mLlEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: 2019-07-21 点击跳转修改资料页面
            }
        });

        mTvLogin.setOnClickListener(
                v -> mPresenter.login(mEtTeleNum.getText().toString(),
                        mEtVarCode.getText().toString()));
    }


    @Override
    public void setPresenter(MineContract.Presenter presenter) {
        this.mPresenter = presenter;
    }

    @Override
    public void showLoading() {
        mLoadingView.show();
    }

    @Override
    public void cancelLoading() {
        mLoadingView.hide();
    }


    @Override
    public void showUserInfo(LoginBean.UserInfoBean userInfoBean) {
        mTvUserName.setText(userInfoBean.getNickname());
        mTvUserSingle.setText(userInfoBean.getSignature());
        GlideUtils.loadCircleImage(userInfoBean.getImages().get(0).getFilePath(), mIvUserImg);
    }

    @Override
    public void showUserInfo(UserInfoBean userInfoBean) {
        mTvUserName.setText(userInfoBean.getUserInfo().getNickname());
        mTvUserSingle.setText(userInfoBean.getUserInfo().getSignature());
        GlideUtils.loadCircleImage(userInfoBean.getUserInfo().getPic(), mIvUserImg);
    }

    @Override
    public void showCloseIcon(boolean isShow) {
        mIvClose.setVisibility(isShow ? View.VISIBLE : View.GONE);
    }

    @Override
    public void showLoginLayout(boolean isShow) {
        mRlLogin.setVisibility(isShow ? View.VISIBLE : View.GONE);
    }

    @Override
    public void showMyFollowLayout(boolean isShow) {
        mLlVieMyFollow.setVisibility(isShow ? View.VISIBLE : View.GONE);
    }

    @Override
    public void showPopWindow(boolean isShow) {
        if (isShow) {
            showPopWindow();
        } else {
            window.dismiss();
        }
    }


}
