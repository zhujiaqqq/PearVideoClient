package com.example.pearvideoclient.entity;

import java.util.List;

/**
 * @Description: java类作用描述
 * @Author: jiazhu
 * @Date: 2019-08-18 11:33
 * @ClassName: UserPostsBean
 */
public class UserPostsBean {

    /**
     * resultCode : 1
     * resultMsg : success
     * reqId : 21c2e2f1-27b8-4349-8570-a592cf534e24
     * systemTime : 1566098608306
     * nextUrl : http://app.pearvideo.com/clt/jsp/v4/getUserPosts.jsp?userId=12179403&start=20&score=1.5652481300001562E12
     * userInfo : {}
     * topList : []
     * postList : [{"postId":"1550641","name":"机器人反击人类？未来这会成真吗？","commentTimes":"41","pubTime":"08-12 10:02","userInfo":{"userId":"12179403","nickname":"原色","pic":"http://image.pearvideo.com/node/3195-11592316-logo.png","level":"2"},"postHtml":"https://app.pearvideo.com/clt/page/v4/post.jsp?postId=1550641"},{"postId":"1563360","name":"收入多工作忙：中国童模生活啥样？","commentTimes":"0","pubTime":"08-11 16:38","userInfo":{"userId":"12179403","nickname":"原色","pic":"http://image.pearvideo.com/node/3195-11592316-logo.png","level":"2"},"postHtml":"https://app.pearvideo.com/clt/page/v4/post.jsp?postId=1563360"},{"postId":"1563353","name":"雀斑妆流行：你觉得美不美？","commentTimes":"0","pubTime":"08-11 16:37","userInfo":{"userId":"12179403","nickname":"原色","pic":"http://image.pearvideo.com/node/3195-11592316-logo.png","level":"2"},"postHtml":"https://app.pearvideo.com/clt/page/v4/post.jsp?postId=1563353"},{"postId":"1563223","name":"好莱坞往事专访：昆丁小李子有话说","commentTimes":"0","pubTime":"08-10 21:11","userInfo":{"userId":"12179403","nickname":"原色","pic":"http://image.pearvideo.com/node/3195-11592316-logo.png","level":"2"},"postHtml":"https://app.pearvideo.com/clt/page/v4/post.jsp?postId=1563223"},{"postId":"1563216","name":"塞纳河上飞驰的\u201c泡泡船\u201d长啥样？","commentTimes":"0","pubTime":"08-10 20:53","userInfo":{"userId":"12179403","nickname":"原色","pic":"http://image.pearvideo.com/node/3195-11592316-logo.png","level":"2"},"postHtml":"https://app.pearvideo.com/clt/page/v4/post.jsp?postId=1563216"},{"postId":"1562796","name":"隐形眼镜对环境的破坏，比你想得大","commentTimes":"0","pubTime":"08-09 15:57","userInfo":{"userId":"12179403","nickname":"原色","pic":"http://image.pearvideo.com/node/3195-11592316-logo.png","level":"2"},"postHtml":"https://app.pearvideo.com/clt/page/v4/post.jsp?postId=1562796"},{"postId":"1562600","name":"与你无关？北极变暖会带来4大影响","commentTimes":"0","pubTime":"08-09 10:19","userInfo":{"userId":"12179403","nickname":"原色","pic":"http://image.pearvideo.com/node/3195-11592316-logo.png","level":"2"},"postHtml":"https://app.pearvideo.com/clt/page/v4/post.jsp?postId=1562600"},{"postId":"1533611","name":"中国长大的外国人：我的认同在哪？","commentTimes":"79","pubTime":"08-09 07:00","userInfo":{"userId":"12179403","nickname":"原色","pic":"http://image.pearvideo.com/node/3195-11592316-logo.png","level":"2"},"postHtml":"https://app.pearvideo.com/clt/page/v4/post.jsp?postId=1533611"},{"postId":"1562114","name":"法国钢铁侠发明飞行滑板背后的故事","commentTimes":"1","pubTime":"08-08 17:08","userInfo":{"userId":"12179403","nickname":"原色","pic":"http://image.pearvideo.com/node/3195-11592316-logo.png","level":"2"},"postHtml":"https://app.pearvideo.com/clt/page/v4/post.jsp?postId=1562114"},{"postId":"1562228","name":"维密跨性别模特：摆脱标签与成见","commentTimes":"0","pubTime":"08-08 15:08","userInfo":{"userId":"12179403","nickname":"原色","pic":"http://image.pearvideo.com/node/3195-11592316-logo.png","level":"2"},"postHtml":"https://app.pearvideo.com/clt/page/v4/post.jsp?postId=1562228"}]
     */

    private String resultCode;
    private String resultMsg;
    private String reqId;
    private String systemTime;
    private String nextUrl;
    private List<PostListBean> postList;

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

    public List<PostListBean> getPostList() {
        return postList;
    }

    public void setPostList(List<PostListBean> postList) {
        this.postList = postList;
    }

    public static class PostListBean {
        /**
         * postId : 1550641
         * name : 机器人反击人类？未来这会成真吗？
         * commentTimes : 41
         * pubTime : 08-12 10:02
         * userInfo : {"userId":"12179403","nickname":"原色","pic":"http://image.pearvideo.com/node/3195-11592316-logo.png","level":"2"}
         * postHtml : https://app.pearvideo.com/clt/page/v4/post.jsp?postId=1550641
         */

        private String postId;
        private String name;
        private String commentTimes;
        private String pubTime;
        private UserInfoBean userInfo;
        private String postHtml;

        public String getPostId() {
            return postId;
        }

        public void setPostId(String postId) {
            this.postId = postId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getCommentTimes() {
            return commentTimes;
        }

        public void setCommentTimes(String commentTimes) {
            this.commentTimes = commentTimes;
        }

        public String getPubTime() {
            return pubTime;
        }

        public void setPubTime(String pubTime) {
            this.pubTime = pubTime;
        }

        public UserInfoBean getUserInfo() {
            return userInfo;
        }

        public void setUserInfo(UserInfoBean userInfo) {
            this.userInfo = userInfo;
        }

        public String getPostHtml() {
            return postHtml;
        }

        public void setPostHtml(String postHtml) {
            this.postHtml = postHtml;
        }

        public static class UserInfoBean {
            /**
             * userId : 12179403
             * nickname : 原色
             * pic : http://image.pearvideo.com/node/3195-11592316-logo.png
             * level : 2
             */

            private String userId;
            private String nickname;
            private String pic;
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

            public String getPic() {
                return pic;
            }

            public void setPic(String pic) {
                this.pic = pic;
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
