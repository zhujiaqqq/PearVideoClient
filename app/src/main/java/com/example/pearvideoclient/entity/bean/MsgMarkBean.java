package com.example.pearvideoclient.entity.bean;

/**
 * @Description: java类作用描述
 * @Author: jiazhu
 * @Date: 2019-07-21 16:31
 * @ClassName: MsgMarkBean
 */
public class MsgMarkBean {


    /**
     * reqId : 53359ea5-589a-4342-98cd-d752377cde5a
     * resultMsg : SUCCESS
     * systemTime : 1563695031867
     * data : {"atMeMark":0,"biankePitchMark":0,"biankePitchMissMark":0,"contOrderMark":1,"orderContLastTime":null,"paikeCashMark":0,"paikeVideoMark":0,"postFavoritesMark":0,"suggestMark":0,"sysMsgMark":0,"tagFavoritesMark":0,"userId":13504593}
     * resultCode : 1
     */

    private String reqId;
    private String resultMsg;
    private long systemTime;
    private DataBean data;
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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public static class DataBean {
        /**
         * atMeMark : 0
         * biankePitchMark : 0
         * biankePitchMissMark : 0
         * contOrderMark : 1
         * orderContLastTime : null
         * paikeCashMark : 0
         * paikeVideoMark : 0
         * postFavoritesMark : 0
         * suggestMark : 0
         * sysMsgMark : 0
         * tagFavoritesMark : 0
         * userId : 13504593
         */

        private int atMeMark;
        private int biankePitchMark;
        private int biankePitchMissMark;
        private int contOrderMark;
        private Object orderContLastTime;
        private int paikeCashMark;
        private int paikeVideoMark;
        private int postFavoritesMark;
        private int suggestMark;
        private int sysMsgMark;
        private int tagFavoritesMark;
        private Integer userId;

        public int getAtMeMark() {
            return atMeMark;
        }

        public void setAtMeMark(int atMeMark) {
            this.atMeMark = atMeMark;
        }

        public int getBiankePitchMark() {
            return biankePitchMark;
        }

        public void setBiankePitchMark(int biankePitchMark) {
            this.biankePitchMark = biankePitchMark;
        }

        public int getBiankePitchMissMark() {
            return biankePitchMissMark;
        }

        public void setBiankePitchMissMark(int biankePitchMissMark) {
            this.biankePitchMissMark = biankePitchMissMark;
        }

        public int getContOrderMark() {
            return contOrderMark;
        }

        public void setContOrderMark(int contOrderMark) {
            this.contOrderMark = contOrderMark;
        }

        public Object getOrderContLastTime() {
            return orderContLastTime;
        }

        public void setOrderContLastTime(Object orderContLastTime) {
            this.orderContLastTime = orderContLastTime;
        }

        public int getPaikeCashMark() {
            return paikeCashMark;
        }

        public void setPaikeCashMark(int paikeCashMark) {
            this.paikeCashMark = paikeCashMark;
        }

        public int getPaikeVideoMark() {
            return paikeVideoMark;
        }

        public void setPaikeVideoMark(int paikeVideoMark) {
            this.paikeVideoMark = paikeVideoMark;
        }

        public int getPostFavoritesMark() {
            return postFavoritesMark;
        }

        public void setPostFavoritesMark(int postFavoritesMark) {
            this.postFavoritesMark = postFavoritesMark;
        }

        public int getSuggestMark() {
            return suggestMark;
        }

        public void setSuggestMark(int suggestMark) {
            this.suggestMark = suggestMark;
        }

        public int getSysMsgMark() {
            return sysMsgMark;
        }

        public void setSysMsgMark(int sysMsgMark) {
            this.sysMsgMark = sysMsgMark;
        }

        public int getTagFavoritesMark() {
            return tagFavoritesMark;
        }

        public void setTagFavoritesMark(int tagFavoritesMark) {
            this.tagFavoritesMark = tagFavoritesMark;
        }

        public Integer getUserId() {
            return userId;
        }

        public void setUserId(Integer userId) {
            this.userId = userId;
        }
    }
}
