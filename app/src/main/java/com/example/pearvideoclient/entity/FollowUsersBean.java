package com.example.pearvideoclient.entity;

import java.util.List;

/**
 * @Description: java类作用描述
 * @Author: jiazhu
 * @Date: 2019-09-02 16:08
 * @ClassName: FollowUsersBean
 */
public class FollowUsersBean {

    /**
     * resultCode : 1
     * resultMsg : success
     * reqId : bdb734ac-b2a2-4c0b-a731-699e2945dcf2
     * systemTime : 1567411341770
     * nextUrl :
     * userList : [{"userId":"10736549","nickname":"咪咕动漫","signature":"各种好玩有趣的内容，通通待命等你来看啦！","pic":"http://image.pearvideo.com/node/1102-10652572-logo.png","isFollow":"0","level":"2"},{"userId":"11129855","nickname":"五号车论","signature":"一档试驾、对比和用车选车的视频节目。","pic":"http://image.pearvideo.com/node/2289-10730441-logo.png","isFollow":"0","level":"2"},{"userId":"11487675","nickname":"世界多美丽","signature":"全外景旅行真人秀/旅行攻略类短视频栏目；","pic":"http://image.pearvideo.com/node/2716-11720865-logo.png","isFollow":"0","level":"2"},{"userId":"11198469","nickname":"装修PLUS","signature":"硬装软装，深度浅说，装修听我说！","pic":"http://image.pearvideo.com/node/2943-11134230-logo.png","isFollow":"0","level":"2"},{"userId":"10161303","nickname":"一刻talks","signature":"思想好声音,全球创见者分享平台","pic":"http://image.pearvideo.com/node/296-10902923-logo.png","isFollow":"0","level":"2"},{"userId":"10899851","nickname":"落甜视频","signature":"专注咖啡与法式甜品的系统化学习视频","pic":"http://image.pearvideo.com/node/1539-10518318-logo.png","isFollow":"0","level":"2"},{"userId":"10247367","nickname":"中新经纬","signature":"轻松聊财经","pic":"http://image.pearvideo.com/node/546-10149455-logo.png","isFollow":"0","level":"2"},{"userId":"10443088","nickname":"这班挺有闹","signature":"爆笑校园动画批发工场","pic":"http://image.pearvideo.com/node/752-12074086-logo.png","isFollow":"0","level":"2"},{"userId":"10815868","nickname":"动漫三分钟","signature":"每日更新动漫视频，欢迎关注！","pic":"http://image.pearvideo.com/node/1204-11218085-logo.png","isFollow":"0","level":"2"},{"userId":"11835349","nickname":"ACG废柴君","signature":"二刺猿废宅/B站UP/ACG资讯","pic":"http://image.pearvideo.com/node/2896-11976639-logo.png","isFollow":"0","level":"2"},{"userId":"12086734","nickname":"偶像请回答","signature":"嗨皮旗下原创趣味明星采访互动短视频","pic":"http://image.pearvideo.com/node/3174-11904158-logo.png","isFollow":"0","level":"2"},{"userId":"11852754","nickname":"BTSZD孙子团","signature":"由各行业年轻女性组成的成都舞蹈团体","pic":"http://image.pearvideo.com/node/2922-11103482-logo.png","isFollow":"0","level":"2"}]
     */

    private String resultCode;
    private String resultMsg;
    private String reqId;
    private String systemTime;
    private String nextUrl;
    private List<UserBean> userList;

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

    public String getNextUrl() {
        return nextUrl;
    }

    public void setNextUrl(String nextUrl) {
        this.nextUrl = nextUrl;
    }

    public List<UserBean> getUserList() {
        return userList;
    }

    public void setUserList(List<UserBean> userList) {
        this.userList = userList;
    }

    public static class UserBean {
        public static final String FOLLOW = "关注";
        public static final String FOLLOWED = "已关注";
        /**
         * userId : 10736549
         * nickname : 咪咕动漫
         * signature : 各种好玩有趣的内容，通通待命等你来看啦！
         * pic : http://image.pearvideo.com/node/1102-10652572-logo.png
         * isFollow : 0
         * level : 2
         */

        private String userId;
        private String nickname;
        private String signature;
        private String pic;
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
