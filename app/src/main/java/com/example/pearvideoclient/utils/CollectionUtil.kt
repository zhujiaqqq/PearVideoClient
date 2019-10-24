package com.example.pearvideoclient.utils

/**
 * @Description: java类作用描述
 * @Author: jiazhu
 * @Date: 2019-08-31 09:23
 * @ClassName: CollectionUtil
 */
object CollectionUtil {
    @JvmStatic
    fun isEmpty(collection: Collection<*>?): Boolean {
        return collection == null || collection.isEmpty()
    }
}
