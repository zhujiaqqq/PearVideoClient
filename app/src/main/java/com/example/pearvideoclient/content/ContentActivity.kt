package com.example.pearvideoclient.content

import android.content.Intent
import android.content.pm.ActivityInfo
import android.content.res.Configuration
import android.os.Bundle
import android.os.Message
import android.transition.ChangeBounds
import android.transition.ChangeTransform
import android.transition.Fade
import android.transition.TransitionSet
import android.util.Log
import android.view.*
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.SeekBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.widget.NestedScrollView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.apublic.LocalHandler
import com.example.pearvideoclient.MyApplication
import com.example.pearvideoclient.R
import com.example.pearvideoclient.author.AuthorActivity
import com.example.pearvideoclient.content.ContentPresenter.Companion.FOLLOW_USER
import com.example.pearvideoclient.content.ContentPresenter.Companion.MSG_HIDDEN_CONTROLLER
import com.example.pearvideoclient.content.ContentPresenter.Companion.UN_FOLLOW_USER
import com.example.pearvideoclient.entity.content.*
import com.example.pearvideoclient.utils.GlideUtils
import com.example.pearvideoclient.utils.MyToast
import com.example.pearvideoclient.utils.ScreenUtils
import com.example.pearvideoclient.view.FlowLayout
import com.example.pearvideoclient.view.video.AbstractVideoPlayerListener
import com.example.pearvideoclient.view.video.VideoPlayerIJK
import com.wang.avi.AVLoadingIndicatorView
import tv.danmaku.ijk.media.player.IMediaPlayer
import tv.danmaku.ijk.media.player.IjkMediaPlayer

/**
 * @Description: java类作用描述
 * @Author: jiazhu
 * @Date: 2019-07-28 11:13
 * @ClassName: ContentActivity
 */
class ContentActivity : AppCompatActivity(), ContentContract.View, LocalHandler.IHandler {

    private var mVideoPlayer: VideoPlayerIJK? = null
    private var mTvVideoName: TextView? = null
    private var mTvVideoPubTime: TextView? = null
    private var mTvStar: TextView? = null
    private var mTvCollect: TextView? = null
    private var mTvDownload: TextView? = null
    private var mTvDetailInfo: TextView? = null
    private var mTvDetail: TextView? = null
    private var mTvUserName: TextView? = null
    private var mTvUserSingle: TextView? = null
    private var mTvAttention: TextView? = null
    private var mIvSmallUserImage: ImageView? = null
    private var mRvRelatedVideos: RecyclerView? = null
    private var mRvHotVideos: RecyclerView? = null
    private var mLoadingView: AVLoadingIndicatorView? = null
    private var mPlayBottomLayout: RelativeLayout? = null
    private var mIvArrow: ImageView? = null
    private var mNsvVideoInfo: NestedScrollView? = null
    private var mFlowLayout: FlowLayout? = null
    private var mRlUserLayout: RelativeLayout? = null
    private var mIvVideoImg: ImageView? = null


    private var mIvPlay: ImageView? = null
    private var mIvFullScreen: ImageView? = null
    private var mSeekBar: SeekBar? = null
    private var mTvTime: TextView? = null

    private var mPresenter: ContentContract.Presenter? = null

    private var contId: String? = null

    private var mRelatedVideoAdapter: RelatedVideoAdapter? = null
    private var mHotVideoAdapter: RelatedVideoAdapter? = null


    private val localHandler = LocalHandler(this)


