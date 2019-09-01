package com.example.pearvideoclient.utils;

import java.util.Collection;

/**
 * @Description: java类作用描述
 * @Author: jiazhu
 * @Date: 2019-08-31 09:23
 * @ClassName: CollectionUtil
 */
public class CollectionUtil {
    public static boolean isEmpty(Collection<?> collection) {
        return collection == null || collection.isEmpty();
    }
}
