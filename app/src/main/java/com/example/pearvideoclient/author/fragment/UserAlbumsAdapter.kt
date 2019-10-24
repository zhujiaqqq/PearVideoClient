package com.example.pearvideoclient.author.fragment

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.core.app.ActivityOptionsCompat
import androidx.core.util.Pair
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pearvideoclient.R
import com.example.pearvideoclient.author.AlbumActivity
import com.example.pearvideoclient.content.ContentActivity
import com.example.pearvideoclient.entity.UserAlbumsBean
import com.example.pearvideoclient.utils.GlideUtils
import java.util.*

/**
 * @Description: java类作用描述
 * @Author: jiazhu
 * @Date: 2019-08-17 09:33
 * @ClassName: UserAlbumsAdapter
 */
class UserAlbumsAdapter internal constructor(private val mContext: Context, private var mList: MutableList<UserAlbumsBean.AlbumListBean>?) : RecyclerView.Adapter<UserAlbumsAdapter.UserAlbumsHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): UserAlbumsHolder {
        return UserAlbumsHolder(LayoutInflater.from(mContext).inflate(R.layout.adapter_user_albums_item, viewGroup, false))
    }

    override fun onBindViewHolder(holder: UserAlbumsHolder, i: Int) {
        val bean = mList!![i]
        val contList = bean.contList
        holder.rvVideoList.layoutManager = GridLayoutManager(
                mContext, 2
        )
        val innerVideoAdapter = InnerVideoAdapter(mContext, contList)
        holder.rvVideoList.adapter = innerVideoAdapter
        holder.tvAlbumsName.text = bean.name
        holder.ivArrowExpend.visibility = if (contList.size < 4) View.GONE else View.VISIBLE
        holder.rlToExpend.setOnClickListener {
            if (contList.size == 4) {
                val intent = Intent(mContext, AlbumActivity::class.java)
                intent.putExtra(AlbumActivity.ALBUM_ID, bean.albumId)
                mContext.startActivity(intent)
            }
        }

    }

    override fun getItemCount(): Int {
        return if (mList == null) 0 else mList!!.size
    }

    fun replaceData(albumList: List<UserAlbumsBean.AlbumListBean>) {
        if (mList == null) {
            mList = ArrayList()
        } else {
            mList!!.clear()
        }
        mList!!.addAll(albumList)
        notifyDataSetChanged()
    }

    fun addData(albumList: List<UserAlbumsBean.AlbumListBean>) {
        if (mList == null) {
            mList = ArrayList()
        }
        mList!!.addAll(albumList)
        notifyDataSetChanged()
    }

    inner class UserAlbumsHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvAlbumsName: TextView = itemView.findViewById(R.id.tv_albums_name)
        var rvVideoList: RecyclerView = itemView.findViewById(R.id.rv_video_list)
        var ivArrowExpend: ImageView = itemView.findViewById(R.id.iv_arrow_expend)
        var rlToExpend: RelativeLayout = itemView.findViewById(R.id.rl_to_expend)

    }


    inner class InnerVideoAdapter(private val mContext: Context, private val mList: List<UserAlbumsBean.AlbumListBean.ContListBean>?) : RecyclerView.Adapter<InnerVideoAdapter.InnerVideoHolder>() {

        override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): InnerVideoHolder {
            return InnerVideoHolder(LayoutInflater.from(mContext).inflate(
                    R.layout.adapter_category_conts_item, viewGroup, false
            ))
        }

        override fun onBindViewHolder(holder: InnerVideoHolder, i: Int) {
            val cont = mList!![i]
            holder.tvVideoName.text = cont.name
            holder.tvVideoAuthorDuration.text = (cont.userInfo.nickname + " | " + cont.duration)

            if (!TextUtils.isEmpty(cont.cornerLabelDesc)) {
                holder.tvSingleBroadcast.text = cont.cornerLabelDesc
                holder.tvSingleBroadcast.visibility = View.VISIBLE
            } else {
                holder.tvSingleBroadcast.visibility = View.GONE
            }

            GlideUtils.loadWithPlaceHolder(cont.pic, holder.ivVideoImg, null, null)

            holder.tvVideoName.transitionName = "textView"
            holder.rlParent.setOnClickListener {
                val intent = Intent(mContext, ContentActivity::class.java)
                intent.putExtra("contId", cont.contId)
                intent.putExtra("userId", cont.userInfo.userId)
                val namePair = Pair<View, String>(holder.tvVideoName, ViewCompat.getTransitionName(holder.tvVideoName))

                val option = ActivityOptionsCompat.makeSceneTransitionAnimation(
                        mContext as Activity, namePair)
                mContext.startActivity(intent, option.toBundle())
            }

        }

        override fun getItemCount(): Int {
            var res = 0
            if (mList == null) {
                return res
            }
            res = if (mList.size <= 4) {
                mList.size
            } else {
                4
            }
            return res
        }

        inner class InnerVideoHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            var ivVideoImg: ImageView = itemView.findViewById(R.id.iv_video_img)
            var tvVideoName: TextView = itemView.findViewById(R.id.tv_video_name)
            var tvVideoAuthorDuration: TextView = itemView.findViewById(R.id.tv_video_author_duration)
            var tvSingleBroadcast: TextView = itemView.findViewById(R.id.tv_single_broadcast)
            var rlParent: RelativeLayout = itemView.findViewById(R.id.rl_parent)

        }

    }

}

