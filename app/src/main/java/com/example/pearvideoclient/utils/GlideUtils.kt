package com.example.pearvideoclient.utils

import android.graphics.drawable.Drawable
import android.widget.ImageView

import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.transition.DrawableCrossFadeFactory
import com.example.pearvideoclient.MyApplication
import com.example.pearvideoclient.R

/**
 * @Description: java类作用描述
 * @Author: jiazhu
 * @Date: 2019-08-20 11:36
 * @ClassName: GlideUtils
 */
object GlideUtils {

    private val sRequestOptions = RequestOptions.bitmapTransform(CircleCrop()).diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
    private val sPlaceholder = RequestOptions().placeholder(R.drawable.ic_placeholder).diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
    private val sDrawableCrossFadeFactory = DrawableCrossFadeFactory.Builder(300).setCrossFadeEnabled(true).build()

    fun loadCircleImage(url: String, imageView: ImageView) {
        Glide.with(MyApplication.instance).asBitmap()
                .apply(sRequestOptions)
                .load(url)
                .into(imageView)
    }

    fun load(url: String, imageView: ImageView) {
        Glide.with(MyApplication.instance)
                .load(url)
                .into(imageView)
    }

    fun load(drawable: Drawable, imageView: ImageView) {
        Glide.with(MyApplication.instance)
                .load(drawable)
                .into(imageView)
    }

    fun loadWithPlaceHolder(url: String,
                            imageView: ImageView,
                            requestOptions: RequestOptions?,
                            drawableCrossFadeFactory: DrawableCrossFadeFactory?) {
        Glide.with(MyApplication.instance)
                .load(url)
                .apply(requestOptions ?: sPlaceholder)
                .transition(DrawableTransitionOptions.with(drawableCrossFadeFactory
                        ?: sDrawableCrossFadeFactory))
                .into(imageView)
    }
}
