package com.example.apublic;


/**
 * 通用接口
 *
 * @author 88399359
 */
@FunctionalInterface
public interface CommonCallBack<In, Out> {
    /**
     * 接口回调
     *
     * @param in 入参类型
     * @return Out  返回参类型
     */
    Out todo(In in);
}
