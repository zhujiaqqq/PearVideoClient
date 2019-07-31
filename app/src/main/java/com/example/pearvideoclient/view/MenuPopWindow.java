package com.example.pearvideoclient.view;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;

import com.example.pearvideoclient.R;

/**
 * @author zhujiaqqq
 * @date 2019-07-12
 */
public class MenuPopWindow extends PopupWindow {

    private Context mContext;
    private View.OnClickListener mListener;

    private float firstX;
    private float firstY;

    public MenuPopWindow(Context context, View.OnClickListener listener) {
        mContext = context;
        mListener = listener;
        View view = LayoutInflater.from(mContext).inflate(R.layout.pop_window, null);

        setContentView(view);

        view.findViewById(R.id.ll_my_message).setOnClickListener(listener);
        view.findViewById(R.id.ll_my_favour).setOnClickListener(listener);
        view.findViewById(R.id.ll_my_download).setOnClickListener(listener);
        view.findViewById(R.id.tv_history).setOnClickListener(listener);
        view.findViewById(R.id.tv_my_video).setOnClickListener(listener);
        view.findViewById(R.id.tv_my_income).setOnClickListener(listener);
        view.findViewById(R.id.tv_my_focus).setOnClickListener(listener);
        view.findViewById(R.id.tv_free_video).setOnClickListener(listener);
        view.findViewById(R.id.tv_setting).setOnClickListener(listener);
        view.findViewById(R.id.tv_help).setOnClickListener(listener);


        setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
//        setFocusable(true);
        setBackgroundDrawable(new ColorDrawable());
//        关闭popwindow的监听
        setOnDismissListener(() -> {
            // TODO: 2019-07-12 显示X
        });
//        设置popwindow的进场退场动画
        setAnimationStyle(R.style.PopupAnimation);

//        监听向下滑动事件，关闭popwindow
        setTouchInterceptor((v, event) -> {
            float x = event.getX();
            float y = event.getY();
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    firstX = x;
                    firstY = y;
                    break;
                case MotionEvent.ACTION_MOVE:
                    if ((y - firstY > 200) && (Math.abs(y - firstY) > Math.abs(x - firstX))) {
                        dismiss();
                        return true;
                    }
                    break;
                case MotionEvent.ACTION_UP:
                    break;
                default:
                    break;
            }
            return false;
        });
    }
}
