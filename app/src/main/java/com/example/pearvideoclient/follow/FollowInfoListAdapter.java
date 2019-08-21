package com.example.pearvideoclient.follow;

import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.pearvideoclient.R;
import com.example.pearvideoclient.content.AbstractVideoPlayerListener;
import com.example.pearvideoclient.content.VideoPlayerIJK;
import com.example.pearvideoclient.entity.MyFollowContBean;
import com.example.pearvideoclient.utils.GlideUtils;

import java.util.List;

import tv.danmaku.ijk.media.player.IMediaPlayer;

/**
 * @author zhujiaqqq
 * @date 2019-07-15
 */
public class FollowInfoListAdapter extends BaseQuickAdapter<MyFollowContBean.DataListBean, BaseViewHolder> {
    private int mPlay = 0;
    AbstractVideoPlayerListener listener = new AbstractVideoPlayerListener() {
        @Override
        public void onBufferingUpdate(IMediaPlayer iMediaPlayer, int i) {
            //
        }

        @Override
        public void onCompletion(IMediaPlayer iMediaPlayer) {
        }

        @Override
        public boolean onError(IMediaPlayer iMediaPlayer, int i, int i1) {
            return false;
        }

        @Override
        public boolean onInfo(IMediaPlayer iMediaPlayer, int i, int i1) {
            return false;
        }

        @Override
        public void onPrepared(IMediaPlayer iMediaPlayer) {
            iMediaPlayer.start();
        }

        @Override
        public void onSeekComplete(IMediaPlayer iMediaPlayer) {

        }

        @Override
        public void onVideoSizeChanged(IMediaPlayer iMediaPlayer, int i, int i1, int i2, int i3) {

        }
    };

    public FollowInfoListAdapter(int layoutResId, @Nullable List<MyFollowContBean.DataListBean> data) {
        super(layoutResId, data);

    }

    @Override
    protected void convert(BaseViewHolder helper, MyFollowContBean.DataListBean item) {
        helper.setText(R.id.tv_author_name, item.getContInfo().getUserInfo().getNickname())
                .setText(R.id.tv_video_name, item.getContInfo().getName())
                .setText(R.id.tv_like, String.format(mContext.getString(R.string.like_count), item.getContInfo().getCommentTimes()));
        ImageView ivAuthorImg = helper.getView(R.id.iv_author_img);
        ImageView ivVideoImg = helper.getView(R.id.iv_video_img);
        SeekBar seekBar = helper.getView(R.id.seek_bar);
        GlideUtils.loadCircleImage(item.getContInfo().getUserInfo().getPic(), ivAuthorImg);
        GlideUtils.loadWithPlaceHolder(item.getContInfo().getPic(), ivVideoImg, null, null);

        VideoPlayerIJK videoPlayer = helper.getView(R.id.video_player);

        videoPlayer.setListener(listener);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                //
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                //
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                //
            }
        });

        if (mPlay == helper.getAdapterPosition()) {
            if (!videoPlayer.isPlaying()) {
                videoPlayer.setVideoPath(item.getContInfo().getVideos().get(0).getUrl());
            }
            ivVideoImg.setVisibility(View.GONE);
        } else {
            if (videoPlayer.isPlaying()) {
                videoPlayer.reset();
                ivVideoImg.setVisibility(View.VISIBLE);
            }
        }

    }

    public void setPlay(int playPosition) {
        this.mPlay = playPosition;
        notifyDataSetChanged();
    }
}
