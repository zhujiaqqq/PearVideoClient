package com.example.pearvideoclient.utils;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.transition.DrawableCrossFadeFactory;
import com.example.pearvideoclient.MyApplication;
import com.example.pearvideoclient.R;

import org.jetbrains.annotations.NotNull;

/**
 * @Description: java类作用描述
 * @Author: jiazhu
 * @Date: 2019-08-20 11:36
 * @ClassName: GlideUtils
 */
public class GlideUtils {
    private GlideUtils() {

    }

    private static RequestOptions sRequestOptions =
            RequestOptions.bitmapTransform(new CircleCrop()).diskCacheStrategy(DiskCacheStrategy.NONE);
    private static RequestOptions sPlaceholder =
            new RequestOptions().placeholder(R.drawable.ic_placeholder);
    private static DrawableCrossFadeFactory sDrawableCrossFadeFactory =
            new DrawableCrossFadeFactory.Builder(300).setCrossFadeEnabled(true).build();

    public static void loadCircleImage(@NotNull String url, ImageView imageView) {
        Glide.with(MyApplication.getInstance()).asBitmap()
                .apply(sRequestOptions)
                .load(url)
                .into(imageView);
    }

    public static void load(@NotNull String url, ImageView imageView) {
        Glide.with(MyApplication.getInstance())
                .load(url)
                .into(imageView);
    }

    public static void load(@NotNull Drawable drawable, ImageView imageView) {
        Glide.with(MyApplication.getInstance())
                .load(drawable)
                .into(imageView);
    }

    public static void loadWithPlaceHolder(@NotNull String url,
                                           ImageView imageView,
                                           RequestOptions requestOptions,
                                           DrawableCrossFadeFactory drawableCrossFadeFactory) {
        Glide.with(MyApplication.getInstance())
                .load(url)
                .apply(requestOptions == null ? sPlaceholder : requestOptions)
                .transition(DrawableTransitionOptions.with(drawableCrossFadeFactory == null ?
                        sDrawableCrossFadeFactory : drawableCrossFadeFactory))
                .into(imageView);
    }
}
