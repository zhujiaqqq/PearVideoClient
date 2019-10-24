package com.example.pearvideoclient.channel

/**
 * @Description: java类作用描述
 * @Author: jiazhu
 * @Date: 2019-07-20 15:49
 * @ClassName: IOperationData
 */
interface IOperationData {
    /**
     * 数据交换
     *
     * @param fromPosition
     * @param toPosition
     */
    fun onItemMove(fromPosition: Int, toPosition: Int)

    /**
     * 数据删除
     *
     * @param position
     */
    fun onItemDismiss(position: Int)
}
