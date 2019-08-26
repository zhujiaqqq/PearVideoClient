package com.example.pearvideoclient.entity;

import java.util.List;

/**
 * @Description: java类作用描述
 * @Author: jiazhu
 * @Date: 2019-08-26 14:09
 * @ClassName: PaikeFineVideoBean
 */
public class PaikeFineVideoBean {

    /**
     * resultCode : 1
     * resultMsg : success
     * reqId : 1cab4a20-6519-4072-a6aa-1aaca3729d67
     * systemTime : 1566790686927
     * configInfo : {"h5Url":"https://app.pearvideo.com/clt/page/v4/earnings-instruction.jsp","bannerUrl":"http://image.pearvideo.com/const/paike_common_flow_banner.png","sampleVideo":"http://video.pearvideo.com/const/paike_course_2.mp4","sampleVideoSize":"10129375","adId":"97"}
     * videoList : [{"videoId":"10015","title":"别人家老公！婚礼开场新郎跑酷出场","nickname":"记录君","pic":"http://imageugc2.pearvideo.com/paikefinevideo/20180412/10015-video-192859.png","award":"10000","aspectRatio":"0","url":"http://video.pearvideo.com/mp4/activity/finevideo/20180412/10015-192545-sd.mp4","duration":"01'16\"","fileSize":"7890065"},{"videoId":"10016","title":"女大学生100米夺冠,终点是男友怀抱","nickname":"昆明校园","pic":"http://imageugc2.pearvideo.com/paikefinevideo/20180412/10016-video-193007.png","award":"2400","aspectRatio":"0","url":"http://video.pearvideo.com/mp4/activity/finevideo/20180412/10016-192947-sd.mp4","duration":"00'27\"","fileSize":"3298562"},{"videoId":"10014","title":"多套房爷爷代孙女相亲：娶她中大奖","nickname":"猛犸视频","pic":"http://imageugc2.pearvideo.com/paikefinevideo/20180312/10014-video-144957.png","award":"2400","aspectRatio":"0","url":"http://video.pearvideo.com/mp4/activity/finevideo/20180312/10014-144810-sd.mp4","duration":"02'46\"","fileSize":"18712871"},{"videoId":"10000","title":"环卫大爷凌晨3点起床,路边小憩","nickname":"孙悟空","pic":"http://imageugc1.pearvideo.com/paikefinevideo/20180112/10000-video-140739.png","award":"2400","aspectRatio":"0","url":"http://video.pearvideo.com/mp4/activity/finevideo/20180110/10000-113217-sd.mp4","duration":"03'33\"","fileSize":"22713496"},{"videoId":"10010","title":"爬楼神器！有它大爷上下楼再也不喘","nickname":"花花花花","pic":"http://imageugc.pearvideo.com/paikefinevideo/20180112/10010-video-140630.png","award":"500","aspectRatio":"0","url":"http://video.pearvideo.com/mp4/activity/finevideo/20180112/10010-140544-sd.mp4","duration":"00'37\"","fileSize":"4090200"},{"videoId":"10011","title":"最美校风!全校停电,学生打手电考试","nickname":"精武门陈真","pic":"http://imageugc2.pearvideo.com/paikefinevideo/20180112/10011-video-141216.png","award":"400","aspectRatio":"0","url":"http://video.pearvideo.com/mp4/activity/finevideo/20180112/10011-141046-sd.mp4","duration":"00'29\"","fileSize":"3112201"},{"videoId":"10004","title":"月入3千儿有6房，8旬翁却迷恋修锅","nickname":"头条君","pic":"http://imageugc2.pearvideo.com/paikefinevideo/20180112/10004-video-140855.png","award":"400","aspectRatio":"0","url":"http://video.pearvideo.com/mp4/activity/finevideo/20180111/10004-173408-sd.mp4","duration":"03'30\"","fileSize":"23415970"},{"videoId":"10006","title":"女主人买菜好霸气！金毛陪同帮拎菜","nickname":"新奇趣事","pic":"http://imageugc1.pearvideo.com/paikefinevideo/20180112/10006-video-140808.png","award":"400","aspectRatio":"0","url":"http://video.pearvideo.com/mp4/activity/finevideo/20180111/10006-180355-sd.mp4","duration":"01'27\"","fileSize":"9132943"},{"videoId":"10005","title":"狗被放车顶1天，主人:丢餐馆怕扰客","nickname":"梨园春色","pic":"http://imageugc.pearvideo.com/paikefinevideo/20180112/10005-video-140907.png","award":"400","aspectRatio":"0","url":"http://video.pearvideo.com/mp4/activity/finevideo/20180111/10005-174144-sd.mp4","duration":"01'05\"","fileSize":"7113930"},{"videoId":"10003","title":"服!\"00后\"感冒发烧,打着点滴写作业","nickname":"电动车跑现场","pic":"http://imageugc1.pearvideo.com/paikefinevideo/20180112/10003-video-140924.png","award":"400","aspectRatio":"0","url":"http://video.pearvideo.com/mp4/activity/finevideo/20180111/10003-170840-sd.mp4","duration":"01'32\"","fileSize":"10384300"},{"videoId":"10002","title":"放飞自我!妈妈抱娃狂跳舞:久未出门","nickname":"Krystal","pic":"http://imageugc2.pearvideo.com/paikefinevideo/20180112/10002-video-140937.png","award":"400","aspectRatio":"0","url":"http://video.pearvideo.com/mp4/activity/finevideo/20180111/10002-170447-sd.mp4","duration":"01'26\"","fileSize":"9409466"},{"videoId":"10013","title":"堵车不堵心！他们高速上跳起广场舞","nickname":"鲁圣乾坤","pic":"http://imageugc1.pearvideo.com/paikefinevideo/20180112/10013-video-141948.png","award":"400","aspectRatio":"0","url":"http://video.pearvideo.com/mp4/activity/finevideo/20180112/10013-141942-sd.mp4","duration":"00'04\"","fileSize":"436316"}]
     */

