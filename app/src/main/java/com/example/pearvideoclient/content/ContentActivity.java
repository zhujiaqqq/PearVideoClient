package com.example.pearvideoclient.content;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.transition.ChangeBounds;
import android.transition.ChangeTransform;
import android.transition.Fade;
import android.transition.TransitionSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
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
import com.example.pearvideoclient.author.AuthorActivity;
import com.example.pearvideoclient.entity.content.Content;
import com.example.pearvideoclient.entity.content.HotConts;
import com.example.pearvideoclient.entity.content.RelateConts;
import com.example.pearvideoclient.entity.content.Tags;
import com.example.pearvideoclient.entity.content.Videos;
import com.example.pearvideoclient.view.FlowLayout;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.List;

import tv.danmaku.ijk.media.player.IMediaPlayer;
import tv.danmaku.ijk.media.player.IjkMediaPlayer;

import static com.example.pearvideoclient.content.ContentPresenter.FOLLOW_USER;
import static com.example.pearvideoclient.content.ContentPresenter.MSG_HIDDEN_CONTROLLER;
import static com.example.pearvideoclient.content.ContentPresenter.UN_FOLLOW_USER;

/**
 * @Description: java类作用描述
 * @Author: jiazhu
 * @Date: 2019-07-28 11:13
 * @ClassName: ContentActivity
 */
public class ContentActivity extends AppCompatActivity implements ContentContract.View, LocalHandler.IHandler {
    private static final String TAG = "ContentActivity";

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
    private RecyclerView mRvHotVideos;
    private AVLoadingIndicatorView mLoadingView;
    private RelativeLayout mPlayBottomLayout;
    private ImageView mIvArrow;
    private NestedScrollView mNsvVideoInfo;
    private FlowLayout mFlowLayout;
    private RelativeLayout mRlUserLayout;


    private ImageView mIvPlay;
    private ImageView mIvStop;
    private SeekBar mSeekBar;
    private TextView mTvTime;

    private ContentContract.Presenter mPresenter;

    private String contId;

    private RelatedVideoAdapter mRelatedVideoAdapter;
    private RelatedVideoAdapter mHotVideoAdapter;


    private LocalHandler localHandler = new LocalHandler(this);


