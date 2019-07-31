package com.example.pearvideoclient.content;

import android.os.Bundle;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.example.pearvideoclient.LocalHandler;
import com.example.pearvideoclient.MyApplication;
import com.example.pearvideoclient.R;
import com.example.pearvideoclient.entity.bean.content.Content;
import com.example.pearvideoclient.entity.bean.content.RelateConts;
import com.example.pearvideoclient.entity.bean.content.Tags;
import com.example.pearvideoclient.entity.bean.content.Videos;
import com.example.pearvideoclient.view.FlowLayout;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.List;

import tv.danmaku.ijk.media.player.IMediaPlayer;
import tv.danmaku.ijk.media.player.IjkMediaPlayer;

import static com.example.pearvideoclient.content.ContentPresenter.MSG_HIDDEN_CONTROLLER;

/**
 * @Description: java类作用描述
 * @Author: jiazhu
 * @Date: 2019-07-28 11:13
 * @ClassName: ContentActivity
 */
public class ContentActivity extends AppCompatActivity implements ContentContract.View, LocalHandler.IHandler {
    public static final int MSG_REFRESH = 1001;

    private VideoPlayerIJK mVideoPlayer;
    private TextView mTvVideoName;
    private TextView mTvVideoPubTime;
    private TextView mTvStar;
    private TextView mTvCollect;
    private TextView mTvDownload;
    private TextView mTvDetailInfo;
    private TextView mTvDetail;
    private TextView mTvUserName;
    private TextView mTvUserSingle;
    private TextView mTvAttention;
    private ImageView mIvSmallUserImage;
    private RecyclerView mRvRelatedVideos;
    private AVLoadingIndicatorView mLoadingView;
    private RelativeLayout mPlayBottomLayout;
    private ImageView mIvArrow;
    private NestedScrollView mNsvVideoInfo;
    private FlowLayout mFlowLayout;

    private ImageView mIvPlay;
    private ImageView mIvStop;
    private SeekBar mSeekBar;
    private TextView mTvTime;

    private ContentContract.Presenter mPresenter;

    private String contId;

    private RelatedVideoAdapter mRelatedVideoAdapter;


    private LocalHandler localHandler = new LocalHandler(this);


    private AbstractVideoPlayerListener playerListener = new AbstractVideoPlayerListener() {
        @Override
        public void onBufferingUpdate(IMediaPlayer iMediaPlayer, int i) {
            //
        }

        @Override
        public void onCompletion(IMediaPlayer iMediaPlayer) {
            mIvPlay.setImageDrawable(getDrawable(R.drawable.ic_play_arrow_white_24dp));
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
            mIvPlay.setImageDrawable(getDrawable(R.drawable.ic_pause_white_24dp));
        }

        @Override
        public void onSeekComplete(IMediaPlayer iMediaPlayer) {
            //
        }

        @Override
        public void onVideoSizeChanged(IMediaPlayer iMediaPlayer, int i, int i1, int i2, int i3) {
            //
        }
    };