    private String resultCode;
    private String resultMsg;
    private String reqId;
    private String systemTime;
    private ConfigInfoBean configInfo;
    private List<VideoBean> videoList;

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

    public ConfigInfoBean getConfigInfo() {
        return configInfo;
    }

    public void setConfigInfo(ConfigInfoBean configInfo) {
        this.configInfo = configInfo;
    }

    public List<VideoBean> getVideoList() {
        return videoList;
    }

    public void setVideoList(List<VideoBean> videoList) {
        this.videoList = videoList;
    }

    public static class ConfigInfoBean {
        /**
         * h5Url : https://app.pearvideo.com/clt/page/v4/earnings-instruction.jsp
         * bannerUrl : http://image.pearvideo.com/const/paike_common_flow_banner.png
         * sampleVideo : http://video.pearvideo.com/const/paike_course_2.mp4
         * sampleVideoSize : 10129375
         * adId : 97
         */

        private String h5Url;
        private String bannerUrl;
        private String sampleVideo;
        private String sampleVideoSize;
        private String adId;

        public String getH5Url() {
            return h5Url;
        }

        public void setH5Url(String h5Url) {
            this.h5Url = h5Url;
        }

        public String getBannerUrl() {
            return bannerUrl;
        }

        public void setBannerUrl(String bannerUrl) {
            this.bannerUrl = bannerUrl;
        }

        public String getSampleVideo() {
            return sampleVideo;
        }

        public void setSampleVideo(String sampleVideo) {
            this.sampleVideo = sampleVideo;
        }

        public String getSampleVideoSize() {
            return sampleVideoSize;
        }

        public void setSampleVideoSize(String sampleVideoSize) {
            this.sampleVideoSize = sampleVideoSize;
        }

        public String getAdId() {
            return adId;
        }

        public void setAdId(String adId) {
            this.adId = adId;
        }
    }

    public static class VideoBean {
        /**
         * videoId : 10015
         * title : 别人家老公！婚礼开场新郎跑酷出场
         * nickname : 记录君
         * pic : http://imageugc2.pearvideo.com/paikefinevideo/20180412/10015-video-192859.png
         * award : 10000
         * aspectRatio : 0
         * url : http://video.pearvideo.com/mp4/activity/finevideo/20180412/10015-192545-sd.mp4
         * duration : 01'16"
         * fileSize : 7890065
         */

        private String videoId;
        private String title;
        private String nickname;
        private String pic;
        private String award;
        private String aspectRatio;
        private String url;
        private String duration;
        private String fileSize;

        public String getVideoId() {
            return videoId;
        }

        public void setVideoId(String videoId) {
            this.videoId = videoId;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
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

        public String getAward() {
            return award;
        }

        public void setAward(String award) {
            this.award = award;
        }

        public String getAspectRatio() {
            return aspectRatio;
        }

        public void setAspectRatio(String aspectRatio) {
            this.aspectRatio = aspectRatio;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getDuration() {
            return duration;
        }

        public void setDuration(String duration) {
            this.duration = duration;
        }

        public String getFileSize() {
            return fileSize;
        }

        public void setFileSize(String fileSize) {
            this.fileSize = fileSize;
        }
    }
}