    private AbstractVideoPlayerListener playerListener = new AbstractVideoPlayerListener() {
        @Override
        public void onBufferingUpdate(IMediaPlayer iMediaPlayer, int i) {
            //
        }

        @Override
        public void onCompletion(IMediaPlayer iMediaPlayer) {
            mIvPlay.setImageDrawable(getDrawable(R.drawable.ic_play_arrow_white_24dp));
            Log.d(TAG, "onCompletion: ");
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
            Log.d(TAG, "onSeekComplete: ");
        }

        @Override
        public void onVideoSizeChanged(IMediaPlayer iMediaPlayer, int i, int i1, int i2, int i3) {
            Log.d(TAG, "onVideoSizeChanged: ");
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
            mVideoPlayer.seekTo((long) (mVideoPlayer.getDuration() * seekBar.getProgress() / 100.0));
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
                    if (mVideoPlayer.isPlaying()) {
                        mVideoPlayer.pause();
                        mIvPlay.setImageDrawable(getDrawable(R.drawable.ic_play_arrow_white_24dp));
                    } else {
                        mVideoPlayer.start();
                        mIvPlay.setImageDrawable(getDrawable(R.drawable.ic_pause_white_24dp));
                    }
                    break;
                case R.id.iv_full_screen:

                    break;
                case R.id.tv_star:
                    mPresenter.star(contId);
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
                    mPresenter.toOptUserFollow(isAttention ? UN_FOLLOW_USER : FOLLOW_USER, userId);
                    isAttention = !isAttention;
                    break;
                case R.id.rl_user_layout:
                    Intent intent = new Intent(ContentActivity.this, AuthorActivity.class);
                    intent.putExtra("userId", userId);
                    startActivity(intent);
                    break;
                default:
                    break;
            }
        }
    };
    private String userId;
    /**
     * 是否关注作者
     */
    private boolean isAttention;
    private boolean mBackPressed;

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
        mRvHotVideos = findViewById(R.id.rv_hot_videos);
        mLoadingView = findViewById(R.id.loading_view);
        mPlayBottomLayout = findViewById(R.id.play_bottom_layout);
        mNsvVideoInfo = findViewById(R.id.nsv_video_info);
        mIvArrow = findViewById(R.id.iv_arrow);
        mFlowLayout = findViewById(R.id.flow_layout);
        mRlUserLayout = findViewById(R.id.rl_user_layout);

        mIvPlay = findViewById(R.id.iv_play);
        mIvStop = findViewById(R.id.iv_full_screen);
        mSeekBar = findViewById(R.id.seek_bar);
        mTvTime = findViewById(R.id.tv_time);

        ViewCompat.setTransitionName(mTvVideoName, "textView");
        getWindow().setEnterTransition(new Fade());
        getWindow().setExitTransition(new Fade());

        TransitionSet transitionSet = new TransitionSet();
        transitionSet.addTransition(new ChangeBounds());
        transitionSet.addTransition(new ChangeTransform());
        transitionSet.addTarget(mTvVideoName);
        getWindow().setSharedElementEnterTransition(transitionSet);
        getWindow().setSharedElementExitTransition(transitionSet);
    }

    private void initData() {

        contId = getIntent().getStringExtra("contId");
        userId = getIntent().getStringExtra("userId");

        mTvDetail.setOnClickListener(onClickListener);
        mTvStar.setOnClickListener(onClickListener);
        mTvCollect.setOnClickListener(onClickListener);
        mTvDownload.setOnClickListener(onClickListener);
        mTvAttention.setOnClickListener(onClickListener);
        mIvStop.setOnClickListener(onClickListener);
        mIvPlay.setOnClickListener(onClickListener);
        mVideoPlayer.setOnClickListener(onClickListener);
        mRlUserLayout.setOnClickListener(onClickListener);

        mRvRelatedVideos.setLayoutManager(new LinearLayoutManager(ContentActivity.this, LinearLayoutManager.VERTICAL, false));
        mRelatedVideoAdapter = new RelatedVideoAdapter(R.layout.adapter_related_video_item, null);
        mRelatedVideoAdapter.setListener(nextContId -> {
            mPresenter.loadContent(nextContId);
            //scrollerView滑动到顶部
            mNsvVideoInfo.scrollTo(0, 0);
        });
        mRvRelatedVideos.setAdapter(mRelatedVideoAdapter);

        mRvHotVideos.setLayoutManager(new LinearLayoutManager(ContentActivity.this, LinearLayoutManager.VERTICAL, false));
        mHotVideoAdapter = new RelatedVideoAdapter(R.layout.adapter_related_video_item, null);
        mHotVideoAdapter.setListener(nextContId -> {
            mPresenter.loadContent(nextContId);
            //scrollerView滑动到顶部
            mNsvVideoInfo.scrollTo(0, 0);
        });
        mRvHotVideos.setAdapter(mHotVideoAdapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        //加载so文件
        try {
            IjkMediaPlayer.loadLibrariesOnce(null);
            IjkMediaPlayer.native_profileBegin("libijkplayer.so");
        } catch (Exception e) {
            this.finish();
        }
        mPresenter.subscribe();
        mPresenter.loadContent(this.contId);

        mVideoPlayer.setListener(playerListener);
        mSeekBar.setOnSeekBarChangeListener(seekBarChangeListener);
    }

    @Override
    public void onBackPressed() {
        mBackPressed = true;
        super.onBackPressed();
    }

    @Override
    protected void onStop() {
        mPresenter.unsubscribe();
        mVideoPlayer.stop();
        mVideoPlayer.release();

        IjkMediaPlayer.native_profileEnd();
        localHandler.removeCallbacksAndMessages(null);
        super.onStop();

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

        if (content.getUserInfo().getIsFollow() != null && "1".equals(content.getUserInfo().getIsFollow())) {
            isAttention = true;
            toggleAttention();
        }
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
    public void showHotVideos(List<HotConts> hotConts) {
        mHotVideoAdapter.replaceData(hotConts);
    }

    @Override
    public void showTags(List<Tags> tags) {
        mFlowLayout.removeAllViews();
        for (Tags tag : tags) {
            TextView tv = (TextView) LayoutInflater.from(this).inflate(R.layout.tag_layout, mFlowLayout, false);
            tv.setText(tag.getName());
            tv.setOnClickListener(v -> {
                //
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
    public void showStar(boolean isStar) {
        mTvStar.setText(starAdded());
        mTvStar.setCompoundDrawablesWithIntrinsicBounds(
                getDrawable(R.drawable.ic_favorite_red_24dp),
                null, null, null);
        mTvStar.startAnimation(AnimationUtils.loadAnimation(this, R.anim.to_star));
    }

    @Override
    public void toggleAttention() {
        mTvAttention.setText(isAttention ? "已关注" : "关注");
        mTvAttention.setBackground(isAttention ?
                getDrawable(R.drawable.bg_round_f2) : getDrawable(R.drawable.bg_round_yellow));
    }

    private String starAdded() {
        String oldStar = mTvStar.getText().toString().trim();
        try {
            Integer old = Integer.valueOf(oldStar);
            return String.valueOf(old + 1);
        } catch (Exception e) {
            return oldStar;
        }
    }

    @Override
    public void handlerMessage(Message msg) {
        switch (msg.what) {
            case MSG_REFRESH:
                if (mVideoPlayer.isPlaying()) {
                    refresh();
                }
                localHandler.sendEmptyMessageDelayed(MSG_REFRESH, 1000);
                break;
            case MSG_HIDDEN_CONTROLLER:
                mPresenter.hidePlayController();
                break;
            default:
                break;
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        contId = intent.getStringExtra("contId");
        userId = intent.getStringExtra("userId");

        mPresenter.subscribe();
        mPresenter.loadContent(this.contId);

    }

    private void refresh() {
        long current = mVideoPlayer.getCurrentPosition() / 1000;
        long currentSecond = current % 60;
        long currentMinute = current / 60;
        long duration = mVideoPlayer.getDuration() / 1000;
        long totalSecond = duration % 60;
        long totalMinute = duration / 60;

        String time = String.format("%02d:%02d/%02d:%02d", currentMinute, currentSecond, totalMinute, totalSecond);

        mTvTime.setText(time);
        if (duration != 0) {
            mSeekBar.setProgress((int) (current * 100 / duration));
        }
    }
}
