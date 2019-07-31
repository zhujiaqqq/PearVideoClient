package com.example.pearvideoclient.channel;

import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

/**
 * @Description: java类作用描述
 * @Author: jiazhu
 * @Date: 2019-07-20 15:48
 * @ClassName: IHCallback
 */
public class IHCallback extends ItemTouchHelper.Callback {
    private CategoryListAdapter mCategoryListAdapter;

    public IHCallback(CategoryListAdapter categoryListAdapter) {
        this.mCategoryListAdapter = categoryListAdapter;
    }

    @Override
    public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
        if (recyclerView.getLayoutManager() instanceof GridLayoutManager) {
            final int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN |
                    ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
            final int swipeFlags = 0;
            return makeMovementFlags(dragFlags, swipeFlags);
        } else {
            final int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
            final int swipeFlags = 0;
            return makeMovementFlags(dragFlags, swipeFlags);
        }
    }

    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
        mCategoryListAdapter.onItemMove(viewHolder.getAdapterPosition(), target.getAdapterPosition());
        return true;
    }

    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        mCategoryListAdapter.onItemDismiss(viewHolder.getAdapterPosition());
    }
//
//    @Override
//    public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
//        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
//        if(actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {
//            //滑动时改变Item的透明度
//            final float alpha = 1 - Math.abs(dX) / (float)viewHolder.itemView.getWidth();
//            viewHolder.itemView.setAlpha(alpha);
//        }
//    }
}
