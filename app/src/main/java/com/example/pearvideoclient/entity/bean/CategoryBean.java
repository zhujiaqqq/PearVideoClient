package com.example.pearvideoclient.entity.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhujiaqqq
 * @date 2019-07-17
 */
public class CategoryBean {

    /**
     * resultCode : 1
     * resultMsg : success
     * reqId : 50a26770-424b-4149-9ce4-c6ea11d5b856
     * systemTime : 1563361659620
     * localChannelInfo : {"channelCode":"320100","name":"南京","aliasName":"南京·生活圈","isLocal":"1","isLifeCircle":"0"}
     * autoLocalChannelInfo : {"channelCode":"320100","name":"南京","aliasName":"南京·生活圈","isLocal":"1","isLifeCircle":"0"}
     * categoryList : [{"categoryId":"10","name":"新知","color":"#A2B0A0"},{"categoryId":"1","name":"社会","color":"#F04A50"},{"categoryId":"2","name":"世界","color":"#33B7A7"},{"categoryId":"9","name":"体育","color":"#FECE3E"},{"categoryId":"5","name":"生活","color":"#8CD931"},{"categoryId":"8","name":"科技","color":"#33A7D8"},{"categoryId":"4","name":"娱乐","color":"#E966AE"},{"categoryId":"3","name":"财富","color":"#3276B5"},{"categoryId":"31","name":"汽车","color":"#6E8490"},{"categoryId":"6","name":"美食","color":"#F58D4E"},{"categoryId":"59","name":"音乐","color":"#B936EB"}]
     */

    private String resultCode;
    private String resultMsg;
    private String reqId;
    private String systemTime;
    private LocalChannelInfoBean localChannelInfo;
    private AutoLocalChannelInfoBean autoLocalChannelInfo;
    private ArrayList<CategoryListBean> categoryList;

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public String getResultMsg() {
        return resultMsg;
    }

    public void setResultMsg(String resultMsg) {
        this.resultMsg = resultMsg;
    }

    public String getReqId() {
        return reqId;
    }

    public void setReqId(String reqId) {
        this.reqId = reqId;
    }

    public String getSystemTime() {
        return systemTime;
    }

    public void setSystemTime(String systemTime) {
        this.systemTime = systemTime;
    }

    public LocalChannelInfoBean getLocalChannelInfo() {
        return localChannelInfo;
    }

    public void setLocalChannelInfo(LocalChannelInfoBean localChannelInfo) {
        this.localChannelInfo = localChannelInfo;
    }

    public AutoLocalChannelInfoBean getAutoLocalChannelInfo() {
        return autoLocalChannelInfo;
    }

    public void setAutoLocalChannelInfo(AutoLocalChannelInfoBean autoLocalChannelInfo) {
        this.autoLocalChannelInfo = autoLocalChannelInfo;
    }

    public ArrayList<CategoryListBean> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(ArrayList<CategoryListBean> categoryList) {
        this.categoryList = categoryList;
    }

    @Override
    public String toString() {
        return "CategoryBean{" +
                "resultCode='" + resultCode + '\'' +
                ", resultMsg='" + resultMsg + '\'' +
                ", reqId='" + reqId + '\'' +
                ", systemTime='" + systemTime + '\'' +
                ", localChannelInfo=" + localChannelInfo +
                ", autoLocalChannelInfo=" + autoLocalChannelInfo +
                ", categoryList=" + categoryList +
                '}';
    }

    public static class LocalChannelInfoBean {
        /**
         * channelCode : 320100
         * name : 南京
         * aliasName : 南京·生活圈
         * isLocal : 1
         * isLifeCircle : 0
         */

        private String channelCode;
        private String name;
        private String aliasName;
        private String isLocal;
        private String isLifeCircle;

        public String getChannelCode() {
            return channelCode;
        }

        public void setChannelCode(String channelCode) {
            this.channelCode = channelCode;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAliasName() {
            return aliasName;
        }

        public void setAliasName(String aliasName) {
            this.aliasName = aliasName;
        }

        public String getIsLocal() {
            return isLocal;
        }

        public void setIsLocal(String isLocal) {
            this.isLocal = isLocal;
        }

        public String getIsLifeCircle() {
            return isLifeCircle;
        }

        public void setIsLifeCircle(String isLifeCircle) {
            this.isLifeCircle = isLifeCircle;
        }

        @Override
        public String toString() {
            return "LocalChannelInfoBean{" +
                    "channelCode='" + channelCode + '\'' +
                    ", name='" + name + '\'' +
                    ", aliasName='" + aliasName + '\'' +
                    ", isLocal='" + isLocal + '\'' +
                    ", isLifeCircle='" + isLifeCircle + '\'' +
                    '}';
        }
    }

    public static class AutoLocalChannelInfoBean {
        /**
         * channelCode : 320100
         * name : 南京
         * aliasName : 南京·生活圈
         * isLocal : 1
         * isLifeCircle : 0
         */

        private String channelCode;
        private String name;
        private String aliasName;
        private String isLocal;
        private String isLifeCircle;

        public String getChannelCode() {
            return channelCode;
        }

        public void setChannelCode(String channelCode) {
            this.channelCode = channelCode;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAliasName() {
            return aliasName;
        }

        public void setAliasName(String aliasName) {
            this.aliasName = aliasName;
        }

        public String getIsLocal() {
            return isLocal;
        }

        public void setIsLocal(String isLocal) {
            this.isLocal = isLocal;
        }

        public String getIsLifeCircle() {
            return isLifeCircle;
        }

        public void setIsLifeCircle(String isLifeCircle) {
            this.isLifeCircle = isLifeCircle;
        }

        @Override
        public String toString() {
            return "AutoLocalChannelInfoBean{" +
                    "channelCode='" + channelCode + '\'' +
                    ", name='" + name + '\'' +
                    ", aliasName='" + aliasName + '\'' +
                    ", isLocal='" + isLocal + '\'' +
                    ", isLifeCircle='" + isLifeCircle + '\'' +
                    '}';
        }
    }

    public static class CategoryListBean implements Parcelable {
        /**
         * categoryId : 10
         * name : 新知
         * color : #A2B0A0
         */

        private String categoryId;
        private String name;
        private String color;

        public String getCategoryId() {
            return categoryId;
        }

        public void setCategoryId(String categoryId) {
            this.categoryId = categoryId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }

        @Override
        public String toString() {
            return "CategoryListBean{" +
                    "categoryId='" + categoryId + '\'' +
                    ", name='" + name + '\'' +
                    ", color='" + color + '\'' +
                    '}';
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.categoryId);
            dest.writeString(this.name);
            dest.writeString(this.color);
        }

        public CategoryListBean() {
        }

        protected CategoryListBean(Parcel in) {
            this.categoryId = in.readString();
            this.name = in.readString();
            this.color = in.readString();
        }

        public static final Parcelable.Creator<CategoryListBean> CREATOR = new Parcelable.Creator<CategoryListBean>() {
            @Override
            public CategoryListBean createFromParcel(Parcel source) {
                return new CategoryListBean(source);
            }

            @Override
            public CategoryListBean[] newArray(int size) {
                return new CategoryListBean[size];
            }
        };
    }
}
