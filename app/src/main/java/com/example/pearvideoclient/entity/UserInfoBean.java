package com.example.pearvideoclient.entity;

/**
 * @Description: java类作用描述
 * @Author: jiazhu
 * @Date: 2019-07-21 15:59
 * @ClassName: UserInfoBean
 */
public class UserInfoBean {

    /**
     * resultCode : 1
     * resultMsg : success
     * reqId : 831ef6a7-fc5d-45df-8b63-6408c5cd7a9d
     * systemTime : 1563695032073
     * userInfo : {"userId":"13504593","nickname":"zhujiaqqq","signature":"一二三四五上山","pic":"http://imageugc.pearvideo.com/user/20190619/13504593-215406.jpg","isFollow":"0","level":"0","backgroundImg":"http://image.pearvideo.com/const/author_bg.png","hasAlbum":"0","shareInfo":{"url":"https://www.pearvideo.com/author_13504593","title":"zhujiaqqq","summary":"一二三四五上山","logo":"http://imageugc.pearvideo.com/user/20190619/13504593-215406.jpg"}}
     */

    private String resultCode;
    private String resultMsg;
    private String reqId;
    private String systemTime;
    private InfoBean userInfo;

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

    public InfoBean getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(InfoBean userInfo) {
        this.userInfo = userInfo;
    }

    public static class InfoBean {
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
}
