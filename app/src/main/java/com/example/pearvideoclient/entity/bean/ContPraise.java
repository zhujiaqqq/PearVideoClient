package com.example.pearvideoclient.entity.bean;

/**
 * @Description: java类作用描述
 * @Author: jiazhu
 * @Date: 2019-08-04 15:51
 * @ClassName: ContPraise
 */
public class ContPraise {
    /**
     * reqId : 157069ca-19eb-4184-9404-6f0ce23b3379
     * resultMsg : SUCCESS
     * systemTime : 1564904895786
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
