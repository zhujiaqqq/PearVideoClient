package com.example.pearvideoclient.view;

import android.content.Context;
import android.os.Message;
import androidx.viewpager.widget.ViewPager;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.pearvideoclient.LocalHandler;
import com.example.pearvideoclient.content.AbstractVideoPlayerListener;
import com.example.pearvideoclient.content.VideoPlayerIJK;

import java.util.ArrayList;
import java.util.List;

import tv.danmaku.ijk.media.player.IMediaPlayer;

/**
 * @Description: java类作用描述
 * @Author: jiazhu
 * @Date: 2019-08-22 10:25
 * @ClassName: VideoBanner
 */
public class VideoBanner extends RelativeLayout implements LocalHandler.IHandler {
    public static final int MSG_UPDATE_VIEWPAGER = 1;

    private ViewPager mViewPager;

    private int autoCurrIndex = 1;
    private boolean isAutoPlay = false;
    private List<View> mViews;
    private VideoBannerAdapter mBannerAdapter;
    private int delayedTime;
    private Time time;

    private AbstractVideoPlayerListener mPlayerListener = new AbstractVideoPlayerListener() {
        @Override
        public void onBufferingUpdate(IMediaPlayer iMediaPlayer, int i) {
            //
        }

        @Override
        public void onCompletion(IMediaPlayer iMediaPlayer) {
            //
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

    public VideoBanner(Context context) {
        super(context);
        init();
    }

    public VideoBanner(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public VideoBanner(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public VideoBanner(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        time = new Time();
        mViewPager = new ViewPager(getContext());
        mViewPager.setOffscreenPageLimit(1);
        LinearLayout.LayoutParams vpParam = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        mViewPager.setLayoutParams(vpParam);
        this.addView(mViewPager);
    }

    public void setAdapter(VideoBannerAdapter adapter) {
        mViewPager.setAdapter(adapter);
    }

    public void setDataList(List<String> dataList) {
        if (dataList == null) {
            dataList = new ArrayList<>();
        }
        if (mViews == null) {
            mViews = new ArrayList<>();
        } else {
            mViews.clear();
        }
        LinearLayout.LayoutParams lp =
                new LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        for (String s : dataList) {
            VideoPlayerIJK videoView = new VideoPlayerIJK(getContext());
            videoView.setLayoutParams(lp);
            videoView.setVideoPath(s);
            videoView.setListener(mPlayerListener);
            mViews.add(videoView);
        }
    }

    public void startBanner() {
        mBannerAdapter = new VideoBannerAdapter(mViews);
        mViewPager.setAdapter(mBannerAdapter);
        mViewPager.setOffscreenPageLimit(1);
        mViewPager.setCurrentItem(autoCurrIndex);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {
                //
            }

            @Override
            public void onPageSelected(int position) {
                autoCurrIndex = position;
                getDelayedTime(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                int pageIndex = autoCurrIndex;
                if (autoCurrIndex == 0) {
                    pageIndex = mViews.size() - 2;
                } else if (autoCurrIndex == mViews.size() - 1) {
                    pageIndex = 1;
                }

                if (pageIndex != autoCurrIndex) {
                    mViewPager.setCurrentItem(pageIndex, false);
                }

                if (state == ViewPager.SCROLL_STATE_IDLE && mViews.size() > 1) {
                    VideoPlayerIJK videoView = (VideoPlayerIJK) mViews.get(pageIndex);
                    long currentPosition = videoView.getCurrentPosition();
                    long duration = videoView.getDuration();
                    long delayTime = duration - currentPosition;
                    if (delayTime <= 0) {
                        time.getDelayedTime(videoView, runnable);
                        mHandler.postDelayed(runnable, delayTime);
                    } else {
                        mHandler.postDelayed(runnable, delayTime);
                    }
                }
            }
        });
    }

    /**
     * 开启自动循环
     */
    public void startAutoPlay() {
        isAutoPlay = true;
        if (mViews.size() > 1) {
            getDelayedTime(autoCurrIndex);

            mHandler.postDelayed(runnable, delayedTime);

        }
    }

    /**
     * 发消息，进行循环
     */
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            mHandler.sendEmptyMessage(MSG_UPDATE_VIEWPAGER);
        }
    };

    /**
     * 获取delyedTime
     *
     * @param position 当前位置
     */
    private void getDelayedTime(int position) {
        View view1 = mViews.get(position);
        VideoPlayerIJK videoView = (VideoPlayerIJK) view1;
        videoView.start();
        videoView.seekTo(0);
        delayedTime = (int) videoView.getDuration();
        time.getDelayedTime(videoView, runnable);
    }

    public void destroy() {
        mHandler.removeCallbacksAndMessages(null);
        mHandler = null;
        time = null;
        runnable = null;
        mViews.clear();
        mViews = null;
        mViewPager = null;
        mBannerAdapter = null;
    }

    /**
     * 改变数据
     *
     * @param list urls
     */
    public void dataChange(List<String> list) {
        if (list != null && !list.isEmpty()) {
            //改变资源时要重新开启循环，否则会把视频的时长赋给图片，或者相反
            //因为delyedTime也要改变，所以要重新获取delyedTime
            mHandler.removeCallbacks(runnable);
            setDataList(list);
            mBannerAdapter.setList(mViews);
            mBannerAdapter.notifyDataSetChanged();
            mViewPager.setCurrentItem(autoCurrIndex, false);
            //开启循环
            if (isAutoPlay && mViews.size() > 1) {
                getDelayedTime(autoCurrIndex);
                mHandler.postDelayed(runnable, delayedTime);
            }
        }
    }

    private LocalHandler mHandler = new LocalHandler(this);

    @Override
    public void handlerMessage(Message msg) {
        if (msg.what == MSG_UPDATE_VIEWPAGER) {
            mViewPager.setCurrentItem(autoCurrIndex + 1);
        }
    }

//    //接受消息实现轮播
//    private Handler mHandler = new Handler() {
//        public void handleMessage(Message msg) {
//            switch (msg.what) {
//                case MSG_UPDATE_VIEWPAGER:
//                    mViewPager.setCurrentItem(autoCurrIndex + 1);
//                    break;
//                default:
//                    break;
//            }
//        }
//    };

    /**
     * 获取视频长度，以及已经播放的时间
     */
    private class Time implements Runnable {

        private VideoPlayerIJK videoView;
        private Runnable runnable;

        public void getDelayedTime(VideoPlayerIJK videoView, Runnable runnable) {
            this.videoView = videoView;
            this.runnable = runnable;
        }

        @Override
        public void run() {
            long current = videoView.getCurrentPosition();
            long duration = videoView.getDuration();
            long delyedTime = duration - current;
            mHandler.postDelayed(runnable, delyedTime);
        }
    }
}
