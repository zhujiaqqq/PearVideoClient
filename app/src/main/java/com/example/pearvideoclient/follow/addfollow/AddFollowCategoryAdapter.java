package com.example.pearvideoclient.follow.addfollow;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pearvideoclient.R;
import com.example.pearvideoclient.databinding.AdapterDomainItemBinding;
import com.example.pearvideoclient.entity.DomainListBean;

import java.util.List;
import java.util.Objects;

/**
 * @Description: java类作用描述
 * @Author: jiazhu
 * @Date: 2019-09-02 17:32
 * @ClassName: AddFollowCategoryAdapter
 */
public class AddFollowCategoryAdapter extends RecyclerView.Adapter<AddFollowCategoryAdapter.DomainViewHolder> {
    private List<DomainListBean.DomainBean> mDomainLists;
    @Nullable
    private final DomainClickCallback mDomainClickCallback;

    public AddFollowCategoryAdapter(@Nullable DomainClickCallback domainClickCallback) {
        mDomainClickCallback = domainClickCallback;
    }

    public List<DomainListBean.DomainBean> getDomainLists() {
        return this.mDomainLists;
    }

    public void setDomainLists(final List<DomainListBean.DomainBean> domainLists) {
        if (this.mDomainLists == null) {
            this.mDomainLists = domainLists;
            notifyItemRangeChanged(0, domainLists.size());
        } else {
            DiffUtil.DiffResult result = DiffUtil.calculateDiff(new DiffUtil.Callback() {
                @Override
                public int getOldListSize() {
                    return AddFollowCategoryAdapter.this.mDomainLists.size();
                }

                @Override
                public int getNewListSize() {
                    return domainLists.size();
                }

                @Override
                public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                    return AddFollowCategoryAdapter.this.mDomainLists.get(oldItemPosition).getDomainId().equals(domainLists.get(newItemPosition).getDomainId());
                }

                @Override
                public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
                    DomainListBean.DomainBean newDomain = domainLists.get(newItemPosition);
                    DomainListBean.DomainBean oldDomain = domainLists.get(oldItemPosition);
                    return Objects.equals(newDomain.getDomainId(), oldDomain.getDomainId()) &&
                            Objects.equals(newDomain.getName(), oldDomain.getName());
                }
            });
            this.mDomainLists = domainLists;
            result.dispatchUpdatesTo(this);
        }
    }

    @NonNull
    @Override
    public DomainViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        AdapterDomainItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.adapter_domain_item, parent, false);
        binding.setCallback(mDomainClickCallback);
        return new DomainViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull DomainViewHolder holder, int position) {
        holder.mBinding.setDomain(mDomainLists.get(position));
    }

    @Override
    public int getItemCount() {
        return mDomainLists == null ? 0 : mDomainLists.size();
    }

    static class DomainViewHolder extends RecyclerView.ViewHolder {
        final AdapterDomainItemBinding mBinding;

        public DomainViewHolder(AdapterDomainItemBinding binding) {
            super(binding.getRoot());
            this.mBinding = binding;
        }
    }
}
