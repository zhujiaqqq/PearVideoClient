package com.example.pearvideoclient.follow

import android.view.View
import android.widget.ImageView
import android.widget.SeekBar
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.example.pearvideoclient.R
import com.example.pearvideoclient.entity.MyFollowContBean
import com.example.pearvideoclient.utils.GlideUtils
import com.example.pearvideoclient.view.video.AbstractVideoPlayerListener
import com.example.pearvideoclient.view.video.VideoPlayerIJK
import tv.danmaku.ijk.media.player.IMediaPlayer

/**
 * @author zhujiaqqq
 * @date 2019-07-15
 */
class FollowInfoListAdapter(layoutResId: Int, data: List<MyFollowContBean.DataListBean>?) : BaseQuickAdapter<MyFollowContBean.DataListBean, BaseViewHolder>(layoutResId, data) {
    private var mPlay = 0
    private var listener: AbstractVideoPlayerListener = object : AbstractVideoPlayerListener {
        override fun onBufferingUpdate(iMediaPlayer: IMediaPlayer, i: Int) {
            //
        }

        override fun onCompletion(iMediaPlayer: IMediaPlayer) {}

        override fun onError(iMediaPlayer: IMediaPlayer, i: Int, i1: Int): Boolean {
            return false
        }

        override fun onInfo(iMediaPlayer: IMediaPlayer, i: Int, i1: Int): Boolean {
            return false
        }

        override fun onPrepared(iMediaPlayer: IMediaPlayer) {
            iMediaPlayer.start()
        }

        override fun onSeekComplete(iMediaPlayer: IMediaPlayer) {

        }

        override fun onVideoSizeChanged(iMediaPlayer: IMediaPlayer, i: Int, i1: Int, i2: Int, i3: Int) {

        }
    }

    override fun convert(helper: BaseViewHolder, item: MyFollowContBean.DataListBean) {
        helper.setText(R.id.tv_author_name, item.contInfo.userInfo.nickname)
                .setText(R.id.tv_video_name, item.contInfo.name)
                .setText(R.id.tv_like, String.format(mContext.getString(R.string.like_count), item.contInfo.commentTimes))
        val ivAuthorImg = helper.getView<ImageView>(R.id.iv_author_img)
        val ivVideoImg = helper.getView<ImageView>(R.id.iv_video_img)
        val seekBar = helper.getView<SeekBar>(R.id.seek_bar)
        GlideUtils.loadCircleImage(item.contInfo.userInfo.pic, ivAuthorImg)
        GlideUtils.loadWithPlaceHolder(item.contInfo.pic, ivVideoImg, null, null)

        val videoPlayer = helper.getView<VideoPlayerIJK>(R.id.video_player)

        videoPlayer.setListener(listener)

        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                //
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {
                //
            }

            override fun onStopTrackingTouch(seekBar: SeekBar) {
                //
            }
        })

        if (mPlay == helper.adapterPosition) {
            if (!videoPlayer.isPlaying) {
                videoPlayer.setVideoPath(item.contInfo.videos[0].url)
            }
            ivVideoImg.visibility = View.GONE
        } else {
            if (videoPlayer.isPlaying) {
                videoPlayer.reset()
                ivVideoImg.visibility = View.VISIBLE
            }
        }

    }

    fun setPlay(playPosition: Int) {
        this.mPlay = playPosition
        notifyDataSetChanged()
    }
}
