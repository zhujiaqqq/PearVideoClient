package com.example.pearvideoclient.entity.bean;

import java.util.List;

/**
 * @Description: java类作用描述
 * @Author: jiazhu
 * @Date: 2019-07-21 16:02
 * @ClassName: UserHomeBean
 */
public class UserHomeBean {

    /**
     * resultCode : 1
     * resultMsg : success
     * reqId : e73ab34c-7117-4242-a098-2f2bef051546
     * systemTime : 1563695032529
     * userInfo : {"userId":"13504593","nickname":"zhujiaqqq","signature":"一二三四五上山","pic":"http://imageugc.pearvideo.com/user/20190619/13504593-215406.jpg","isFollow":"0","level":"0","backgroundImg":"http://image.pearvideo.com/const/author_bg.png","hasAlbum":"0","shareInfo":{"url":"https://www.pearvideo.com/author_13504593","title":"zhujiaqqq","summary":"一二三四五上山","logo":"http://imageugc.pearvideo.com/user/20190619/13504593-215406.jpg"}}
     * nextUrl :
     * dataList : [{"otype":"9","ctype":"0","pubTime":"2019-07-15 16:01","userInfo":{"userId":"10100152","nickname":"阿斗带你看电影","signature":"介绍电影故事","pic":"http://image.pearvideo.com/node/184-10068007-logo.png","backgroundImg":"http://image.pearvideo.com/node/184-10068008-bg.png","isFollow":"1","level":"2"}},{"otype":"9","ctype":"0","pubTime":"2019-07-15 16:01","userInfo":{"userId":"10159028","nickname":"小片片说大片","signature":"幽默搞笑的影评吐槽，分分钟带你看大片。","pic":"http://image.pearvideo.com/node/274-10925566-logo.png","backgroundImg":"http://image.pearvideo.com/node/274-10925564-bg.png","isFollow":"1","level":"2"}},{"otype":"9","ctype":"0","pubTime":"2019-07-15 11:11","userInfo":{"userId":"11549136","nickname":"一手调查","signature":"在这里看中国小人物的大梦想","pic":"http://image.pearvideo.com/node/67-10047964-logo.jpg","backgroundImg":"http://image.pearvideo.com/node/67-10047965-bg.jpg","isFollow":"1","level":"2"}},{"otype":"9","ctype":"0","pubTime":"2019-07-10 17:50","userInfo":{"userId":"10410771","nickname":"刘老师说电影","signature":"大家好我是刘老师，由我来为大家解说电影吧","pic":"http://image.pearvideo.com/node/640-10165003-logo.png","backgroundImg":"http://image.pearvideo.com/node/640-10165004-bg.png","isFollow":"1","level":"2"}}]
     */

    private String resultCode;
    private String resultMsg;
    private String reqId;
    private String systemTime;
    private UserInfoBean userInfo;
    private String nextUrl;
    private List<DataListBean> dataList;

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

