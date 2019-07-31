package com.example.pearvideoclient.channel;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.pearvideoclient.R;
import com.example.pearvideoclient.entity.bean.CategoryBean;

import java.util.Collections;
import java.util.List;

/**
 * @Description: java类作用描述
 * @Author: jiazhu
 * @Date: 2019-07-19 10:16
 * @ClassName: CategoryListAdapter
 */
public class CategoryListAdapter extends RecyclerView.Adapter<CategoryListAdapter.CategoryListHolder>
        implements IOperationData {

    private Context mContext;
    private List<CategoryBean.CategoryListBean> mList;

    public CategoryListAdapter(Context context, List<CategoryBean.CategoryListBean> list) {
        this.mContext = context;
        this.mList = list;
    }

    @NonNull
    @Override
    public CategoryListHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new CategoryListHolder(
                LayoutInflater.from(mContext).inflate(
                        R.layout.adapter_category_item, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryListHolder holder, int position) {
        CategoryBean.CategoryListBean categoryListBean = mList.get(position);
        holder.textView.setText(categoryListBean.getName());
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    class CategoryListHolder extends RecyclerView.ViewHolder {
        private TextView textView;

        public CategoryListHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.tv_category_name);
        }
    }

    @Override
    public void onItemMove(int fromPosition, int toPosition) {
        if (fromPosition < toPosition) {
            for (int i = fromPosition; i < toPosition; i++) {
                Collections.swap(mList, i, i + 1);
            }
        } else {
            for (int i = fromPosition; i > toPosition; i--) {
                Collections.swap(mList, i, i - 1);
            }
        }

        notifyItemMoved(fromPosition, toPosition);
    }

    @Override
    public void onItemDismiss(int position) {
        //移除数据
        mList.remove(position);
        notifyItemRemoved(position);
    }
}
