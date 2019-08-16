package com.example.pearvideoclient.entity.bean;

/**
 * @Description: java类作用描述
 * @Author: jiazhu
 * @Date: 2019-08-04 16:23
 * @ClassName: UserFollowBean
 */
public class UserFollowBean {
    /**
     * reqId : 97238c5c-012c-4405-8327-6613ce2bed09
     * resultMsg : SUCCESS
     * systemTime : 1564906979600
     * resultCode : 1
     */

    private String reqId;
    private String resultMsg;
    private long systemTime;
    private String resultCode;

    public String getReqId() {
        return reqId;
    }

    public void setReqId(String reqId) {
        this.reqId = reqId;
    }

    public String getResultMsg() {
        return resultMsg;
    }

    public void setResultMsg(String resultMsg) {
        this.resultMsg = resultMsg;
    }

    public long getSystemTime() {
        return systemTime;
    }

    public void setSystemTime(long systemTime) {
        this.systemTime = systemTime;
    }

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

}
