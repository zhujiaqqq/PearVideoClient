package com.example.pearvideoclient.author.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.util.Pair;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pearvideoclient.R;
import com.example.pearvideoclient.author.AlbumActivity;
import com.example.pearvideoclient.content.ContentActivity;
import com.example.pearvideoclient.entity.UserAlbumsBean;
import com.example.pearvideoclient.utils.GlideUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: java类作用描述
 * @Author: jiazhu
 * @Date: 2019-08-17 09:33
 * @ClassName: UserAlbumsAdapter
 */
public class UserAlbumsAdapter extends RecyclerView.Adapter<UserAlbumsAdapter.UserAlbumsHolder> {
    private Context mContext;
    private List<UserAlbumsBean.AlbumListBean> mList;

    UserAlbumsAdapter(Context context, List<UserAlbumsBean.AlbumListBean> list) {
        mContext = context;
        mList = list;
    }

    @NonNull
    @Override
    public UserAlbumsHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new UserAlbumsHolder(LayoutInflater.from(mContext).inflate(R.layout.adapter_user_albums_item, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull UserAlbumsHolder holder, int i) {
        UserAlbumsBean.AlbumListBean bean = mList.get(i);
        List<UserAlbumsBean.AlbumListBean.ContListBean> contList = bean.getContList();
        holder.rvVideoList.setLayoutManager(new GridLayoutManager(
                mContext, 2
        ));
        InnerVideoAdapter innerVideoAdapter = new InnerVideoAdapter(mContext, contList);
        holder.rvVideoList.setAdapter(innerVideoAdapter);
        holder.tvAlbumsName.setText(bean.getName());
        holder.ivArrowExpend.setVisibility(contList.size() < 4 ? View.GONE : View.VISIBLE);
        holder.rlToExpend.setOnClickListener(v -> {
            if (contList.size() == 4) {
                Intent intent = new Intent(mContext, AlbumActivity.class);
                intent.putExtra(AlbumActivity.ALBUM_ID, bean.getAlbumId());
                mContext.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    public void replaceData(List<UserAlbumsBean.AlbumListBean> albumList) {
        if (mList == null) {
            mList = new ArrayList<>();
        } else {
            mList.clear();
        }
        mList.addAll(albumList);
        notifyDataSetChanged();
    }

    public void addData(List<UserAlbumsBean.AlbumListBean> albumList) {
        if (mList == null) {
            mList = new ArrayList<>();
        }
        mList.addAll(albumList);
        notifyDataSetChanged();
    }

    class UserAlbumsHolder extends RecyclerView.ViewHolder {
        TextView tvAlbumsName;
        RecyclerView rvVideoList;
        ImageView ivArrowExpend;
        RelativeLayout rlToExpend;

        UserAlbumsHolder(@NonNull View itemView) {
            super(itemView);
            tvAlbumsName = itemView.findViewById(R.id.tv_albums_name);
            rvVideoList = itemView.findViewById(R.id.rv_video_list);
            ivArrowExpend = itemView.findViewById(R.id.iv_arrow_expend);
            rlToExpend = itemView.findViewById(R.id.rl_to_expend);
        }
    }


    class InnerVideoAdapter extends RecyclerView.Adapter<InnerVideoAdapter.InnerVideoHolder> {
        private Context mContext;
        private List<UserAlbumsBean.AlbumListBean.ContListBean> mList;

        InnerVideoAdapter(Context context, List<UserAlbumsBean.AlbumListBean.ContListBean> list) {
            mContext = context;
            mList = list;
        }

        @NonNull
        @Override
        public InnerVideoHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            return new InnerVideoHolder(LayoutInflater.from(mContext).inflate(
                    R.layout.adapter_category_conts_item, viewGroup, false
            ));
        }

        @Override
        public void onBindViewHolder(@NonNull InnerVideoHolder holder, int i) {
            UserAlbumsBean.AlbumListBean.ContListBean cont = mList.get(i);
            holder.tvVideoName.setText(cont.getName());
            holder.tvVideoAuthorDuration.setText((cont.getUserInfo().getNickname() + " | " + cont.getDuration()));

            if (!TextUtils.isEmpty(cont.getCornerLabelDesc())) {
                holder.tvSingleBroadcast.setText(cont.getCornerLabelDesc());
                holder.tvSingleBroadcast.setVisibility(View.VISIBLE);
            } else {
                holder.tvSingleBroadcast.setVisibility(View.GONE);
            }

            GlideUtils.loadWithPlaceHolder(cont.getPic(), holder.ivVideoImg, null, null);

            holder.tvVideoName.setTransitionName("textView");
            holder.rlParent.setOnClickListener(v -> {
                Intent intent = new Intent(mContext, ContentActivity.class);
                intent.putExtra("contId", cont.getContId());
                intent.putExtra("userId", cont.getUserInfo().getUserId());
                Pair<View, String> namePair = new Pair<>(holder.tvVideoName, ViewCompat.getTransitionName(holder.tvVideoName));

                ActivityOptionsCompat option = ActivityOptionsCompat.makeSceneTransitionAnimation(
                        (Activity) mContext, namePair);
                mContext.startActivity(intent, option.toBundle());
            });

        }

        @Override
        public int getItemCount() {
            int res = 0;
            if (mList == null) {
                return res;
            }
            if (mList.size() <= 4) {
                res = mList.size();
            } else {
                res = 4;
            }
            return res;
        }

        class InnerVideoHolder extends RecyclerView.ViewHolder {
            ImageView ivVideoImg;
            TextView tvVideoName;
            TextView tvVideoAuthorDuration;
            TextView tvSingleBroadcast;
            RelativeLayout rlParent;

            InnerVideoHolder(@NonNull View itemView) {
                super(itemView);
                ivVideoImg = itemView.findViewById(R.id.iv_video_img);
                tvVideoName = itemView.findViewById(R.id.tv_video_name);
                tvVideoAuthorDuration = itemView.findViewById(R.id.tv_video_author_duration);
                tvSingleBroadcast = itemView.findViewById(R.id.tv_single_broadcast);
                rlParent = itemView.findViewById(R.id.rl_parent);
            }
        }

    }

}