    private val playerListener = object : AbstractVideoPlayerListener {
        override fun onBufferingUpdate(iMediaPlayer: IMediaPlayer, i: Int) {
            //
        }

        override fun onCompletion(iMediaPlayer: IMediaPlayer) {
            mIvPlay!!.setImageDrawable(getDrawable(R.drawable.ic_play_arrow_white_24dp))
        }

        override fun onError(iMediaPlayer: IMediaPlayer, i: Int, i1: Int): Boolean {
            return false
        }

        override fun onInfo(iMediaPlayer: IMediaPlayer, i: Int, i1: Int): Boolean {
            return false
        }

        override fun onPrepared(iMediaPlayer: IMediaPlayer) {
            //            当播放时，视频占位图隐藏
            mIvVideoImg!!.visibility = View.GONE
            iMediaPlayer.start()
            mIvPlay!!.setImageDrawable(getDrawable(R.drawable.ic_pause_white_24dp))
        }

        override fun onSeekComplete(iMediaPlayer: IMediaPlayer) {
            Log.d(TAG, "onSeekComplete: ")
        }

        override fun onVideoSizeChanged(iMediaPlayer: IMediaPlayer, i: Int, i1: Int, i2: Int, i3: Int) {
            Log.d(TAG, "onVideoSizeChanged: ")
        }
    }

    private val seekBarChangeListener = object : SeekBar.OnSeekBarChangeListener {
        override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
            //进度改变
        }

        override fun onStartTrackingTouch(seekBar: SeekBar) {
            //开始拖动
            localHandler.removeCallbacksAndMessages(null)
        }

