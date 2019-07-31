package com.example.pearvideoclient.entity.bean;

/**
 * 登出bean
 *
 * @author zhujiaqqq
 * @date 2019-07-09
 */
public class LogoutBean  {

    /**
     * reqId : 5447fe51-19d4-4bc1-aa49-4faa8bbc5fcb
     * resultMsg : SUCCESS
     * systemTime : 1563697744772
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