    private SeekBar.OnSeekBarChangeListener seekBarChangeListener = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            //进度改变
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {
            //开始拖动
            localHandler.removeCallbacksAndMessages(null);
        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
            //停止拖动
            mVideoPlayer.seekTo(mVideoPlayer.getDuration() * seekBar.getProgress() / 100);
            localHandler.sendEmptyMessageDelayed(MSG_REFRESH, 100);
            localHandler.sendEmptyMessageDelayed(MSG_HIDDEN_CONTROLLER, 3000);
        }
    };

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.video_player:
                    mPresenter.showPlayController();
                    break;
                case R.id.iv_play:
                    mPresenter.play();
                    break;
                case R.id.iv_stop:
                    mPresenter.stop();
                    break;
                case R.id.tv_star:
                    mPresenter.star();
                    break;
                case R.id.tv_collect:
                    mPresenter.collect();
                    break;
                case R.id.tv_download:
                    mPresenter.download();
                    break;
                case R.id.tv_detail:
                    mPresenter.toggleDetail();
                    break;
                case R.id.tv_attention:
                    mPresenter.attention();
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);
        new ContentPresenter(this, localHandler);
        initView();
        initData();
    }

    private void initView() {
        mVideoPlayer = findViewById(R.id.video_player);
        mTvVideoName = findViewById(R.id.tv_video_name);
        mTvVideoPubTime = findViewById(R.id.tv_video_pub_time);
        mTvStar = findViewById(R.id.tv_star);
        mTvCollect = findViewById(R.id.tv_collect);
        mTvDownload = findViewById(R.id.tv_download);
        mTvDetailInfo = findViewById(R.id.tv_detail_info);
        mTvDetail = findViewById(R.id.tv_detail);
        mTvUserName = findViewById(R.id.tv_user_name);
        mTvUserSingle = findViewById(R.id.tv_user_single);
        mTvAttention = findViewById(R.id.tv_attention);
        mIvSmallUserImage = findViewById(R.id.iv_small_user_image);
        mRvRelatedVideos = findViewById(R.id.rv_related_videos);
        mLoadingView = findViewById(R.id.loading_view);
        mPlayBottomLayout = findViewById(R.id.play_bottom_layout);
        mNsvVideoInfo = findViewById(R.id.nsv_video_info);
        mIvArrow = findViewById(R.id.iv_arrow);
        mFlowLayout = findViewById(R.id.flow_layout);

        mIvPlay = findViewById(R.id.iv_play);
        mIvStop = findViewById(R.id.iv_stop);
        mSeekBar = findViewById(R.id.seek_bar);
        mTvTime = findViewById(R.id.tv_time);

    }

    private void initData() {
        //加载so文件
        try {
            IjkMediaPlayer.loadLibrariesOnce(null);
            IjkMediaPlayer.native_profileBegin("libijkplayer.so");
        } catch (Exception e) {
            this.finish();
        }

        contId = getIntent().getStringExtra("contId");
        mVideoPlayer.setListener(playerListener);
        mSeekBar.setOnSeekBarChangeListener(seekBarChangeListener);

        mTvDetail.setOnClickListener(onClickListener);
        mTvStar.setOnClickListener(onClickListener);
        mTvCollect.setOnClickListener(onClickListener);
        mTvDownload.setOnClickListener(onClickListener);
        mTvAttention.setOnClickListener(onClickListener);
        mIvStop.setOnClickListener(onClickListener);
        mIvPlay.setOnClickListener(onClickListener);
        mVideoPlayer.setOnClickListener(onClickListener);

        mRvRelatedVideos.setLayoutManager(new LinearLayoutManager(ContentActivity.this, LinearLayoutManager.VERTICAL, false));
        mRelatedVideoAdapter = new RelatedVideoAdapter(R.layout.adapter_related_video_item, null);
        mRelatedVideoAdapter.setListener(nextContId -> {
            mPresenter.loadContent(nextContId);
            //scrollerView滑动到顶部
            mNsvVideoInfo.scrollTo(0, 0);
        });
        mRvRelatedVideos.setAdapter(mRelatedVideoAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.subscribe();
        mPresenter.loadContent(this.contId);
    }

    @Override
    public void setPresenter(ContentContract.Presenter presenter) {
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
    public void showVideoInfo(Content content) {
        mTvVideoName.setText(content.getName());
        mTvVideoPubTime.setText(content.getPubTime());
        mTvStar.setText(content.getPraiseTimes());
        mTvDetailInfo.setText(content.getSummary());
        mTvUserName.setText(content.getUserInfo().getNickname());
        mTvUserSingle.setText(content.getUserInfo().getSignature());
        Glide.with(MyApplication.getInstance()).asBitmap()
                .apply(RequestOptions.bitmapTransform(new CircleCrop())
                        .diskCacheStrategy(DiskCacheStrategy.NONE))
                .load(content.getUserInfo().getPic())
                .into(mIvSmallUserImage);
    }

    @Override
    public void showVideo(List<Videos> videos) {
        mVideoPlayer.setVideoPath(videos.get(0).getUrl());
        localHandler.sendEmptyMessageDelayed(MSG_REFRESH, 1000);
    }

    @Override
    public void showRelatedVideos(List<RelateConts> relateConts) {
        mRelatedVideoAdapter.replaceData(relateConts);
    }

    @Override
    public void showTags(List<Tags> tags) {
        for (Tags tag : tags) {
            TextView tv = (TextView) LayoutInflater.from(this).inflate(R.layout.tag_layout, mFlowLayout, false);
            tv.setText(tag.getName());
            tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    System.out.println(tag.getTagId());
                }
            });
            mFlowLayout.addView(tv);
        }
    }

    @Override
    public void showDetail(boolean isShow) {
        mTvDetailInfo.setVisibility(isShow ? View.VISIBLE : View.GONE);

        mIvArrow.setImageDrawable(isShow ? getDrawable(R.drawable.ic_keyboard_arrow_up_gray_24dp) :
                getDrawable(R.drawable.ic_keyboard_arrow_down_gray_24dp));
        mTvDetail.setText(isShow ? getString(R.string.detail_up) : getString(R.string.detail_down));
    }

    @Override
    public void showController(boolean isShow) {
        mSeekBar.setVisibility(isShow ? View.VISIBLE : View.GONE);
        mIvPlay.setVisibility(isShow ? View.VISIBLE : View.GONE);
        mIvStop.setVisibility(isShow ? View.VISIBLE : View.GONE);
        mTvTime.setVisibility(isShow ? View.VISIBLE : View.GONE);
    }

    @Override
    public void viewDoAnimation(Animation animation) {
        mPlayBottomLayout.startAnimation(animation);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mPresenter.unsubscribe();
    }

    @Override
    protected void onStop() {
        IjkMediaPlayer.native_profileEnd();
        localHandler.removeCallbacksAndMessages(null);
        super.onStop();

    }

    @Override
    protected void onDestroy() {
        if (mVideoPlayer != null) {
            mVideoPlayer.stop();
            mVideoPlayer.release();
            mVideoPlayer = null;
        }
        super.onDestroy();
    }

    @Override
    public void handlerMessage(Message msg) {
        switch (msg.what) {
            case MSG_REFRESH:
                if (mVideoPlayer.isPlaying()) {
                    refresh();
                    localHandler.sendEmptyMessageDelayed(MSG_REFRESH, 1000);
                }
                break;
            case MSG_HIDDEN_CONTROLLER:
                mPresenter.hidePlayController();
                break;
            default:
                break;
        }
    }

    private void refresh() {
        long current = mVideoPlayer.getCurrentPosition() / 1000;
        long duration = mVideoPlayer.getDuration() / 1000;
        long currentSecond = current % 60;
        long currentMinute = current / 60;
        long totalSecond = duration % 60;
        long totalMinute = duration / 60;
        String time = currentMinute + ":" + currentSecond + "/" + totalMinute + ":" + totalSecond;
        mTvTime.setText(time);
        if (duration != 0) {
            mSeekBar.setProgress((int) (current * 100 / duration));
        }
    }
}
