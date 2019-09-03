package com.example.pearvideoclient.follow.addfollow;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pearvideoclient.R;
import com.example.pearvideoclient.databinding.AdapterAddFollowUserItemBinding;
import com.example.pearvideoclient.entity.FollowUsersBean;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: java类作用描述
 * @Author: jiazhu
 * @Date: 2019-09-03 17:52
 * @ClassName: AddFollowUserAdapter
 */
public class AddFollowUserAdapter extends RecyclerView.Adapter<AddFollowUserAdapter.UserViewHolder> {
    private List<FollowUsersBean.UserBean> mUserBeans;
    @Nullable
    private final UserClickCallback mUserClickCallback;

    public AddFollowUserAdapter(@Nullable UserClickCallback userClickCallback) {
        mUserClickCallback = userClickCallback;
    }

    public void serUserLists(List<? extends FollowUsersBean.UserBean> userBeans) {
        if (mUserBeans == null) {
            mUserBeans = new ArrayList<>();
        } else {
            mUserBeans.clear();
        }
        mUserBeans.addAll(userBeans);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        AdapterAddFollowUserItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.adapter_add_follow_user_item, parent, false);
        binding.setCallback(mUserClickCallback);
        return new UserViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        holder.mBinding.setUserBean(mUserBeans.get(position));
    }

    @Override
    public int getItemCount() {
        return mUserBeans == null ? 0 : mUserBeans.size();
    }

    static class UserViewHolder extends RecyclerView.ViewHolder {
        final AdapterAddFollowUserItemBinding mBinding;

        public UserViewHolder(AdapterAddFollowUserItemBinding binding) {
            super(binding.getRoot());
            this.mBinding = binding;
        }
    }


}
