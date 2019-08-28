package com.example.pearvideoclient.entity;

import java.util.List;

/**
 * @Description: java类作用描述
 * @Author: jiazhu
 * @Date: 2019-08-28 19:14
 * @ClassName: AlbumContBean
 */
public class AlbumContBean {

    /**
     * resultCode : 1
     * resultMsg : success
     * reqId : 8c6ba21e-d9e2-4b60-9366-898d3291170b
     * systemTime : 1566990588354
     * albumInfo : {"albumId":"101145","name":"文化史上的今天"}
     * nextUrl : http://app.pearvideo.com/clt/jsp/v4/getAlbumConts.jsp?albumId=101145&start=10
     * contList : [{"contId":"1584322","name":"174517号囚犯：纪念莱维诞辰100年","pic":"http://image.pearvideo.com/cont/20190730/cont-1584322-12064173.jpg","userInfo":{"userId":"11549091","nickname":"眼镜儿","pic":"http://image.pearvideo.com/node/19-10027896-logo.jpg","level":"2"},"link":"http://","linkType":"0","cornerLabel":"","cornerLabelDesc":"","forwordType":"1","videoType":"1","duration":"02'52\"","liveStatus":"","liveStartTime":"","isAppoint":"0","praiseTimes":"477"},{"contId":"1581694","name":"大仲马生日，回顾《基督山伯爵》","pic":"http://image2.pearvideo.com/cont/20190724/cont-1581694-12054602.jpg","userInfo":{"userId":"11549091","nickname":"眼镜儿","pic":"http://image.pearvideo.com/node/19-10027896-logo.jpg","level":"2"},"link":"http://","linkType":"0","cornerLabel":"","cornerLabelDesc":"","forwordType":"1","videoType":"1","duration":"03'34\"","liveStatus":"","liveStartTime":"","isAppoint":"0","praiseTimes":"346"},{"contId":"1578314","name":"回顾1995版《傲慢与偏见》经典场景","pic":"http://image1.pearvideo.com/cont/20190716/cont-1578314-12042322.jpg","userInfo":{"userId":"11549091","nickname":"眼镜儿","pic":"http://image.pearvideo.com/node/19-10027896-logo.jpg","level":"2"},"link":"http://","linkType":"0","cornerLabel":"","cornerLabelDesc":"","forwordType":"1","videoType":"1","duration":"02'16\"","liveStatus":"","liveStartTime":"","isAppoint":"0","praiseTimes":"719"},{"contId":"1576706","name":"电影大师伯格曼的一天","pic":"http://image1.pearvideo.com/cont/20190712/cont-1576706-12036475.jpeg","userInfo":{"userId":"11549091","nickname":"眼镜儿","pic":"http://image.pearvideo.com/node/19-10027896-logo.jpg","level":"2"},"link":"http://","linkType":"0","cornerLabel":"","cornerLabelDesc":"","forwordType":"1","videoType":"1","duration":"02'55\"","liveStatus":"","liveStartTime":"","isAppoint":"0","praiseTimes":"112"},{"contId":"1562884","name":"普希金诞辰220年，听俄文念他的诗","pic":"http://image2.pearvideo.com/cont/20190605/cont-1562884-11987876.jpg","userInfo":{"userId":"11549091","nickname":"眼镜儿","pic":"http://image.pearvideo.com/node/19-10027896-logo.jpg","level":"2"},"link":"http://","linkType":"0","cornerLabel":"","cornerLabelDesc":"","forwordType":"1","videoType":"1","duration":"01'04\"","liveStatus":"","liveStartTime":"","isAppoint":"0","praiseTimes":"298"},{"contId":"1546487","name":"费孝通：中国人要写中国的事","pic":"http://image.pearvideo.com/cont/20190423/cont-1546487-11934849.png","userInfo":{"userId":"11549091","nickname":"眼镜儿","pic":"http://image.pearvideo.com/node/19-10027896-logo.jpg","level":"2"},"link":"http://","linkType":"0","cornerLabel":"","cornerLabelDesc":"","forwordType":"1","videoType":"1","duration":"02'17\"","liveStatus":"","liveStartTime":"","isAppoint":"0","praiseTimes":"390"},{"contId":"1529343","name":"霍金：我们只有此生，为此我很感恩","pic":"http://image1.pearvideo.com/cont/20190313/cont-1529343-11877415.png","userInfo":{"userId":"11549091","nickname":"眼镜儿","pic":"http://image.pearvideo.com/node/19-10027896-logo.jpg","level":"2"},"link":"http://","linkType":"0","cornerLabel":"","cornerLabelDesc":"","forwordType":"1","videoType":"1","duration":"00'49\"","liveStatus":"","liveStartTime":"","isAppoint":"0","praiseTimes":"713"},{"contId":"1520311","name":"安迪沃霍尔：我为何不厌其烦地复制","pic":"http://image2.pearvideo.com/cont/20190221/cont-1520311-11846900.png","userInfo":{"userId":"11549091","nickname":"眼镜儿","pic":"http://image.pearvideo.com/node/19-10027896-logo.jpg","level":"2"},"link":"http://","linkType":"0","cornerLabel":"","cornerLabelDesc":"","forwordType":"1","videoType":"1","duration":"00'54\"","liveStatus":"","liveStartTime":"","isAppoint":"0","praiseTimes":"308"},{"contId":"1513951","name":"罗密欧与朱丽叶初见，朱生豪怎么译","pic":"http://image2.pearvideo.com/cont/20190202/cont-1513951-11824841.png","userInfo":{"userId":"11549091","nickname":"眼镜儿","pic":"http://image.pearvideo.com/node/19-10027896-logo.jpg","level":"2"},"link":"http://","linkType":"0","cornerLabel":"","cornerLabelDesc":"","forwordType":"1","videoType":"1","duration":"02'42\"","liveStatus":"","liveStartTime":"","isAppoint":"0","praiseTimes":"1021"}]
     */