        override fun onStopTrackingTouch(seekBar: SeekBar) {
            //停止拖动
            mVideoPlayer!!.seekTo((mVideoPlayer!!.duration * seekBar.progress / 100.0).toLong())
            localHandler.sendEmptyMessageDelayed(MSG_REFRESH, 100)
            localHandler.sendEmptyMessageDelayed(MSG_HIDDEN_CONTROLLER, 3000)
        }
    }

    private val onClickListener = View.OnClickListener { v ->
        when (v.id) {
            R.id.video_player -> mPresenter!!.showPlayController()
            R.id.iv_play -> if (mVideoPlayer!!.isPlaying) {
                mVideoPlayer!!.pause()
                mIvPlay!!.setImageDrawable(getDrawable(R.drawable.ic_play_arrow_white_24dp))
            } else {
                mVideoPlayer!!.start()
                mIvPlay!!.setImageDrawable(getDrawable(R.drawable.ic_pause_white_24dp))
            }
            R.id.iv_full_screen -> initFullScreen()
            R.id.tv_star -> mPresenter!!.star(contId!!)
            R.id.tv_collect -> mPresenter!!.collect()
            R.id.tv_download -> mPresenter!!.download()
            R.id.tv_detail -> mPresenter!!.toggleDetail()
            R.id.tv_attention -> {
                mPresenter!!.toOptUserFollow(if (isAttention) UN_FOLLOW_USER else FOLLOW_USER, userId!!)
                isAttention = !isAttention
            }
            R.id.rl_user_layout -> {
                val intent = Intent(this@ContentActivity, AuthorActivity::class.java)
                intent.putExtra("userId", userId)
                startActivity(intent)
            }
            else -> {
            }
        }
    }
    private var userId: String? = null
    /**
     * 是否关注作者
     */
    private var isAttention: Boolean = false
    private var mVideoView: RelativeLayout? = null

    /**
     * 是否为横屏
     *
     * @return true 横屏
     */
    private val isLandScreen: Boolean
        get() = this.resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.i(TAG, "onCreate: ")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_content)
        ContentPresenter(this, localHandler)
        initView()
        initData()
    }

    private fun initView() {
        Log.i(TAG, "initView: ")
        mVideoPlayer = findViewById(R.id.video_player)
        mTvVideoName = findViewById(R.id.tv_video_name)
        mTvVideoPubTime = findViewById(R.id.tv_video_pub_time)
        mTvStar = findViewById(R.id.tv_star)
        mTvCollect = findViewById(R.id.tv_collect)
        mTvDownload = findViewById(R.id.tv_download)
        mTvDetailInfo = findViewById(R.id.tv_detail_info)
        mTvDetail = findViewById(R.id.tv_detail)
        mTvUserName = findViewById(R.id.tv_user_name)
        mTvUserSingle = findViewById(R.id.tv_user_single)
        mTvAttention = findViewById(R.id.tv_attention)
        mIvSmallUserImage = findViewById(R.id.iv_small_user_image)
        mRvRelatedVideos = findViewById(R.id.rv_related_videos)
        mRvHotVideos = findViewById(R.id.rv_hot_videos)
        mLoadingView = findViewById(R.id.loading_view)
        mPlayBottomLayout = findViewById(R.id.play_bottom_layout)
        mNsvVideoInfo = findViewById(R.id.nsv_video_info)
        mIvArrow = findViewById(R.id.iv_arrow)
        mFlowLayout = findViewById(R.id.flow_layout)
        mRlUserLayout = findViewById(R.id.rl_user_layout)
        mIvVideoImg = findViewById(R.id.iv_video_img)

        mIvPlay = findViewById(R.id.iv_play)
        mIvFullScreen = findViewById(R.id.iv_full_screen)
        mSeekBar = findViewById(R.id.seek_bar)
        mTvTime = findViewById(R.id.tv_time)
        mVideoView = findViewById(R.id.video_view)
        ViewCompat.setTransitionName(mTvVideoName!!, "textView")
        window.enterTransition = Fade()
        window.exitTransition = Fade()

        val transitionSet = TransitionSet()
        transitionSet.addTransition(ChangeBounds())
        transitionSet.addTransition(ChangeTransform())
        transitionSet.addTarget(mTvVideoName)
        window.sharedElementEnterTransition = transitionSet
        window.sharedElementExitTransition = transitionSet
    }

    private fun initData() {
        Log.i(TAG, "initData: ")
        contId = intent.getStringExtra("contId")
        userId = intent.getStringExtra("userId")

        mTvDetail!!.setOnClickListener(onClickListener)
        mTvStar!!.setOnClickListener(onClickListener)
        mTvCollect!!.setOnClickListener(onClickListener)
        mTvDownload!!.setOnClickListener(onClickListener)
        mTvAttention!!.setOnClickListener(onClickListener)
        mIvFullScreen!!.setOnClickListener(onClickListener)
        mIvPlay!!.setOnClickListener(onClickListener)
        mVideoPlayer!!.setOnClickListener(onClickListener)
        mRlUserLayout!!.setOnClickListener(onClickListener)

        mRvRelatedVideos!!.layoutManager = LinearLayoutManager(this@ContentActivity, LinearLayoutManager.VERTICAL, false)
        mRelatedVideoAdapter = RelatedVideoAdapter(R.layout.adapter_related_video_item, null)
        mRelatedVideoAdapter!!.setListener(object : RelatedVideoAdapter.MyListener {
            override fun onClick(contId: String) {
                mPresenter!!.loadContent(contId)
                //scrollerView滑动到顶部
                mNsvVideoInfo!!.scrollTo(0, 0)
            }
        })
        mRvRelatedVideos!!.adapter = mRelatedVideoAdapter

        mRvHotVideos!!.layoutManager = LinearLayoutManager(this@ContentActivity, LinearLayoutManager.VERTICAL, false)
        mHotVideoAdapter = RelatedVideoAdapter(R.layout.adapter_related_video_item, null)
        mHotVideoAdapter!!.setListener(object : RelatedVideoAdapter.MyListener {
            override fun onClick(contId: String) {
                mPresenter!!.loadContent(contId)
                //scrollerView滑动到顶部
                mNsvVideoInfo!!.scrollTo(0, 0)
            }
        })

        mRvHotVideos!!.adapter = mHotVideoAdapter
    }

    override fun onStart() {
        Log.i(TAG, "onStart: ")
        super.onStart()
        //加载so文件
        try {
            IjkMediaPlayer.loadLibrariesOnce(null)
            IjkMediaPlayer.native_profileBegin("libijkplayer.so")
        } catch (e: Exception) {
            this.finish()
        }

        mPresenter!!.subscribe()
        mPresenter!!.loadContent(this.contId!!)
        Log.i(TAG, "onStart: contId " + contId!!)

        mVideoPlayer!!.setListener(playerListener)
        mSeekBar!!.setOnSeekBarChangeListener(seekBarChangeListener)
    }

    override fun onStop() {
        Log.i(TAG, "onStop: ")

        mVideoPlayer!!.stop()
        mVideoPlayer!!.release()

        IjkMediaPlayer.native_profileEnd()
        localHandler.removeCallbacksAndMessages(null)
        super.onStop()

    }

    override fun onDestroy() {
        mPresenter!!.unsubscribe()
        super.onDestroy()
    }

    override fun setPresenter(presenter: ContentContract.Presenter) {
        this.mPresenter = presenter
    }

    override fun showLoading() {
        mLoadingView!!.show()
    }

    override fun cancelLoading() {
        mLoadingView!!.hide()
    }

    override fun showErrorToast(loadingFail: String) {
        MyToast.getInstance(MyApplication.instance).show(loadingFail, 3000)
    }

    override fun showVideoInfo(content: Content) {
        mTvVideoName!!.text = content.name
        mTvVideoPubTime!!.text = content.pubTime
        mTvStar!!.text = content.praiseTimes
        mTvDetailInfo!!.text = content.summary
        mTvUserName!!.text = content.userInfo.nickname
        mTvUserSingle!!.text = content.userInfo.signature
        GlideUtils.load(content.pic, mIvVideoImg!!)
        GlideUtils.loadCircleImage(content.userInfo.pic, mIvSmallUserImage!!)

        if (content.userInfo.isFollow != null && FOLLOW_USER == content.userInfo.isFollow) {
            isAttention = true
            toggleAttention()
        }
    }

    override fun showVideo(videos: List<Videos>) {
        mVideoPlayer!!.setVideoPath(videos[0].url)
        localHandler.sendEmptyMessageDelayed(MSG_REFRESH, 1000)
    }

    override fun showRelatedVideos(relateConts: List<RelateConts>) {
        mRelatedVideoAdapter!!.replaceData(relateConts)
    }

    override fun showHotVideos(hotConts: List<HotConts>) {
        mHotVideoAdapter!!.replaceData(hotConts)
    }

    override fun showTags(tags: List<Tags>) {
        mFlowLayout!!.removeAllViews()
        for (tag in tags) {
            val tv = LayoutInflater.from(this).inflate(R.layout.tag_layout, mFlowLayout, false) as TextView
            tv.text = tag.name
            tv.setOnClickListener {
                //
            }
            mFlowLayout!!.addView(tv)
        }
    }

    override fun showDetail(isShow: Boolean) {
        mTvDetailInfo!!.visibility = if (isShow) View.VISIBLE else View.GONE

        mIvArrow!!.setImageDrawable(if (isShow)
            getDrawable(R.drawable.ic_keyboard_arrow_up_gray_24dp)
        else
            getDrawable(R.drawable.ic_keyboard_arrow_down_gray_24dp))
        mTvDetail!!.text = if (isShow) getString(R.string.detail_up) else getString(R.string.detail_down)
    }


    override fun showOrHideController() {
        val isShow = mPlayBottomLayout!!.visibility == View.GONE
        mSeekBar!!.visibility = if (isShow) View.VISIBLE else View.GONE
        mIvPlay!!.visibility = if (isShow) View.VISIBLE else View.GONE
        mIvFullScreen!!.visibility = if (isShow) View.VISIBLE else View.GONE
        mTvTime!!.visibility = if (isShow) View.VISIBLE else View.GONE
        mPlayBottomLayout!!.visibility = if (isShow) View.VISIBLE else View.GONE
    }

    override fun showStar(isStar: Boolean) {
        mTvStar!!.text = starAdded()
        mTvStar!!.setCompoundDrawablesWithIntrinsicBounds(
                getDrawable(R.drawable.ic_favorite_red_24dp), null, null, null)
        mTvStar!!.startAnimation(AnimationUtils.loadAnimation(this, R.anim.to_star))
    }

    override fun toggleAttention() {
        mTvAttention!!.text = if (isAttention) "已关注" else "关注"
        mTvAttention!!.background = if (isAttention)
            getDrawable(R.drawable.bg_round_f2)
        else
            getDrawable(R.drawable.bg_round_50_yellow)
    }

    private fun starAdded(): String {
        val oldStar = mTvStar!!.text.toString().trim { it <= ' ' }
        return try {
            val old = Integer.valueOf(oldStar)
            (old + 1).toString()
        } catch (e: Exception) {
            oldStar
        }

    }

    override fun handlerMessage(msg: Message) {
        when (msg.what) {
            MSG_REFRESH -> {
                if (mVideoPlayer!!.isPlaying) {
                    refresh()
                }
                localHandler.sendEmptyMessageDelayed(MSG_REFRESH, 1000)
            }
            MSG_HIDDEN_CONTROLLER -> mPresenter!!.hidePlayController()
            else -> {
            }
        }
    }

    override fun onNewIntent(intent: Intent) {
        Log.i(TAG, "onNewIntent: ")
        super.onNewIntent(intent)
        contId = intent.getStringExtra("contId")
        userId = intent.getStringExtra("userId")

        //        mPresenter.subscribe();
        //        mPresenter.loadContent(this.contId);

    }

    private fun setFullScreenVisible(b: Boolean) {
        if (b) {
            mNsvVideoInfo!!.visibility = View.GONE
            mLoadingView!!.visibility = View.GONE
            mPlayBottomLayout!!.visibility = View.GONE
            val layoutParams1 = mVideoView!!.layoutParams
            layoutParams1.height = ViewGroup.LayoutParams.MATCH_PARENT
            mVideoView!!.layoutParams = layoutParams1
        } else {
            mNsvVideoInfo!!.visibility = View.VISIBLE


            val layoutParams1 = mVideoView!!.layoutParams
            layoutParams1.height = ScreenUtils.dip2px(this, 200f)
            mVideoView!!.layoutParams = layoutParams1
        }
    }

    private fun initFullScreen() {
        if (!isLandScreen) {
            //横屏提前设置参数充满整个屏幕（只有提前设置在横竖屏切换时才会生效）
            //定义全屏参数
            val flag = WindowManager.LayoutParams.FLAG_FULLSCREEN
            //获得当前窗体对象
            val window = window
            //设置当前窗体为全屏显示
            window.setFlags(flag, flag)
            //改变屏幕方向（设置为横屏）
            requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        } else {
            //切换竖屏（横屏头部返回键）
            requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
            //切换为竖屏显示（提前设置无效，只有现用现设置）
            val flagBack = WindowManager.LayoutParams.FLAG_FULLSCREEN
            //获得当前窗体对象
            val windowBack = this.window
            //设置当前窗体为全屏显示
            windowBack.clearFlags(flagBack)
        }
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        //按返回键
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            // 检测屏幕的方向：纵向或横向
            if (this.resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
                //当前为横屏，切换至竖屏
                this.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
            } else if (this.resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
                //当前为竖屏，按退出键后就结束当前activity
                finish()
            }
            return true
        }
        return super.onKeyDown(keyCode, event)
    }

    private fun refresh() {
        val current = mVideoPlayer!!.currentPosition / 1000
        val currentSecond = current % 60
        val currentMinute = current / 60
        val duration = mVideoPlayer!!.duration / 1000
        val totalSecond = duration % 60
        val totalMinute = duration / 60

        val time = String.format("%02d:%02d/%02d:%02d", currentMinute, currentSecond, totalMinute, totalSecond)

        mTvTime!!.text = time
        if (duration != 0L) {
            mSeekBar!!.progress = (current * 100 / duration).toInt()
        }
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        if (this.resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            // 当前为横屏，设置为竖屏
            //动态改变布局
            setFullScreenVisible(true)
        } else if (this.resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
            // 当前为竖屏，设置为横屏
            //动态改变布局
            setFullScreenVisible(false)
        }
    }

    companion object {
        private const val TAG = "ContentActivity"

        const val MSG_REFRESH = 1001
    }
}
