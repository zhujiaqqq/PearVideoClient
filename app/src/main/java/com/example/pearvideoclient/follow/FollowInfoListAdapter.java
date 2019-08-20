package com.example.pearvideoclient.follow;

import android.support.annotation.Nullable;
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
        ImageView ivPlay = helper.getView(R.id.iv_play);
        final boolean[] isPrepared = {false};
        final IMediaPlayer[] mMediaPlayer = new IMediaPlayer[1];
        videoPlayer.setListener(new AbstractVideoPlayerListener() {
            @Override
            public void onBufferingUpdate(IMediaPlayer iMediaPlayer, int i) {
                //
            }

            @Override
            public void onCompletion(IMediaPlayer iMediaPlayer) {
                ivPlay.setImageDrawable(mContext.getDrawable(R.drawable.ic_play_arrow_white_24dp));
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
                mMediaPlayer[0] = iMediaPlayer;
                iMediaPlayer.pause();
                isPrepared[0] = true;
            }

            @Override
            public void onSeekComplete(IMediaPlayer iMediaPlayer) {

            }

            @Override
            public void onVideoSizeChanged(IMediaPlayer iMediaPlayer, int i, int i1, int i2, int i3) {

            }
        });
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

        videoPlayer.setVideoPath(item.getContInfo().getVideos().get(0).getUrl());
        ivVideoImg.setVisibility(View.VISIBLE);
        ivVideoImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ivVideoImg.getVisibility() == View.VISIBLE && isPrepared[0]) {
                    ivVideoImg.setVisibility(View.GONE);
                    mMediaPlayer[0].start();
                    ivPlay.setImageDrawable(mContext.getDrawable(R.drawable.ic_pause_white_24dp));
                }
            }
        });

    }
}
