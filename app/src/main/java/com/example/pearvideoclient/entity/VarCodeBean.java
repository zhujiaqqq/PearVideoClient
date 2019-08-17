package com.example.pearvideoclient.entity;

/**
 * @Description: 获取验证码
 * @Author: jiazhu
 * @Date: 2019-07-21 16:14
 * @ClassName: VarCodeBean
 */
public class VarCodeBean {

    /**
     * reqId : 9d44cb11-e9dc-4e30-8c20-437037c4a1bf
     * resultMsg : SUCCESS
     * systemTime : 1563695015722
     * resultCode : 1
     * verType : 5
     * loginName : 13815865892
     */

    private String reqId;
    private String resultMsg;
    private long systemTime;
    private String resultCode;
    private int verType;
    private String loginName;

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

    public int getVerType() {
        return verType;
    }

    public void setVerType(int verType) {
        this.verType = verType;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    @Override
    public String toString() {
        return "VarCodeBean{" +
                "reqId='" + reqId + '\'' +
                ", resultMsg='" + resultMsg + '\'' +
                ", systemTime=" + systemTime +
                ", resultCode='" + resultCode + '\'' +
                ", verType=" + verType +
                ", loginName='" + loginName + '\'' +
                '}';
    }
}
