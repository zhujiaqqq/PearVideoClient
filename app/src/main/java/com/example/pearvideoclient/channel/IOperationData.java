package com.example.pearvideoclient.channel;

/**
 * @Description: java类作用描述
 * @Author: jiazhu
 * @Date: 2019-07-20 15:49
 * @ClassName: IOperationData
 */
public interface IOperationData {
    /**
     * 数据交换
     *
     * @param fromPosition
     * @param toPosition
     */
    void onItemMove(int fromPosition, int toPosition);

    /**
     * 数据删除
     *
     * @param position
     */
    void onItemDismiss(int position);
}