    public UserInfoBean getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfoBean userInfo) {
        this.userInfo = userInfo;
    }

    public String getNextUrl() {
        return nextUrl;
    }

    public void setNextUrl(String nextUrl) {
        this.nextUrl = nextUrl;
    }

    public List<DataListBean> getDataList() {
        return dataList;
    }

    public void setDataList(List<DataListBean> dataList) {
        this.dataList = dataList;
    }

    public static class UserInfoBean {
        /**
         * userId : 13504593
         * nickname : zhujiaqqq
         * signature : 一二三四五上山
         * pic : http://imageugc.pearvideo.com/user/20190619/13504593-215406.jpg
         * isFollow : 0
         * level : 0
         * backgroundImg : http://image.pearvideo.com/const/author_bg.png
         * hasAlbum : 0
         * shareInfo : {"url":"https://www.pearvideo.com/author_13504593","title":"zhujiaqqq","summary":"一二三四五上山","logo":"http://imageugc.pearvideo.com/user/20190619/13504593-215406.jpg"}
         */

        private String userId;
        private String nickname;
        private String signature;
        private String pic;
        private String isFollow;
        private String level;
        private String backgroundImg;
        private String hasAlbum;
        private ShareInfoBean shareInfo;

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getSignature() {
            return signature;
        }

        public void setSignature(String signature) {
            this.signature = signature;
        }

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }

        public String getIsFollow() {
            return isFollow;
        }

        public void setIsFollow(String isFollow) {
            this.isFollow = isFollow;
        }

        public String getLevel() {
            return level;
        }

        public void setLevel(String level) {
            this.level = level;
        }

        public String getBackgroundImg() {
            return backgroundImg;
        }

        public void setBackgroundImg(String backgroundImg) {
            this.backgroundImg = backgroundImg;
        }

        public String getHasAlbum() {
            return hasAlbum;
        }

        public void setHasAlbum(String hasAlbum) {
            this.hasAlbum = hasAlbum;
        }

        public ShareInfoBean getShareInfo() {
            return shareInfo;
        }

        public void setShareInfo(ShareInfoBean shareInfo) {
            this.shareInfo = shareInfo;
        }

        public static class ShareInfoBean {
            /**
             * url : https://www.pearvideo.com/author_13504593
             * title : zhujiaqqq
             * summary : 一二三四五上山
             * logo : http://imageugc.pearvideo.com/user/20190619/13504593-215406.jpg
             */

            private String url;
            private String title;
            private String summary;
            private String logo;

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getSummary() {
                return summary;
            }

            public void setSummary(String summary) {
                this.summary = summary;
            }

            public String getLogo() {
                return logo;
            }

            public void setLogo(String logo) {
                this.logo = logo;
            }
        }
    }

    public static class DataListBean {
        /**
         * otype : 9
         * ctype : 0
         * pubTime : 2019-07-15 16:01
         * userInfo : {"userId":"10100152","nickname":"阿斗带你看电影","signature":"介绍电影故事","pic":"http://image.pearvideo.com/node/184-10068007-logo.png","backgroundImg":"http://image.pearvideo.com/node/184-10068008-bg.png","isFollow":"1","level":"2"}
         */

        private String otype;
        private String ctype;
        private String pubTime;
        private UserInfoBeanX userInfo;

        public String getOtype() {
            return otype;
        }

        public void setOtype(String otype) {
            this.otype = otype;
        }

        public String getCtype() {
            return ctype;
        }

        public void setCtype(String ctype) {
            this.ctype = ctype;
        }

        public String getPubTime() {
            return pubTime;
        }

        public void setPubTime(String pubTime) {
            this.pubTime = pubTime;
        }

        public UserInfoBeanX getUserInfo() {
            return userInfo;
        }

        public void setUserInfo(UserInfoBeanX userInfo) {
            this.userInfo = userInfo;
        }

        public static class UserInfoBeanX {
            /**
             * userId : 10100152
             * nickname : 阿斗带你看电影
             * signature : 介绍电影故事
             * pic : http://image.pearvideo.com/node/184-10068007-logo.png
             * backgroundImg : http://image.pearvideo.com/node/184-10068008-bg.png
             * isFollow : 1
             * level : 2
             */

            private String userId;
            private String nickname;
            private String signature;
            private String pic;
            private String backgroundImg;
            private String isFollow;
            private String level;

            public String getUserId() {
                return userId;
            }

            public void setUserId(String userId) {
                this.userId = userId;
            }

            public String getNickname() {
                return nickname;
            }

            public void setNickname(String nickname) {
                this.nickname = nickname;
            }

            public String getSignature() {
                return signature;
            }

            public void setSignature(String signature) {
                this.signature = signature;
            }

            public String getPic() {
                return pic;
            }

            public void setPic(String pic) {
                this.pic = pic;
            }

            public String getBackgroundImg() {
                return backgroundImg;
            }

            public void setBackgroundImg(String backgroundImg) {
                this.backgroundImg = backgroundImg;
            }

            public String getIsFollow() {
                return isFollow;
            }

            public void setIsFollow(String isFollow) {
                this.isFollow = isFollow;
            }

            public String getLevel() {
                return level;
            }

            public void setLevel(String level) {
                this.level = level;
            }
        }
    }
}
