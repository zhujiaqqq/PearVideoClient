package com.example.pearvideoclient.content;

import tv.danmaku.ijk.media.player.IMediaPlayer;


/**
 * @Description: 提供回调的接口
 * @Author: jiazhu
 * @Date: 2019-07-28 11:13
 * @ClassName: AbstractVideoPlayerListener
 */
public abstract class AbstractVideoPlayerListener
        implements IMediaPlayer.OnBufferingUpdateListener,
        IMediaPlayer.OnCompletionListener,
        IMediaPlayer.OnPreparedListener,
        IMediaPlayer.OnInfoListener,
        IMediaPlayer.OnVideoSizeChangedListener,
        IMediaPlayer.OnErrorListener,
        IMediaPlayer.OnSeekCompleteListener {
}