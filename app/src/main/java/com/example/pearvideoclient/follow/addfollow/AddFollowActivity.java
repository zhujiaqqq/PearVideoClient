package com.example.pearvideoclient.follow.addfollow;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.pearvideoclient.MyApplication;
import com.example.pearvideoclient.R;
import com.example.pearvideoclient.databinding.ActivityAddFollowBinding;
import com.example.pearvideoclient.entity.DomainListBean;
import com.example.pearvideoclient.entity.FollowUsersBean;

import java.util.List;

/**
 * @Description: java类作用描述
 * @Author: jiazhu
 * @Date: 2019-09-02 16:34
 * @ClassName: AddFollowActivity
 */
public class AddFollowActivity extends AppCompatActivity {

    private ActivityAddFollowBinding binding;
    private AddFollowCategoryAdapter mCategoryAdapter;
    private AddFollowUserAdapter mUserAdapter;
    private AddFollowViewModel mViewModel;
    private DomainClickCallback mDomainClickCallback = bean -> {
        changeDomainList(bean);
        binding.setIsLoadingUser(true);
        mViewModel.getUsers(bean.getDomainId(), bean.getType(), userListBeans -> {
            mUserAdapter.serUserLists(userListBeans);
            binding.setIsLoadingUser(false);
            return null;
        });
    };

    /**
     * 修改列表被选择的状态
     *
     * @param bean 当前选中的item
     */
    private void changeDomainList(DomainListBean.DomainBean bean) {
        List<DomainListBean.DomainBean> domainLists = mCategoryAdapter.getDomainLists();
        for (DomainListBean.DomainBean domainBean : domainLists) {
            if (bean.getDomainId().equals(domainBean.getDomainId())) {
                domainBean.setChecked(true);
            } else {
                domainBean.setChecked(false);
            }
        }
        mCategoryAdapter.notifyDataSetChanged();
    }

    UserClickCallback mUserClickCallback = new UserClickCallback() {
        @Override
        public void click(FollowUsersBean.UserBean bean) {

        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_follow);

        initData();
    }

    private void initData() {
        mCategoryAdapter = new AddFollowCategoryAdapter(mDomainClickCallback);
        binding.rvCategoryList.setAdapter(mCategoryAdapter);

        mUserAdapter = new AddFollowUserAdapter(mUserClickCallback);
        binding.rvUserList.setAdapter(mUserAdapter);

        binding.setIsLoading(true);

        mViewModel = new AddFollowViewModel(MyApplication.getInstance());
        mViewModel.getDomainList(domainBeans -> {
            mCategoryAdapter.setDomainLists(domainBeans);
            DomainListBean.DomainBean bean = domainBeans.get(0);
            mViewModel.getUsers(bean.getDomainId(), bean.getType(), userListBeans -> {
                mUserAdapter.serUserLists(userListBeans);
                return null;
            });
            binding.setIsLoading(false);
            return null;
        });
    }

    @Override
    protected void onDestroy() {
        mViewModel.dispose();
        mViewModel = null;
        super.onDestroy();
    }
}