    private String resultCode;
    private String resultMsg;
    private String reqId;
    private String systemTime;
    private AlbumInfoBean albumInfo;
    private String nextUrl;
    private List<ContListBean> contList;

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

    public AlbumInfoBean getAlbumInfo() {
        return albumInfo;
    }

    public void setAlbumInfo(AlbumInfoBean albumInfo) {
        this.albumInfo = albumInfo;
    }

    public String getNextUrl() {
        return nextUrl;
    }

    public void setNextUrl(String nextUrl) {
        this.nextUrl = nextUrl;
    }

    public List<ContListBean> getContList() {
        return contList;
    }

    public void setContList(List<ContListBean> contList) {
        this.contList = contList;
    }

    public static class AlbumInfoBean {
        /**
         * albumId : 101145
         * name : 文化史上的今天
         */

        private String albumId;
        private String name;

        public String getAlbumId() {
            return albumId;
        }

        public void setAlbumId(String albumId) {
            this.albumId = albumId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public static class ContListBean {
        /**
         * contId : 1584322
         * name : 174517号囚犯：纪念莱维诞辰100年
         * pic : http://image.pearvideo.com/cont/20190730/cont-1584322-12064173.jpg
         * userInfo : {"userId":"11549091","nickname":"眼镜儿","pic":"http://image.pearvideo.com/node/19-10027896-logo.jpg","level":"2"}
         * link : http://
         * linkType : 0
         * cornerLabel :
         * cornerLabelDesc :
         * forwordType : 1
         * videoType : 1
         * duration : 02'52"
         * liveStatus :
         * liveStartTime :
         * isAppoint : 0
         * praiseTimes : 477
         */

        private String contId;
        private String name;
        private String pic;
        private UserInfoBean userInfo;
        private String link;
        private String linkType;
        private String cornerLabel;
        private String cornerLabelDesc;
        private String forwordType;
        private String videoType;
        private String duration;
        private String liveStatus;
        private String liveStartTime;
        private String isAppoint;
        private String praiseTimes;

        public String getContId() {
            return contId;
        }

        public void setContId(String contId) {
            this.contId = contId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }

        public UserInfoBean getUserInfo() {
            return userInfo;
        }

        public void setUserInfo(UserInfoBean userInfo) {
            this.userInfo = userInfo;
        }

        public String getLink() {
            return link;
        }

        public void setLink(String link) {
            this.link = link;
        }

        public String getLinkType() {
            return linkType;
        }

        public void setLinkType(String linkType) {
            this.linkType = linkType;
        }

        public String getCornerLabel() {
            return cornerLabel;
        }

        public void setCornerLabel(String cornerLabel) {
            this.cornerLabel = cornerLabel;
        }

        public String getCornerLabelDesc() {
            return cornerLabelDesc;
        }

        public void setCornerLabelDesc(String cornerLabelDesc) {
            this.cornerLabelDesc = cornerLabelDesc;
        }

        public String getForwordType() {
            return forwordType;
        }

        public void setForwordType(String forwordType) {
            this.forwordType = forwordType;
        }

        public String getVideoType() {
            return videoType;
        }

        public void setVideoType(String videoType) {
            this.videoType = videoType;
        }

        public String getDuration() {
            return duration;
        }

        public void setDuration(String duration) {
            this.duration = duration;
        }

        public String getLiveStatus() {
            return liveStatus;
        }

        public void setLiveStatus(String liveStatus) {
            this.liveStatus = liveStatus;
        }

        public String getLiveStartTime() {
            return liveStartTime;
        }

        public void setLiveStartTime(String liveStartTime) {
            this.liveStartTime = liveStartTime;
        }

        public String getIsAppoint() {
            return isAppoint;
        }

        public void setIsAppoint(String isAppoint) {
            this.isAppoint = isAppoint;
        }

        public String getPraiseTimes() {
            return praiseTimes;
        }

        public void setPraiseTimes(String praiseTimes) {
            this.praiseTimes = praiseTimes;
        }

        public static class UserInfoBean {
            /**
             * userId : 11549091
             * nickname : 眼镜儿
             * pic : http://image.pearvideo.com/node/19-10027896-logo.jpg
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
